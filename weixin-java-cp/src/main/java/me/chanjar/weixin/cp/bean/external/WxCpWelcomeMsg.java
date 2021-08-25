package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.bean.external.msg.Text;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 新客户欢迎语.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang Create on 2020-08-16</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxCpWelcomeMsg implements Serializable {
  private static final long serialVersionUID = 4170843890468921757L;

  @SerializedName("welcome_code")
  private String welcomeCode;

  @SerializedName("text")
  private Text text;

  @SerializedName("attachments")
  private List<Attachment> attachments;

  @Builder
  public WxCpWelcomeMsg(String welcomeCode, String text, Attachment... attachments) {
    this.welcomeCode = welcomeCode;
    this.text = new Text(text);
    this.attachments = Arrays.asList(attachments);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
