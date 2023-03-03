package me.chanjar.weixin.cp.bean.corpgroup;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.bean.corpgroup
 * @Description: 获取下级/下游企业的access_token
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 9:07 PM
 */
@Data
public class WxCpCorpGroupCorpGetTokenReq implements Serializable {
  private static final long serialVersionUID = -1876754768932436524L;
  @SerializedName("corpid")
  private String corpId;
  @SerializedName("business_type")
  private int businessType;
  @SerializedName("agentid")
  private int agentId;
}
