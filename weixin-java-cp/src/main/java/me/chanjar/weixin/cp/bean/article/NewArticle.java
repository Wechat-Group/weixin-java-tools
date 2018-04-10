package me.chanjar.weixin.cp.bean.article;

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
public class NewArticle implements Serializable {
  private static final long serialVersionUID = 4087852055781140659L;

  private String title;
  private String description;
  private String url;
  private String picUrl;

}
