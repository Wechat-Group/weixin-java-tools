package cn.binarywang.wx.miniapp.bean.live;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author linlinjava
 */
@Data
public class WxMaLiveSharedCode implements Serializable {
    private static final long serialVersionUID = 8525117884393611947L;
    /**
     * 分享二维码
     */
    @SerializedName("cdnUrl")
    private String cdnUrl;
    /**
     * 分享路径
     */
    @SerializedName("pagePath")
    private String pagePath;
    /**
     * 分享海报
     */
    @SerializedName("posterUrl")
    private String posterUrl;
}
