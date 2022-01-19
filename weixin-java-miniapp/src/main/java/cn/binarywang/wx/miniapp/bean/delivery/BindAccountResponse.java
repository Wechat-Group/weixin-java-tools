package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 微信小程序即时配送 拉取已绑定账号 响应参数.
 * <pre>
 * 使用场景：
 *      1.商家可通过本接口查询自己已经在小程序后台绑定的和配送公司签约的账号；
 *      2.服务商可通过本接口查询代开发的小程序在小程序后台绑定的和配送公司签约的账号，为其完成后续的接口代开发业务；
 * </pre>
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BindAccountResponse extends WxMaDeliveryBaseResponse implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;

    /**
     * 店铺账号信息集合.
     */
    @SerializedName("shop_list")
    private List<Shop> shopList;

    /**
     * 店铺账号信息.
     */
    @Data
    @Accessors(chain = true)
    public static class Shop implements Serializable {

        private static final long serialVersionUID = -3759074878713856529L;

        /**
         * 配送公司Id.
         */
        @SerializedName("delivery_id")
        private String deliveryId;

        /**
         * 商家id.
         */
        @SerializedName("shopid")
        private String shopId;

        /**
         * 审核状态 0：审核通过、1：审核中、2：审核不通过.
         */
        @SerializedName("audit_result")
        private String auditResult;

    }

}
