package me.chanjar.weixin.mp.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.mp.bean.*;
import me.chanjar.weixin.mp.bean.card.WxMpCard;
import me.chanjar.weixin.mp.bean.card.WxMpCardResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserCumulate;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserSummary;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.material.*;
import me.chanjar.weixin.mp.bean.card.membercard.WxMpMemberCardActivateTempInfoResult;
import me.chanjar.weixin.mp.bean.card.membercard.WxMpMemberCardUpdateResult;
import me.chanjar.weixin.mp.bean.card.membercard.WxMpMemberCardUserInfoResult;
import me.chanjar.weixin.mp.bean.result.*;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import java.util.Objects;

/**
 * @author someone
 */
public class WxMpGsonBuilder {

  private static final GsonBuilder INSTANCE = new GsonBuilder();
  private static volatile Gson GSON_INSTANCE;

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxMpKefuMessage.class, new WxMpKefuMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassNews.class, new WxMpMassNewsGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassTagMessage.class, new WxMpMassTagMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassOpenIdsMessage.class, new WxMpMassOpenIdsMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUser.class, new WxMpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpChangeOpenid.class, new WxMpChangeOpenidGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUserList.class, new WxUserListGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassVideo.class, new WxMpMassVideoAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassSendResult.class, new WxMpMassSendResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassUploadResult.class, new WxMpMassUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpQrCodeTicket.class, new WxQrCodeTicketAdapter());
    INSTANCE.registerTypeAdapter(WxMpTemplateMessage.class, new WxMpTemplateMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpSubscribeMessage.class, new WxMpSubscribeMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpSemanticQueryResult.class, new WxMpSemanticQueryResultAdapter());
    INSTANCE.registerTypeAdapter(WxDataCubeUserSummary.class, new WxMpUserSummaryGsonAdapter());
    INSTANCE.registerTypeAdapter(WxDataCubeUserCumulate.class, new WxMpUserCumulateGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialUploadResult.class, new WxMpMaterialUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialVideoInfoResult.class, new WxMpMaterialVideoInfoResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialArticleUpdate.class, new WxMpMaterialArticleUpdateGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialCountResult.class, new WxMpMaterialCountResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialNews.class, new WxMpMaterialNewsGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpNewsArticle.class, new WxMpNewsArticleGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.class, new WxMpMaterialNewsBatchGetGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem.class, new WxMpMaterialNewsBatchGetGsonItemAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.class, new WxMpMaterialFileBatchGetGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem.class, new WxMpMaterialFileBatchGetGsonItemAdapter());
    INSTANCE.registerTypeAdapter(WxMpCardResult.class, new WxMpCardResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpCard.class, new WxMpCardGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassPreviewMessage.class, new WxMpMassPreviewMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMediaImgUploadResult.class, new WxMediaImgUploadResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpTemplateIndustry.class, new WxMpIndustryGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUserBlacklistGetResult.class, new WxUserBlacklistGetResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMemberCardUserInfoResult.class, new WxMpMemberCardUserInfoResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMemberCardUpdateResult.class, new WxMpMemberCardUpdateResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMemberCardActivateTempInfoResult.class, new WxMpMemberCardActivateTempInfoResultGsonAdapter());
  }

  public static Gson create() {
    if (Objects.isNull(GSON_INSTANCE)) {
      synchronized (GSON_INSTANCE) {
        if (Objects.isNull(GSON_INSTANCE)) {
          GSON_INSTANCE = INSTANCE.create();
        }
      }
    }
    return GSON_INSTANCE;
  }

}
