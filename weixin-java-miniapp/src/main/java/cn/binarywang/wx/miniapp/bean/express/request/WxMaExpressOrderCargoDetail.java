package cn.binarywang.wx.miniapp.bean.express.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 包裹商品详情对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressOrderCargoDetail implements Serializable {

  private static final long serialVersionUID = 5988620921216969796L;

  /**
   * 商品名
   * <pre>
   * 是否必填： 是
   * 描述： 不超过128字节
   * </pre>
   */
  private String name;

  /**
   * 商品数量
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  private Integer count;
}
