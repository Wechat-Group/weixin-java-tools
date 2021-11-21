package me.chanjar.weixin.cp.bean.external.moment;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerItem {
  @SerializedName("external_userid")
  private String externalUserId;
  @SerializedName("userid")
  private String userId;
}
