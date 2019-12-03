package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 二维码规则
 *
 * <a href="https://github.com/hanwei59">hanwei59</a>
 */
@Data
public class WxQrcodeJumpRule {

    //二维码规则
    @SerializedName("prefix")
    private String prefix;

    //是否独占符合二维码前缀匹配规则的所有子规则：1为不占用，2为占用
    //详细说明：https://mp.weixin.qq.com/debug/wxadoc/introduction/qrcode.html#前缀占用规则
    @SerializedName("permit_sub_rule")
    private String permitSubRule;

    //小程序功能页面
    @SerializedName("path")
    private String path;

    //测试范围：
    //1为开发版（配置只对开发者生效）
    //2为体验版（配置对管理员、体验者生效）
    //3为线上版本（配置对管理员、开发者和体验者生效）
    @SerializedName("open_version")
    private String openVersion;

    //测试链接（选填）可填写不多于5个用于测试的二维码完整链接，此链接必须符合已填写的二维码规则。
    @SerializedName("debug_url")
    private List<String> debugUrl;

    //编辑标志位，0表示新增二维码规则，1表示修改已有二维码规则
    @SerializedName("is_edit")
    private String isEdit;
}
