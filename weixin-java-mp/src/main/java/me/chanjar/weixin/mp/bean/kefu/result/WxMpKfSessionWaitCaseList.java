package me.chanjar.weixin.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 *
 * @author Binary Wang
 *
 */
public class WxMpKfSessionWaitCaseList {
  /**
   * count 未接入会话数量
   */
  @SerializedName("count")
  private Long count;

  /**
   * waitcaselist 未接入会话列表，最多返回100条数据
   */
  @SerializedName("waitcaselist")
  private List<WxMpKfSession> kfSessionWaitCaseList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxMpKfSessionWaitCaseList fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json,
        WxMpKfSessionWaitCaseList.class);
  }

  public List<WxMpKfSession> getKfSessionWaitCaseList() {
    return this.kfSessionWaitCaseList;
  }

  public void setKfSessionWaitCaseList(List<WxMpKfSession> kfSessionWaitCaseList) {
    this.kfSessionWaitCaseList = kfSessionWaitCaseList;
  }

}
