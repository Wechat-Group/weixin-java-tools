package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * 已设置的二维码规则信息
 *
 * @author <a href="https://github.com/hanwei59">hanwei59</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxGetQrcodeJumpResult extends WxOpenResult {

    //二维码规则详情，数组形式
    @SerializedName("rule_list")
    List<WxQrcodeJumpRule> ruleList;

    //是否已经打开二维码跳转链接设置
    @SerializedName("qrcodejump_open")
    private String qrcodejumpOpen;

    //本月还可发布的次数
    @SerializedName("qrcodejump_pub_quota")
    private Integer qrcodejumpPubQuota;

    //二维码规则数量
    @SerializedName("list_size")
    private Integer listSize;

    @Override
    public String toString() {
        return WxOpenGsonBuilder.create().toJson(this);
    }

}
