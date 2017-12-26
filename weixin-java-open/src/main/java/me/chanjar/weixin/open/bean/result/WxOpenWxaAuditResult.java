/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * @author lfj
 */
@Data
public class WxOpenWxaAuditResult implements Serializable {

  private static final long serialVersionUID = 6433216513169781611L;

  private Long auditid;// 审核编号

  public static WxOpenWxaAuditResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaAuditResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
