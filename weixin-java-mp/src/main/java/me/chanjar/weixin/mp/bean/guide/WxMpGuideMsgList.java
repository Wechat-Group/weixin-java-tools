package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 顾问聊天记录列表
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/7/007
 */

@Data
public class WxMpGuideMsgList implements Serializable {
  private static final long serialVersionUID = -4041549590019624417L;

  /**
   * 顾问聊天记录总数量
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 顾问聊天记录列表
   */
  @SerializedName("msg_list")
  private List<WxMpGuideMsg> msgList;

  public static WxMpGuideMsgList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideMsgList.class);
  }
}
