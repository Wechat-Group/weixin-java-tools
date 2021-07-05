package me.chanjar.weixin.open.bean.minishop;


import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author luowentao
 * @since 2021-01-27
 */
@Data
@Accessors(chain = true)
public class MinishopSuperAdministratorInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 店铺管理员ID
   */
  private Integer superAdminInfoId;

  /**
   * 个体工商户/企业/党政、机关及事业单位/其他组织，可选择：65-法人/经营者、66- 负责人。
   * （负责人：经商户授权办理微信支付业务的人员，授权范围包括但不限于签约，入驻过程需完成账户验证）。
   * 示例值：65
   */
  private String type;

  /**
   * 1、若管理员类型为“法人”，则该姓名需与法人身份证姓名一致。
   * 2、若管理员类型为“经办人”，则可填写实际经办人的姓名。
   */
  private String name;

  /**
   * 1、若管理员类型为法人，则该身份证号码需与法人身份证号码一致。若管理员类型为经办人，
   * 则可填写实际经办人的身份证号码。
   * 2、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码。
   * 3、超级管理员签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。
   */
  private String idCardNumber;

  /**
   * 请填写管理员的手机号，11位数字， 用于接收微信支付的重要管理信息及日常操作验证码 。
   */
  private String phone;

  /**
   * 1、用于接收微信支付的开户邮件及日常业务通知。
   * 2、需要带@，遵循邮箱格式校验 。
   */
  private String mail;


  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("type", type);
    jsonObject.addProperty("name", name);
    jsonObject.addProperty("id_card_number", idCardNumber);
    jsonObject.addProperty("phone", phone);
    jsonObject.addProperty("mail", mail);
    return jsonObject;
  }
}
