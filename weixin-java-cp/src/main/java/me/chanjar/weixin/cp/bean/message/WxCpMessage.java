package me.chanjar.weixin.cp.bean.message;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.Collection;

/**
 * 文档地址
 * <p>
 * https://work.weixin.qq.com/api/doc/90000/90135/90236
 * </p>
 *
 * @author caiqy
 */
@Slf4j
@Data
public abstract class WxCpMessage implements Serializable {

  private static final long serialVersionUID = -8108866042801612419L;

  /**
   * The constant YES.
   */
  public static final Integer YES = 1;

  /**
   * The constant NO.
   */
  public static final Integer NO = 0;

  /**
   * all
   */
  public static final String ALL = "@all";

  /**
   * 30分钟
   */
  public static final Integer MIN_CHECK_INTERVAL = 30 * 60;

  /**
   * 4小时
   */
  public static final Integer MAX_CHECK_INTERVAL = 4 * 60 * 60;

  public static final String PIPE = "|";

  /**
   * 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   * 特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   */
  @SerializedName("touser")
  private String toUser;

  /**
   * 指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   * 当touser为”@all”时忽略本参数
   */
  @SerializedName("toparty")
  private String toParty;

  /**
   * 指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   * 当touser为”@all”时忽略本参数
   */
  @SerializedName("totag")
  private String toTag;

  /**
   * 消息类型
   */
  @SerializedName("msgtype")
  private String msgType;

  /**
   * 企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
   */
  @SerializedName("agentid")
  private Integer agentId;

  /**
   * 是否是保密消息，0表示可对外分享，1表示不能分享且内容显示水印，默认为0
   */
  @SerializedName("safe")
  private Integer safe;

  /**
   * 是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
   */
  @SerializedName("enable_id_trans")
  private Integer enableIdTrans;

  /**
   * 是否开启重复消息检查，0表示否，1表示是，默认0
   */
  @SerializedName("enable_duplicate_check")
  private Integer enableDuplicateCheck;

  /**
   * 是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
   */
  @SerializedName("duplicate_check_interval")
  private Integer duplicateCheckInterval;

  protected void setTo(String toUser, String toParty, String toTag) {
    this.toUser = toUser;
    this.toParty = toParty;
    this.toTag = toTag;
    checkTo();
  }

  protected void checkTo() {
    if (StrUtil.isAllBlank(getToParty(), getToUser(), getToTag())) {
      throw new WxErrorException("touser、toparty、totag不能同时为空");
    }
  }

  protected void resetInterval() {
    if (getDuplicateCheckInterval() == null) {
      log.warn("重复消息检查最小为30分钟,系统自动重置为30分钟");
      setDuplicateCheckInterval(MAX_CHECK_INTERVAL);
    }
    if (getDuplicateCheckInterval() > MAX_CHECK_INTERVAL) {
      log.warn("重复消息检查最大为4小时,系统自动重置为4小时");
      setDuplicateCheckInterval(MAX_CHECK_INTERVAL);
    }
    if (getDuplicateCheckInterval() < MIN_CHECK_INTERVAL) {
      log.warn("重复消息检查最小为30分钟,系统自动重置为30分钟");
      setDuplicateCheckInterval(MIN_CHECK_INTERVAL);
    }
  }

  protected void init(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    setTo(toUser, toParty, toTag);
    setAgentId(agentId);
    setSafe(safe);
    setEnableIdTrans(enableIdTrans);
    setEnableDuplicateCheck(enableDuplicateCheck);
    setDuplicateCheckInterval(duplicateCheckInterval);
    // 检查参数合理性
    checkTo();
    resetInterval();
  }

  protected static String combine(String... str) {
    if (str != null) {
      return ArrayUtil.join(str, PIPE);
    }
    return null;
  }

  protected static String combine(Collection<String> str) {
    if (str != null) {
      return CollUtil.join(str, PIPE);
    }
    return null;
  }

  public void addUser(String userId) {
    if (StrUtil.isNotBlank(userId)) {
      if (StrUtil.isBlank(getToUser())) {
        setToUser(userId);
      } else {
        setToUser(getToUser() + PIPE + userId);
      }
    }
  }

  public void addParty(String partyId) {
    if (StrUtil.isNotBlank(partyId)) {
      if (StrUtil.isBlank(getToParty())) {
        setToParty(partyId);
      } else {
        setToParty(getToParty() + PIPE + partyId);
      }
    }
  }

  public void addTag(String tagId) {
    if (StrUtil.isNotBlank(tagId)) {
      if (StrUtil.isBlank(getToTag())) {
        setToTag(tagId);
      } else {
        setToTag(getToTag() + PIPE + tagId);
      }
    }
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  protected MediaMessage generateMediaMessage(String mediaId) {
    return new MediaMessage(mediaId);
  }

  protected MediaMessage generateMediaMessage(String mediaId, String title, String description) {
    return new MediaMessage(mediaId, title, description);
  }

  @Data
  public static class MediaMessage implements Serializable {

    private static final long serialVersionUID = -1662135535260756955L;

    @SerializedName("media_id")
    private String mediaId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    public MediaMessage(String mediaId) {
      this.mediaId = mediaId;
    }

    public MediaMessage(String mediaId, String title, String description) {
      this.mediaId = mediaId;
      this.title = title;
      this.description = description;
    }
  }
}
