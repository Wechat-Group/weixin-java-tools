package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.kf.WxCpKfGetCorpStatisticResp;

import java.lang.reflect.Type;

/**
 * @author zhongjun
 * @date 2022/4/25
 **/
public class StatisticListAdapter implements JsonDeserializer<WxCpKfGetCorpStatisticResp.StatisticList> {

  @Override
  public WxCpKfGetCorpStatisticResp.StatisticList deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxCpKfGetCorpStatisticResp.StatisticList statisticList = new WxCpKfGetCorpStatisticResp.StatisticList();
    JsonObject asJsonObject = jsonElement.getAsJsonObject();
    statisticList.setStatTime(asJsonObject.get("stat_time").getAsLong());
    JsonElement statistic = asJsonObject.get("statistic");
    if (GsonHelper.isNotNull(statistic)) {
      WxCpKfGetCorpStatisticResp.Statistic statisticObj = new WxCpKfGetCorpStatisticResp.Statistic();
      statisticObj.setSessionCnt(statistic.getAsJsonObject().get("session_cnt").getAsInt());
      statisticObj.setCustomerCnt(statistic.getAsJsonObject().get("customer_cnt").getAsInt());
      statisticObj.setCustomerMsgCnt(statistic.getAsJsonObject().get("customer_msg_cnt").getAsInt());
      statisticObj.setUpgradeServiceCustomerCnt(statistic.getAsJsonObject().get("upgrade_service_customer_cnt").getAsInt());
      statisticObj.setAiTransferRate(statistic.getAsJsonObject().get("ai_transfer_rate").getAsInt());
      statisticObj.setAiKnowledgeHitRate(statistic.getAsJsonObject().get("ai_knowledge_hit_rate").getAsInt());
    }
    return statisticList;
  }
}
