package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * @author leiin
 * @date 2022/1/26 4:29 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfServicerListResp extends WxCpBaseResp {

  private static final long serialVersionUID = -5079770046571012449L;
  @SerializedName("servicer_list")
  private List<WxCpKfServicerStatus> servicerList;

  @NoArgsConstructor
  @Data
  public static class WxCpKfServicerStatus {
    @SerializedName("userid")
    private String userId;
    private Integer status;
  }

  public static WxCpKfServicerListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfServicerListResp.class);
  }
}
