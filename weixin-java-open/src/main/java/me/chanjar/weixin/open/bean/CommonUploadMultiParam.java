package me.chanjar.weixin.open.bean;

import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.common.bean.CommonUploadParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author xzh
 * @Description
 * @createTime 2024/08/14 16:20
 */
@Data
@Builder
public class CommonUploadMultiParam implements Serializable {
  private static final long serialVersionUID = -7935687108401829869L;

  private List<NormalParam> normalParams;

  private CommonUploadParam uploadParam;

  @Data
  @Builder
  public static class NormalParam implements Serializable {
    private static final long serialVersionUID = 4345164516827726194L;

    /**
     * 参数名称(非文件名)，如：type
     */
    private String name;

    /**
     * 参数名称对应值，如：image
     */
    private String value;
  }
}
