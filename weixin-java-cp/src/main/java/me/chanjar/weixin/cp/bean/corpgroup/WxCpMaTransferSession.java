package me.chanjar.weixin.cp.bean.corpgroup;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.bean.corpgroup
 * @Description: 获取下级/下游企业小程序session返回类
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 9:10 PM
 */
@Data
public class WxCpMaTransferSession implements Serializable{

  private static final long serialVersionUID = 4189407986285166516L;
  @SerializedName("userid")
  private String userId;
  @SerializedName("session_key")
  private String sessionKey;
}
