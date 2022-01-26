package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author leiin
 * @date 2022/1/26 6:33 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfMenuMsg {

  /**
   * 参数：head_content
   * 是否必须：否
   * 类型：string
   * 说明：起始文本 不多于1024字节
   */
  @SerializedName("head_content")
  private String headContent;

  private List<WxCpKfMenuItem> list;

  /**
   * 参数：tail_content
   * 是否必须：否
   * 类型：string
   * 说明：结束文本 不多于1024字节
   */
  @SerializedName("tail_content")
  private String tailContent;

  @NoArgsConstructor
  @Data
  public static class WxCpKfMenuItem {
    /**
     * 参数：type
     * 是否必须：是
     * 类型：string
     * 说明：菜单类型。click-回复菜单 view-超链接菜单 miniprogram-小程序菜单
     */
    private String type;

    /**
     * type为click的菜单项
     */
    private MenuClick click;
    /**
     * type为view的菜单项
     */
    private MenuView view;
    /**
     * type为miniprogram的菜单项
     */
    @SerializedName("miniprogram")
    private MiniProgram miniProgram;
  }

  @Getter
  @Setter
  public static class MenuClick {

    /**
     * <pre>
     *   是否必须：否
     *   说明：菜单ID。不少于1字节 不多于128字节
     * </pre>
     */
    private String id;
    /**
     * <pre>
     *   是否必须：是
     *   说明：菜单显示内容。不少于1字节 不多于128字节
     * </pre>
     */
    private String content;
  }

  @Getter
  @Setter
  public static class MenuView {
    /**
     * <pre>
     *   是否必须：是
     *   说明：点击后跳转的链接。不少于1字节 不多于2048字节
     * </pre>
     */
    private String url;
    /**
     * <pre>
     *   是否必须：是
     *   说明：菜单显示内容。不少于1字节 不多于1024字节
     * </pre>
     */
    private String content;
  }

  @Getter
  @Setter
  public static class MiniProgram {
    /**
     * <pre>
     *   是否必须：是
     *   说明：小程序appid。
     * </pre>
     */
    @SerializedName("appid")
    private String appId;
    /**
     * <pre>
     *   点击后进入的小程序页面。
     * </pre>
     */
    @SerializedName("pagepath")
    private String pagePath;
    /**
     * <pre>
     *   菜单显示内容。不多于1024字节
     * </pre>
     */
    private String content;
  }
}
