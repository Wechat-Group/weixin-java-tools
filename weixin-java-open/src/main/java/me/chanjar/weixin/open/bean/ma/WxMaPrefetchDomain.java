package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 清心
 * create on 2022-10-01 18:07
 */
@Data
public class WxMaPrefetchDomain implements Serializable {
  private static final long serialVersionUID = 1593947263587362155L;

  @SerializedName("prefetch_dns_domain")
  private List<DnsDomain> prefetchDnsDomain;

  @Data
  public static class DnsDomain {
    private String url;
  }
}
