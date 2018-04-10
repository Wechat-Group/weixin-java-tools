package me.chanjar.weixin.common.session;

import me.chanjar.weixin.common.util.res.StringManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StandardSession implements WxSession, InternalSession {

  /**
   * The string manager for this package.
   */
  protected static final StringManager SM = StringManager.getManager(Constants.PACKAGE);
  /**
   * Type array.
   */
  private static final String[] EMPTY_ARRAY = new String[0];

  protected Map<String, Object> attributes = new ConcurrentHashMap<>();
  /**
   * The session identifier of this Session.
   */
  protected String id = null;
  /**
   * Flag indicating whether this session is valid or not.
   */
  protected volatile boolean isValid = false;
  /**
   * We are currently processing a session expiration, so bypass
   * certain IllegalStateException tests.  NOTE:  This value is not
   * included in the serialized version of this object.
   */
  protected transient volatile boolean expiring = false;
  /**
   * The Manager with which this Session is associated.
   */
  protected transient InternalSessionManager manager = null;

  // ------------------------------ InternalSession
  /**
   * The time this session was created, in milliseconds since midnight,
   * January 1, 1970 GMT.
   */
  protected long creationTime = 0L;
  /**
   * The current accessed time for this session.
   */
  protected volatile long thisAccessedTime = this.creationTime;
  /**
   * The default maximum inactive interval for Sessions created by
   * this Manager.
   */
  protected int maxInactiveInterval = 30 * 60;
  /**
   * The facade associated with this session.  NOTE:  This value is not
   * included in the serialized version of this object.
   */
  protected transient StandardSessionFacade facade = null;
  /**
   * The access count for this session.
   */
  protected transient AtomicInteger accessCount = null;

  public StandardSession(InternalSessionManager manager) {
    this.manager = manager;
    this.accessCount = new AtomicInteger();
  }

  @Override
  public Object getAttribute(String name) {

    if (!isValidInternal()) {
      throw new IllegalStateException
        (SM.getString("sessionImpl.getAttribute.ise"));
    }

    if (name == null) {
      return null;
    }

    return this.attributes.get(name);
  }

  @Override
  public Enumeration<String> getAttributeNames() {
    if (!isValidInternal()) {
      throw new IllegalStateException(SM.getString("sessionImpl.getAttributeNames.ise"));
    }

    Set<String> names = new HashSet<>();
    names.addAll(this.attributes.keySet());
    return Collections.enumeration(names);
  }

  @Override
  public void setAttribute(String name, Object value) {
    // Name cannot be null
    if (name == null) {
      throw new IllegalArgumentException(SM.getString("sessionImpl.setAttribute.namenull"));
    }

    // Null value is the same as removeAttribute()
    if (value == null) {
      removeAttribute(name);
      return;
    }

    // Validate our current state
    if (!isValidInternal()) {
      throw new IllegalStateException(SM.getString("sessionImpl.setAttribute.ise", getIdInternal()));
    }

    this.attributes.put(name, value);

  }

  @Override
  public void removeAttribute(String name) {
    removeAttributeInternal(name);
  }

  @Override
  public void invalidate() {
    if (!isValidInternal()) {
      throw new IllegalStateException(SM.getString("sessionImpl.invalidate.ise"));
    }

    // Cause this session to expire
    expire();

  }

  @Override
  public WxSession getSession() {
    if (this.facade == null) {
      this.facade = new StandardSessionFacade(this);
    }

    return this.facade;
  }

  /**
   * Return the <code>isValid</code> flag for this session without any expiration
   * check.
   */
  protected boolean isValidInternal() {
    return this.isValid;
  }

  @Override
  public boolean isValid() {
    if (!this.isValid) {
      return false;
    }

    if (this.expiring) {
      return true;
    }

    if (this.accessCount.get() > 0) {
      return true;
    }

    if (this.maxInactiveInterval > 0) {
      long timeNow = System.currentTimeMillis();
      int timeIdle;
      timeIdle = (int) ((timeNow - this.thisAccessedTime) / 1000L);
      if (timeIdle >= this.maxInactiveInterval) {
        expire();
      }
    }

    return this.isValid;
  }

  /**
   * Set the <code>isValid</code> flag for this session.
   *
   * @param isValid The new value for the <code>isValid</code> flag
   */
  @Override
  public void setValid(boolean isValid) {
    this.isValid = isValid;
  }

  @Override
  public String getIdInternal() {
    return this.id;
  }

  protected void removeAttributeInternal(String name) {
    // Avoid NPE
    if (name == null) {
      return;
    }

    // Remove this attribute from our collection
    this.attributes.remove(name);

  }

  @Override
  public void expire() {
    // Check to see if session has already been invalidated.
    // Do not check expiring at this point as expire should not return until
    // isValid is false
    if (!this.isValid) {
      return;
    }

    synchronized (this) {
      // Check again, now we are inside the sync so this code only runs once
      // Double check locking - isValid needs to be volatile
      // The check of expiring is to ensure that an infinite loop is not
      // entered as per bug 56339
      if (this.expiring || !this.isValid) {
        return;
      }

      if (this.manager == null) {
        return;
      }

      // Mark this session as "being expired"
      this.expiring = true;

      this.accessCount.set(0);

      // Remove this session from our manager's active sessions
      this.manager.remove(this, true);


      // We have completed expire of this session
      setValid(false);
      this.expiring = false;

      // Unbind any objects associated with this session
      String[] keys = keys();
      for (String key : keys) {
        removeAttributeInternal(key);
      }
    }


  }


  @Override
  public void access() {

    this.thisAccessedTime = System.currentTimeMillis();
    this.accessCount.incrementAndGet();

  }


  @Override
  public void endAccess() {

    this.thisAccessedTime = System.currentTimeMillis();
    this.accessCount.decrementAndGet();

  }

  @Override
  public void setCreationTime(long time) {

    this.creationTime = time;
    this.thisAccessedTime = time;

  }

  @Override
  public void setMaxInactiveInterval(int interval) {
    this.maxInactiveInterval = interval;
  }


  @Override
  public void setId(String id) {
    if ((this.id != null) && (this.manager != null)) {
      this.manager.remove(this);
    }

    this.id = id;

    if (this.manager != null) {
      this.manager.add(this);
    }
  }

  /**
   * Return the names of all currently defined session attributes
   * as an array of Strings.  If there are no defined attributes, a
   * zero-length array is returned.
   */
  protected String[] keys() {

    return this.attributes.keySet().toArray(EMPTY_ARRAY);

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StandardSession)) {
      return false;
    }

    StandardSession session = (StandardSession) o;

    if (this.creationTime != session.creationTime) {
      return false;
    }
    if (this.expiring != session.expiring) {
      return false;
    }
    if (this.isValid != session.isValid) {
      return false;
    }
    if (this.maxInactiveInterval != session.maxInactiveInterval) {
      return false;
    }
    if (this.thisAccessedTime != session.thisAccessedTime) {
      return false;
    }
    if (this.accessCount.get() != session.accessCount.get()) {
      return false;
    }
    if (!this.attributes.equals(session.attributes)) {
      return false;
    }
    if (!this.facade.equals(session.facade)) {
      return false;
    }

    return this.id.equals(session.id) && this.manager.equals(session.manager);

  }

  @Override
  public int hashCode() {
    int result = this.attributes.hashCode();
    result = 31 * result + this.id.hashCode();
    result = 31 * result + (this.isValid ? 1 : 0);
    result = 31 * result + (this.expiring ? 1 : 0);
    result = 31 * result + this.manager.hashCode();
    result = 31 * result + (int) (this.creationTime ^ (this.creationTime >>> 32));
    result = 31 * result + (int) (this.thisAccessedTime ^ (this.thisAccessedTime >>> 32));
    result = 31 * result + this.maxInactiveInterval;
    result = 31 * result + this.facade.hashCode();
    result = 31 * result + this.accessCount.hashCode();
    return result;
  }

}
