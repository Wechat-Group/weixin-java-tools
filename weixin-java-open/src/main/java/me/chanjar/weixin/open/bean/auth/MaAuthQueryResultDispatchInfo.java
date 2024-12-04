package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * 小程序认证 查询操作 响应数据 - 审核提供商分配信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
public class MaAuthQueryResultDispatchInfo {

  /**
   * 提供商，如：上海倍通企业信用征信有限公司
   */
  @NotNull
  private String provider;

  /**
   * 联系方式，如：咨询电话：0411-84947888，咨询时间：周一至周五（工作日）8：30-17：30
   */
  @NotNull
  private String contact;

  /**
   * 派遣时间戳(秒)，如：1704952913
   */
  @NotNull
  @SerializedName("dispatch_time")
  private Long dispatchTime;
}
