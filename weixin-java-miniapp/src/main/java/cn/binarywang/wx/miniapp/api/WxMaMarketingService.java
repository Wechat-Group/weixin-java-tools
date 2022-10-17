package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.marketing.WxMaUserAction;
import me.chanjar.weixin.common.error.WxErrorException;
import java.util.List;

/**
 *
 * @Description :微信营销接口
 * @author <a href="https://github.com/184759547">184759547</a>
 * @since : 2021/12/28
 */
public interface WxMaMarketingService {
  /**
   * <pre>
   * 创建数据源.
   * 接口调用请求说明
   * https://ad.weixin.qq.com/guide/457
   * </pre>
   *
   * @param type        用户行为源类型
   * @param name        用户行为源名称 必填
   * @param description 用户行为源描述，字段长度最小 1 字节，长度最大 128 字节
   */
  long addUserActionSets(String type, String name, String description) throws WxErrorException;

  /**
   * 回传数据.
   * 接口调用请求说明
   * https://ad.weixin.qq.com/guide/457
   *
   * @param actions 用户行为源类型
   */
  String addUserAction(List<WxMaUserAction> actions, Long userActionSetId) throws WxErrorException;
}
