package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * 统计接口的共用属性类
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016/8/25.
 */
public class WxDataCubeBaseResult {
  /**
   * ref_date
   * 数据的日期，需在begin_date和end_date之间
   */
  @SerializedName("ref_date")
  private String refDate;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getRefDate() {
    return this.refDate;
  }

  public void setRefDate(String refDate) {
    this.refDate = refDate;
  }

}
