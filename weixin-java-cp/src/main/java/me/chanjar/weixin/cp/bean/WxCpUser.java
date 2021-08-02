package me.chanjar.weixin.cp.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户信息.
 *
 * @author Daniel Qian
 */
@Data
@Accessors(chain = true)
public class WxCpUser implements Serializable {
  private static final long serialVersionUID = -5696099236344075582L;

  private String userId;
  private String newUserId;
  private String name;
  private Long[] departIds;
  private Integer[] orders;
  private String position;
  private String[] positions;
  private String mobile;
  private Gender gender;
  private String email;
  private String avatar;
  private String thumbAvatar;
  private String mainDepartment;
  /**
   * 全局唯一。对于同一个服务商，不同应用获取到企业内同一个成员的open_userid是相同的，最多64个字节。仅第三方应用可获取
   */
  private String openUserId;

  /**
   * 地址。长度最大128个字符
   */
  private String address;
  private String avatarMediaId;
  private Integer status;
  private Integer enable;
  /**
   * 别名；第三方仅通讯录应用可获取
   */
  private String alias;
  private Integer isLeader;
  /**
   * is_leader_in_dept.
   * 个数必须和department一致，表示在所在的部门内是否为上级。1表示为上级，0表示非上级。在审批等应用里可以用来标识上级审批人
   */
  private Integer[] isLeaderInDept;
  private final List<Attr> extAttrs = new ArrayList<>();
  private Integer hideMobile;
  private String englishName;
  private String telephone;
  private String qrCode;
  private Boolean toInvite;
  /**
   * 成员对外信息.
   */
  private List<ExternalAttribute> externalAttrs = new ArrayList<>();
  private String externalPosition;
  private String externalCorpName;
  private WechatChannels wechatChannels;




  public void addExternalAttr(ExternalAttribute externalAttr) {
    this.externalAttrs.add(externalAttr);
  }

  public void addExtAttr(String name, String value) {
    this.extAttrs.add(new Attr().setType(0).setName(name).setTextValue(value));
  }

  public void addExtAttr(Attr attr) {
    this.extAttrs.add(attr);
  }

  public static WxCpUser fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUser.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Data
  @Accessors(chain = true)
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Attr implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    /**
     * 属性类型: 0-文本 1-网页
     */
    private Integer type;
    private String name;
    private String textValue;
    private String webUrl;
    private String webTitle;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExternalAttribute implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    /**
     * 属性类型: 0-本文 1-网页 2-小程序.
     */
    private Integer type;
    /**
     * 属性名称： 需要先确保在管理端有创建改属性，否则会忽略.
     */
    private String name;
    /**
     * 文本属性内容,长度限制12个UTF8字符.
     */
    private String value;
    /**
     * 网页的url,必须包含http或者https头.
     */
    private String url;
    /**
     * 小程序的展示标题,长度限制12个UTF8字符.
     * 或者 网页的展示标题,长度限制12个UTF8字符
     */
    private String title;
    /**
     * 小程序appid，必须是有在本企业安装授权的小程序，否则会被忽略.
     */
    private String appid;
    /**
     * 小程序的页面路径.
     */
    private String pagePath;
  }


  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class WechatChannels implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    private String nickname;

    private Integer status;

  }
}
