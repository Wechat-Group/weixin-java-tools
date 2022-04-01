package me.chanjar.weixin.cp.bean.external.moment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Boris
 */
@Data
public class VisibleRange implements Serializable {
  private static final long serialVersionUID = 5356285705365931051L;

  @SerializedName("sender_list")
  private SenderList senderList;
  @SerializedName("external_contact_list")
  private ExternalContactList externalContactList;
}
