package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 企业法人认证需要的信息
 *
 * @author lg
 * created on  2023/12/19
 */
@Data
public class WxMaVerifyBetaWeappVerifyInfo implements Serializable {
  private static final long serialVersionUID = 2128265093276395400L;

  /**
   * 企业名（需与工商部门登记信息一致)；如果是“无主体名称个体工商户”则填“个体户+法人姓名”，例如“个体户张三”
   */
  @SerializedName("enterprise_name")
  private String enterpriseName;

  /**
   * 企业代码
   */
  private String code;

  /**
   * 企业代码类型 1：统一社会信用代码（18 位） 2：组织机构代码（9 位 xxxxxxxx-x） 3：营业执照注册号(15 位)
   */
  @SerializedName("code_type")
  private Integer codeType;

  /**
   * 法人微信号
   */
  @SerializedName("legal_persona_wechat")
  private String legalPersonaWechat;

  /**
   * 法人姓名（绑定银行卡）
   */
  @SerializedName("legal_persona_name")
  private String legalPersonaName;

  /**
   * 第三方联系电话
   */
  @SerializedName("component_phone")
  private String componentPhone;

  /**
   * 法人身份证号
   */
  @SerializedName("legal_persona_idcard")
  private String legalPersonaIdcard;
}
