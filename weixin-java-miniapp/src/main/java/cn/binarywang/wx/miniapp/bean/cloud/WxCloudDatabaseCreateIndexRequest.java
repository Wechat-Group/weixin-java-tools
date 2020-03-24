package cn.binarywang.wx.miniapp.bean.cloud;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 云开发新增索引的请求对象.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-26
 */
@Accessors(chain = true)
@Data
public class WxCloudDatabaseCreateIndexRequest implements Serializable {
  private static final long serialVersionUID = -8308393731157121109L;

  /**
   * name	string		是	索引名
   */
  private String name;

  /**
   * unique	boolean		是	是否唯一
   */
  private boolean unique;

  /**
   * keys	Array.<Object>		是	索引字段
   */
  private List<IndexKey> keys;

  @Data
  @Accessors(chain = true)
  public static class IndexKey implements Serializable {
    private static final long serialVersionUID = -252641130547960325L;

    /**
     * name	string		是	字段名
     */
    private String name;

    /**
     * direction	string		是	字段排序
     * <pre>
     *   direction 的合法值
     * 值	说明
     * "1"	升序
     * "-1"	降序
     * "2dsphere"	地理位置
     *
     * </pre>
     */
    private String direction;
  }
}
