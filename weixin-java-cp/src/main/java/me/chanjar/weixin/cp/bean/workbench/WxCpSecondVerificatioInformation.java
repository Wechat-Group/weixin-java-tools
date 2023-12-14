package me.chanjar.weixin.cp.bean.workbench;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Hugo
 * <pre>
 *  获取用户二次验证信息的结果类
 * </pre>
 * <p>
 * 文档1：https://developer.work.weixin.qq.com/document/path/99499
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxCpSecondVerificatioInformation {
  private static final long serialVersionUID = -4301564507150486556L;

  private String userId;
  private String tfa_code;
}
