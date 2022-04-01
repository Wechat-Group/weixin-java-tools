package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 获取商品图册列表执行结果
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/95096#获取商品图册列表
 * </pre>
 *
 * @author <a href="https://github.com/Loading-Life">Lo_ading</a>
 */
@Getter
@Setter
public class WxCpProductAlbumListResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 121265727802015428L;

  @SerializedName("product_list")
  private List<WxCpProductAlbumInfo> productList;

  @SerializedName("next_cursor")
  private String nextCursor;

  public static WxCpProductAlbumListResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpProductAlbumListResult.class);
  }

}
