package me.chanjar.weixin.common.session;

import me.chanjar.weixin.common.util.res.StringManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 基于内存的session manager
 */
public class StandardSessionManager implements WxSessionManager, InternalSessionManager {

  protected static final StringManager sm =
          StringManager.getManager(Constants.Package);
  /**
   * The descriptive name of this Manager implementation (for logging).
   */
  private static final String name = "SessionManagerImpl";
  protected final Logger log = LoggerFactory.getLogger(StandardSessionManager.class);
  private final Object maxActiveUpdateLock = new Object();
  /**
   * 后台清理线程是否已经开启
   */
  private final AtomicBoolean backgroundProcessStarted = new AtomicBoolean(false);


  // -------------------------------------- InternalSessionManager
  /**
   * The set of currently active Sessions for this Manager, keyed by
   * session identifier.
   */
  protected Map<String, InternalSession> sessions = new ConcurrentHashMap<>();
  /**
   * The maximum number of active Sessions allowed, or -1 for no limit.
   */
  protected int maxActiveSessions = -1;

  /**
   * Number of session creations that failed due to maxActiveSessions.
   */
  protected int rejectedSessions = 0;

  /**
   * The default maximum inactive interval for Sessions created by
   * this Manager.
   */
  protected int maxInactiveInterval = 30 * 60;

  // Number of sessions created by this manager
  protected long sessionCounter = 0;

  protected volatile int maxActive = 0;
  /**
   * Processing time during session expiration.
   */
  protected long processingTime = 0;
  /**
   * Frequency of the session expiration, and related manager operations.
   * Manager operations will be done once for the specified amount of
   * backgrondProcess calls (ie, the lower the amount, the most often the
   * checks will occur).
   */
  protected int processExpiresFrequency = 6;
  /**
   * background processor delay in seconds
   */
  protected int backgroundProcessorDelay = 10;
  /**
   * Iteration count for background processing.
   */
  private int count = 0;

  @Override
  public WxSession getSession(String sessionId) {
    return getSession(sessionId, true);
  }

  @Override
  public WxSession getSession(String sessionId, boolean create) {
    if (sessionId == null) {
      throw new IllegalStateException
              (sm.getString("sessionManagerImpl.getSession.ise"));
    }

    InternalSession session = findSession(sessionId);
    if ((session != null) && !session.isValid()) {
      session = null;
    }
    if (session != null) {
      session.access();
      return session.getSession();
    }

    // Create a new session if requested and the response is not committed
    if (!create) {
      return (null);
    }

    session = createSession(sessionId);

    if (session == null) {
      return null;
    }

    session.access();
    return session.getSession();
  }

  @Override
  public void remove(InternalSession session) {
    remove(session, false);
  }

  @Override
  public void remove(InternalSession session, boolean update) {
    if (session.getIdInternal() != null) {
      this.sessions.remove(session.getIdInternal());
    }
  }


  @Override
  public InternalSession findSession(String id) {

    if (id == null)
      return (null);
    return this.sessions.get(id);

  }

  @Override
  public InternalSession createSession(String sessionId) {
    if (sessionId == null) {
      throw new IllegalStateException
              (sm.getString("sessionManagerImpl.createSession.ise"));
    }

    if ((this.maxActiveSessions >= 0) &&
            (getActiveSessions() >= this.maxActiveSessions)) {
      this.rejectedSessions++;
      throw new TooManyActiveSessionsException(
              sm.getString("sessionManagerImpl.createSession.tmase"),
              this.maxActiveSessions);
    }

    // Recycle or create a Session instance
    InternalSession session = createEmptySession();

    // Initialize the properties of the new session and return it
    session.setValid(true);
    session.setCreationTime(System.currentTimeMillis());
    session.setMaxInactiveInterval(this.maxInactiveInterval);
    String id = sessionId;
    session.setId(id);
    this.sessionCounter++;

    return (session);

  }


  @Override
  public int getActiveSessions() {
    return this.sessions.size();
  }


  @Override
  public InternalSession createEmptySession() {
    return (getNewSession());
  }

  /**
   * Get new session class to be used in the doLoad() method.
   */
  protected InternalSession getNewSession() {
    return new StandardSession(this);
  }


  @Override
  public void add(InternalSession session) {

    // 当第一次有session创建的时候，开启session清理线程
    if (!this.backgroundProcessStarted.getAndSet(true)) {
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
            try {
              // 每秒清理一次
              Thread.sleep(StandardSessionManager.this.backgroundProcessorDelay * 1000l);
              backgroundProcess();
            } catch (InterruptedException e) {
              StandardSessionManager.this.log.error("SessionManagerImpl.backgroundProcess error", e);
            }
          }
        }
      });
      t.setDaemon(true);
      t.start();
    }

    this.sessions.put(session.getIdInternal(), session);
    int size = getActiveSessions();
    if (size > this.maxActive) {
      synchronized (this.maxActiveUpdateLock) {
        if (size > this.maxActive) {
          this.maxActive = size;
        }
      }
    }

  }

  /**
   * Return the set of active Sessions associated with this Manager.
   * If this Manager has no active Sessions, a zero-length array is returned.
   */
  @Override
  public InternalSession[] findSessions() {

    return this.sessions.values().toArray(new InternalSession[0]);

  }

  @Override
  public void backgroundProcess() {
    this.count = (this.count + 1) % this.processExpiresFrequency;
    if (this.count == 0)
      processExpires();
  }

  /**
   * Invalidate all sessions that have expired.
   */
  public void processExpires() {

    long timeNow = System.currentTimeMillis();
    InternalSession sessions[] = findSessions();
    int expireHere = 0;

    if (this.log.isDebugEnabled())
      this.log.debug("Start expire sessions {} at {} sessioncount {}", getName(), timeNow, sessions.length);
    for (int i = 0; i < sessions.length; i++) {
      if (sessions[i] != null && !sessions[i].isValid()) {
        expireHere++;
      }
    }
    long timeEnd = System.currentTimeMillis();
    if (this.log.isDebugEnabled())
      this.log.debug("End expire sessions {} processingTime {} expired sessions: {}", getName(), timeEnd - timeNow, expireHere);
    this.processingTime += (timeEnd - timeNow);

  }


  @Override
  public void setMaxInactiveInterval(int interval) {

    this.maxInactiveInterval = interval;

  }

  /**
   * Set the manager checks frequency.
   *
   * @param processExpiresFrequency the new manager checks frequency
   */
  @Override
  public void setProcessExpiresFrequency(int processExpiresFrequency) {

    if (processExpiresFrequency <= 0) {
      return;
    }

    this.processExpiresFrequency = processExpiresFrequency;

  }

  @Override
  public void setBackgroundProcessorDelay(int backgroundProcessorDelay) {
    this.backgroundProcessorDelay = backgroundProcessorDelay;
  }

  /**
   * Return the descriptive short name of this Manager implementation.
   */
  public String getName() {

    return (name);

  }

  /**
   * Set the maximum number of active Sessions allowed, or -1 for
   * no limit.
   *
   * @param max The new maximum number of sessions
   */
  @Override
  public void setMaxActiveSessions(int max) {

    this.maxActiveSessions = max;

  }

}
