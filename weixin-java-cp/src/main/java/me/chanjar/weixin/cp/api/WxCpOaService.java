package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.*;

import java.util.Date;
import java.util.List;

/**
 * 企业微信OA相关接口.
 *
 * @author Element & Wang_Wong
 * @date 2019-04-06 10:52
 */
public interface WxCpOaService {

  /**
   * <pre>提交审批申请
   * 调试工具
   * 企业可通过审批应用或自建应用Secret调用本接口，代应用可见范围内员工在企业微信“审批应用”内提交指定类型的审批申请。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/91853
   * </pre>
   *
   * @param request 请求
   * @return 表单提交成功后 ，返回的表单编号
   * @throws WxErrorException .
   */
  String apply(WxCpOaApplyEventRequest request) throws WxErrorException;

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
   * @return 打卡数据列表 checkin data
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
   * @return 打卡规则列表 checkin option
   * @throws WxErrorException .
   */
  List<WxCpCheckinOption> getCheckinOption(Date datetime, List<String> userIdList) throws WxErrorException;


  /**
   * <pre>
   *   获取企业所有打卡规则
   *   API doc : https://work.weixin.qq.com/api/doc/90000/90135/93384
   * </pre>
   *
   * @return 打卡规则列表
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCropCheckinOption> getCropCheckinOption() throws WxErrorException;

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
   * @return WxCpApprovalInfo approval info
   * @throws WxErrorException .
   */
  WxCpApprovalInfo getApprovalInfo(@NonNull Date startTime, @NonNull Date endTime, Integer cursor, Integer size,
                                   List<WxCpApprovalInfoQueryFilter> filters) throws WxErrorException;

  /**
   * short method
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @return WxCpApprovalInfo approval info
   * @throws WxErrorException .
   * @see me.chanjar.weixin.cp.api.WxCpOaService#getApprovalInfo me.chanjar.weixin.cp.api.WxCpOaService#getApprovalInfo
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
   * @return WxCpApprovaldetail approval detail
   * @throws WxErrorException .
   */
  WxCpApprovalDetailResult getApprovalDetail(@NonNull String spNo) throws WxErrorException;


  /**
   * 获取企业假期管理配置
   * 企业可通过审批应用或自建应用Secret调用本接口，获取可见范围内员工的“假期管理”配置，包括：各个假期的id、名称、请假单位、时长计算方式、发放规则等。
   * 第三方应用可获取应用可见范围内员工的“假期管理”配置，包括：各个假期的id、名称、请假单位、时长计算方式、发放规则等。
   *
   * 请求方式：GET(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/oa/vacation/getcorpconf?access_token=ACCESS_TOKEN
   *
   * @return
   * @throws WxErrorException
   */
  WxCpCorpConfInfo getCorpConf() throws WxErrorException;


  /**
   * 获取成员假期余额
   * 企业可通过审批应用或自建应用Secret调用本接口，获取可见范围内各个员工的假期余额数据。
   * 第三方应用可获取应用可见范围内各个员工的假期余额数据。
   *
   * 请求方式：POST(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/oa/vacation/getuservacationquota?access_token=ACCESS_TOKEN
   *
   * @param userId 需要获取假期余额的成员的userid
   * @return
   * @throws WxErrorException
   */
  WxCpUserVacationQuota getUserVacationQuota(@NonNull String userId) throws WxErrorException;


  /**
   * 获取审批数据（旧）
   * 提示：推荐使用新接口“批量获取审批单号”及“获取审批申请详情”，此接口后续将不再维护、逐步下线。
   * 通过本接口来获取公司一段时间内的审批记录。一次拉取调用最多拉取100个审批记录，可以通过多次拉取的方式来满足需求，但调用频率不可超过600次/分。
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/corp/getapprovaldata?access_token=ACCESS_TOKEN
   *
   * @param startTime 获取审批记录的开始时间。Unix时间戳
   * @param endTime 获取审批记录的结束时间。Unix时间戳
   * @param nextSpNum 第一个拉取的审批单号，不填从该时间段的第一个审批单拉取
   * @return
   * @throws WxErrorException
   */
  WxCpGetApprovalData getApprovalData(@NonNull Long startTime, @NonNull Long endTime, Long nextSpNum) throws WxErrorException;


  /**
   * 修改成员假期余额
   * 企业可通过审批应用或自建应用Secret调用本接口，修改可见范围内员工的“假期余额”。
   * 第三方应用可通过应本接口修改应用可见范围内指定员工的“假期余额”。
   *
   * 请求方式：POST(HTTPS)
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/oa/vacation/setoneuserquota?access_token=ACCESS_TOKEN
   *
   * @param userId 需要修改假期余额的成员的userid
   * @param vacationId 假期id
   * @param leftDuration 设置的假期余额，单位为秒，不能大于1000天或24000小时，当假期时间刻度为按小时请假时，必须为360整倍数，即0.1小时整倍数，按天请假时，必须为8640整倍数，即0.1天整倍数
   * @param timeAttr 假期时间刻度：0-按天请假；1-按小时请假
   * @param remarks 修改备注，用于显示在假期余额的修改记录当中，可对修改行为作说明，不超过200字符
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp setOneUserQuota(@NonNull String userId, @NonNull Integer vacationId, @NonNull Integer leftDuration, @NonNull Integer timeAttr, String remarks) throws WxErrorException;


  /**
   * 获取公费电话拨打记录
   *
   * @param startTime 查询的起始时间戳
   * @param endTime   查询的结束时间戳
   * @param offset    分页查询的偏移量
   * @param limit     分页查询的每页大小,默认为100条，如该参数大于100则按100处理
   * @return . dial record
   * @throws WxErrorException .
   */
  List<WxCpDialRecord> getDialRecord(Date startTime, Date endTime, Integer offset,
                                     Integer limit) throws WxErrorException;

  /**
   * 获取审批模板详情
   *
   * @param templateId 模板ID
   * @return . template detail
   * @throws WxErrorException .
   */
  WxCpTemplateResult getTemplateDetail(@NonNull String templateId) throws WxErrorException;


  /**
   * 获取打卡日报数据
   *
   * @param startTime  获取日报的开始时间
   * @param endTime    获取日报的结束时间
   * @param userIdList 获取日报的userid列表
   * @return 日报数据列表 checkin day data
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCheckinDayData> getCheckinDayData(Date startTime, Date endTime, List<String> userIdList) throws WxErrorException;


  /**
   * 获取打卡月报数据
   *
   * @param startTime  获取月报的开始时间
   * @param endTime    获取月报的结束时间
   * @param userIdList 获取月报的userid列表
   * @return 月报数据列表
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCheckinMonthData> getCheckinMonthData(Date startTime, Date endTime, List<String> userIdList) throws WxErrorException;

  /**
   * 获取打卡人员排班信息
   *
   * @param startTime  获取排班信息的开始时间。Unix时间戳
   * @param endTime    获取排班信息的结束时间。Unix时间戳（与starttime跨度不超过一个月）
   * @param userIdList 需要获取排班信息的用户列表（不超过100个）
   * @return 排班表信息
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCheckinSchedule> getCheckinScheduleList(Date startTime, Date endTime, List<String> userIdList) throws WxErrorException;


  /**
   * 为打卡人员排班
   *
   * @param wxCpSetCheckinSchedule the wx cp set checkin schedule
   * @throws WxErrorException the wx error exception
   */
  void setCheckinScheduleList(WxCpSetCheckinSchedule wxCpSetCheckinSchedule) throws WxErrorException;
  /**
   * <pre>
   * 录入打卡人员人脸信息
   * 企业可通过打卡应用Secret调用本接口，为企业打卡人员录入人脸信息，人脸信息仅用于人脸打卡。
   * 上传图片大小限制:图片数据不超过1M
   * 请求方式：POST(HTTPS)
   * 请求地址：<a href="https://qyapi.weixin.qq.com/cgi-bin/checkin/addcheckinuserface?access_token=ACCESS_TOKEN">https://qyapi.weixin.qq.com/cgi-bin/checkin/addcheckinuserface?access_token=ACCESS_TOKEN</a>
   * 文档地址：<a href="https://developer.work.weixin.qq.com/document/path/93378">https://developer.work.weixin.qq.com/document/path/93378</a>
   * <pre>
   * @param userId 需要录入的用户id
   * @param userFace 需要录入的人脸图片数据，需要将图片数据base64处理后填入，对已录入的人脸会进行更新处理
   */
  void addCheckInUserFace(String userId, String userFace) throws WxErrorException;
}
