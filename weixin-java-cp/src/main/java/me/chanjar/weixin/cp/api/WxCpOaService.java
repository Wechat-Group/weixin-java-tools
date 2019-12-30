package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.*;

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
  List<WxCpCheckinData> getCheckinData(Integer openCheckinDataType, Date startTime, Date endTime,
                                       List<String> userIdList) throws WxErrorException;

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
   *
   * 批量获取审批单号
   *
   * 审批应用及有权限的自建应用，可通过Secret调用本接口，以获取企业一段时间内企业微信“审批应用”单据的审批编号，支持按模板类型、申请人、部门、申请单审批状态等条件筛选。
   * 自建应用调用此接口，需在“管理后台-应用管理-审批-API-审批数据权限”中，授权应用允许提交审批单据。
   *
   * 一次拉取调用最多拉取100个审批记录，可以通过多次拉取的方式来满足需求，但调用频率不可超过600次/分。
   *
   * API doc : https://work.weixin.qq.com/api/doc/90000/90135/91816
   * </pre>
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @param cursor    分页查询游标，默认为0，后续使用返回的next_cursor进行分页拉取
   * @param size      一次请求拉取审批单数量，默认值为100，上限值为100
   * @param filters   筛选条件，可对批量拉取的审批申请设置约束条件，支持设置多个条件,nullable
   * @return ApprovalInfo
   */
  WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime, Integer cursor, Integer size,
                                   List<WxCpApprovalInfoQueryFilter> filters) throws WxErrorException;

  /**
   * short method
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @return WxCpApprovalInfo
   * @throws WxErrorException
   * @see me.chanjar.weixin.cp.api.WxCpOaService#getApprovalInfo
   */
  WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime) throws WxErrorException;

  /**
   * <pre>
   *   获取审批申请详情
   *
   *   企业可通过审批应用或自建应用Secret调用本接口，根据审批单号查询企业微信“审批应用”的审批申请详情。
   *
   *   API Doc : https://work.weixin.qq.com/api/doc/90000/90135/91983
   * </pre>
   *
   * @param spNo 审批单编号。
   * @return WxCpApprovaldetail
   * @throws WxErrorException
   */
  WxCpApprovaldetail getApprovalDetail(@NonNull String spNo) throws WxErrorException;

  /**
   * <pre>
   *   获取审批数据 (已过期, 请使用"批量获取审批单号" && "获取审批申请详情")
   *   通过本接口来获取公司一段时间内的审批记录。一次拉取调用最多拉取10000个审批记录，可以通过多次拉取的方式来满足需求，但调用频率不可超过600次/分。
   *   API doc : https://work.weixin.qq.com/api/doc#90000/90135/91530
   * </pre>
   *
   * @param startTime 获取审批记录的开始时间
   * @param endTime   获取审批记录的结束时间
   * @param nextSpnum 第一个拉取的审批单号，不填从该时间段的第一个审批单拉取
   * @throws WxErrorException
   * @see me.chanjar.weixin.cp.api.WxCpOaService#getApprovalInfo
   * @see me.chanjar.weixin.cp.api.WxCpOaService#getApprovalDetail
   */
  @Deprecated
  WxCpApprovalDataResult getApprovalData(Date startTime, Date endTime, Long nextSpnum) throws WxErrorException;

  /**
   * 获取公费电话拨打记录
   *
   * @param startTime 查询的起始时间戳
   * @param endTime   查询的结束时间戳
   * @param offset    分页查询的偏移量
   * @param limit     分页查询的每页大小,默认为100条，如该参数大于100则按100处理
   * @return
   * @throws WxErrorException
   */
  List<WxCpDialRecord> getDialRecord(Date startTime, Date endTime, Integer offset,
                                     Integer limit) throws WxErrorException;

}
