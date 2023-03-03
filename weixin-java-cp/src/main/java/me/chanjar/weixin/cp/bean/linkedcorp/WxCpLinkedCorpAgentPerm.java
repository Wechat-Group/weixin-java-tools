package me.chanjar.weixin.cp.bean.linkedcorp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.bean.linkedcorp
 * @Description: 获取应用可见范围请求类
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 28/2/2023 6:16 PM
 */
@Data
public class WxCpLinkedCorpAgentPerm implements Serializable {
  private static final long serialVersionUID = 6794613362541093845L;
  @SerializedName("userids")
  private String[] userIdList;
  @SerializedName("department_ids")
  private String[] departmentIdList;
}
