package me.chanjar.weixin.cp.bean.license.order;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseBaseAccount;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 创建下单续期帐号任务
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95646
 *
 * @author Totoro  created on  2022/6/27 11:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseRenewOrderJobRequest implements Serializable {

  private static final long serialVersionUID = 8074896339359557034L;
  /**
   * 对应的企业ID
   */
  @SerializedName("corpid")
  private String corpId;
  /**
   * 续费的用户UserId
   */
  @SerializedName("account_list")
  private List<WxCpTpLicenseBaseAccount> accountList;
  /**
   * 任务id，若不传则默认创建一个新任务。若指定第一次调用后拿到jobid，可以通过该接口将jobid关联多个userid
   */
  @SerializedName("jobid")
  private String jobId;


  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }


}
