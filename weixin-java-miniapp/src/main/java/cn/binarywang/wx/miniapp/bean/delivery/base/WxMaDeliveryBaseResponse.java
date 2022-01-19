package cn.binarywang.wx.miniapp.bean.delivery.base;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 微信小程序 即时配送 基础响应参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:36
 */
@Data
@Accessors(chain = true)
public abstract class WxMaDeliveryBaseResponse implements Serializable {

    private static final long serialVersionUID = -6811550517417623460L;

    /**
     * 成功状态码.
     */
    private static final int SUCCESS_CODE = 0;

    /**
     * 运力返回的错误码.
     */
    @SerializedName("resultcode")
    private Integer resultCode;

    /**
     * 运力返回的错误描述.
     */
    @SerializedName("resultmsg")
    private String resultMsg;

    /**
     * 是否响应成功.
     *
     * @return true：成功、false：失败
     */
    public boolean success() {
        return SUCCESS_CODE == getResultCode();
    }

    /**
     * 解析响应.
     *
     * @param json      响应内容
     * @param valueType 类型
     * @param <T>       类型
     * @return 结果
     */
    public static <T extends WxMaDeliveryBaseResponse> T fromJson(final String json, final Class<T> valueType) {
        if (StringUtils.isBlank(json)) {
            throw new RuntimeException("the json cannot be empty");
        }
        // 解析成对应响应对象
        return WxMaGsonBuilder.create().fromJson(json, valueType);
    }

}
