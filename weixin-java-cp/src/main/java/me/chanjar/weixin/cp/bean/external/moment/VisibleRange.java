package me.chanjar.weixin.cp.bean.external.moment;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

@Data
public class VisibleRange implements Serializable {
  @SerializedName("sender_list")
  private SenderList senderList;
  @SerializedName("external_contact_list")
  private ExternalContactList externalContactList;
}
