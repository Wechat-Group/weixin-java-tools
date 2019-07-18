package me.chanjar.weixin.open.bean.result;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信开放平台小程序域名设置返回对象.
 *
 * @author yqx
 * @date 2018/9/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaDomainResult extends WxOpenResult {
  private static final long serialVersionUID = 3406315629639573330L;

  @SerializedName("requestdomain")
  List<String> requestdomainList;

  @SerializedName("wsrequestdomain")
  List<String> wsrequestdomainList;

  @SerializedName("uploaddomain")
  List<String> uploaddomainList;

  @SerializedName("downloaddomain")
  List<String> downloaddomainList;

}
