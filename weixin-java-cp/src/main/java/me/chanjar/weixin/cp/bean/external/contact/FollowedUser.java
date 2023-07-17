package me.chanjar.weixin.cp.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加了外部联系人的企业成员.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-11-04
 */
@Data
public class FollowedUser implements Serializable {
  private static final long serialVersionUID = -4301684507150486556L;

  @SerializedName("userid")
  private String userId;

  private String remark;

  private String description;

  @SerializedName("createtime")
  private Long createTime;

  private String state;

  @SerializedName("remark_company")
  private String remarkCompany;

  @SerializedName("remark_mobiles")
  private String[] remarkMobiles;

  /**
   * 批量获取客户详情 接口专用
   */
  @SerializedName("tag_id")
  private String[] tagIds;

  /**
   * 获取客户详情  接口专用
   */
  private Tag[] tags;

  @SerializedName("remark_corp_name")
  private String remarkCorpName;

  @SerializedName("add_way")
  private String addWay;

  @SerializedName("oper_userid")
  private String operatorUserId;

  /**
   * 该成员添加此客户的来源add_way为10时，对应的视频号信息
   */
  @SerializedName("wechat_channels")
  private WechatChannels wechatChannels;

  /**
   * The type Tag.
   */
  @Data
  public static class Tag implements Serializable {
    private static final long serialVersionUID = -7556237053703295482L;

    /**
     * 该成员添加此外部联系人所打标签的分组名称（标签功能需要企业微信升级到2.7.5及以上版本）
     */
    @SerializedName("group_name")
    private String groupName;

    /**
     * 该成员添加此外部联系人所打标签名称
     */
    @SerializedName("tag_name")
    private String tagName;

    /**
     * 该成员添加此外部联系人所打企业标签的id，仅企业设置（type为1）的标签返回
     */
    @SerializedName("tag_id")
    private String tagId;

    /**
     * 该成员添加此外部联系人所打标签类型, 1-企业设置, 2-用户自定义
     */
    private int type;
  }

  /**
   * The type WechatChannels.
   */
  @Data
  public static class WechatChannels implements Serializable {
    private static final long serialVersionUID = -7940080094561469369L;

    /**
     * 视频号名称
     */
    private String nickname;

    /**
     * 视频号添加场景，0-未知 1-视频号主页 2-视频号直播间 3-视频号留资服务（微信版本要求：iOS ≥ 8.0.20，Android ≥ 8.0.21，且添加时间不早于2022年4月21日。否则添加场景值为0）
     */
    private Integer source;
  }
}
