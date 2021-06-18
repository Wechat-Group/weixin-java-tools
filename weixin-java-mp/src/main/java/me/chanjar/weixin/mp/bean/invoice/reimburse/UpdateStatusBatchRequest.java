package me.chanjar.weixin.mp.bean.invoice.reimburse;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 批量更新发票状态参数对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusBatchRequest implements Serializable {

  private static final long serialVersionUID = 7016357689566912199L;
  /**
   * 用户openid
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  private String openid;

  /**
   * 发票报销状态
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("reimburse_status")
  private String reimburseStatus;

  /**
   * 发票列表
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("invoice_list")
  private List<InvoiceInfoRequest> invoiceList;


  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
