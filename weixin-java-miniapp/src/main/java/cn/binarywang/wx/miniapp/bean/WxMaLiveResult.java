package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 直播间操作返回结果
 * Created by lipengjun on 2020/6/29.
 * </pre>
 *
 * @author <a href="https://github.com/lipengjun92">lipengjun (939961241@qq.com)</a>
 */
@Data
public class WxMaLiveResult implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer errcode;
  private String errmsg;
  private Integer total;
  private Integer auditId;
  private Integer goodsId;
  private List<Goods> goods;

  public static WxMaLiveResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaLiveResult.class);
  }

  /**
   * 商品列表
   */
  @Data
  public static class Goods implements Serializable {
    private static final long serialVersionUID = 5769245932149287574L;
    @SerializedName("goods_id")
    private Integer goodsId;
    @SerializedName("cover_img_url")
    private String coverImgUrl;
    private String name;
    private String url;
    @SerializedName("price_type")
    private Integer priceType;
    /**
     * 0：未审核，1：审核中，2:审核通过，3审核失败
     */
    @SerializedName("audit_status")
    private Integer auditStatus;
    private String price;
    private String price2;
    /**
     * 1, 2：表示是为api添加商品，否则是在MP添加商品
     */
    @SerializedName("third_party_tag")
    private String thirdPartyTag;
  }
}
