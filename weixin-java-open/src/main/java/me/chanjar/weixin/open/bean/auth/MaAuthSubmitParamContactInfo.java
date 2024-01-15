package me.chanjar.weixin.open.bean.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * 联系人信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
public class MaAuthSubmitParamContactInfo {

  /**
   * 姓名
   */
  @NotNull
  private String name;

  /**
   * 邮箱
   */
  @NotNull
  private String email;
}
