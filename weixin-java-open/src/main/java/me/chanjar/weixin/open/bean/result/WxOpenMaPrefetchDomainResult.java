package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 清心
 * create on 2022-10-01 18:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxOpenMaPrefetchDomainResult extends WxOpenResult{

  @SerializedName("prefetch_dns_domain")
  private List<PreDnsDomain> prefetchDnsDomain;

  @SerializedName("size_limit")
  private Integer sizeLimit;

  @Data
  public static class PreDnsDomain {

    @SerializedName("url")
    private String url;

    @SerializedName("status")
    private Integer status;
  }

}
