package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.meetingroom.WxCpOaMeetingRoom;

import java.util.List;

/**
 * 企业微信会议室接口.
 *
 * @author <a href="https://github.com/lm93129">lm93129</a> created on  2022年8月12日22:33:36
 */
public interface WxCpOaMeetingRoomService {
  /**
   * 创建会议室.
   * <pre>
   * 该接口用于通过应用在企业内创建一个会议室。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/add?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoom 会议室对象
   * @return 会议室ID string
   * @throws WxErrorException .
   */
  String addMeetingRoom(WxCpOaMeetingRoom meetingRoom) throws WxErrorException;

  /**
   * 查询会议室.
   * <pre>
   * 该接口用于通过应用在企业内查询会议室列表。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/list?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoomRequest 会议室查询对象
   * @return 会议室ID list
   * @throws WxErrorException .
   */
  List<WxCpOaMeetingRoom> listMeetingRoom(WxCpOaMeetingRoom meetingRoomRequest) throws WxErrorException;

  /**
   * 编辑会议室.
   * <pre>
   * 该接口用于通过应用在企业内编辑会议室。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/edit?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoom 会议室对象
   * @throws WxErrorException .
   */
  void editMeetingRoom(WxCpOaMeetingRoom meetingRoom) throws WxErrorException;

  /**
   * 编辑会议室.
   * <pre>
   * 该接口用于通过应用在企业内编辑会议室。
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/meetingroom/del?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/93619
   * </pre>
   *
   * @param meetingRoomId 会议室对象
   * @throws WxErrorException .
   */
  void deleteMeetingRoom(Integer meetingRoomId) throws WxErrorException;
}
