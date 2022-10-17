package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Wx cp kf business card msg.
 *
 * @author leiin  created on  2022/1/26 5:35 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfBusinessCardMsg {
  @SerializedName("userid")
  private String userId;
}
