package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaWebDomainResult extends WxOpenResult {

  private static final long serialVersionUID = -2182687859448940313L;

  @SerializedName("webviewdomain")
  List<String> webviewdomainList;

}
