package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 企业微信打卡规则.
 *
 * @author Element  created on  2019-04-06 13:22
 */
@Data
public class WxCpCheckinOption implements Serializable {
  private static final long serialVersionUID = -1964233697990417482L;

  @SerializedName("userid")
  private String userId;

  @SerializedName("group")
  private WxCpCheckinGroupBase group;
}
