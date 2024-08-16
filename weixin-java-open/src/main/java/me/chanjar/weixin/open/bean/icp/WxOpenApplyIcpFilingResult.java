package me.chanjar.weixin.open.bean.icp;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;


/**
 * @author xzh
 * @Description 申请小程序备案
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenApplyIcpFilingResult extends WxOpenResult {
  private static final long serialVersionUID = 9215343492997218227L;

  /**
   * 错误提示
   */
  @SerializedName("hints")
  private List<Hint> hints;

  @Data
  @EqualsAndHashCode(callSuper = true)
  public static class Hint extends WxOpenResult {

    private static final long serialVersionUID = 6585787444231217123L;

    /**
     * 校验失败的字段
     */
    @SerializedName("err_field")
    private String errField;
  }
}
