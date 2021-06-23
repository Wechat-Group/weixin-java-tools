package cn.binarywang.wx.miniapp.bean.invoice.reimburse;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 批量查询报销发票信息参数对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceBatchRequest implements Serializable {

  private static final long serialVersionUID = -9121443117105107231L;

  /**
   * 发票卡券的card_id
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("item_list")
  private List<InvoiceInfoRequest> itemList;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
