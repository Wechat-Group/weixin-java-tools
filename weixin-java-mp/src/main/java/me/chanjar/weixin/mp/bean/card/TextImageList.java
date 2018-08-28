package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 图文列表
 * author:yuanqixun
 * date:2018-08-25 00:35
 */
@Data
public class TextImageList implements Serializable {

    /**
     * 图片链接,必须调用 上传图片接口 上传图片获得链接，并在此填入， 否则报错
     */
    @SerializedName( "image_url")
    private String imageUrl;

    /**
     * 图文描述
     */
    @SerializedName("text")
    private String text;

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
