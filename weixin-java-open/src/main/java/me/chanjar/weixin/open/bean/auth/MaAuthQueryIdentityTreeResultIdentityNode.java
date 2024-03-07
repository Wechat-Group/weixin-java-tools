package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 职业身份 节点信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
public class MaAuthQueryIdentityTreeResultIdentityNode {

  /**
   * 职业身份名
   */
  @NotNull
  private String name;

  /**
   * 职业身份节点ID
   */
  @NotNull
  @SerializedName("node_id")
  private Integer nodeId;

  /**
   * 要求信息 叶子节点特有
   */
  @Nullable
  @SerializedName("leaf_info")
  private MaAuthQueryIdentityTreeResultIdentityLeaf leafInfo;

  /**
   * 子节点信息 非叶子节点必有
   */
  @Nullable
  @SerializedName("node_list")
  private List<MaAuthQueryIdentityTreeResultIdentityNode> nodeList;
}
