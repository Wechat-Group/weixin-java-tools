package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpApprovalDataResult;
import me.chanjar.weixin.cp.bean.WxCpCheckinData;
import me.chanjar.weixin.cp.bean.WxCpCheckinOption;
import me.chanjar.weixin.cp.bean.WxCpDialRecord;

import java.util.Date;
import java.util.List;

/**
 * 企业微信OA相关接口.
 *
 * @author Element
 * @date 2019-04-06 10:52
 */
public interface WxCpOaService {

  /**
   * <pre>
   *  获取打卡数据
   *  API doc : https://work.weixin.qq.com/api/doc#90000/90135/90262
   * </pre>
   *
   * @param openCheckinDataType 打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
   * @param startTime           获取打卡记录的开始时间
   * @param endTime             获取打卡记录的结束时间
   * @param userIdList          需要获取打卡记录的用户列表
   * @return 打卡数据列表
   * @throws WxErrorException 异常
   */
  List<WxCpCheckinData> getCheckinData(Integer openCheckinDataType, Date startTime, Date endTime, List<String> userIdList) throws WxErrorException;

  /**
   * <pre>
   *   获取打卡规则
   *   API doc : https://work.weixin.qq.com/api/doc#90000/90135/90263
   * </pre>
   *
   * @param datetime   需要获取规则的当天日期
   * @param userIdList 需要获取打卡规则的用户列表
   * @return 打卡规则列表
   * @throws WxErrorException 异常
   */
  List<WxCpCheckinOption> getCheckinOption(Date datetime, List<String> userIdList) throws WxErrorException;

  /**
   * <pre>
   *   获取审批数据
   *   通过本接口来获取公司一段时间内的审批记录。一次拉取调用最多拉取10000个审批记录，可以通过多次拉取的方式来满足需求，但调用频率不可超过600次/分。
   *   API doc : https://work.weixin.qq.com/api/doc#90000/90135/91530
   * </pre>
   *
   * @param startTime 获取审批记录的开始时间
   * @param endTime   获取审批记录的结束时间
   * @param nextSpnum 第一个拉取的审批单号，不填从该时间段的第一个审批单拉取
   * @throws WxErrorException 异常
   */
  WxCpApprovalDataResult getApprovalData(Date startTime, Date endTime, Long nextSpnum) throws WxErrorException;

  List<WxCpDialRecord> getDialRecord(Date startTime, Date endTime, Integer offset, Integer limit) throws WxErrorException;

}
