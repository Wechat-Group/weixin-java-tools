package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzibin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionGetMsgResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = -7807426027724675223L;

  /*
    {
    "send_cnt" : 2,
    "fail_cnt" : 1,
    "fail_info" : [
      {
        "id" : "123",
        "errcode" : 103010
      }
    ],
    "fail_openid_url" : "https://xxxxxxxxxx",
    "msg_type" : 1,
    "content" : "{\"title\":\"今日优惠活动\",\"topic\":\"双十一大促\",\"desc\":\"三件五折，两件七折\",\"date\":\"2022/10/28\"}",
    "appid" : "xxxxx",
    "path" : "pages/index/xxxxx",
    "list_type" : 1,
    "role_id" : [ 1, 2 ],
    "errcode": 0,
    "errmsg": "OK"
    }
   */

  /**
   * 发送总数
   */
  @SerializedName("send_cnt")
  private Long sendCnt;

  /**
   * 当前已发送比例
   */
  @SerializedName("percent")
  private Long percent;

  /**
   * 失败总数，在全部发送完后更新，发送进度参考percent
   */
  @SerializedName("fail_cnt")
  private Long failCnt;

  /**
   * 包含推广员唯一id和失败错误码，失败数量超过一千会生成文件，不会返回明细
   * 非必填
   */
  @SerializedName("fail_info")
  private List<FailInfo> failInfo;

  /**
   * fail_info文件url
   */
  @SerializedName("fail_info_url")
  private String failInfoUrl;

  /**
   * 消息类型，枚举值参考小程序推广员消息模板汇总
   */
  @SerializedName("msg_type")
  private Long msgType;

  /**
   * 消息内容，为json格式的字符串，不同类型对应的字符串示例见模板列表
   */
  @SerializedName("content")
  private String content;

  /**
   * 消息体跳转appid，不填默认为调起接口的appid
   */
  @SerializedName("appid")
  private String appId;

  /**
   * 消息体跳转path
   */
  @SerializedName("path")
  private String path;

  /**
   * 下发类型（0：全量下发，1：按role_id下发，2：按retail_id下发，3：按推广员id下发）
   */
  @SerializedName("list_type")
  private Long listType;

  /**
   * list_type为1有值
   */
  @SerializedName("role_id")
  private List<Long> roleId;

  /**
   * list_type为2有值
   */
  @SerializedName("retail_id")
  private List<String> retailId;

  /**
   * list_type为3有值
   */
  @SerializedName("id")
  private List<String> id;

  @Data
  public static class FailInfo {
    /**
     * id
     */
    @SerializedName("id")
    private String id;

    /**
     * 失败错误码
     */
    @SerializedName("errorcode")
    private Long errorcode;

  }
}
