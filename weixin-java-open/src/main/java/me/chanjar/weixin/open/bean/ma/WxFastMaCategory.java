package me.chanjar.weixin.open.bean.ma;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 修改更新类目所需实体.
 *
 * @author Hipple
 * @since 2019/1/25 10:49
 */
@Data
public class WxFastMaCategory implements Serializable {
  private static final long serialVersionUID = 1595589596066509155L;

  /**
   * 一级类目ID.
   */
  private int first;

  /**
   * 二级类目ID.
   */
  private int second;

  /**
   * 资质信息.
   */
  private List<Certificate> certicates;

  @Data
  public static class Certificate {
    private String key;
    private String value;
  }
}
