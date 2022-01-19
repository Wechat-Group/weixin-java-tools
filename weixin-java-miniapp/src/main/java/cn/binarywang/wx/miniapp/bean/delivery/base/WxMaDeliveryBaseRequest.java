package cn.binarywang.wx.miniapp.bean.delivery.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 微信小程序 即时配送 基础请求参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:36
 */
@Data
@Accessors(chain = true)
public abstract class WxMaDeliveryBaseRequest implements Serializable {

    private static final long serialVersionUID = -6811550517417623460L;

    /**
     * 配送公司ID.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("delivery_id")
    private String deliveryId;

    /**
     * 唯一标识订单的 ID，由商户生成, 不超过 128 字节.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("shop_order_id")
    private String shopOrderId;

    /**
     * 下单用户的openid.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 商家门店编号，在配送公司登记，如果只有一个门店，美团闪送必填, 值为店铺id.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("shop_no")
    private String shopNo;

    /**
     * 商家id，由配送公司分配的appKey.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("shopid")
    private String shopId;

    /**
     * 用配送公司提供的appSecret加密的校验串.
     * <pre>
     * 除了平台本身的加解密和签名，和订单相关的请求还需要带上运力侧签名delivery_sign，签名规则为
     * 如果接口请求里有字段shop_order_id ，则delivery_sign=SHA1(shopid + shop_order_id + AppSecret)，其中shopid对应运力侧的appkey，shop_order_id对应订单id，AppSecret即配送公司帐号对应的秘钥
     * 如果请求里没有字段shop_order_id ，则delivery_sign=SHA1(shopid + AppSecret)，其中shopid对应运力侧的appkey，AppSecret即配送公司帐号对应的秘钥
     * 示例：shopid=“test_shop_id”，shop_order_id =“test_shop_order_id”， AppSecret=“test_app_secrect”，则delivery_sign=“a93d8d6bae9a9483c1b1d4e8670e7f6226ec94cb”
     * 是否必填：是
     * </pre>
     */
    @Setter(AccessLevel.NONE)
    @SerializedName("delivery_sign")
    private String deliverySign;

    /**
     * 配送公司分配的appSecret.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @Expose
    private String appSecret;

    /**
     * 获取签名.
     * <pre>
     * 除了平台本身的加解密和签名，和订单相关的请求还需要带上运力侧签名delivery_sign，签名规则为
     * 如果接口请求里有字段shop_order_id ，则delivery_sign=SHA1(shopid + shop_order_id + AppSecret)，其中shopid对应运力侧的appkey，shop_order_id对应订单id，AppSecret即配送公司帐号对应的秘钥
     * 如果请求里没有字段shop_order_id ，则delivery_sign=SHA1(shopid + AppSecret)，其中shopid对应运力侧的appkey，AppSecret即配送公司帐号对应的秘钥
     * 示例：shopid=“test_shop_id”，shop_order_id =“test_shop_order_id”， AppSecret=“test_app_secrect”，则delivery_sign=“a93d8d6bae9a9483c1b1d4e8670e7f6226ec94cb”
     * 是否必填：是
     * </pre>
     *
     * @return 结果
     */
    public String getDeliverySign() {
        if (StringUtils.isBlank(getShopId()) || StringUtils.isBlank(getAppSecret())) {
            throw new RuntimeException("shopId or appSecret can not be empty");
        }
        String str = getShopId();
        if (StringUtils.isNotBlank(getShopOrderId())) {
            str = str.concat(getShopOrderId());
        }
        str = str.concat(getAppSecret());
        return DigestUtils.sha1Hex(str);
    }

}
