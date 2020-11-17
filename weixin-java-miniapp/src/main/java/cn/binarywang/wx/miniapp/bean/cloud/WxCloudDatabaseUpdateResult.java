package cn.binarywang.wx.miniapp.bean.cloud;

import lombok.Data;

import java.io.Serializable;

/**
 * 云开发数据库更新记录接口请求结果.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-26
 */
@Data
public class WxCloudDatabaseUpdateResult implements Serializable {
  private static final long serialVersionUID = -3905429931117999004L;

  /**
   * matched	number	更新条件匹配到的结果数
   */
  private Long matched;

  /**
   * modified	number	修改的记录数，注意：使用set操作新插入的数据不计入修改数目
   */
  private Long modified;

  /**
   * id	string	新插入记录的id，注意：只有使用set操作新插入数据时这个字段会有值
   */
  private String id;

}
