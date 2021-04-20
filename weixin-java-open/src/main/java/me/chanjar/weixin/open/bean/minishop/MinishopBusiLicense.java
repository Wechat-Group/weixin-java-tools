package me.chanjar.weixin.open.bean.minishop;


import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 企业上传的营业执照信息
 * </p>
 *
 * @author luowentao
 * @since 2021-01-27
 */
@Data
@Accessors(chain = true)
public class MinishopBusiLicense implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 营业执照相关信息
     */
    private Integer busiLicenseId;

    /**
     * 营业执照类型 1:三证合一 2: 普通营业执照
     */
    private Integer licenseType;

    /**
     * 营业执照图片
     */
    private MinishopPicFile picFile;


  /**
   * 营业执照图片url
   */
    private String picFileUrl;

  /**
   * 请填写营业执照上的注册号/统一社会信用代码，
须为15位数字或18位数字大写字母。
示例值：123456789012345678 特殊规则：长度最小15个字节
     */
    private String registrationNum;

    /**
     * 1、请填写营业执照/登记证书的商家名称，2~110个字符，支持括号
2、个体工商户/党政、机关及事业单位，不能以“公司”结尾。
3、个体工商户，若营业执照上商户名称为空或为“无”，请填写"个体户+经营者姓名"，
如“个体户张三” 。示例值：腾讯科技有限公司
     */
    private String merchantName;

    /**
     * 请填写证件的经营者/法定代表人姓名。示例值：张三
     */
    private String legalRepresentative;

    /**
     * 注册地址
     */
    private String registeredAddrs;

    /**
     * 注册日期，格式：2014-01-01
     */
    private String startDate;

    /**
     * 结束有效期，格式：2014-01-01
1、若证件有效期为长期，请填写：长期。
2、结束时间需大于开始时间。
3、有效期必须大于60天，即结束时间距当前时间需超过60天。
     */
    private String endDate;


    public JsonObject toJsonObject() {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("license_type", this.licenseType);
      jsonObject.add("pic_file", picFile.toJsonObject());
      jsonObject.addProperty("registration_num", registrationNum);
      jsonObject.addProperty("merchant_name", merchantName);
      jsonObject.addProperty("legal_representative", legalRepresentative);
      if (registeredAddrs != null) {
        jsonObject.addProperty("registered_addrs", registeredAddrs);
      }
      jsonObject.addProperty("start_date", startDate);
      jsonObject.addProperty("end_date", endDate);
      return jsonObject;
    }
}
