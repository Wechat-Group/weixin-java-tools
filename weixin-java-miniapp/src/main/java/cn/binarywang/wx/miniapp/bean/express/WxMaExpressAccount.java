package cn.binarywang.wx.miniapp.bean.express;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 物流账号对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressAccount implements Serializable {


  private static final long serialVersionUID = -4991983596742569736L;

  /**
   * 快递公司客户编码
   */
  @SerializedName("biz_id")
  private String bizId;

  /**
   * 快递公司ID
   */
  @SerializedName("delivery_id")
  private String deliveryId;

  /**
   * 账号绑定时间
   */
  @SerializedName("create_time")
  private Long createTime;

  /**
   * 账号更新时间
   */
  @SerializedName("update_time")
  private Long updateTime;

  /**
   * 绑定状态
   * status_code 的合法值 : 0-绑定成功;1-审核中;2-绑定失败
   */
  @SerializedName("status_code")
  private Integer statusCode;

  /**
   * 账号别名
   */
  @SerializedName("alias")
  private String alias;

  /**
   * 账号绑定失败的错误信息（EMS审核结果）
   */
  @SerializedName("remark_wrong_msg")
  private String remarkWrongMsg;

  /**
   * 账号绑定时的备注内容（提交EMS审核需要）
   */
  @SerializedName("remark_content")
  private String remarkContent;

  /**
   * 电子面单余额
   */
  @SerializedName("quota_num")
  private Integer quotaNum;

  /**
   * 电子面单余额更新时间
   */
  @SerializedName("quota_update_time")
  private Integer quotaUpdateTime;

  /**
   * 支持的服务类型
   */
  @SerializedName("service_type")
  private List<WxMaExpressDelivery.ServiceType> serviceType;

  public static List<WxMaExpressAccount> fromJsonList(String json) {
    JsonObject jsonObject = GsonParser.parse(json);
    return WxMaGsonBuilder.create().fromJson(jsonObject.get("list").toString(),
      new TypeToken<List<WxMaExpressAccount>>() {
      }.getType());
  }

  public static WxMaExpressAccount fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaExpressAccount.class);
  }
}
