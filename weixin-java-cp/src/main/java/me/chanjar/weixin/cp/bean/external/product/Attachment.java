package me.chanjar.weixin.cp.bean.external.product;

import java.io.Serializable;
import lombok.Data;
import me.chanjar.weixin.cp.constant.WxCpConsts;

/**
 * 商品画册附件
 *
 * @author <a href="https://github.com/Loading-Life">Lo_ading</a>
 */
@Data
public class Attachment implements Serializable {

  private static final long serialVersionUID = -4545283630169056262L;

  /**
   * 附件类型，目前仅支持image
   */
  private String type = WxCpConsts.ProductAttachmentType.IMAGE ;

  private Image image;

}
