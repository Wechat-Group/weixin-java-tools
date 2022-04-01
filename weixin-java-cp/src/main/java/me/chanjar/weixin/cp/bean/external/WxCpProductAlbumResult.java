package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

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
  private static final long serialVersionUID = 4076734101839851497L;

  @SerializedName("product")
  private WxCpProductAlbumInfo product;

  public static WxCpProductAlbumResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpProductAlbumResult.class);
  }

}
