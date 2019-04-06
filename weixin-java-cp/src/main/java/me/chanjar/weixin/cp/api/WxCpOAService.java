package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpCheckinData;
import me.chanjar.weixin.cp.bean.WxCpCheckinOption;

import java.util.Date;
import java.util.List;

/**
 * @author Element
 * @Package me.chanjar.weixin.cp.api
 * @date 2019-04-06 10:52
 * @Description: <pre>
 *     企业微信OA相关接口
 *
 * </pre>
 */
public interface WxCpOAService {

  /**
   * <pre>
   *     获取打卡数据
   *     API doc : https://work.weixin.qq.com/api/doc#90000/90135/90262
   * </pre>
   * @param openCheckinDataType 打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
   * @param starttime 获取打卡记录的开始时间
   * @param endtime 获取打卡记录的结束时间
   * @param userIdList  需要获取打卡记录的用户列表
   */
  List<WxCpCheckinData> getCheckinData(Integer openCheckinDataType, Date starttime, Date endtime, String[] userIdList) throws WxErrorException;

  List<WxCpCheckinOption> getCheckinOption(Date datetime, String[] userIdList) throws WxErrorException;

  void getApprovalData(Date starttime,Date endtime,Long nextSpnum) throws WxErrorException;

}
