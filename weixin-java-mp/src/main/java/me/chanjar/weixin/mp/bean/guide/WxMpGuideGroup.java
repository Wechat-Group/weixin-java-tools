package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 顾问分组信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
public class WxMpGuideGroup implements ToJson, Serializable {
  private static final long serialVersionUID = 6235142804489175294L;

  /**
   * 顾问分组id
   */
  @SerializedName("id")
  private Long id;

  /**
   * 顾问分组名称
   */
  @SerializedName("name")
  private String name;

  /**
   * 创建时间戳
   */
  @SerializedName("create_time")
  private Long createTime;

  /**
   * 更新时间戳
   */
  @SerializedName("update_time")
  private Long updateTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideGroup fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideGroup.class);
  }
}
