package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//子对象列表

/**
 * 微信会员卡高级字段信息
 * author:yuanqixun
 * date:2018-08-25 00:36
 */
@Data
public class AdvancedInfo implements Serializable {

//    public AdvancedInfo(){
//        useCondition = new UseCondition();
//        abstractInfo = new Abstract();
//        textImageList = new ArrayList<>();
//        timeLimit = new TimeLimit();
//    }

    /**
     * 使用门槛（条件）,若不填写使用条件则在券面拼写 ：无最低消费限制，全场通用，不限品类；并在使用说明显示： 可与其他优惠共享
     */
    @SerializedName( "use_condition")
    private UseCondition useCondition;

    /**
     * 封面摘要
     */
    @SerializedName( "abstract")
    private Abstract abstractInfo;

    /**
     * 图文列表,显示在详情内页 ，优惠券券开发者须至少传入 一组图文列表
     */
    @SerializedName( "text_image_list")
    private List<TextImageList> textImageList;

    /**
     * 商家服务类型,数组类型:BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位； BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi， 可多选
     */
    @SerializedName( "business_service")
    private String businessService;

    /**
     * 使用时段限制
     */
    @SerializedName( "time_limit")
    private TimeLimit timeLimit;

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
