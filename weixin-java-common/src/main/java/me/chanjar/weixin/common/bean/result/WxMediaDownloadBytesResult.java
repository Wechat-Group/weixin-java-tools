package me.chanjar.weixin.common.bean.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Daniel Qian
 */
@Data
@Builder
public class WxMediaDownloadBytesResult implements Serializable {

  private static final long serialVersionUID = -3299530194125570200L;

  private byte[] bytes;

  /**
   * 文件后缀名
   */
  private String extension;

  /**
   * 文件名,不包含后缀
   */
  private String baseName;

  /**
   * 文件全名,包含后缀
   */
  private String fileName;

}
