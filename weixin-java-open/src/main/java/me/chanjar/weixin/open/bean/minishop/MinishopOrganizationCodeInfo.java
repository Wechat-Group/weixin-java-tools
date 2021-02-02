package me.chanjar.weixin.open.bean.minishop;

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
public class MinishopOrganizationCodeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer organizationCodeInfoId;

    /**
     * 小程序ID
     */
    private String appId;

    /**
     * 组织机构代码证图片
     */
    private String mediaId;

    /**
     * 支付图片media_id
     */
    private String payMediaId;

    /**
     * 1、请填写组织机构代码证上的组织机构代码。
2、可填写9或10位 数字\字母\连字符。示例值：12345679-A
     */
    private String organizationCode;

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


}
