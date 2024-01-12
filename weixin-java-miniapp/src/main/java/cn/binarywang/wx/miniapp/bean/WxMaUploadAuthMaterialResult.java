package cn.binarywang.wx.miniapp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 小程序认证上传补充材料
 *
 * @author penhuozhu
 * @since 2024/01/07
 */
@Data
public class WxMaUploadAuthMaterialResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;

    @SerializedName("mediaid")
    private String mediaId;

    public static WxMaUploadAuthMaterialResult fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxMaUploadAuthMaterialResult.class);
    }

    @Override
    public String toString() {
        return WxGsonBuilder.create().toJson(this);
    }
}
