package me.chanjar.weixin.cp.bean.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 *  Created by BinaryWang on 2017/3/27.
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewArticle implements Serializable {
  private static final long serialVersionUID = 4087852055781140659L;
  /**
   * 标题，不超过128个字节，超过会自动截断
   */
  private String title;
  /**
   * 描述，不超过512个字节，超过会自动截断
   */
  private String description;
  /**
   * 点击后跳转的链接。
   */
  private String url;
  /**
   * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图1068*455，小图150*150。
   */
  private String picUrl;

  /**
   * 按钮文字，仅在图文数为1条时才生效。 默认为“阅读全文”， 不超过4个文字，超过自动截断。该设置只在企业微信上生效，微工作台（原企业号）上不生效。
   */
  private String btnText;
}
