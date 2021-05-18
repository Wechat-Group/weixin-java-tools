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

  public Long getErrcode() {
    return errcode;
  }

  public void setErrcode(Long errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }


  public List<String> getExternalUserId() {
    return externalUserId;
  }

  public void setExternalUserId(List<String> externalUserId) {
    this.externalUserId = externalUserId;
  }

  public static WxCpUserExternalContactList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalContactList.class);
  }
}
