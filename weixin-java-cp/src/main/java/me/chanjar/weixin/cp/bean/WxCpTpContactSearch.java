package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * @author uianz
 * @description
 * @since 2020/12/23 下午 02:43
 */
@Data
@Accessors(chain = true)
public class WxCpTpContactSearch {

    /**
     * 查询的企业corpid
     */
    @SerializedName("auth_corpid")
    private String authCorpId;

    /**
     * 搜索关键词。当查询用户时应为用户名称、名称拼音或者英文名；当查询部门时应为部门名称或者部门名称拼音
     */
    @SerializedName("query_word")
    private String queryWord;

    /**
     * 查询类型 1：查询用户，返回用户userid列表 2：查询部门，返回部门id列表。 不填该字段或者填0代表同时查询部门跟用户
     */
    @SerializedName("query_type")
    private Integer type;

    /**
     * 应用id，若非0则只返回应用可见范围内的用户或者部门信息
     */
    @SerializedName("agentid")
    private Integer agentId;

    /**
     * 查询的偏移量，每次调用的offset在上一次offset基础上加上limit
     */
    @SerializedName("offset")
    private Integer offset;

    /**
     * 查询返回的最大数量，默认为50，最多为200，查询返回的数量可能小于limit指定的值
     */
    @SerializedName("limit")
    private Integer limit;

    /**
     * 如果需要精确匹配用户名称或者部门名称或者英文名，不填则默认为模糊匹配；1：匹配用户名称或者部门名称 2：匹配用户英文名
     */
    @SerializedName("full_match_field")
    private Integer fullMatchField;

    public String toJson() {
        return WxCpGsonBuilder.create().toJson(this);
    }
}
