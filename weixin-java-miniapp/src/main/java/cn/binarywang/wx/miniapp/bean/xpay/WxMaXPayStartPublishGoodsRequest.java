package cn.binarywang.wx.miniapp.bean.xpay;

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
public class WxMaXPayStartPublishGoodsRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("env")
  private Integer env;

  @SerializedName("publish_item")
  private List<PublishItem> publishItem;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Data
  public static class PublishItem {
    @SerializedName("id")
    private String id;

  }
}
