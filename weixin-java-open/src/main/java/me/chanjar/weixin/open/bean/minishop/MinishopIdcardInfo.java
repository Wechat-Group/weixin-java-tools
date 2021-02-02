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
@Accessors(chain = true)
public class MinishopIdcardInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 身份证信息Id
     */
    private Integer idCardInfoId;

    /**
     * 小程序ID
     */
    private String appId;

    /**
     * 人像面照片media_id
     */
    private String portraitPicFileMediaId;

    private String portraitPicFilePayMediaId;

    /**
     * 国徽面照片
     */
    private String nationPicFileMediaId;

    private String nationPicFilePayMediaId;

    /**
     * 请填写经营者/法定代表人对应身份证的姓名，2~30个中文字符、英文字符、符号。
     */
    private String idCardName;

    /**
     * 请填写经营者/法定代表人对应身份证的号码
     */
    private String idCardNumber;

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
