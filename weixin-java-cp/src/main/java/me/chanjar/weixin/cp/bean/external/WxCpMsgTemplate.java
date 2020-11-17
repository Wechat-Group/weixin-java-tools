package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.external.msg.Image;
import me.chanjar.weixin.cp.bean.external.msg.Link;
import me.chanjar.weixin.cp.bean.external.msg.MiniProgram;
import me.chanjar.weixin.cp.bean.external.msg.Text;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 企业群发消息任务
 * <p>
 * Created by songfan on 2020/7/14.
 *
 * @author songfan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpMsgTemplate implements Serializable {
  private static final long serialVersionUID = 3172331565173474358L;

  @SerializedName("chat_type")
  private String chatType;

  @SerializedName("external_userid")
  private List<String> externalUserid;

  private String sender;

  private Text text;

  private Image image;

  private Link link;

  private MiniProgram miniprogram;

  public static WxCpMsgTemplate fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMsgTemplate.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
