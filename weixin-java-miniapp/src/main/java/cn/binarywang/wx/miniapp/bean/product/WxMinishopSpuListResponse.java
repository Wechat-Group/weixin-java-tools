package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/20 4:46 下午
 */
@Data
public class WxMinishopSpuListResponse extends WxMinishopResult {
  @SerializedName("total_num")
  private Long totalNum;

  private List<WxMinishopSpu> spus;
}
