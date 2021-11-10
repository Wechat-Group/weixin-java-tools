package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * <pre>
 * 获取商品图册执行结果
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/95096#获取商品图册
 * </pre>
 *
 * @author <a href="https://github.com/Loading-Life">Lo_ading</a>
 */
@Getter
@Setter
public class WxCpProductAlbumResult extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = 3464981991558716620L;

  @SerializedName("product")
  private WxCpProductAlbumInfo product;

  public static WxCpProductAlbumResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpProductAlbumResult.class);
  }

}
