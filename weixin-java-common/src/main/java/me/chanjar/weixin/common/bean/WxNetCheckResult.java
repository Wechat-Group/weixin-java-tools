package me.chanjar.weixin.common.bean;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络检测.
 * @author billytomato
 */
@Data
public class WxNetCheckResult implements Serializable {

  private static final long serialVersionUID = 6918924418847404172L;

  private List<WxNetCheckDnsInfo> dnsInfos = new ArrayList<>();
  private List<WxNetCheckPingInfo> pingInfos = new ArrayList<>();

  public static WxNetCheckResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxNetCheckResult.class);
  }

  @Data
  public static class WxNetCheckDnsInfo implements Serializable{
    private static final long serialVersionUID = 82631178029516008L;
    private String ip;
    private String realOperator;
  }

  @Data
  public static class WxNetCheckPingInfo implements Serializable{
    private static final long serialVersionUID = -1871970825359178319L;
    private String ip;
    private String fromOperator;
    private String packageLoss;
    private String time;
  }
}


