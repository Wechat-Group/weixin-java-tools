package me.chanjar.weixin.mp.util.json;

import com.google.gson.*;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 群发消息json转换适配器.
 *
 * @author chanjarster
 */
public class WxMpMassTagMessageGsonAdapter implements JsonSerializer<WxMpMassTagMessage> {

  @Override
  public JsonElement serialize(WxMpMassTagMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();

    JsonObject filter = new JsonObject();
    if (message.isSendAll() || null == message.getTagId()) {
      filter.addProperty("is_to_all", true);
    } else {
      filter.addProperty("is_to_all", false);
      filter.addProperty("tag_id", message.getTagId());
    }
    messageJson.add("filter", filter);

    if (WxConsts.MassMsgType.MPNEWS.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WxConsts.MassMsgType.MPNEWS, sub);
    }
    if (WxConsts.MassMsgType.TEXT.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("content", message.getContent());
      messageJson.add(WxConsts.MassMsgType.TEXT, sub);
    }
    if (WxConsts.MassMsgType.VOICE.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WxConsts.MassMsgType.VOICE, sub);
    }
    if (WxConsts.MassMsgType.IMAGE.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      List<String> mediaIds = message.getMediaIds();
      if (mediaIds != null && !mediaIds.isEmpty() ) {
        JsonArray json = new JsonArray();
        mediaIds.forEach(json::add);
        sub.add("media_ids", json);
        messageJson.add(WxConsts.MassMsgType.IMAGES, sub);
      } else {
        String mediaId = message.getMediaId();
        sub.addProperty("media_id", mediaId);
        messageJson.add(WxConsts.MassMsgType.IMAGE, sub);
      }
    }
    if (WxConsts.MassMsgType.MPVIDEO.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WxConsts.MassMsgType.MPVIDEO, sub);
    }
    messageJson.addProperty("msgtype", message.getMsgType());
    messageJson.addProperty("send_ignore_reprint", message.isSendIgnoreReprint() ? 1 : 0);

    if (StringUtils.isNotEmpty(message.getClientMsgId())) {
      messageJson.addProperty("clientmsgid", message.getClientMsgId());
    }

    return messageJson;
  }

}
