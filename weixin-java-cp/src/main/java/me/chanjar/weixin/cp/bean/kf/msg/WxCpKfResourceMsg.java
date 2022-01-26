package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * @date 2022/1/26 5:31 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfResourceMsg {
  @SerializedName("media_id")
  private String mediaId;
}
