package me.chanjar.weixin.cp.bean.external.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品画册图片
 *
 * @author <a href="https://github.com/Loading-Life">Lo_ading</a>
 */
@Data
public class Image implements Serializable {

  private static final long serialVersionUID = -2737415903252627814L;

  @SerializedName("media_id")
  private String mediaId;

}
