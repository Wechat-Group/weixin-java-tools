package me.chanjar.weixin.mp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustryEnum;

import java.lang.reflect.Type;

/**
 * @author miller
 */
public class WxMpIndustryGsonAdapter implements JsonSerializer<WxMpTemplateIndustry>,
  JsonDeserializer<WxMpTemplateIndustry> {
  @Override
  public JsonElement serialize(WxMpTemplateIndustry wxMpIndustry, Type type, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    json.addProperty("industry_id1", wxMpIndustry.getPrimaryIndustry().getCode());
    json.addProperty("industry_id2", wxMpIndustry.getSecondIndustry().getCode());
    return json;
  }

  @Override
  public WxMpTemplateIndustry deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
    throws JsonParseException {
    return new WxMpTemplateIndustry()
      .setPrimaryIndustry(this.convertFromJson(jsonElement.getAsJsonObject().get("primary_industry").getAsJsonObject()))
      .setSecondIndustry(this.convertFromJson(jsonElement.getAsJsonObject().get("secondary_industry").getAsJsonObject()));
  }

  private WxMpTemplateIndustryEnum convertFromJson(JsonObject json) {
    String firstClass = GsonHelper.getString(json, "first_class");
    // 兼容微信接口实际返回跟官方文档不符的文字
    if (firstClass != null) {
      firstClass = firstClass.replace("医疗护理", "医药护理");
    }
    String secondClass = GsonHelper.getString(json, "second_class");
    final WxMpTemplateIndustryEnum industryEnum = WxMpTemplateIndustryEnum.findByClass(firstClass, secondClass);
    if (industryEnum != null) {
      return industryEnum;
    }

    if (secondClass.contains("|")) {
      secondClass = secondClass.split("\\|")[1];
    }

    return WxMpTemplateIndustryEnum.findByClass(firstClass, secondClass);
  }

}
