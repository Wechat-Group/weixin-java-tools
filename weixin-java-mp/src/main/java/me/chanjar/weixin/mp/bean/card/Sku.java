package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 商品信息
 * author:yuanqixun
 * date:2018-08-25 00:32
 */
@Data
public class Sku implements Serializable {

    /**
     * 卡券库存的数量,不支持填写0，上限为100000000。
     */
    @SerializedName("quantity")
    private Integer quantity=100000000;

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
