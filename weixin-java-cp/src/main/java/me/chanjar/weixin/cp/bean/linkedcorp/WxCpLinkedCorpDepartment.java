package me.chanjar.weixin.cp.bean.linkedcorp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 获取互联企业部门列表
 *
 * @author libo
 */
@Data
public class WxCpLinkedCorpDepartment implements Serializable {
  private static final long serialVersionUID = -210249269343292440L;
  @SerializedName("department_id")
  private String departmentId;
  @SerializedName("department_name")
  private String departmentName;
  @SerializedName("parentid")
  private String parentId;
  @SerializedName("order")
  private Integer order;
}
