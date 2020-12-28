package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * @author uianz
 * @description
 * @since 2020/12/23 下午 02:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpContactSearchResp extends WxCpBaseResp {

    @SerializedName("is_last")
    private Boolean isLast;

    @SerializedName("query_result")
    private QueryResult queryResult;

    @Data
    public static class QueryResult {

        @SerializedName("user")
        private User user;
        @SerializedName("party")
        private Party party;

        @Data
        public static class User {
            @SerializedName("userid")
            private List<String> userid;
            @SerializedName("open_userid")
            private List<String> openUserId;
        }

        @Data
        public static class Party {
            @SerializedName("department_id")
            private List<Integer> departmentId;
        }

    }

    public static WxCpTpContactSearchResp fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpTpContactSearchResp.class);
    }

}
