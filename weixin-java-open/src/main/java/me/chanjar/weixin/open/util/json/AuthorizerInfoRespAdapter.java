package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.open.bean.resp.AuthorizerInfoResp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JasonY on 17/10/24.
 */
public class AuthorizerInfoRespAdapter implements JsonDeserializer<AuthorizerInfoResp> {
  @Override
  public AuthorizerInfoResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    JsonObject authorizerInfoObject = o.getAsJsonObject("authorizer_info");
    JsonObject authorizationInfoObject = o.getAsJsonObject("authorization_info");

    AuthorizerInfoResp authorizerInfoResp = new AuthorizerInfoResp();

    if (authorizerInfoObject != null) {
      authorizerInfoResp.setNickName(GsonHelper.getString(authorizerInfoObject, "nick_name"));
      authorizerInfoResp.setHeadImg(GsonHelper.getString(authorizerInfoObject, "head_img"));

      JsonObject serviceTypeInfoObject = authorizerInfoObject.getAsJsonObject("service_type_info");
      if (serviceTypeInfoObject != null) {
        authorizerInfoResp.setServiceTypeInfoId(GsonHelper.getInteger(serviceTypeInfoObject, "id"));
      } else {
        authorizerInfoResp.setServiceTypeInfoId(0);
      }

      JsonObject verifyTypeInfoObject = authorizerInfoObject.getAsJsonObject("verify_type_info");
      if (verifyTypeInfoObject != null) {
        authorizerInfoResp.setVerifyTypeInfoId(GsonHelper.getInteger(verifyTypeInfoObject, "id"));
      } else {
        authorizerInfoResp.setVerifyTypeInfoId(0);
      }

      authorizerInfoResp.setUserName(GsonHelper.getString(authorizerInfoObject, "user_name"));
      authorizerInfoResp.setPrincipalName(GsonHelper.getString(authorizerInfoObject, "principal_name"));
      authorizerInfoResp.setAlias(GsonHelper.getString(authorizerInfoObject, "alias"));

      JsonObject businessInfoObject = authorizerInfoObject.getAsJsonObject("business_info");
      if (businessInfoObject != null) {
        authorizerInfoResp.setOpenStore(GsonHelper.getInteger(businessInfoObject, "open_store"));
        authorizerInfoResp.setOpenScan(GsonHelper.getInteger(businessInfoObject, "open_scan"));
        authorizerInfoResp.setOpenPay(GsonHelper.getInteger(businessInfoObject, "open_pay"));
        authorizerInfoResp.setOpenCard(GsonHelper.getInteger(businessInfoObject, "open_card"));
      }

      authorizerInfoResp.setQrcodeUrl(GsonHelper.getString(authorizerInfoObject, "qrcode_url"));
      authorizerInfoResp.setSignature(GsonHelper.getString(authorizerInfoObject, "signature"));

      JsonObject miniProgramInfoObject = authorizerInfoObject.getAsJsonObject("MiniProgramInfo");
      if (miniProgramInfoObject != null) {
        AuthorizerInfoResp.MiniProgramInfo miniProgramInfo = authorizerInfoResp.buildMiniProgramInfo();
        authorizerInfoResp.setMiniProgramInfo(miniProgramInfo);

        JsonObject networkObject = miniProgramInfoObject.getAsJsonObject("network");
        if (networkObject != null) {
          JsonArray requestDomainArray = networkObject.getAsJsonArray("RequestDomain");
          if (requestDomainArray != null) {
            List<String> requestDomains = new ArrayList<>();
            for (JsonElement e : requestDomainArray) {
              requestDomains.add(e.getAsString());
            }
            miniProgramInfo.setRequestDomains(requestDomains);
          }

          JsonArray wsRequestDomainArray = networkObject.getAsJsonArray("WsRequestDomain");
          if (wsRequestDomainArray != null) {
            List<String> wsRequestDomains = new ArrayList<>();
            for (JsonElement e : wsRequestDomainArray) {
              wsRequestDomains.add(e.getAsString());
            }
            miniProgramInfo.setWsRequestDomains(wsRequestDomains);
          }

          JsonArray uploadDomainArray = networkObject.getAsJsonArray("UploadDomain");
          if (uploadDomainArray != null) {
            List<String> uploadDomains = new ArrayList<>();
            for (JsonElement e : uploadDomainArray) {
              uploadDomains.add(e.getAsString());
            }
            miniProgramInfo.setUploadDomains(uploadDomains);
          }

          JsonArray downloadDomainArray = networkObject.getAsJsonArray("DownloadDomain");
          if (downloadDomainArray != null) {
            List<String> downloadDomains = new ArrayList<>();
            for (JsonElement e : downloadDomainArray) {
              downloadDomains.add(e.getAsString());
            }
            miniProgramInfo.setDownloadDomains(downloadDomains);
          }
        }

        JsonArray categoryArray = miniProgramInfoObject.getAsJsonArray("categories");
        if (categoryArray != null) {
          List<Map<String, String>> categories = new ArrayList<>();
          for (JsonElement e : categoryArray) {
            Map<String, String> category = new HashMap<>();
            category.put("first", GsonHelper.getString(e.getAsJsonObject(), "first"));
            category.put("second", GsonHelper.getString(e.getAsJsonObject(), "second"));
            categories.add(category);
          }
          miniProgramInfo.setCategories(categories);
        }

        miniProgramInfo.setVisitStatus(GsonHelper.getInteger(miniProgramInfoObject, "visit_status"));
      }
    }

    if (authorizationInfoObject != null) {
      authorizerInfoResp.setAppid(GsonHelper.getString(authorizationInfoObject, "appid"));

      JsonArray funcInfoArray = authorizationInfoObject.getAsJsonArray("func_info");
      if (funcInfoArray != null && funcInfoArray.isJsonArray()) {
        List<Integer> funcInfoIds = new ArrayList<>();
        for (JsonElement funcInfo : funcInfoArray) {
          JsonObject scopeCategory = funcInfo.getAsJsonObject().getAsJsonObject("funcscope_category");
          funcInfoIds.add(GsonHelper.getInteger(scopeCategory, "id"));
        }
        authorizerInfoResp.setFuncInfoIds(funcInfoIds);
      }
    }

    return authorizerInfoResp;
  }
}
