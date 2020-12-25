package me.chanjar.weixin.qidian.bean.dial;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

@Data
public class IVRDialRequest implements Serializable {
  private static final long serialVersionUID = -5552935329136465927L;

  private String phone_number;
  private String ivr_id;
  private List<String> corp_phone_list;
  private Integer loc_pref_on = 1;
  private List<String> backup_corp_phone_list;
  private Boolean skip_restrict = false;

  @Override
  public String toString() {
    return this.toJson();
  }

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }
}
