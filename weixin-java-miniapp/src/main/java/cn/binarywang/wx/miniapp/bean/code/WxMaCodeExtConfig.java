package cn.binarywang.wx.miniapp.bean.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 上传代码需要用到的第三方自定义的配置
 * 详细文档，参考：<a href="https://developers.weixin.qq.com/miniprogram/dev/framework/config.html">文档</a>
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
   * 关于新增 requiredPrivateInfos 说明
   * 关于地理位置接口新增与相关流程调整可以查看社区公告：
   * <a href="https://developers.weixin.qq.com/community/develop/doc/000a02f2c5026891650e7f40351c01">...</a>
   * 7.14后，在代码中使用的地理位置相关接口（共计 8 个，见表1），第三方开发者均需要在 ext_json 参数中 requiredPrivateInfos 配置项中声明
   * 在ext_json参数中配置requiredPrivateInfos，其规则为「整体替换」。即如果在 app.json 里也配置了，那么最终会是ext_json的配置会覆盖 app.json
   * 配置的requiredPrivateInfos。其余规则可查看下方的「ext_json补充说明」
   * 在ext_json参数中配置 requiredPrivateInfos 示例如下
   * {
   *   "template_id": "95",
   *   "ext_json": "{\"requiredPrivateInfos\":[\"onLocationChange\",\"startLocationUpdate\"]}",
   *   "user_version": "V1.0",
   *   "user_desc": "test"
   * }
   * requiredPrivateInfos主要会检查格式是否正确，填入的 api 名称是否正确，填入的 api 名称是否有权限，填入的 api 名称是否互斥。对应的错误码可查看文档末尾的错误码文档。
   * requiredPrivateInfos在2022.7.14后才会生效，文档提前更新是为了方便开发者可以提前了解接口的参数变更规则，提前进行调整。
   */
  private String[] requiredPrivateInfos;

  /**
   * page.json 配置，页面配置
   * <a href="https://mp.weixin.qq.com/debug/wxadoc/dev/framework/config.html">文档</a>
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PageConfig implements Serializable {
    private static final long serialVersionUID = -8615574764987479723L;
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
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TabBar implements Serializable {
    private static final long serialVersionUID = -3037016532526129399L;

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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item implements Serializable {
      private static final long serialVersionUID = -5824322265161612460L;
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
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NetworkTimeout implements Serializable {
    private static final long serialVersionUID = -9180176522015880991L;

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
