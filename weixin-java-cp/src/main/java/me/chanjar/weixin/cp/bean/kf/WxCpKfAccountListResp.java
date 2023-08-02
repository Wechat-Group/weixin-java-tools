package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 获取客服帐号列表-结果
 *
 * @author Fu  created on  2022/1/19 19:13
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfAccountListResp extends WxCpBaseResp {

  private static final long serialVersionUID = -1317201649692262217L;

  /**
   * 帐号信息列表
   */
  @SerializedName("account_list")
  private List<AccountListDTO> accountList;

  /**
   * The type Account list dto.
   */
  @NoArgsConstructor
  @Data
  public static class AccountListDTO {
    /**
     * 客服帐号ID
     */
    @SerializedName("open_kfid")
    private String openKfid;

    /**
     * 客服名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 客服头像URL
     */
    @SerializedName("avatar")
    private String avatar;
  }

  /**
   * From json wx cp kf account list resp.
   *
   * @param json the json
   * @return the wx cp kf account list resp
   */
  public static WxCpKfAccountListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfAccountListResp.class);
  }
}
