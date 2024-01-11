package me.chanjar.weixin.cp.bean.workbench;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Hugo
 * <pre>
 *  获取用户二次验证信息的结果类
 * </pre>
 * <p>
 * <a href="https://developer.work.weixin.qq.com/document/path/99499">文档</a>
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxCpSecondVerificationInfo implements Serializable {
  private static final long serialVersionUID = -4301564507150486556L;

  private String userId;

  @SerializedName("tfa_code")
  private String tfaCode;
}
