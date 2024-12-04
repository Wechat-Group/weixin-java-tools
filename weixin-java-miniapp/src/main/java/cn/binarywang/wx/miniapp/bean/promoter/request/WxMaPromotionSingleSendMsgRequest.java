package cn.binarywang.wx.miniapp.bean.promoter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaPromotionSingleSendMsgRequest implements Serializable {

  private static final long serialVersionUID = 3552361351502585916L;

  /*
    {
    "msg_type" : 1,
    "content" : "{\"title\":\"今日优惠活动\",\"topic\":\"双十一大促\",\"desc\":\"三件五折，两件七折\",\"date\":\"2022/10/28\"}",
    "appid" : "xxxxx",
    "path" : "pages/index/xxxxx",
    "id" : "1"
    }
    或
    {
    "msg_type" : 1,
    "content" : "{\"title\":\"今日优惠活动\",\"topic\":\"双十一大促\",\"desc\":\"三件五折，两件七折\",\"date\":\"2022/10/28\"}",
    "appid" : "xxxxx",
    "path" : "pages/index/xxxxx",
    "openid" : "xxxxxxxxxxxx"
    }
   */

  /**
   * 消息类型，枚举值参考小程序推广员消息模板汇总
   * <a href="https://docs.qq.com/sheet/DZUVkUFZ6VVhDalZi?tab=BB08J2">小程序推广员消息模板汇总</a>
   * 必填
   */
  @SerializedName("msg_type")
  private Integer msgType;

  /**
   * 消息内容，为json格式的字符串，不同类型对应的字符串示例见模板列表
   * 必填
   */
  @SerializedName("content")
  private String content;

  /**
   * 消息体跳转appid（需与调用接口的appid在同OPEN账号下），不填默认为调起接口的appid
   * 非必填
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 消息体跳转path，确保path无误，否则会报页面不存在
   * 必填
   */
  @SerializedName("path")
  private String path;

  /**
   * 推广员openid或unionid
   * 非必填
   */
  @SerializedName("openid")
  private String openid;
}
