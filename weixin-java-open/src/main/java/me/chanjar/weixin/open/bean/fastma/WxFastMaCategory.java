package me.chanjar.weixin.open.bean.fastma;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hipple
 * @description 修改更新类目所需实体
 * @since 2019/1/25 10:49
 */
@Data
public class WxFastMaCategory implements Serializable {

  /**
   * 一级类目ID
   */
  private int first;

  /**
   * 二级类目ID
   */
  private int second;

  /**
   * 资质信息
   */
  private List<certicaty> certicates;

  @Data
  public static class certicaty {
    private String key;
    private String value;
  }
}
