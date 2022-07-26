package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 批量删除学生请求.
 *
 * @author Wang_Wong
 * @date 2022-07-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpBatchDeleteStudentRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("useridlist")
  private List<String> userIdList;

  public static WxCpBatchDeleteStudentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBatchDeleteStudentRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
