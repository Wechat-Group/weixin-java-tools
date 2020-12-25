package me.chanjar.weixin.qidian.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.qidian.bean.datacube.WxDataCubeUserSummary;
import org.apache.commons.lang3.time.FastDateFormat;

import java.lang.reflect.Type;
import java.text.ParseException;

/**
 * @author Daniel Qian
 */
public class WxMpUserSummaryGsonAdapter implements JsonDeserializer<WxDataCubeUserSummary> {

  private static final FastDateFormat DATE_FORMAT = FastDateFormat
    .getInstance("yyyy-MM-dd");

  @Override
  public WxDataCubeUserSummary deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
    WxDataCubeUserSummary summary = new WxDataCubeUserSummary();
    JsonObject summaryJsonObject = json.getAsJsonObject();

    try {
      String refDate = GsonHelper.getString(summaryJsonObject, "ref_date");
      if (refDate != null) {
        summary.setRefDate(DATE_FORMAT.parse(refDate));
      }
      summary.setUserSource(GsonHelper.getInteger(summaryJsonObject, "user_source"));
      summary.setNewUser(GsonHelper.getInteger(summaryJsonObject, "new_user"));
      summary.setCancelUser(GsonHelper.getInteger(summaryJsonObject, "cancel_user"));
    } catch (ParseException e) {
      throw new JsonParseException(e);
    }

    return summary;
  }

}
