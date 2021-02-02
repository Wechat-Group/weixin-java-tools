package me.chanjar.weixin.open.bean.minishop;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class MinishopMerchantinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 小商店认证ID
     */
    private Long merchantId;

    /**
     * 小商店APPID
     */
    private String appId;

    /**
     * 主体类型
"4"：个体工商户，营业执照上的主体类型一般为个体户、个体工商户、个体经营。
"2"：企业，营业执照上的主体类型一般为有限公司、有限责任公司。
     */
    private String subjectType;

    /**
     * 商户简称 UTF-8格式，中文占3个字节，即最多16个汉字长度。
将在支付完成页向买家展示，需与商家的实际售卖商品相符 。示例值：腾讯
     */
    private String merchantShortname;

    /**
     * 补充描述
     */
    private String supplementaryDesc;

    /**
     * 营业执照/登记证书信息
     */
    private Integer busiLicenseId;

    /**
     * 组织机构代码证信息(非必填）
     */
    private Integer organizationCodeInfo;

    /**
     * 身份证信息
     */
    private Integer idCardInfo;

    /**
     * 超级管理员信息 请填写店铺的超级管理员信息。超级管理员需在开户后进行签约，
并可接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
     */
    private Integer superAdministratorInfoId;

    /**
     * 特殊资质 1、根据商户经营业务要求提供相关资质，详情查看《行业对应特殊资质》。
2、请提供为“申请商家主体”所属的特殊资质，可授权使用总公司/分公司的特殊资 质；
3、最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
     */
    private Integer specialQualificationId;

    /**
     * 补充材料
     */
    private Integer supplementaryMaterialId;

    /**
     * 状态：0为审核中，1为已通过，-1为审批驳回
     */
    private Integer status;

    /**
     * 提交时间
     */
    private Date submitTime;


}
