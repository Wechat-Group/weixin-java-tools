package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 顾问的客户列表
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/11/011
 */
@Data
public class WxMpGuideBuyerInfoList implements Serializable {
  private static final long serialVersionUID = 9094928050460133322L;

  /**
   * 客户总数量
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 客户列表
   */
  private List<WxMpGuideBuyerInfo> list;

  public static WxMpGuideBuyerInfoList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideBuyerInfoList.class);
  }
}
