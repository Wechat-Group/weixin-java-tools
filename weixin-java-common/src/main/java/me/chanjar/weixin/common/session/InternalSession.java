package me.chanjar.weixin.common.session;

/**
 *
 * @author Daniel Qian
 */
public interface InternalSession {

  /**
   * Return the <code>HttpSession</code> for which this object
   * is the facade.
   */
  WxSession getSession();

  /**
   * Return the <code>isValid</code> flag for this session.
   */
  boolean isValid();

  /**
   * Set the <code>isValid</code> flag for this session.
   *
   * @param isValid The new value for the <code>isValid</code> flag
   */
  void setValid(boolean isValid);

  /**
   * Return the session identifier for this session.
   */
  String getIdInternal();

  /**
   * Perform the internal processing required to invalidate this session,
   * without triggering an exception if the session has already expired.
   */
  void expire();

  /**
   * Update the accessed time information for this session.  This method
   * should be called by the context when a request comes in for a particular
   * session, even if the application does not reference it.
   */
  void access();

  /**
   * End the access.
   */
  void endAccess();

  /**
   * Set the creation time for this session.  This method is called by the
   * Manager when an existing Session instance is reused.
   *
   * @param time The new creation time
   */
  void setCreationTime(long time);

  /**
   * Set the default maximum inactive interval (in seconds)
   * for Sessions created by this Manager.
   *
   * @param interval The new default value
   */
  void setMaxInactiveInterval(int interval);

  /**
   * Set the session identifier for this session.
   *
   * @param id The new session identifier
   */
  void setId(String id);
}
