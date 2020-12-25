package me.chanjar.weixin.qidian.bean.dial;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.qidian.bean.common.QidianResponse;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

@Data
public class IVRDialResponse extends QidianResponse {
    private String callid;

    public static IVRDialResponse fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, IVRDialResponse.class);
    }

    @Override
    public String toString() {
        return WxMpGsonBuilder.create().toJson(this);
    }
}
