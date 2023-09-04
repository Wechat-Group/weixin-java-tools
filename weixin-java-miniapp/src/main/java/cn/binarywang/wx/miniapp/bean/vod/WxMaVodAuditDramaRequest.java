package cn.binarywang.wx.miniapp.bean.vod;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaVodAuditDramaRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  @SerializedName("drama_id")
  private Integer dramaId;
  @SerializedName("name")
  private String name;
  @SerializedName("media_count")
  private Long mediaCount;
  @SerializedName("media_id_list")
  private List<Integer> mediaIdList;
  @SerializedName("producer")
  private String producer;
  @SerializedName("cover_material_id")
  private String coverMaterialId;
  @SerializedName("authorized_material_id")
  private String authorizedMaterialId;
  @SerializedName("registration_number")
  private String registrationNumber;
  @SerializedName("publish_license")
  private String publishLicense;
  @SerializedName("publish_license_material_id")
  private String publishLicenseMaterialId;
  @SerializedName("expedited")
  private Long expedited;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
