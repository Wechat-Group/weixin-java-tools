package me.chanjar.weixin.cp.bean.school;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取师生健康码.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpResultList extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("result_list")
  private List<QrCodeList> qrCodeList;

  /**
   * The type Qr code list.
   */
  @Setter
  @Getter
  public static class QrCodeList extends WxCpBaseResp {

    @SerializedName("userid")
    private String userId;

    @SerializedName("qrcode_data")
    private String qrCodeData;

    /**
     * From json qr code list.
     *
     * @param json the json
     * @return the qr code list
     */
    public static QrCodeList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, QrCodeList.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp result list.
   *
   * @param json the json
   * @return the wx cp result list
   */
  public static WxCpResultList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpResultList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
