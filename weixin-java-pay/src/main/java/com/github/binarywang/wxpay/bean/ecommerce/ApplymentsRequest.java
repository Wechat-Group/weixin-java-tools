package com.github.binarywang.wxpay.bean.ecommerce;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * <pre>
 * 电商平台，可使用该接口，帮助其二级商户进件成为微信支付商户。
 * 文档地址:https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_1.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class ApplymentsRequest implements Serializable {
  private static final long serialVersionUID = -3092662029966103592L;
  /**
   * <pre>
   * 字段名：业务申请编号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string(124)
   * 描述：
   *  1、服务商自定义的商户唯一编号。
   *  2、每个编号对应一个申请单，每个申请单审核通过后会生成一个微信支付商户号。
   *  3、若申请单被驳回，可填写相同的“业务申请编号”，即可覆盖修改原申请单信息 。
   *  示例值：APPLYMENT_00000000001
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：主体类型
   * 变量名：organization_type
   * 是否必填：是
   * 类型：string(4)
   * 描述：
   *  枚举值：
   *  2401：小微商户，指无营业执照的商户。
   *  4：个体工商户，营业执照上的主体类型一般为个体户、个体工商户、个体经营。
   *  2：企业，营业执照上的主体类型一般为有限公司、有限责任公司。
   *  3：党政、机关及事业单位，包括国内各级、各类政府机构、事业单位等（如：公安、党团、司法、交通、旅游、工商税务、市政、医疗、教育、学校等机构）。
   *  1708：其他组织，不属于企业、政府/事业单位的组织机构（如社会团体、民办非企业、基金会），要求机构已办理组织机构代码证。
   *  示例值：2401
   * </pre>
   */
  @SerializedName(value = "organization_type")
  private String organizationType;

  /**
   * <pre>
   * 字段名：+营业执照/登记证书信息
   * 变量名：business_license_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：
   *  1、主体为“小微”时，不填。
   *  2、主体为“个体工商户/企业”时，请上传营业执照。
   *  3、主体为“党政、机关及事业单位/其他组织”时，请上传登记证书。
   * </pre>
   */
  @SerializedName(value = "business_license_info")
  private BusinessLicenseInfo businessLicenseInfo;

  /**
   * <pre>
   * 字段名：+组织机构代码证信息
   * 变量名：organization_cert_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：主体为企业/党政、机关及事业单位/其他组织，且证件号码不是18位时必填。
   * </pre>
   */
  @SerializedName(value = "organization_cert_info")
  private OrganizationCertInfo organizationCertInfo;

  /**
   * <pre>
   * 字段名：经营者/法人证件类型
   * 变量名：id_doc_type
   * 是否必填：否
   * 类型：string(64)
   * 描述：
   *  1、主体为“小微”，只可选择：身份证。
   *  2、主体为“个体户/企业/党政、机关及事业单位/其他组织”，可选择：任一证件类型。
   *  3、若没有填写，系统默认选择：身份证。
   *  枚举值:
   *  IDENTIFICATION_TYPE_MAINLAND_IDCARD：中国大陆居民-身份证
   *  IDENTIFICATION_TYPE_OVERSEA_PASSPORT：其他国家或地区居民-护照
   *  IDENTIFICATION_TYPE_HONGKONG：中国香港居民–来往内地通行证
   *  IDENTIFICATION_TYPE_MACAO：中国澳门居民–来往内地通行证
   *  IDENTIFICATION_TYPE_TAIWAN：中国台湾居民–来往大陆通行证
   *  示例值：IDENTIFICATION_TYPE_MACAO
   * </pre>
   */
  @SerializedName(value = "id_doc_type")
  private String idDocType;

  /**
   * <pre>
   * 字段名：+经营者/法人身份证信息
   * 变量名：id_card_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：
   *  请填写经营者/法人的身份证信息
   *  证件类型为“身份证”时填写。
   *
   * </pre>
   */
  @SerializedName(value = "id_card_info")
  @SpecEncrypt
  private IdCardInfo idCardInfo;

  /**
   * <pre>
   * 字段名：+经营者/法人其他类型证件信息
   * 变量名：id_doc_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：证件类型为“来往内地通行证、来往大陆通行证、护照”时填写。
   * </pre>
   */
  @SerializedName(value = "id_doc_info")
  @SpecEncrypt
  private IdDocInfo idDocInfo;

  /**
   * <pre>
   * 字段名：经营者/法人是否为受益人
   * 变量名：owner
   * 是否必填：条件选填
   * 类型：bool
   * 描述：主体类型为企业时，需要填写：1、若经营者/法人是最终受益人，则填写：true。2、若经营者/法人不是最终受益人，则填写：false。
   * 示例值：true
   * </pre>
   */
  @SerializedName(value = "owner")
  private Boolean owner;

  /**
   * <pre>
   * 字段名：是否填写结算账户信息
   * 变量名：need_account_info
   * 是否必填：是
   * 类型：bool
   * 描述：
   *  可根据实际情况，填写“true”或“false”。
   *  1、若为“true”，则需填写结算账户信息。
   *  2、若为“false”，则无需填写结算账户信息。
   *  示例值：true
   * </pre>
   */
  @SerializedName(value = "need_account_info")
  private Boolean needAccountInfo;

  /**
   * <pre>
   * 字段名：+结算账户信息
   * 变量名：account_info
   * 是否必填：条件选填
   * 类型：object
   * 描述：若"是否填写结算账户信息"填写为“true”, 则必填，填写为“false”不填 。
   * </pre>
   */
  @SerializedName(value = "account_info")
  @SpecEncrypt
  private AccountInfo accountInfo;

  /**
   * <pre>
   * 字段名：+超级管理员信息
   * 变量名：contact_info
   * 是否必填：是
   * 类型：object
   * 描述：
   *  请填写店铺的超级管理员信息。
   *  超级管理员需在开户后进行签约，并可接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
   * </pre>
   */
  @SerializedName(value = "contact_info")
  @SpecEncrypt
  private ContactInfo contactInfo;

  /**
   * <pre>
   * 字段名：+店铺信息
   * 变量名：sales_scene_info
   * 是否必填：是
   * 类型：object
   * 描述：请填写店铺信息
   * </pre>
   */
  @SerializedName(value = "sales_scene_info")
  private SalesSceneInfo salesSceneInfo;

  /**
   * <pre>
   * 字段名：商户简称
   * 变量名：merchant_shortname
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  UTF-8格式，中文占3个字节，即最多16个汉字长度。将在支付完成页向买家展示，需与商家的实际售卖商品相符 。
   *  示例值：腾讯
   * </pre>
   */
  @SerializedName(value = "merchant_shortname")
  private String merchantShortname;

  /**
   * <pre>
   * 字段名：特殊资质
   * 变量名：qualifications
   * 是否必填：否
   * 类型：string(1024)
   * 描述：
   *  1、若店铺业务包含互联网售药，则需上传特殊资质-《互联网药品交易服务证》。
   *  2、最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
   *  示例值：[\"jTpGmxUX3FBWVQ5NJInE4d2I6_H7I4\"]
   * </pre>
   */
  @SerializedName(value = "qualifications")
  private String qualifications;

  /**
   * <pre>
   * 字段名：补充材料
   * 变量名：business_addition_pics
   * 是否必填：否
   * 类型：string(1024)
   * 描述：
   *   最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
   *  示例值：[\"jTpGmg05InE4d2I6_H7I4\"]
   * </pre>
   */
  @SerializedName(value = "business_addition_pics")
  private String businessAdditionPics;

  /**
   * <pre>
   * 字段名：补充说明
   * 变量名：business_addition_desc
   * 是否必填：否
   * 类型：string(256)
   * 描述：
   *   可填写512字以内 。
   *  示例值：特殊情况，说明原因
   * </pre>
   */
  @SerializedName(value = "business_addition_desc")
  private String businessAdditionDesc;

  @Data
  @NoArgsConstructor
  public static class BusinessLicenseInfo implements Serializable {
    /**
     * <pre>
     * 字段名：证件扫描件
     * 变量名：business_license_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、主体为“个体工商户/企业”时，请上传营业执照的证件图片。
     *  2、主体为“党政、机关及事业单位/其他组织”时，请上传登记证书的证件图片。
     *  3、可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
     *  4、图片要求：
     *  （1）请上传证件的彩色扫描件或彩色数码拍摄件，黑白复印件需加盖公章（公章信息需完整） 。
     *  （2）不得添加无关水印（非微信支付商户申请用途的其他水印）。
     *  （3）需提供证件的正面拍摄件，完整、照面信息清晰可见。信息不清晰、扭曲、压缩变形、反光、不完整均不接受。
     *  （4）不接受二次剪裁、翻拍、PS的证件照片。
     *  示例值： 47ZC6GC-vnrbEny__Ie_An5-tCpqxucuxi-vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "business_license_copy")
    private String businessLicenseCopy;

    /**
     * <pre>
     * 字段名：证件注册号
     * 变量名：business_license_number
     * 是否必填：是
     * 类型：string(18)
     * 描述：
     *  1、主体为“个体工商户/企业”时，请填写营业执照上的注册号/统一社会信用代码，须为15位数字或 18位数字|大写字母。
     *  2、主体为“党政、机关及事业单位/其他组织”时，请填写登记证书的证书编号。
     *  示例值：123456789012345678
     *  特殊规则：长度最小15个字节
     * </pre>
     */
    @SerializedName(value = "business_license_number")
    private String businessLicenseNumber;

    /**
     * <pre>
     * 字段名：商户名称
     * 变量名：merchant_name
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请填写营业执照/登记证书的商家名称，2~110个字符，支持括号 。
     *  2、个体工商户/党政、机关及事业单位，不能以“公司”结尾。
     *  3、个体工商户，若营业执照上商户名称为空或为“无”，请填写"个体户+经营者姓名"，如“个体户张三” 。
     *  示例值：腾讯科技有限公司
     * </pre>
     */
    @SerializedName(value = "merchant_name")
    private String merchantName;

    /**
     * <pre>
     * 字段名：经营者/法定代表人姓名
     * 变量名：legal_person
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  请填写证件的经营者/法定代表人姓名
     *  示例值：张三
     * </pre>
     */
    @SerializedName(value = "legal_person")
    private String legalPerson;

    /**
     * <pre>
     * 字段名：注册地址
     * 变量名：company_address
     * 是否必填：条件选填
     * 类型：string(128)
     * 描述：
     *  主体为“党政、机关及事业单位/其他组织”时必填，请填写登记证书的注册地址。
     *  示例值：深圳南山区科苑路
     * </pre>
     */
    @SerializedName(value = "company_address")
    private String companyAddress;

    /**
     * <pre>
     * 字段名：营业期限
     * 变量名：business_time
     * 是否必填：条件选填
     * 类型：string(256)
     * 描述：
     *  1、主体为“党政、机关及事业单位/其他组织”时必填，请填写证件有效期。
     *  2、若证件有效期为长期，请填写：长期。
     *  3、结束时间需大于开始时间。
     *  4、有效期必须大于60天，即结束时间距当前时间需超过60天。
     *  示例值：[\"2014-01-01\",\"长期\"]
     * </pre>
     */
    @SerializedName(value = "business_time")
    private String businessTime;

  }

  @Data
  @NoArgsConstructor
  public static class OrganizationCertInfo implements Serializable {
    /**
     * <pre>
     * 字段名：组织机构代码证照片
     * 变量名：organization_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID。
     *  示例值：vByf3Gjm7KE53JXv\prrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "organization_copy")
    private String organizationCopy;

    /**
     * <pre>
     * 字段名：组织机构代码
     * 变量名：organization_number
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请填写组织机构代码证上的组织机构代码。
     *  2、可填写9或10位 数字|字母|连字符。
     *  示例值：12345679-A
     * </pre>
     */
    @SerializedName(value = "organization_number")
    private String organizationNumber;

    /**
     * <pre>
     * 字段名：组织机构代码有效期限
     * 变量名：organization_time
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请填写组织机构代码证的有效期限，注意参照示例中的格式。
     *  2、若证件有效期为长期，请填写：长期。
     *  3、结束时间需大于开始时间。
     *  4、有效期必须大于60天，即结束时间距当前时间需超过60天。
     *  示例值：[\"2014-01-01\",\"长期\"]
     * </pre>
     */
    @SerializedName(value = "organization_time")
    private String organizationTime;

  }

  @Data
  @NoArgsConstructor
  public static class IdCardInfo implements Serializable {
    /**
     * <pre>
     * 字段名：身份证人像面照片
     * 变量名：id_card_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请上传经营者/法定代表人的身份证人像面照片。
     *  2、可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID。
     *  示例值：xpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_card_copy")
    private String idCardCopy;

    /**
     * <pre>
     * 字段名：身份证国徽面照片
     * 变量名：id_card_national
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请上传经营者/法定代表人的身份证国徽面照片。
     *  2、可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
     *  示例值：vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "id_card_national")
    private String idCardNational;

    /**
     * <pre>
     * 字段名：身份证姓名
     * 变量名：id_card_name
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请填写经营者/法定代表人对应身份证的姓名，2~30个中文字符、英文字符、符号。
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  示例值：pVd1HJ6v/69bDnuC4EL5Kz4jBHLiCa8MRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     *  字段加密:使用APIv3定义的方式加密
     * </pre>
     */
    @SerializedName(value = "id_card_name")
    @SpecEncrypt
    private String idCardName;

    /**
     * <pre>
     * 字段名：身份证号码
     * 变量名：id_card_number
     * 是否必填：是
     * 类型：string(18)
     * 描述：
     *  1、请填写经营者/法定代表人对应身份证的号码。
     *  2、15位数字或17位数字+1位数字|X ，该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  示例值：zV+BEmytMNQCqQ8juwEc4P4TG5xzchG/5IL9DBd+Z0zZXkw==4
     *  特殊规则：长度最小15个字节
     * </pre>
     */
    @SerializedName(value = "id_card_number")
    @SpecEncrypt
    private String idCardNumber;

    /**
     * <pre>
     * 字段名：身份证有效期限
     * 变量名：id_card_valid_time
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请填写身份证有效期的结束时间，注意参照示例中的格式。
     *  2、若证件有效期为长期，请填写：长期。
     *  3、证件有效期需大于60天。
     *  示例值：2026-06-06，长期
     * </pre>
     */
    @SerializedName(value = "id_card_valid_time")
    private String idCardValidTime;

  }

  @Data
  @NoArgsConstructor
  public static class IdDocInfo implements Serializable {
    /**
     * <pre>
     * 字段名：证件姓名
     * 变量名：id_doc_name
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  请填写经营者/法人姓名。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4LC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_doc_name")
    @SpecEncrypt
    private String idDocName;

    /**
     * <pre>
     * 字段名：证件号码
     * 变量名：id_doc_number
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  7~11位 数字|字母|连字符 。
     *  示例值：jTpGmxUX3FBWVQ5NJTZvlKX_go0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * </pre>
     */
    @SerializedName(value = "id_doc_number")
    @SpecEncrypt
    private String idDocNumber;

    /**
     * <pre>
     * 字段名：证件照片
     * 变量名：id_doc_copy
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID。
     *  2、2M内的彩色图片，格式可为bmp、png、jpeg、jpg或gif 。
     *  示例值：xi-vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     * </pre>
     */
    @SerializedName(value = "id_doc_copy")
    private String idDocCopy;

    /**
     * <pre>
     * 字段名：证件结束日期
     * 变量名：doc_period_end
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、请按照示例值填写。
     *  2、若证件有效期为长期，请填写：长期。
     *  3、证件有效期需大于60天 。
     *  示例值：2020-01-02
     * </pre>
     */
    @SerializedName(value = "doc_period_end")
    private String docPeriodEnd;

  }

  @Data
  @NoArgsConstructor
  public static class AccountInfo implements Serializable {
    /**
     * <pre>
     * 字段名：账户类型
     * 变量名：bank_account_type
     * 是否必填：是
     * 类型：string(2)
     * 描述：
     *  1、若主体为企业/党政、机关及事业单位/其他组织，可填写：74-对公账户。
     *  2、若主体为小微，可填写：75-对私账户。
     *  3、若主体为个体工商户，可填写：74-对公账户或75-对私账户。
     *  示例值：75
     * </pre>
     */
    @SerializedName(value = "bank_account_type")
    private String bankAccountType;

    /**
     * <pre>
     * 字段名：开户银行
     * 变量名：account_bank
     * 是否必填：是
     * 类型：string(10)
     * 描述：
     *  详细参见开户银行对照表。
     *  示例值：工商银行
     * </pre>
     */
    @SerializedName(value = "account_bank")
    private String accountBank;

    /**
     * <pre>
     * 字段名：开户名称
     * 变量名：account_name
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、选择经营者个人银行卡时，开户名称必须与身份证姓名一致。
     *  2、选择对公账户时，开户名称必须与营业执照上的“商户名称”一致。
     *  3、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  示例值：AOZdYGISxo4yw96uY1Pk7Rq79Jtt7+I8juwEc4P4TG5xzchG/5IL9DBd+Z0zZXkw==
     * </pre>
     */
    @SerializedName(value = "account_name")
    @SpecEncrypt
    private String accountName;

    /**
     * <pre>
     * 字段名：开户银行省市编码
     * 变量名：bank_address_code
     * 是否必填：是
     * 类型：string(12)
     * 描述：
     *  至少精确到市，详细参见省市区编号对照表。
     *  示例值：110000
     * </pre>
     */
    @SerializedName(value = "bank_address_code")
    private String bankAddressCode;

    /**
     * <pre>
     * 字段名：开户银行联行号
     * 变量名：bank_branch_id
     * 是否必填：条件选填
     * 类型：string(64)
     * 描述：
     *  1、17家直连银行无需填写，如为其他银行，开户银行全称（含支行）和开户银行联行号二选一。
     *  2、详细参见开户银行全称（含支行）对照表。
     *  示例值：402713354941
     * </pre>
     */
    @SerializedName(value = "bank_branch_id")
    private String bankBranchId;

    /**
     * <pre>
     * 字段名：开户银行全称 （含支行)
     * 变量名：bank_name
     * 是否必填：条件选填
     * 类型：string(128)
     * 描述：
     *  1、17家直连银行无需填写，如为其他银行，开户银行全称（含支行）和开户银行联行号二选一。
     *  2、需填写银行全称，如"深圳农村商业银行XXX支行" 。
     *  3、详细参见开户银行全称（含支行）对照表。
     *  示例值：施秉县农村信用合作联社城关信用社
     * </pre>
     */
    @SerializedName(value = "bank_name")
    private String bankName;

    /**
     * <pre>
     * 字段名：银行帐号
     * 变量名：account_number
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  1、数字，长度遵循系统支持的对公/对私卡号长度要求表。
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  示例值： d+xT+MQCvrLHUVDWv/8MR/dB7TkXLVfSrUxMPZy6jWWYzpRrEEaYQE8ZRGYoeorwC+w==
     * </pre>
     */
    @SerializedName(value = "account_number")
    @SpecEncrypt
    private String accountNumber;

  }

  @Data
  @NoArgsConstructor
  public static class ContactInfo implements Serializable {
    /**
     * <pre>
     * 字段名：超级管理员类型
     * 变量名：contact_type
     * 是否必填：是
     * 类型：string(2)
     * 描述：
     *  1、小微商户，选择：65-法人/经营者。
     *  2、个体工商户/企业/党政、机关及事业单位/其他组织，可选择：65-法人/经营者、66- 负责人。 （负责人：经商户授权办理微信支付业务的人员，授权范围包括但不限于签约，入驻过程需完成账户验证）。
     *  示例值：65
     * </pre>
     */
    @SerializedName(value = "contact_type")
    private String contactType;

    /**
     * <pre>
     * 字段名：超级管理员姓名
     * 变量名：contact_name
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、若管理员类型为“法人”，则该姓名需与法人身份证姓名一致。
     *  2、若管理员类型为“经办人”，则可填写实际经办人的姓名。
     *  3、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  （后续该管理员需使用实名微信号完成签约）
     *  示例值： pVd1HJ6zyvPedzGaV+X3IdGdbDnuC4Eelw/wDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "contact_name")
    @SpecEncrypt
    private String contactName;

    /**
     * <pre>
     * 字段名：超级管理员身份证件号码
     * 变量名：contact_id_card_number
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、若管理员类型为法人，则该身份证号码需与法人身份证号码一致。若管理员类型为经办人，则可填写实际经办人的身份证号码。
     *  2、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码。
     *  3、超级管理员签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。
     *  4、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  示例值：pVd1HJ6zmty7/mYNxLMpRSvMRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "contact_id_card_number")
    @SpecEncrypt
    private String contactIdCardNumber;

    /**
     * <pre>
     * 字段名：超级管理员手机
     * 变量名：mobile_phone
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、请填写管理员的手机号，11位数字， 用于接收微信支付的重要管理信息及日常操作验证码 。
     *  2、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+F6mfjbzQIzfb3HHLEjZ4YiNWWNeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "mobile_phone")
    @SpecEncrypt
    private String mobilePhone;

    /**
     * <pre>
     * 字段名：超级管理员邮箱
     * 变量名：contact_email
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  1、用于接收微信支付的开户邮件及日常业务通知。
     *  2、需要带@，遵循邮箱格式校验 。
     *  3、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     *  示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+FWWNUlw/wDa4SzfeespQO/0kjiwfqdfg==
     * </pre>
     */
    @SerializedName(value = "contact_email")
    @SpecEncrypt
    private String contactEmail;

  }

  @Data
  @NoArgsConstructor
  public static class SalesSceneInfo implements Serializable {
    /**
     * <pre>
     * 字段名：店铺名称
     * 变量名：store_name
     * 是否必填：是
     * 类型：string(256)
     * 描述：
     *  请填写店铺全称。
     *  示例值：爱烧烤
     * </pre>
     */
    @SerializedName(value = "store_name")
    private String storeName;

    /**
     * <pre>
     * 字段名：店铺链接
     * 变量名：store_url
     * 是否必填：二选一
     * 类型：string(1024)
     * 描述：
     *  1、店铺二维码or店铺链接二选一必填。
     *  2、请填写店铺主页链接，需符合网站规范。
     *  示例值：http://www.qq.com
     * </pre>
     */
    @SerializedName(value = "store_url")
    private String storeUrl;

    /**
     * <pre>
     * 字段名：店铺二维码
     * 变量名：store_qr_code
     * 是否必填：1、店铺二维码 or 店铺链接二选一必填。 2、若为电商小程序，可上传店铺页面的小程序二维码。 3、请填写通过图片上传接口预先上传图片生成好的MediaID，仅能上传1张图片 。 示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO1D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     * 类型：string(256)
     * 描述：
     * </pre>
     */
    @SerializedName(value = "store_qr_code")
    private String storeQrCode;

    /**
     * <pre>
     * 字段名：小程序AppID
     * 变量名：mini_program_sub_appid
     * 是否必填：否
     * 类型：string(256)
     * 描述：
     * </pre>
     */
    @SerializedName(value = "mini_program_sub_appid")
    private String miniProgramSubAppid;

  }

}
