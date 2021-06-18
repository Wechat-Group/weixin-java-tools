package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideMassed;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideMassedInfo;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideMaterialInfo;

import java.util.List;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */
public interface WxMpGuideMassedJobService {

  /**
   * 添加群发任务
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguidemassendjob?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/task-account/shopping-guide.addGuideMassendJob.html
   * </pre>
   *
   * @param account       顾问微信号（guide_account和guide_openid二选一）
   * @param openid        顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param taskName      群发任务名称,不超过16字
   * @param taskRemark    群发任务备注,不超过100字
   * @param pushTime      任务下发给顾问的时间, 秒级时间戳, 范围为当前时间开始到最近一个月内
   * @param userOpenIds   客户openid列表
   * @param materialInfos 不超过3个素材
   * @return 群发任务id与客户openid列表
   * @throws WxErrorException 。
   */
  WxMpGuideMassed addGuideMassedJob(String account, String openid, String taskName, String taskRemark, Long pushTime, List<String> userOpenIds, List<WxMpGuideMaterialInfo> materialInfos) throws WxErrorException;

  /**
   * 获取群发任务列表
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidemassendjoblist?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/task-account/shopping-guide.getGuideMassendJobList.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param taskStatus 获取指定状态的任务（为空则表示拉取所有状态的任务）
   * @param offset     偏移位置(从什么位置开始拉取)
   * @param limit      条数（默认50）
   * @return 群发任务列表
   * @throws WxErrorException 。
   */
  List<WxMpGuideMassedInfo> getGuideMassedJobList(String account, String openid, List<Integer> taskStatus, Integer offset, Integer limit) throws WxErrorException;

  /**
   * 获取指定群发任务信息
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidemassendjob?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/task-account/shopping-guide.getGuideMassendJob.html
   * </pre>
   *
   * @param taskId 任务ID
   * @return 群发任务信息
   * @throws WxErrorException 。
   */
  WxMpGuideMassedInfo getGuideMassedJob(String taskId) throws WxErrorException;

  /**
   * 修改群发任务
   * 无法修改已经执行的任务，返回参数错误
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/updateguidemassendjob?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/task-account/shopping-guide.updateGuideMassendJob.html
   * </pre>
   *
   * @param taskId        任务ID
   * @param taskName      群发任务名称,不超过16字
   * @param taskRemark    群发任务备注,不超过100字
   * @param pushTime      下发时间, 秒级时间戳, 范围为当前时间开始到最近一个月内
   * @param userOpenIds   客户openid列表
   * @param materialInfos 不超过3个素材
   * @throws WxErrorException 。
   */
  void updateGuideMassedJob(String taskId, String taskName, String taskRemark, Long pushTime, List<String> userOpenIds, List<WxMpGuideMaterialInfo> materialInfos) throws WxErrorException;

  /**
   * 取消群发任务
   * 取消给顾问分配的群发任务, 已执行的任务无法取消。
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/cancelguidemassendjob?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/task-account/shopping-guide.cancelGuideMassendJob.html
   * </pre>
   *
   * @param taskId 任务ID
   * @throws WxErrorException .
   */
  void cancelGuideMassedJob(String taskId) throws WxErrorException;
}
