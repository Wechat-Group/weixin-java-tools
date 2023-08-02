package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mario Luo
 */
@Data
public class InvoiceAuthPageSetting implements Serializable {
  private static final long serialVersionUID = 4585269585619597753L;

  private AuthField authField;

  @Data
  public static class AuthField implements Serializable {
    private static final long serialVersionUID = 7341329271546930795L;

    private UserField userField;
    private BizField bizField;
  }

  @Data
  public static class UserField implements Serializable {
    private static final long serialVersionUID = -128178697394854697L;

    private Integer showTitle;
    private Integer showPhone;
    private Integer showEmail;
    private Integer requirePhone;
    private Integer requireEmail;
    private List<InvoiceAuthDataResult.KeyValuePair> customField;
  }

  @Data
  public static class BizField implements Serializable {
    private static final long serialVersionUID = -8277885344416192644L;

    private Integer showTitle;
    private Integer showTaxNo;
    private Integer showAddr;
    private Integer showPhone;
    private Integer showBankType;
    private Integer showBankNo;

    private Integer requireTaxNo;
    private Integer requireAddr;
    private Integer requirePhone;
    private Integer requireBankType;
    private Integer requireBankNo;
    private List<InvoiceAuthDataResult.KeyValuePair> customField;
  }

  @Data
  public static class CustomField implements Serializable {
    private static final long serialVersionUID = -3838241240210071209L;

    /**
     * 字段名
     */
    private String key;
    /**
     * 0：否，1：是， 默认为0
     */
    private Integer isRequire;
    /**
     * 提示文案
     */
    private String notice;
  }
}
