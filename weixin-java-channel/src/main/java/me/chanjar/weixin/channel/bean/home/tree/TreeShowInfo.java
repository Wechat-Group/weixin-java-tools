package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类展示信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeShowInfo implements Serializable {

  /** 分类树 */
  @JsonProperty("tree")
  private LevelTreeInfo tree;

  /** 版本号。通过获取商品分类树或者本接口得到 */
  @JsonProperty("version")
  private Integer version;

  /** 表示有哪一些分类ID清空关联得商品,如果不清空,那么分类ID和商品得关联关系会一直存在。如果是一级分类，就填"1"。如果是二级分类，就填"1.2"。 */
  @JsonProperty("classification_id_deleted")
  private List<String> classificationIdDeleted;

  // 一些自定义的方法

  /**
   * 创建Tree节点
   *
   * @return Tree节点
   */
  protected LevelTreeInfo createTree() {
    if (tree == null) {
      tree = new LevelTreeInfo();
    }
    return tree;
  }

  /**
   * 创建一级分类节点
   *
   * @return 一级分类节点
   */
  protected OneLevelTreeNode createLevel1() {
    this.createTree();
    if (tree.getLevel1() == null) {
      tree.setLevel1(new OneLevelTreeNode());
    }
    return tree.getLevel1();
  }

  /**
   * 创建二级分类节点
   *
   * @return 二级分类节点
   */
  protected CatTreeNode createLevel2() {
    OneLevelTreeNode level1 = this.createLevel1();
    if (level1.getLevel2() == null) {
      level1.setLevel2(new CatTreeNode());
    }
    return level1.getLevel2();
  }


  @JsonIgnore
  public void setLevel1Id(Integer id) {
    createLevel1().setId(id);
  }

  @JsonIgnore
  public void setLevel1Name(String name) {
    createLevel1().setName(name);
  }

  @JsonIgnore
  public void setLevel1Displayed(Integer displayed) {
    createLevel1().setDisplayed(displayed);
  }

  @JsonIgnore
  public void setLevel2Id(Integer id) {
    createLevel2().setId(id);
  }

  @JsonIgnore
  public void setLevel2Name(String name) {
    createLevel2().setName(name);
  }

  @JsonIgnore
  public void setLevel2Displayed(Integer displayed) {
    createLevel2().setDisplayed(displayed);
  }
}
