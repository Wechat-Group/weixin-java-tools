/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序域名
 *
 * @author lfj
 */
@Data
public class WxOpenWxaDomain implements Serializable {

  private static final long serialVersionUID = 7251971297859021317L;

  private List<String> requestdomain;// request合法域名，当action参数是get时不需要此字段。
  private List<String> wsrequestdomain;// socket合法域名，当action参数是get时不需要此字段。
  private List<String> uploaddomain;// uploadFile合法域名，当action参数是get时不需要此字段。
  private List<String> downloaddomain;// downloadFile合法域名，当action参数是get时不需要此字段。

  public static WxOpenWxaDomain fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaDomain.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
