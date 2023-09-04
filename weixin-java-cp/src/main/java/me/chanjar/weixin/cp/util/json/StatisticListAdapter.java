package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.kf.WxCpKfGetCorpStatisticResp;

import java.lang.reflect.Type;

/**
 * The type Statistic list adapter.
 *
 * @author zhongjun  created on  2022/4/25
 */
public class StatisticListAdapter implements JsonDeserializer<WxCpKfGetCorpStatisticResp.StatisticList> {

  @Override
  public WxCpKfGetCorpStatisticResp.StatisticList deserialize(JsonElement jsonElement, Type type,
                                                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxCpKfGetCorpStatisticResp.StatisticList statisticList = new WxCpKfGetCorpStatisticResp.StatisticList();
    JsonObject asJsonObject = jsonElement.getAsJsonObject();
    statisticList.setStatTime(asJsonObject.get("stat_time").getAsLong());
    JsonElement statistic = asJsonObject.get("statistic");
    if (GsonHelper.isNotNull(statistic)) {
      WxCpKfGetCorpStatisticResp.Statistic statisticObj = new WxCpKfGetCorpStatisticResp.Statistic();
      statisticObj.setSessionCnt(GsonHelper.isNull(statistic.getAsJsonObject().get("session_cnt")) ? null : statistic.getAsJsonObject().get("session_cnt").getAsInt());
      statisticObj.setCustomerCnt(GsonHelper.isNull(statistic.getAsJsonObject().get("customer_cnt")) ? null : statistic.getAsJsonObject().get("customer_cnt").getAsInt());
      statisticObj.setCustomerMsgCnt(GsonHelper.isNull(statistic.getAsJsonObject().get("customer_msg_cnt")) ? null : statistic.getAsJsonObject().get("customer_msg_cnt").getAsInt());
      statisticObj.setUpgradeServiceCustomerCnt(GsonHelper.isNull(statistic.getAsJsonObject().get("upgrade_service_customer_cnt")) ? null : statistic.getAsJsonObject().get("upgrade_service_customer_cnt").getAsInt());
      statisticObj.setAiSessionReplyCnt(GsonHelper.isNull(statistic.getAsJsonObject().get("ai_session_reply_cnt")) ? null : statistic.getAsJsonObject().get("ai_session_reply_cnt").getAsInt());
      statisticObj.setAiTransferRate(GsonHelper.isNull(statistic.getAsJsonObject().get("ai_transfer_rate")) ? null : statistic.getAsJsonObject().get("ai_transfer_rate").getAsFloat());
      statisticObj.setAiKnowledgeHitRate(GsonHelper.isNull(statistic.getAsJsonObject().get("ai_knowledge_hit_rate")) ? null : statistic.getAsJsonObject().get("ai_knowledge_hit_rate").getAsFloat());
      statisticObj.setMsgRejectedCustomerCnt(GsonHelper.isNull(statistic.getAsJsonObject().get("msg_rejected_customer_cnt")) ? null : statistic.getAsJsonObject().get("msg_rejected_customer_cnt").getAsInt());
      statisticList.setStatistic(statisticObj);
    }
    return statisticList;
  }
}
