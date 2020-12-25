package me.chanjar.weixin.qidian.bean.marketing;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxMpAdLeadResult implements Serializable {
  private static final long serialVersionUID = -1526796632563660821L;

  @SerializedName("page_info")
  private WxMpAdLeadPageInfo pageInfo;
  @SerializedName("list")
  private List<WxMpAdLead> adLeads;

  public static WxMpAdLeadResult fromJson(String json) {

    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(json).get("data"),
      new TypeToken<WxMpAdLeadResult>() {
      }.getType());
  }
}
