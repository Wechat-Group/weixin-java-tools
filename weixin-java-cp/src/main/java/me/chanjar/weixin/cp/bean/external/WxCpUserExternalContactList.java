package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 外部联系人列表
 * Created by Joe Cao on 2019/6/16.
 * 参考文档：https://work.weixin.qq.com/api/doc#90001/90143/91570
 * </pre>
 *
 * @author <a href="https://github.com/JoeCao">Joe Cao</a>
 */
public class WxCpUserExternalContactList implements Serializable {
  private static final long serialVersionUID = -4301684507150486556L;

  @SerializedName("errcode")
  @Expose
  private Long errcode;
  @SerializedName("errmsg")
  @Expose
  private String errmsg;

  @SerializedName("external_userid")
  @Expose
  private List<String> externalUserId = null;

  /**
   * Gets errcode.
   *
   * @return the errcode
   */
  public Long getErrcode() {
    return errcode;
  }

  /**
   * Sets errcode.
   *
   * @param errcode the errcode
   */
  public void setErrcode(Long errcode) {
    this.errcode = errcode;
  }

  /**
   * Gets errmsg.
   *
   * @return the errmsg
   */
  public String getErrmsg() {
    return errmsg;
  }

  /**
   * Sets errmsg.
   *
   * @param errmsg the errmsg
   */
  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }


  /**
   * Gets external user id.
   *
   * @return the external user id
   */
  public List<String> getExternalUserId() {
    return externalUserId;
  }

  /**
   * Sets external user id.
   *
   * @param externalUserId the external user id
   */
  public void setExternalUserId(List<String> externalUserId) {
    this.externalUserId = externalUserId;
  }

  /**
   * From json wx cp user external contact list.
   *
   * @param json the json
   * @return the wx cp user external contact list
   */
  public static WxCpUserExternalContactList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalContactList.class);
  }
}
