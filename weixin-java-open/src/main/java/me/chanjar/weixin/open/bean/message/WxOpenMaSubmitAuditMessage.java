package me.chanjar.weixin.open.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.ma.WxOpenMaSubmitAudit;

import java.io.Serializable;
import java.util.List;

/**
 * 微信小程序代码包提交审核(仅供第三方开发者代小程序调用)
 *
 * @author yqx
 * @date 2018/9/13
 */
@Data
public class WxOpenMaSubmitAuditMessage implements Serializable {

  @SerializedName("item_list")
  private List<WxOpenMaSubmitAudit> itemList;
}
