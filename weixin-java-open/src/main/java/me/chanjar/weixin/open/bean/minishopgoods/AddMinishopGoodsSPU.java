package me.chanjar.weixin.open.bean.minishopgoods;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 *  属性后面的  true 代表必传   false 代表非必传
 */
@Data
@Accessors(chain = true)
public class AddMinishopGoodsSPU {

  /**
   * 商家自定义商品ID，与product_id二选一  true
   */
  private String outProductId;
  /**
   * 标题 true
   */
  private String title;
  /**
   * 副标题  false
   */
  private String subTitle;
  /**
   * 主图,多张,列表 true
   */
  private List<String> headImg;
  /**
   * 商品详情，图文(目前只支持图片) true
   */
  private DescInfo descInfo;
  /**
   * 品牌ID，商家需要申请品牌并通过获取品牌接口brand/get获取，如果是无品牌请填2100000000 true
   */
  private Integer brandId = 2100000000;
  /**
   * 类目 true
   */
  private List<Cat> cats;
  /**
   * 属性 true
   */
  private List<Attr> attrs;
  /**
   * 商品型号 false
   */
  private String model;
  /**
   * 运费模板 true
   */
  private ExpressInfo expressInfo;
  /**
   * SKU false
   */
  private List<Sku> skus;

  public JsonObject toJsonObject() {
    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("out_product_id", outProductId);
    jsonObject.addProperty("title",title);
    jsonObject.addProperty("sub_title",subTitle);
    jsonObject.addProperty("head_img",gson.toJson(headImg));
    jsonObject.addProperty("desc_info",gson.toJson(descInfo));
    jsonObject.addProperty("brand_id",brandId.toString());
    jsonObject.addProperty("cats",gson.toJson(cats));
    jsonObject.addProperty("attrs",gson.toJson(attrs));
    jsonObject.addProperty("model",model);
    jsonObject.addProperty("expressInfo",gson.toJson(expressInfo));
    jsonObject.addProperty("skus",gson.toJson(skus));
    return jsonObject;
  }

  public static void main(String[] args) {
    GoodsCatList goodsCatList = new GoodsCatList();
    goodsCatList.setErrcode(1).setErrmsg("正常").setCatList(Arrays.asList(new GoodsCat().setCatId(1).setFCatId(0).setName("服饰"),
      new GoodsCat().setCatId(2).setFCatId(0).setName("鞋包") ));
    System.out.println(goodsCatList.toString());
    System.out.println(goodsCatList.toJsonObject());
  }


}
