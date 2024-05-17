package cn.binarywang.wx.miniapp.bean.promoter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzibin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaPromotionSendMsgRequest implements Serializable {

  private static final long serialVersionUID = -5282382905925607758L;

  /*
    // list_type不传
    {
      "msg_type" : 1,
      "content" : "{\"title\":\"今日优惠活动\",\"topic\":\"双十一大促\",\"desc\":\"三件五折，两件七折\",\"date\":\"2022/10/28\"}",
      "appid" : "xxxxx",
      "path" : "pages/index/xxxxx"
    }

    // list_type为1
    {
      "msg_type" : 1,
      "content" : "{\"title\":\"今日优惠活动\",\"topic\":\"双十一大促\",\"desc\":\"三件五折，两件七折\",\"date\":\"2022/10/28\"}",
      "appid" : "xxxxx",
      "path" : "pages/index/xxxxx",
      "list_type" : 1,
      "role_id" : [ 1, 2 ]
    }

    // list_type为2
    {
      "msg_type" : 1,
      "content" : "{\"title\":\"今日优惠活动\",\"topic\":\"双十一大促\",\"desc\":\"三件五折，两件七折\",\"date\":\"2022/10/28\"}",
      "appid" : "xxxxx",
      "path" : "pages/index/xxxxx",
      "list_type" : 2,
      "retail_id" : [ "xxxxx", "xxxxx" ]
    }

    // list_type为3
    {
      "msg_type" : 1,
      "content" : "{\"title\":\"今日优惠活动\",\"topic\":\"双十一大促\",\"desc\":\"三件五折，两件七折\",\"date\":\"2022/10/28\"}",
      "appid" : "xxxxx",
      "path" : "pages/index/xxxxx",
      "list_type" : 3,
      "id" : [ "1", "2" ]
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
   * 1：发送给所填role_id下的所有推广员，2：发送给所填retail_id下的所有推广员，3：发送给所填id对应的推广员，0或不填则发送给全部推广员。请保证所填参数的正确性，错误的参数不会发送
   * 非必填
   */
  @SerializedName("list_type")
  private Long listType;

  /**
   * list_type为1时必填，取值可以参考查询推广员身份api返回的结果
   * 非必填
   */
  @SerializedName("role_id")
  private List<Long> roleId;

  /**
   * list_type为2时必填，取值可以参考查询推广员身份api返回的结果
   * 非必填
   */
  @SerializedName("retail_id")
  private List<String> retailId;

  /**
   * list_type为3时必填，取值可以参考查询推广员身份api返回的结果
   * 非必填
   */
  @SerializedName("id")
  private List<String> id;
}
