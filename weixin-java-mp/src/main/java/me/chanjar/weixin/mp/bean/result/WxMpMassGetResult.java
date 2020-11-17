package me.chanjar.weixin.mp.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 查询群发消息发送状态【订阅号与服务号认证后均可用】
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Batch_Sends_and_Originality_Checks.html#%E6%9F%A5%E8%AF%A2%E7%BE%A4%E5%8F%91%E6%B6%88%E6%81%AF%E5%8F%91%E9%80%81%E7%8A%B6%E6%80%81%E3%80%90%E8%AE%A2%E9%98%85%E5%8F%B7%E4%B8%8E%E6%9C%8D%E5%8A%A1%E5%8F%B7%E8%AE%A4%E8%AF%81%E5%90%8E%E5%9D%87%E5%8F%AF%E7%94%A8%E3%80%91
 * @author S <sshzh90@gmail.com>
 */
@Data
public class WxMpMassGetResult implements Serializable {
  private static final long serialVersionUID = -2909694117357278557L;

  @SerializedName("msg_id")
  private Long msgId;

  @SerializedName("msg_status")
  private String msgstatus;

  public static WxMpMassGetResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMassGetResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
