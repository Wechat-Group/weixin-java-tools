package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.WxCpConsts;
import me.chanjar.weixin.cp.api.WxCpOAService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpApprovalDataResult;
import me.chanjar.weixin.cp.bean.WxCpCheckinData;
import me.chanjar.weixin.cp.bean.WxCpCheckinOption;
import me.chanjar.weixin.cp.bean.WxCpDialRecord;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author Element
 * @Package me.chanjar.weixin.cp.api.impl
 * @date 2019-04-06 11:20
 * @Description: TODO
 */
public class WxCpOAServiceImpl implements WxCpOAService {

  private WxCpService mainService;

  public WxCpOAServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public List<WxCpCheckinData> getCheckinData(Integer openCheckinDataType, Date starttime, Date endtime, List<String> userIdList) throws WxErrorException {

    if (starttime == null || endtime == null) {
      throw new RuntimeException("starttime and endtime can't be null");
    }

    if (userIdList == null || userIdList.size() > 100) {
      throw new RuntimeException("用户列表不能为空，不超过100个，若用户超过100个，请分批获取");
    }

    long endtimestamp = endtime.getTime() / 1000L;
    long starttimestamp = starttime.getTime() / 1000L;

    if (endtimestamp - starttimestamp < 0 || endtimestamp - starttimestamp >= 30 * 24 * 60 * 60) {
      throw new RuntimeException("获取记录时间跨度不超过一个月");
    }

    String url = WxCpConsts.getQyapiUrl("/cgi-bin/checkin/getcheckindata");

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();

    jsonObject.addProperty("opencheckindatatype", openCheckinDataType);
    jsonObject.addProperty("starttime", starttimestamp);
    jsonObject.addProperty("endtime", endtimestamp);

    for (String userid : userIdList) {
      jsonArray.add(userid);
    }

    jsonObject.add("useridlist", jsonArray);

    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("checkindata"),
        new TypeToken<List<WxCpCheckinData>>() {
        }.getType()
      );
  }

  @Override
  public List<WxCpCheckinOption> getCheckinOption(Date datetime, List<String> userIdList) throws WxErrorException {
    String url = WxCpConsts.getQyapiUrl("/cgi-bin/checkin/getcheckinoption");
    if (datetime == null) {
      throw new RuntimeException("datetime can't be null");
    }

    if (userIdList == null || userIdList.size() > 100) {
      throw new RuntimeException("用户列表不能为空，不超过100个，若用户超过100个，请分批获取");
    }

    JsonArray jsonArray = new JsonArray();
    for (String userid : userIdList) {
      jsonArray.add(userid);
    }

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("datetime", datetime.getTime() / 1000L);
    jsonObject.add("useridlist", jsonArray);

    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);

    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("info"),
        new TypeToken<List<WxCpCheckinOption>>() {
        }.getType()
      );
  }

  @Override
  public WxCpApprovalDataResult getApprovalData(Date starttime, Date endtime, Long nextSpnum) throws WxErrorException {

    String url = WxCpConsts.getQyapiUrl("/cgi-bin/corp/getapprovaldata");
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("starttime", starttime.getTime() / 1000L);
    jsonObject.addProperty("endtime", endtime.getTime() / 1000L);
    if (nextSpnum != null) {
      jsonObject.addProperty("next_spnum", nextSpnum);
    }

    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpApprovalDataResult.class);
  }

  @Override
  public List<WxCpDialRecord> getDialRecord(Date starttime, Date endtime, Integer offset, Integer limit) throws WxErrorException {

    String url = WxCpConsts.getQyapiUrl("/cgi-bin/dial/get_dial_record");
    JsonObject jsonObject = new JsonObject();

    if (offset == null) {
      offset = 0;
    }

    if (limit == null || limit <= 0) {
      limit = 100;
    }

    jsonObject.addProperty("offset", offset);
    jsonObject.addProperty("limit", limit);

    if (starttime != null && endtime != null) {

      long endtimestamp = endtime.getTime() / 1000L;
      long starttimestamp = starttime.getTime() / 1000L;

      if (endtimestamp - starttimestamp < 0 || endtimestamp - starttimestamp >= 30 * 24 * 60 * 60) {
        throw new RuntimeException("受限于网络传输，起止时间的最大跨度为30天，如超过30天，则以结束时间为基准向前取30天进行查询");
      }

      jsonObject.addProperty("start_time", starttimestamp);
      jsonObject.addProperty("end_time", endtimestamp);


    }

    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);

    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("record"),
        new TypeToken<List<WxCpDialRecord>>() {
        }.getType()
      );
  }
}
