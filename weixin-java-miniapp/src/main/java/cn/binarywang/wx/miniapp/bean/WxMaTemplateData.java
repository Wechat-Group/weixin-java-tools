package cn.binarywang.wx.miniapp.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 参考文档 https://developers.weixin.qq.com/miniprogram/dev/api-backend/templateMessage.send.html
 * Created by Binary Wang on 2018/9/23.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@NoArgsConstructor
public class WxMaTemplateData implements Serializable {
  private static final long serialVersionUID = 855214313056578490L;

  private String name;
  private String value;
  private String color;

  public WxMaTemplateData(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public WxMaTemplateData(String name, String value, String color) {
    this.name = name;
    this.value = value;
    this.color = color;
  }


}
