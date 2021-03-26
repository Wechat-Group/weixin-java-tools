package me.chanjar.weixin.open.bean.minishop.goods;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;
import me.chanjar.weixin.open.bean.minishop.MinishopShopCat;

import java.io.Serializable;
import java.util.List;

@Data
public class WxMinishopSpu implements Serializable {


  private String outProductId;

  private String title;

  private String subTitle;

  private List<String> headImgs;

  private List<String> descInfoImgs;

  private Long brandId;

  private List<MinishopShopCat> shopCats;

  private List<WxMinishopGoodsSkuAttr> attrs;

  private String model;

  private Long expressTemplateId;

  private List<WxMinishopSku> skus;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("out_product_id", outProductId);
    jsonObject.addProperty("title", title);
    jsonObject.addProperty("sub_title", subTitle);
    JsonArray imgArray = new JsonArray();
    for (String img : headImgs) {
      imgArray.add(img);
    }
    jsonObject.add("head_img", imgArray);

    JsonArray descImgArray = new JsonArray();
    for (String img : descInfoImgs) {
      descImgArray.add(img);
    }
    JsonObject descInfo = new JsonObject();
    descInfo.add("imgs", descImgArray);
    jsonObject.add("desc_info", descInfo);

    jsonObject.addProperty("brand_id", brandId);
    JsonArray catArray = new JsonArray();
    for (MinishopShopCat cat : shopCats) {
      JsonObject catObj = new JsonObject();
      catObj.addProperty("cat_id", cat.getShopCatId());
      catObj.addProperty("level", cat.getCatLevel());
      catArray.add(catObj);
    }
    jsonObject.add("cats", catArray);

    JsonArray attrArray = new JsonArray();
    for (WxMinishopGoodsSkuAttr attr : attrs) {
      attrArray.add(attr.toJsonObject());
    }
    jsonObject.add("attrs", attrArray);


    jsonObject.addProperty("model", model);

    JsonObject expressObj = new JsonObject();
    expressObj.addProperty("template_id", expressTemplateId);
    jsonObject.add("express_info", expressObj);

    JsonArray skuArray = new JsonArray();
    for (WxMinishopSku sku : skus) {
      skuArray.add(sku.toJsonObject());
    }
    jsonObject.add("skus", skuArray);
    return jsonObject;
  }
}
