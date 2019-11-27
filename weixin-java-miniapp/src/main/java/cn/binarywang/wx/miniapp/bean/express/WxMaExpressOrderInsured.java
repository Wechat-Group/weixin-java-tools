package cn.binarywang.wx.miniapp.bean.express;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 保价信息对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressOrderInsured implements Serializable {

  private static final long serialVersionUID = -8636857630937445422L;

  /**
   * 是否保价
   * <pre>
   * 是否必填： 是
   * 描述： 0 表示不保价，1 表示保价
   * </pre>
   */
  @SerializedName("use_insured")
  private Integer useInsured;

  /**
   * 保价金额
   * <pre>
   * 是否必填： 是
   * 描述： 单位是分，比如: 10000 表示 100 元
   * </pre>
   */
  @SerializedName("insured_value")
  private Integer insuredValue;


  public  WxMaExpressOrderInsured(){
    this.insuredValue = 0;
    this.useInsured = 0;
  }
}
