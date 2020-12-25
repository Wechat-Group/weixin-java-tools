package me.chanjar.weixin.qidian.bean.call;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.qidian.bean.common.QidianResponse;

@Data
public class GetSwitchBoardListResponse extends QidianResponse {
    private SwitchBoardList data;

    public static GetSwitchBoardListResponse fromJson(String result) {
        return WxGsonBuilder.create().fromJson(result, GetSwitchBoardListResponse.class);
    }
}
