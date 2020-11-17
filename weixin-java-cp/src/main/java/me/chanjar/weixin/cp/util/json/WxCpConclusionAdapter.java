package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.cp.bean.external.WxCpContactWayInfo;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

/**
 * 结束语序列化转换器
 *
 * @author element
 */
public class WxCpConclusionAdapter implements JsonSerializer<WxCpContactWayInfo.ContactWay.Conclusion>, JsonDeserializer<WxCpContactWayInfo.ContactWay.Conclusion> {
  @Override
  public WxCpContactWayInfo.ContactWay.Conclusion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();

    WxCpContactWayInfo.ContactWay.Conclusion conclusion = new WxCpContactWayInfo.ContactWay.Conclusion();

    if (jsonObject.get("text") != null) {
      JsonObject jsonText = jsonObject.get("text").getAsJsonObject();

      if (jsonText.get("content") != null) {
        conclusion.setTextContent(jsonText.get("content").getAsString());
      }
    }

    if (jsonObject.get("image") != null) {
      JsonObject jsonImage = jsonObject.get("image").getAsJsonObject();

      if (jsonImage.get("media_id") != null) {
        conclusion.setImgMediaId(jsonImage.get("media_id").getAsString());
      }

      if (jsonImage.get("pic_url") != null) {
        conclusion.setImgPicUrl(jsonImage.get("pic_url").getAsString());
      }
    }

    if (jsonObject.get("link") != null) {
      JsonObject jsonLink = jsonObject.get("link").getAsJsonObject();

      if (jsonLink.get("title") != null) {
        conclusion.setLinkTitle(jsonLink.get("title").getAsString());
      }
      if (jsonLink.get("picurl") != null) {
        conclusion.setLinkPicUrl(jsonLink.get("picurl").getAsString());
      }
      if (jsonLink.get("desc") != null) {
        conclusion.setLinkDesc(jsonLink.get("desc").getAsString());
      }
      if (jsonLink.get("url") != null) {
        conclusion.setLinkUrl(jsonLink.get("url").getAsString());
      }
    }

    if (jsonObject.get("miniprogram") != null) {

      JsonObject jsonMiniProgram = jsonObject.get("miniprogram").getAsJsonObject();
      if (jsonMiniProgram.get("title") != null) {
        conclusion.setMiniProgramTitle(jsonMiniProgram.get("title").getAsString());
      }
      if (jsonMiniProgram.get("pic_media_id") != null) {
        conclusion.setMiniProgramPicMediaId(jsonMiniProgram.get("pic_media_id").getAsString());
      }
      if (jsonMiniProgram.get("appid") != null) {
        conclusion.setMiniProgramAppId(jsonMiniProgram.get("appid").getAsString());
      }
      if (jsonMiniProgram.get("page") != null) {
        conclusion.setMiniProgramPage(jsonMiniProgram.get("page").getAsString());
      }

    }

    return conclusion;
  }

  @Override
  public JsonElement serialize(WxCpContactWayInfo.ContactWay.Conclusion src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    if (StringUtils.isNotBlank(src.getTextContent())) {
      JsonObject jsonText = new JsonObject();
      jsonText.addProperty("content", src.getTextContent());
      json.add("text", jsonText);
    }

    if (StringUtils.isNotBlank(src.getImgMediaId()) || StringUtils.isNotBlank(src.getImgPicUrl())) {
      JsonObject jsonImg = new JsonObject();
      jsonImg.addProperty("media_id", src.getImgMediaId());
      jsonImg.addProperty("pic_url", src.getImgPicUrl());
      json.add("image", jsonImg);
    }

    if (StringUtils.isNotBlank(src.getLinkTitle())
      || StringUtils.isNotBlank(src.getLinkPicUrl())
      || StringUtils.isNotBlank(src.getLinkDesc())
      || StringUtils.isNotBlank(src.getLinkUrl())
    ) {
      JsonObject jsonLink = new JsonObject();
      jsonLink.addProperty("title", src.getLinkTitle());
      jsonLink.addProperty("picurl", src.getLinkPicUrl());
      jsonLink.addProperty("desc", src.getLinkDesc());
      jsonLink.addProperty("url", src.getLinkUrl());
      json.add("link", jsonLink);
    }

    if (StringUtils.isNotBlank(src.getMiniProgramTitle())
      || StringUtils.isNotBlank(src.getMiniProgramPicMediaId())
      || StringUtils.isNotBlank(src.getMiniProgramAppId())
      || StringUtils.isNotBlank(src.getMiniProgramPage())
    ) {
      JsonObject jsonMiniProgram = new JsonObject();
      jsonMiniProgram.addProperty("title", src.getMiniProgramTitle());
      jsonMiniProgram.addProperty("pic_media_id", src.getMiniProgramPicMediaId());
      jsonMiniProgram.addProperty("appid", src.getMiniProgramAppId());
      jsonMiniProgram.addProperty("page", src.getMiniProgramPage());
      json.add("miniprogram", jsonMiniProgram);
    }

    return json;
  }
}
