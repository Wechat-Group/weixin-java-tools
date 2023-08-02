package me.chanjar.weixin.cp.bean.external.moment;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type External contact list.
 */
@Getter
@Setter
public class ExternalContactList {
  @SerializedName("tag_list")
  private List<String> tagList;
}
