package cn.binarywang.wx.miniapp.bean.code;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传代码需要用到的第三方自定义的配置
 * 详细文档，参考：https://developers.weixin.qq.com/miniprogram/dev/framework/config.html
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaCodeExtConfig implements Serializable {
  private static final long serialVersionUID = -7666911367458178753L;
  /**
   * 配置 ext.json 是否生效.
   * 必填：是
   */
  private boolean extEnable;
  /**
   * 配置 extAppid.
   * 必填：是
   */
  private String extAppid;
  /**
   * 开发自定义的数据字段.
   * 必填：否
   */
  private Object ext;
  /**
   * 单独设置每个页面的 json.
   * 必填：否
   * key: page 名称，如 pages/logs/logs
   * value: page 配置
   */
  private Map<String, PageConfig> extPages;
  /**
   * 是否直接提交到待审核列表.
   * 必填：否
   */
  private Boolean directCommit;
  /**
   * 设置页面路径（同 app.json 相同的字段，填写会覆盖 app.json）.
   * 必填：否
   */
  private List<String> pages;
  /**
   * 设置默认页面的窗口表现（同 app.json 相同的字段，填写会覆盖 app.json）
   * 必填：否
   */
  private PageConfig window;
  /**
   * 设置各种网络请求的超时时间（同 app.json 相同的字段，填写会覆盖 app.json）
   * 必填：否
   */
  private NetworkTimeout networkTimeout;
  /**
   * 设置是否开启 debug 模式（同 app.json 相同的字段，填写会覆盖 app.json）
   * 必填：否
   */
  private Boolean debug;
  /**
   * 底部 tab 栏的表现.
   * 必填：否
   */
  private TabBar tabBar;

  /**
   * page.json 配置，页面配置
   * 文档：https://mp.weixin.qq.com/debug/wxadoc/dev/framework/config.html
   */
  @Data
  @Builder
  public static class PageConfig {
    /**
     * 导航栏背景颜色，如"#000000" HexColor.
     * 默认：#000000
     */
    private String navigationBarBackgroundColor;
    /**
     * 导航栏标题颜色，仅支持 black/white.
     * 默认：white
     */
    private String navigationBarTextStyle;
    /**
     * 导航栏标题文字内容.
     */
    private String navigationBarTitleText;
    /**
     * 窗口的背景色 HexColor.
     * 默认：#ffffff
     */
    private String backgroundColor;
    /**
     * 下拉背景字体、loading 图的样式，仅支持 dark/light.
     * 默认：dark
     */
    private String backgroundTextStyle;
    /**
     * 是否开启下拉刷新，详见页面相关事件处理函数.
     * 默认：false
     */
    private String enablePullDownRefresh;
    /**
     * 设置为 true 则页面整体不能上下滚动；只在 page.json 中有效，无法在 app.json 中设置该项.
     * 默认：false
     */
    private Boolean disableScroll;
    /**
     * 页面上拉触底事件触发时距页面底部距离，单位为px.
     * 默认：50
     */
    private Integer onReachBottomDistance;
  }

  /**
   * tabBar 配置.
   */
  @Data
  @Builder
  public static class TabBar {
    /**
     * HexColor, tab 上的文字默认颜色.
     */
    private String color;
    /**
     * HexColor, tab 上的文字选中时的颜色.
     */
    private String selectedColor;
    /**
     * HexColor, tab 的背景色.
     */
    private String backgroundColor;
    /**
     * tabbar 上边框的颜色，仅支持 black/white.
     */
    private String borderStyle;
    /**
     * tab 的列表，最少2个、最多5个 tab.
     */
    private List<Item> list;
    /**
     * 可选值 bottom、top.
     */
    private String position;

    /**
     * list item.
     */
    @Data
    @Builder
    public static class Item {
      /**
       * 页面路径，必须在 pages 中先定义.
       */
      private String pagePath;
      /**
       * tab 上按钮文字.
       */
      private String text;
      /**
       * 图片路径，icon 大小限制为40kb，建议尺寸为 81px * 81px，当 postion 为 top 时，此参数无效，不支持网络图片.
       */
      private String iconPath;
      /**
       * 选中时的图片路径，icon 大小限制为40kb，建议尺寸为 81px * 81px ，当 postion 为 top 时，此参数无效.
       */
      private String selectedIconPath;
    }
  }

  /**
   * 各种网络请求的超时时间.
   */
  @Data
  @Builder
  public static class NetworkTimeout {
    /**
     * wx.request的超时时间，单位毫秒，默认为：60000.
     * 必填：否
     */
    private Integer request;
    /**
     * wx.connectSocket的超时时间，单位毫秒，默认为：60000.
     * 必填：否
     */
    private Integer connectSocket;
    /**
     * wx.uploadFile的超时时间，单位毫秒，默认为：60000.
     * 必填：否
     */
    private Integer uploadFile;
    /**
     * wx.downloadFile的超时时间，单位毫秒，默认为：60000.
     * 必填：否
     */
    private Integer downloadFile;
  }
}
