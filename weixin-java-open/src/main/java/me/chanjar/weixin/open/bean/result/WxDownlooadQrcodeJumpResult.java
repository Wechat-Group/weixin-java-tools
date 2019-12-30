package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

/**
 * 二维码规则的校验文件名称及内容
 *
 * @author <a href="https://github.com/hanwei59">hanwei59</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxDownlooadQrcodeJumpResult extends WxOpenResult {

    //文件名称
    @SerializedName("file_name")
    private String fileName;

    //文件内容
    @SerializedName("file_content")
    private String fileContent;

    @Override
    public String toString() {
        return WxOpenGsonBuilder.create().toJson(this);
    }

}
