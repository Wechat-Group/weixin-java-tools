package me.chanjar.weixin.common.error;

/**
 * @author Daniel Qian
 */
public class WxErrorException extends RuntimeException {
  private static final long serialVersionUID = -6357149550353160810L;

  private final WxError error;

  private static final int DEFAULT_ERROR_CODE = -99;

  public WxErrorException(String message) {
    this(WxError.builder().errorCode(DEFAULT_ERROR_CODE).errorMsg(message).build());
  }

  public WxErrorException(WxError error) {
    super(error.toString());
    this.error = error;
  }

  public WxErrorException(WxError error, Throwable cause) {
    super(error.toString(), cause);
    this.error = error;
  }

  public WxErrorException(Throwable cause) {
    super(cause.getMessage(), cause);
    this.error = WxError.builder().errorCode(DEFAULT_ERROR_CODE).errorMsg(cause.getMessage()).build();
  }

  public WxError getError() {
    return this.error;
  }
}
