package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 微信第三方平台上传代码到小程序代码标准模板时的参数
 * ext_json补充说明
 * 文档地址:https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/code/commit.html
 * 为了便于第三方平台使用同一个小程序模板为不同的小程序提供服务，第三方可以将自定义信息放置在 ext_json 中，在模板小程序中，可以使用 wx.getExtConfigSync 接口获取自定义信息，从而区分不同的小程序。详见：小程序模板开发
 * ext_json 中的参数可选，参数详见小程序配置;但是，如果是模板id为标准模板库的模板id，则ext_json可支持的参数为：{"extAppid":'', "ext": {}, "window": {}}
 * ext_json 中有限支持 pages，支持配置模板页面的子集（ext_json 中不可新增页面）。
 * ext_json 中有限支持 subPackages，支持配置模板分包及其页面的子集（ext_json 中配置的分包必须已声明于模板中，且不可新增分包页面）。
 * ext_json支持plugins配置，该配置会覆盖模板中的app.json中的plugins配置。关于plugin的使用详情请参考使用插件。
 * 如果代码中已经有配置，则配置的合并规则为：
 * ext整体替换
 * pages整体替换
 * extPages中找到对应页面，同级覆盖page.json
 * window同级覆盖
 * extAppid直接加到app.json
 * networkTimeout同级覆盖
 * customOpen整体替换
 * tabbar同级覆盖
 * functionPages整体替换
 * subPackages整体替换
 * navigateToMiniProgaramAppIdList：整体替换
 * plugins整体替换
 * </p>
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * @since 2021/08/12
 */
@Data
public class WxMaOpenCommitStandardExt implements Serializable {

  private static final long serialVersionUID = 4595618023108631477L;

  /**
   * 授权小程序Appid，可填入商户小程序AppID，以区分不同商户
   */
  @SerializedName("create_time")
  private String extAppId;

  /**
   * 开发自定义的数据字段
   */
  private Map<String, Object> ext;

  /**
   * 授权小程序Appid，可填入商户小程序AppID，以区分不同商户
   */
  private Map<String, Object> window;
}
