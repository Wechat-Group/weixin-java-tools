package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 推广员、分享员信息
 *
 * @author zhongjun
 * created on  2022/5/17
 **/
@Data
public class WxMaShopPromotionInfo implements Serializable {
  private static final long serialVersionUID = -812058443344709898L;
  /**
   * 推广员唯一ID
   */
  @SerializedName("promoter_id")
  private String promoterId;

  /**
   * 推广员视频号昵称
   */
  @SerializedName("finder_nickname")
  private String finderNickname;
  /**
   * 推广员openid
   */
  @SerializedName("promoter_openid")
  private String promoterOpenid;

  /**
   * 分享员openid
   */
  @SerializedName("sharer_openid")
  private String sharerOpenid;
}
