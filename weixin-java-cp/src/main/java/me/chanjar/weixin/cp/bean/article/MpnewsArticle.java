package me.chanjar.weixin.cp.bean.article;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 *  Created by BinaryWang on 2017/3/27.
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@Builder(builderMethodName = "newBuilder")
public class MpnewsArticle implements Serializable {
  private static final long serialVersionUID = 6985871812170756481L;

  private String title;
  private String thumbMediaId;
  private String author;
  private String contentSourceUrl;
  private String content;
  private String digest;
  private String showCoverPic;

}
