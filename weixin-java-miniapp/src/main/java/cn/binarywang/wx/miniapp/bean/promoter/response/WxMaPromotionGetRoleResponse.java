package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzibin
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionGetRoleResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 5030950505158018112L;

  /**
   * 角色集合
   */
  @SerializedName("role_list")
  private List<Role> roleList;

  /**
   * 角色总数
   */
  @SerializedName("total_cnt")
  private Integer totalCnt;

  @Data
  public static class Role {

    /**
     * 角色Id
     */
    @SerializedName("role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 角色描述
     */
    @SerializedName("desc")
    private String desc;
  }
}
