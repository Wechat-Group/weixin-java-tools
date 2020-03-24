package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 *  批量获取审批单号的筛选条件，可对批量拉取的审批申请设置约束条件，支持设置多个条件
 *  注意:
 *  仅“部门”支持同时配置多个筛选条件。
 *  不同类型的筛选条件之间为“与”的关系，同类型筛选条件之间为“或”的关系
 * </pre>
 *
 * @author element
 */
@Data
public class WxCpApprovalInfoQueryFilter implements Serializable {

  private static final long serialVersionUID = 3318064927980231802L;

  private WxCpApprovalInfoQueryFilter.KEY key;

  private Object value;

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static enum KEY {

    /**
     * template_id - 模板类型/模板id；
     */
    @SerializedName("template_id")
    TEMPLATE_ID("template_id"),
    /**
     * creator - 申请人；
     */
    @SerializedName("creator")
    CREATOR("creator"),
    /**
     * department - 审批单提单者所在部门；
     */
    @SerializedName("department")
    DEPARTMENT("department"),
    /**
     * sp_status - 审批状态。
     */
    @SerializedName("sp_status")
    SP_STATUS("sp_status");

    private String value;

    private KEY(String value) {
      this.value = value;
    }
  }
}
