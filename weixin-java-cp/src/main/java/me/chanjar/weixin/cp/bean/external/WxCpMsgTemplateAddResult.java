package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by songfan on 2020/7/14.
 *
 * @author songfan
 */
@Data
public class WxCpMsgTemplateAddResult implements Serializable {
  private static final long serialVersionUID = -5166048319463473188L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("fail_list")
  private List<String> failList;

  @SerializedName("msgid")
  private String msgId;

  public boolean isSuccess() {
    return Integer.valueOf(0).equals(errCode);
  }

  public static WxCpMsgTemplateAddResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMsgTemplateAddResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
