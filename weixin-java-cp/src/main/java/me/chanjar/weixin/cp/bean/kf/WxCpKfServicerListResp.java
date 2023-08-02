package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * The type Wx cp kf servicer list resp.
 *
 * @author leiin  created on  2022/1/26 4:29 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfServicerListResp extends WxCpBaseResp {

  private static final long serialVersionUID = -5079770046571012449L;
  @SerializedName("servicer_list")
  private List<WxCpKfServicerStatus> servicerList;

  /**
   * The type Wx cp kf servicer status.
   */
  @NoArgsConstructor
  @Data
  public static class WxCpKfServicerStatus {
    @SerializedName("userid")
    private String userId;
    private Integer status;
  }

  /**
   * From json wx cp kf servicer list resp.
   *
   * @param json the json
   * @return the wx cp kf servicer list resp
   */
  public static WxCpKfServicerListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfServicerListResp.class);
  }
}
