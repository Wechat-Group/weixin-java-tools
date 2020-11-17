package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.external.msg.Image;
import me.chanjar.weixin.cp.bean.external.msg.Link;
import me.chanjar.weixin.cp.bean.external.msg.MiniProgram;
import me.chanjar.weixin.cp.bean.external.msg.Text;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 新客户欢迎语.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-08-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpWelcomeMsg implements Serializable {
  private static final long serialVersionUID = 4170843890468921757L;

  @SerializedName("welcome_code")
  private String welcomeCode;

  private Text text;

  private Image image;

  private Link link;

  private MiniProgram miniprogram;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
