package me.chanjar.weixin.mp.bean.card;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class WxMpCardLandingPageCreateRequest implements Serializable {

  /**
   * 页面的banner图片链接，须调用，建议尺寸为640*300。
   */
  private String banner;

  /**
   * 页面的title
   */
  @SerializedName("page_title")
  private String title;

  @SerializedName("can_share")
  private boolean canShare;

  /**
   * 投放页面的场景值；
   * SCENE_NEAR_BY 附近
   * SCENE_MENU 自定义菜单
   * SCENE_QRCODE 二维码
   * SCENE_ARTICLE 公众号文章
   * SCENE_H5 h5页面
   * SCENE_IVR 自动回复
   * SCENE_CARD_CUSTOM_CELL 卡券自定义cell
   */
  private String scene;

  @SerializedName("card_list")
  private JsonArray cardList;

  public void addCard(String cardId, String thumbUrl) {
    if (StringUtils.isNoneBlank(cardId, thumbUrl)) {
      if (cardList == null) {
        cardList = new JsonArray();
      }
      JsonObject cardJson = new JsonObject();
      cardJson.addProperty("card_id", cardId);
      cardJson.addProperty("thumb_url", thumbUrl);
      cardList.add(cardJson);
    }
  }

  public static WxMpCardLandingPageCreateRequest fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardLandingPageCreateRequest.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

