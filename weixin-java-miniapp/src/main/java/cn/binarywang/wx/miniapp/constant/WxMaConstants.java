package cn.binarywang.wx.miniapp.constant;

/**
 * <pre>
 *  小程序常量.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaConstants {
  /**
   * 微信接口返回的参数errcode.
   */
  public static final String ERRCODE = "errcode";

  /**
   * 素材类型.
   */
  public static class MediaType {
    /**
     * 图片.
     */
    public static final String IMAGE = "image";
  }

  /**
   * 消息格式.
   */
  public static class MsgDataFormat {
    public static final String XML = "XML";
    public static final String JSON = "JSON";
  }

  /**
   * 客服消息的消息类型.
   */
  public static class KefuMsgType {
    /**
     * 文本消息.
     */
    public static final String TEXT = "text";
    /**
     * 图片消息.
     */
    public static final String IMAGE = "image";
    /**
     * 图文链接.
     */
    public static final String LINK = "link";
    /**
     * 小程序卡片消息.
     */
    public static final String MA_PAGE = "miniprogrampage";
  }

  public static final class ErrorCode {
    /**
     * 40001 获取access_token时AppSecret错误，或者access_token无效.
     */
    public static final int ERR_40001 = 40001;

    /**
     * 42001 access_token超时.
     */
    public static final int ERR_42001 = 42001;

    /**
     * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期）.
     */
    public static final int ERR_40014 = 40014;
  }

  /**
   * 内容安全检测的媒体类型
   */
  public static final class SecCheckMediaType {

    /**
     * 音频
     */
    public static final int VOICE = 1;

    /**
     * 图片
     */
    public static final int IMAGE = 2;
  }

  /**
   * 快递账号绑定类型
   */
  public static final class BindAccountType{

    /**
     * 绑定
     */
    public static final String BIND = "bind";

    /**
     * 解绑
     */
    public static final String UNBIND = "unbind";
  }

  /**
   * 快递下单订单来源
   */
  public static final class OrderAddSource{

    /**
     * 小程序
     */
    public static final int MINI_PROGRAM = 0;

    /**
     * APP或H5
     */
    public static final int APP_OR_H5 = 2;
  }

  /**
   * 快递下单保价
   */
  public static final class OrderAddInsured{
    /**
     * 不保价
     */
    public static final int INSURED_PROGRAM = 0;

    /**
     * 保价
     */
    public static final int USE_INSURED = 1;

    /**
     * 默认保价金额
     */
    public static final int DEFAULT_INSURED_VALUE = 0;
  }
}
