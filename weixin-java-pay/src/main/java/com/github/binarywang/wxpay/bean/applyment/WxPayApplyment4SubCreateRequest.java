package com.github.binarywang.wxpay.bean.applyment;

import com.github.binarywang.wxpay.bean.applyment.enums.*;
import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 特约商户进件 提交申请对象
 *
 * @author zhouyongshen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxPayApplyment4SubCreateRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 业务申请编号
   */
  @SerializedName("business_code")
  private String businessCode;
  /**
   * 超级管理员信息
   */
  @SerializedName("contact_info")
  @SpecEncrypt
  private ContactInfo contactInfo;

  /**
   * 主体资料
   */
  @SerializedName("subject_info")
  @SpecEncrypt
  private SubjectInfo subjectInfo;

  /**
   * 经营资料
   */
  @SerializedName("business_info")
  private BusinessInfo businessInfo;

  /**
   * 结算规则
   */
  @SerializedName("settlement_info")
  private SettlementInfo settlementInfo;

  /**
   * 结算银行账户
   */
  @SerializedName("bank_account_info")
  @SpecEncrypt
  private BankAccountInfo bankAccountInfo;

  /**
   * 结算银行账户
   */
  @SerializedName("addition_info")
  private AdditionInfo additionInfo;

  /**
   * 超级管理员需在开户后进行签约，并接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class ContactInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 超级管理员姓名
     */
    @SerializedName("contact_name")
    @SpecEncrypt
    private String contactName;

    /**
     * 超级管理员身份证件号码
     * 1、“超级管理员身份证号码”与“超级管理员微信openid”，二选一必填。
     * 2、超级管理员签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。
     * 3、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码。
     */
    @SerializedName("contact_id_number")
    @SpecEncrypt
    private String contactIdNumber;

    /**
     * 超级管理员微信openid
     * 1、“超级管理员身份证件号码”与“超级管理员微信openid”，二选一必填。
     * 2、超级管理员签约时，校验微信号是否与该微信openid一致。
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 1、11位数字。
     * 2、用于接收微信支付的重要管理信息及日常操作验证码。
     */
    @SerializedName("mobile_phone")
    @SpecEncrypt
    private String mobilePhone;

    /**
     * 1、用于接收微信支付的开户邮件及日常业务通知。
     * 2、需要带@，遵循邮箱格式校验，该字段需进行加密处理，
     */
    @SerializedName("contact_email")
    @SpecEncrypt
    private String contactEmail;

  }

  /**
   * 主体资料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class SubjectInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主体类型
     */
    @SerializedName("subject_type")
    private SubjectTypeEnum subjectType;

    /**
     * 营业执照
     */
    @SerializedName("business_license_info")
    private BusinessLicenseInfo businessLicenseInfo;
    /**
     * 登记证书
     */
    @SerializedName("certificate_info")
    private CertificateInfo certificateInfo;

    /**
     * 组织机构代码证
     */
    @SerializedName("organization_info")
    private OrganizationInfo organizationInfo;

    /**
     * 单位证明函照片
     */
    @SerializedName("certificate_letter_copy")
    private String certificateLetterCopy;

    /**
     * 经营者/法人身份证件
     */
    @SerializedName("identity_info")
    @SpecEncrypt
    private IdentityInfo identityInfo;

    /**
     * 最终受益人信息(UBO]
     */
    @SerializedName("ubo_info")
    @SpecEncrypt
    private UboInfo uboInfo;

    /**
     * 小微辅助证明材料（subjectType为小微商户时必填）
     */
    @SerializedName("micro_biz_info")
    private MicroBizInfo microBizInfo;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class BusinessLicenseInfo implements Serializable {
      private static final long serialVersionUID = -1016615300418945838L;
      /**
       * 营业执照照片
       */
      @SerializedName("license_copy")
      private String licenseCopy;
      /**
       * 注册号/统一社会信用代码
       */
      @SerializedName("license_number")
      private String licenseNumber;
      /**
       * 商户名称
       */
      @SerializedName("merchant_name")
      private String merchantName;
      /**
       * 个体户经营者/法人姓名
       */
      @SerializedName("legal_person")
      private String legalPerson;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class CertificateInfo implements Serializable {
      private static final long serialVersionUID = 5080675335337916895L;

      /**
       * 登记证书照片
       */
      @SerializedName("cert_copy")
      private String certCopy;

      /**
       * 登记证书类型
       */
      @SerializedName("cert_type")
      private CertTypeEnum certType;


      /**
       * 证书号
       */
      @SerializedName("cert_number")
      private String certNumber;


      /**
       * 商户名称
       */
      @SerializedName("merchant_name")
      private String merchantName;


      /**
       * 注册地址
       */
      @SerializedName("company_address")
      private String companyAddress;


      /**
       * 法人姓名
       */
      @SerializedName("legal_person")
      private String legalPerson;


      /**
       * 有效期限开始日期
       */
      @SerializedName("period_begin")
      private String periodBegin;


      /**
       * 有效期限结束日期
       */
      @SerializedName("period_end")
      private String periodEnd;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class OrganizationInfo implements Serializable {
      private static final long serialVersionUID = 6497045652770046337L;
      /**
       * 组织机构代码证照片
       */
      @SerializedName("organization_copy")
      private String organizationCopy;
      /**
       * 组织机构代码
       */
      @SerializedName("organization_code")
      private String organizationCode;
      /**
       * 组织机构代码证有效期开始日期
       */
      @SerializedName("org_period_begin")
      private String orgPeriodBegin;
      /**
       * 组织机构代码证有效期结束日期
       */
      @SerializedName("org_period_end")
      private String orgPeriodEnd;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class IdentityInfo implements Serializable {
      private static final long serialVersionUID = 1683704338370383827L;

      /**
       * 证件类型
       */
      @SerializedName("id_doc_type")
      private IdTypeEnum idDocType;

      /**
       * 身份证信息
       */
      @SerializedName("id_card_info")
      @SpecEncrypt
      private IdCardInfo idCardInfo;

      /**
       * 其他类型证件信息
       */
      @SerializedName("id_doc_info")
      @SpecEncrypt
      private IdDocInfo idDocInfo;

      /**
       * 经营者/法人是否为受益人
       */
      @SerializedName("owner")
      private boolean owner;

      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class IdCardInfo implements Serializable {
        private static final long serialVersionUID = -2897792705297641786L;
        /**
         * 身份证人像面照片
         */
        @SerializedName("id_card_copy")
        private String idCardCopy;
        /**
         * 身份证国徽面照片
         */
        @SerializedName("id_card_national")
        private String idCardNational;

        /**
         * 身份证姓名
         */
        @SerializedName("id_card_name")
        @SpecEncrypt
        private String idCardName;
        /**
         * 身份证号码
         */
        @SerializedName("id_card_number")
        @SpecEncrypt
        private String idCardNumber;
        /**
         * 身份证有效期开始时间
         */
        @SerializedName("card_period_begin")
        private String cardPeriodBegin;
        /**
         * 身份证有效期结束时间
         */
        @SerializedName("card_period_end")
        private String cardPeriodEnd;

      }

      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class IdDocInfo implements Serializable {
        private static final long serialVersionUID = 7335589815924447719L;
        /**
         * 证件照片
         */
        @SerializedName("id_doc_copy")
        private String idDocCopy;

        /**
         * 证件姓名
         */
        @SerializedName("id_doc_name")
        @SpecEncrypt
        private String idDocName;

        /**
         * 证件号码
         */
        @SerializedName("id_doc_number")
        @SpecEncrypt
        private String idDocNumber;
        /**
         * 证件有效期开始时间
         */
        @SerializedName("doc_period_begin")
        private String docPeriodBegin;
        /**
         * 证件有效期结束时间
         */
        @SerializedName("doc_period_end")
        private String docPeriodEnd;
      }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class UboInfo implements Serializable {
      private static final long serialVersionUID = 7918585690831975042L;
      /**
       * 证件类型
       */
      @SerializedName("id_type")
      private IdTypeEnum idType;
      /**
       * 身份证人像面照片
       */
      @SerializedName("id_card_copy")
      private String idCardCopy;
      /**
       * 身份证国徽面照片
       */
      @SerializedName("id_card_national")
      private String idCardNational;
      /**
       * 证件照片
       */
      @SerializedName("id_doc_copy")
      private String idDocCopy;
      /**
       * 受益人姓名
       */
      @SerializedName("name")
      @SpecEncrypt
      private String name;
      /**
       * 证件号码
       */
      @SerializedName("id_number")
      @SpecEncrypt
      private String idNumber;
      /**
       * 证件有效期开始时间
       */
      @SerializedName("id_period_begin")
      private String idPeriodBegin;
      /**
       * 证件有效期结束时间
       */
      @SerializedName("id_period_end")
      private String idPeriodEnd;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class MicroBizInfo implements Serializable {
      private static final long serialVersionUID = -5679477993681265764L;
      /**
       * 小微经营类型
       */
      @SerializedName("micro_biz_type")
      private MicroBizTypeEnum microBizType;

      /**
       * 门店场所---经营类型为“门店场所”时填写
       */
      @SerializedName("micro_store_info")
      private MicroStoreInfo microStoreInfo;

      /**
       * 经营类型为“流动经营/便民服务”时填写
       */
      @SerializedName("micro_mobile_info")
      private MicroMobileInfo microMobileInfo;

      /**
       * 经营类型为“线上商品/服务交易”时填写
       */
      @SerializedName("micro_online_info")
      private MicroOnlineInfo microOnlineInfo;

      /**
       * 门店场所
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class MicroStoreInfo implements Serializable {
        private static final long serialVersionUID = 5277440587305558389L;
        /**
         * 门店名称
         */
        @SerializedName("micro_name")
        private String microName;
        /**
         * 门店省市编码 填写门店省市编码，只能由数字组成，详细参见《微信支付提供的省市对照表》
         *
         * @see <a href='https://pay.weixin.qq.com/wiki/doc/apiv3/download/%E7%9C%81%E5%B8%82%E5%8C%BA%E7%BC%96%E5%8F%B7%E5%AF%B9%E7%85%A7%E8%A1%A8.xlsx'>下载微信支付提供的省市对照表</a>
         */
        @SerializedName("micro_address_code")
        private String microAddressCode;
        /**
         * 门店地址(填写店铺详细地址，具体区/县及街道门牌号或大厦楼层)
         */
        @SerializedName("micro_address")
        private String microAddress;
        /**
         * 门店门头照片
         * <per>
         * 1、提交门店门口照片，要求招牌清晰可见
         * 2、可上传1张图片，请填写通过《图片上传API》预先上传图片生成好的MediaID
         * </per>
         *
         * @see <a href='https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml'>图片上传API</a>
         */
        @SerializedName("store_entrance_pic")
        private String storeEntrancePic;
        /**
         * 店内环境照片
         * <per>
         * 1、提交店内环境照片
         * 2、可上传1张图片，请填写通过《图片上传API》预先上传图片生成好的MediaID
         * </per>
         *
         * @see <a href='https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml'>图片上传API</a>
         */
        @SerializedName("micro_indoor_copy")
        private String microIndoorCopy;
        /**
         * 门店经度
         */
        @SerializedName("store_longitude")
        private String storeLongitude;
        /**
         * 门店纬度
         */
        @SerializedName("store_latitude")
        private String storeLatitude;
      }

      /**
       * 流动经营/便民服务
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class MicroMobileInfo implements Serializable {
        private static final long serialVersionUID = -1308090894511066935L;
        /**
         * 经营/服务名称
         */
        @SerializedName("micro_mobile_name")
        private String microMobileName;
        /**
         * 经营/服务所在地省市
         */
        @SerializedName("micro_mobile_city")
        private String microMobileCity;
        /**
         * 经营/服务所在地（不含省市) 填写“无"
         */
        @SerializedName("micro_mobile_address")
        private String microMobileAddress;
        /**
         * 经营/服务现场照片
         * <per>
         * 1、提交经营/服务现场照片
         * 2、可上传多张图片，请填写通过《图片上传API》预先上传图片生成好的MediaID
         * </per>
         *
         * @see <a href='https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml'>图片上传API</a>
         */
        @SerializedName("micro_mobile_pics")
        private String microMobilePics;
      }

      /**
       * 线上商品/服务交易
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class MicroOnlineInfo implements Serializable {
        private static final long serialVersionUID = 9029168841403055743L;
        /**
         * 线上店铺名称
         */
        @SerializedName("micro_online_store")
        private String microOnlineStore;
        /**
         * 电商平台名称
         */
        @SerializedName("micro_ec_name")
        private String microEcName;
        /**
         * 店铺二维码
         * <per>
         * 1、店铺二维码或店铺链接二选一必填
         * 2、可上传多张图片，请填写通过《图片上传API》预先上传图片生成好的MediaID
         * </per>
         *
         * @see <a href='https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml'>图片上传API</a>
         */
        @SerializedName("micro_qrcode")
        private String microQrcode;
        /**
         * 店铺二维码
         * <per>
         * 1、店铺二维码或店铺链接二选一必填
         * 2、请填写店铺主页链接，需符合网站规范
         * </per>
         */
        @SerializedName("micro_link")
        private String microLink;
      }
    }
  }

  /**
   * 经营资料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class BusinessInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商户简称
     */
    @SerializedName("merchant_shortname")
    private String merchantShortname;

    /**
     * 客服电话
     */
    @SerializedName("service_phone")
    private String servicePhone;

    /**
     * 经营场景
     */
    @SerializedName("sales_info")
    private SalesInfo salesInfo;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class SalesInfo implements Serializable {
      private static final long serialVersionUID = 6428044729204137659L;
      /**
       * 经营场景类型
       */
      @SerializedName("sales_scenes_type")
      private List<SalesScenesTypeEnum> salesScenesType;

      /**
       * 线下门店场景
       */
      @SerializedName("biz_store_info")
      private BizStoreInfo bizStoreInfo;

      /**
       * 公众号场景
       */
      @SerializedName("mp_info")
      private MpInfo mpInfo;

      /**
       * 小程序场景
       */
      @SerializedName("mini_program_info")
      private MiniProgramInfo miniProgramInfo;

      /**
       * APP场景
       */
      @SerializedName("app_info")
      private AppInfo appInfo;

      /**
       * 互联网网站场景
       */
      @SerializedName("web_info")
      private WebInfo webInfo;

      /**
       * 企业微信场景
       */
      @SerializedName("wework_info")
      private WeworkInfo weworkInfo;

      /**
       * 线下门店场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class BizStoreInfo implements Serializable {
        private static final long serialVersionUID = 4397253725912709093L;
        /**
         * 门店名称
         */
        @SerializedName("biz_store_name")
        private String bizStoreName;

        /**
         * 门店省市编码
         */
        @SerializedName("biz_address_code")
        private String bizAddressCode;

        /**
         * 门店地址
         */
        @SerializedName("biz_store_address")
        private String bizStoreAddress;

        /**
         * 门店门头照片
         */
        @SerializedName("store_entrance_pic")
        private List<String> storeEntrancePic;

        /**
         * 店内环境照片
         */
        @SerializedName("indoor_pic")
        private List<String> indoorPic;

        /**
         * 线下场所对应的商家APPID
         */
        @SerializedName("biz_sub_appid")
        private String bizSubAppid;

      }

      /**
       * 公众号场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class MpInfo implements Serializable {
        private static final long serialVersionUID = 167582552189873597L;
        /**
         * 服务商公众号APPID
         */
        @SerializedName("mp_appid")
        private String mpAppid;

        /**
         * 商家公众号APPID
         */
        @SerializedName("mp_sub_appid")
        private String mpSubAppid;

        /**
         * 公众号页面截图
         */
        @SerializedName("mp_pics")
        private List<String> mpPics;

      }

      /**
       * 小程序场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class MiniProgramInfo implements Serializable {
        private static final long serialVersionUID = -371749335686796436L;
        /**
         * 服务商小程序APPID
         */
        @SerializedName("mini_program_appid")
        private String miniProgramAppid;

        /**
         * 商家小程序APPID
         */
        @SerializedName("mini_program_sub_appid")
        private String miniProgramSubAppid;

        /**
         * 小程序截图
         */
        @SerializedName("mini_program_pics")
        private List<String> miniProgramPics;


      }

      /**
       * APP场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class AppInfo implements Serializable {
        private static final long serialVersionUID = 3959643687528770473L;
        /**
         * 服务商应用APPID
         */
        @SerializedName("app_appid")
        private String appAppid;

        /**
         * 商家应用APPID
         */
        @SerializedName("app_sub_appid")
        private String appSubAppid;

        /**
         * APP截图
         */
        @SerializedName("app_pics")
        private List<String> appPics;

      }

      /**
       * 互联网网站场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class WebInfo implements Serializable {
        private static final long serialVersionUID = -4183874827185822310L;
        /**
         * 互联网网站域名
         */
        @SerializedName("domain")
        private String domain;

        /**
         * 网站授权函
         */
        @SerializedName("web_authorisation")
        private String webAuthorisation;

        /**
         * 互联网网站对应的商家APPID
         */
        @SerializedName("web_appid")
        private String webAppid;

      }

      /**
       * 企业微信场景
       */
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @Accessors(chain = true)
      public static class WeworkInfo implements Serializable {
        private static final long serialVersionUID = 9075531305717309383L;
        /**
         * 商家企业微信CorpID
         */
        @SerializedName("sub_corp_id")
        private String subCorpId;

        /**
         * 企业微信页面截图
         */
        @SerializedName("wework_pics")
        private List<String> weworkPics;

      }
    }
  }

  /**
   * 结算规则
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class SettlementInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 入驻结算规则ID
     */
    @SerializedName("settlement_id")
    private String settlementId;

    /**
     * 所属行业
     */
    @SerializedName("qualification_type")
    private String qualificationType;

    /**
     * 特殊资质图片
     */
    @SerializedName("qualifications")
    private List<String> qualifications;

    /**
     * 优惠费率活动ID
     */
    @SerializedName("activities_id")
    private String activitiesId;

    /**
     * 优惠费率活动值
     */
    @SerializedName("activities_rate")
    private String activitiesRate;

    /**
     * 优惠费率活动补充材料
     */
    @SerializedName("activities_additions")
    private List<String> activitiesAdditions;

  }

  /**
   * 结算银行账户
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class BankAccountInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账户类型
     */
    @SerializedName("bank_account_type")
    private BankAccountTypeEnum bankAccountType;

    /**
     * 开户名称
     */
    @SerializedName("account_name")
    @SpecEncrypt
    private String accountName;

    /**
     * 开户银行
     */
    @SerializedName("account_bank")
    private String accountBank;

    /**
     * 开户银行省市编码
     */
    @SerializedName("bank_address_code")
    private String bankAddressCode;

    /**
     * 开户银行联行号
     */
    @SerializedName("bank_branch_id")
    private String bankBranchId;

    /**
     * 开户银行全称（含支行]
     */
    @SerializedName("bank_name")
    private String bankName;

    /**
     * 银行账号
     */
    @SerializedName("account_number")
    @SpecEncrypt
    private String accountNumber;

  }


  /**
   * 补充材料
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class AdditionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 法人开户承诺函
     */
    @SerializedName("legal_person_commitment")
    private String legalPersonCommitment;

    /**
     * 法人开户意愿视频
     */
    @SerializedName("legal_person_video")
    private String legalPersonVideo;

    /**
     * 补充材料
     */
    @SerializedName("business_addition_pics")
    private List<String> businessAdditionPics;

    /**
     * 补充说明
     */
    @SerializedName("business_addition_msg")
    private String businessAdditionMsg;

  }

}
