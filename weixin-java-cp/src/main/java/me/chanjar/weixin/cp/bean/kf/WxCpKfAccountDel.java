package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 删除客服帐号-请求参数
 *
 * @author Fu  created on  2022/1/19 19:09
 */
@NoArgsConstructor
@Data
public class WxCpKfAccountDel implements Serializable {

  private static final long serialVersionUID = 1997221467585676772L;

  /**
   * 客服帐号ID。
   * 不多于64字节
   */
  @SerializedName("open_kfid")
  private String openKfid;

}
