package cn.binarywang.wx.miniapp.bean.cloud;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 云开发获取集合接口的结果.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-28
 */
@Data
public class WxCloudDatabaseCollectionGetResult implements Serializable {
  private static final long serialVersionUID = 3702855196387039823L;

  /**
   * 分页信息
   */
  private WxCloudDatabaseQueryResult.Pager pager;

  /**
   * 查询结果
   */
  private CollectionInfo[] collections;

  @Data
  public static class CollectionInfo implements Serializable {
    private static final long serialVersionUID = -3280126948752330438L;

    /**
     * name	string	集合名
     */
    @SerializedName("name")
    private String name;

    /**
     * count	number	表中文档数量
     */
    @SerializedName("count")
    private Long count;

    /**
     * size	number	表的大小（即表中文档总大小），单位：字节
     */
    @SerializedName("size")
    private Long size;

    /**
     * index_count	number	索引数量
     */
    @SerializedName("index_count")
    private Long indexCount;

    /**
     * index_size	number	索引占用大小，单位：字节
     */
    @SerializedName("index_size")
    private Long indexSize;
  }

  @Data
  public static class Pager implements Serializable {
    private static final long serialVersionUID = 5045727687673687839L;

    /**
     * Offset	number	偏移
     */
    @SerializedName("Offset")
    private Long offset;

    /**
     * Limit	number	单次查询限制
     */
    @SerializedName("Limit")
    private Long limit;

    /**
     * Total	number	符合查询条件的记录总数
     */
    @SerializedName("Total")
    private Long total;

  }
}
