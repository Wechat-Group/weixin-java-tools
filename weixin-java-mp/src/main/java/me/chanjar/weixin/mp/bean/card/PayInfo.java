package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 支付功能
 * author:yuanqixun
 * date:2018-08-25 00:33
 */
@Data
public class PayInfo implements Serializable {

    public PayInfo(){
        swipeCard = new SwipeCard();
    }

    /**
     * 刷卡功能
     */
    @SerializedName( "swipe_card")
    private SwipeCard swipeCard;

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
