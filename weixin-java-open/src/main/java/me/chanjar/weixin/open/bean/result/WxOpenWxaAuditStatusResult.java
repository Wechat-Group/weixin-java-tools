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
public class WxOpenWxaAuditStatusResult implements Serializable {

  private static final long serialVersionUID = 3110949395745109006L;

  private Long auditid;// 最新的审核ID
  private Integer status;// 审核状态，其中0为审核成功，1为审核失败，2为审核中
  private String reason;// 当status=1，审核被拒绝时，返回的拒绝原因

  public static WxOpenWxaAuditStatusResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaAuditStatusResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
