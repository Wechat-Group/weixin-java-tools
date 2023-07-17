package me.chanjar.weixin.cp.bean.linkedcorp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 获取应用可见范围请求类
 *
 * @author libo
 */
@Data
public class WxCpLinkedCorpAgentPerm implements Serializable {
  private static final long serialVersionUID = 6794613362541093845L;
  @SerializedName("userids")
  private String[] userIdList;
  @SerializedName("department_ids")
  private String[] departmentIdList;
}
