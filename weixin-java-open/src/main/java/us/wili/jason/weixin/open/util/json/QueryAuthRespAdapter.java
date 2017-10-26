package us.wili.jason.weixin.open.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import us.wili.jason.weixin.open.bean.resp.QueryAuthResp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonY on 17/10/24.
 */
public class QueryAuthRespAdapter implements JsonDeserializer<QueryAuthResp> {

  @Override
  public QueryAuthResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();

    if (o == null) {
      return null;
    }

    JsonObject authorizationInfo = o.getAsJsonObject("authorization_info");
    if (authorizationInfo != null) {
      QueryAuthResp queryAuthResp = new QueryAuthResp();
      queryAuthResp.setAuthorizerAppid(GsonHelper.getString(authorizationInfo, "authorizer_appid"));
      queryAuthResp.setAuthorizerAccessToken(GsonHelper.getString(authorizationInfo, "authorizer_access_token"));
      queryAuthResp.setExpiresIn(GsonHelper.getInteger(authorizationInfo, "expires_in"));
      queryAuthResp.setAuthorizerRefreshToken(GsonHelper.getString(authorizationInfo, "authorizer_refresh_token"));

      JsonArray funcInfos = authorizationInfo.getAsJsonArray("func_info");
      if (funcInfos != null && funcInfos.isJsonArray()) {
        List<Integer> funcInfoIds = new ArrayList<>();
        for (JsonElement funcInfo : funcInfos) {
          JsonObject scopeCategory = funcInfo.getAsJsonObject().getAsJsonObject("funcscope_category");
          funcInfoIds.add(GsonHelper.getInteger(scopeCategory, "id"));
        }
        queryAuthResp.setFuncInfoIds(funcInfoIds);
      }

      return queryAuthResp;
    }

    return null;
  }
}
