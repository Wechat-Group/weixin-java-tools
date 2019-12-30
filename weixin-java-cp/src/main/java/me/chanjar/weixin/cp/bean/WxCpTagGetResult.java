package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  管理企业号应用-测试
 *  Created by huansinho on 2018/4/16.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxCpTagGetResult implements Serializable {

  @SerializedName("errcode")
  private Integer errcode;

  @SerializedName("errmsg")
  private String errmsg;

  /**
   * 用户列表.
   */
  @SerializedName("userlist")
  private List<WxCpUser> userlist;

  /**
   * 部门列表.
   */
  @SerializedName("partylist")
  private List<Integer> partylist;

  /**
   * 标签名称.
   */
  @SerializedName("tagname")
  private String tagname;

  public static WxCpTagGetResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTagGetResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
