package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.external.product.Attachment;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * <pre>
 * 获取商品图册
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/95096#获取商品图册
 * </pre>
 *
 * @author <a href="https://github.com/Loading-Life">Lo_ading</a>
 */
@Getter
@Setter
public class WxCpProductAlbumInfo implements Serializable {

  private static final long serialVersionUID = -8338202601802366899L;

  @SerializedName("product_id")
  private String productId;

  @SerializedName("product_sn")
  private String productSn;

  @SerializedName("description")
  private String description;

  /**
   * NOTE: 20211110 价钱返回全部为0
   */
  @SerializedName("price")
  private Integer price;

  /**
   * NOTE: 20211110 商品列表接口不返回此字段, 商品详情接口返回
   */
  @SerializedName("create_time")
  private Long createTime;

  @SerializedName("attachments")
  private List<Attachment> attachments;

  public static WxCpProductAlbumInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpProductAlbumInfo.class);
  }

}
