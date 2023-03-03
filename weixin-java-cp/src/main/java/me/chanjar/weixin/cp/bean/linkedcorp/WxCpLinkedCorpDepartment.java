package me.chanjar.weixin.cp.bean.linkedcorp;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.bean.linkedcorp
 * @Description: 获取互联企业部门列表
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 28/2/2023 6:16 PM
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
