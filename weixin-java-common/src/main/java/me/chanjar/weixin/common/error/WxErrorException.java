package me.chanjar.weixin.common.error;

/**
 * @author Daniel Qian
 */
public class WxErrorException extends Exception {
  private static final long serialVersionUID = -6357149550353160810L;

  private final WxError error;

  public WxErrorException(String message) {
    this(WxError.builder().errorCode(-1).errorMsg(message).build());
  }

  public WxErrorException(WxError error) {
    super(error.toString());
    this.error = error;
  }

  public WxErrorException(WxError error, Throwable cause) {
    super(error.toString(), cause);
    this.error = error;
  }

  public WxError getError() {
    return this.error;
  }


}
