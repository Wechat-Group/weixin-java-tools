package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.living.WxCpLivingResult;
import me.chanjar.weixin.cp.bean.school.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 企业微信家校应用 复学码相关接口.
 * https://developer.work.weixin.qq.com/document/path/93744
 * <p>
 * 权限说明：
 * 仅复学码应用可以调用
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date: 2022/5/31 9:10
 */
public interface WxCpSchoolService {

  /**
   * 获取老师健康信息
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/user/get_teacher_customize_health_info?access_token=ACCESS_TOKEN
   *
   * @param date
   * @param nextKey
   * @param limit
   * @return
   * @throws WxErrorException
   */
  WxCpCustomizeHealthInfo getTeacherCustomizeHealthInfo(@NotNull String date, String nextKey, Integer limit) throws WxErrorException;

  /**
   * 获取学生健康信息
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/user/get_student_customize_health_info?access_token=ACCESS_TOKEN
   *
   * @param date
   * @param nextKey
   * @param limit
   * @return
   * @throws WxErrorException
   */
  WxCpCustomizeHealthInfo getStudentCustomizeHealthInfo(@NotNull String date, String nextKey, Integer limit) throws WxErrorException;

  /**
   * 获取师生健康码
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/get_health_qrcode?access_token=ACCESS_TOKEN
   *
   * @param userIds
   * @param type
   * @return
   * @throws WxErrorException
   */
  WxCpResultList getHealthQrCode(@NotNull List<String> userIds, @NotNull Integer type) throws WxErrorException;

  /**
   * 获取学生付款结果
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/get_payment_result?access_token=ACCESS_TOKEN
   *
   * @param paymentId
   * @return
   * @throws WxErrorException
   */
  WxCpPaymentResult getPaymentResult(@NotNull String paymentId) throws WxErrorException;

  /**
   * 获取订单详情
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/get_trade?access_token=ACCESS_TOKEN
   *
   * @param paymentId
   * @param tradeNo
   * @return
   * @throws WxErrorException
   */
  WxCpTrade getTrade(@NotNull String paymentId, @NotNull String tradeNo) throws WxErrorException;

  /**
   * 获取直播详情
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/living/get_living_info?access_token=ACCESS_TOKEN&livingid=LIVINGID
   *
   * @param livingId
   * @return
   */
  WxCpSchoolLivingInfo getLivingInfo(@NotNull String livingId) throws WxErrorException;

  /**
   * 获取老师直播ID列表
   * 通过此接口可以获取指定老师的所有直播ID
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/living/get_user_all_livingid?access_token=ACCESS_TOKEN
   *
   * @param userId
   * @param cursor
   * @param limit
   * @return
   * @throws WxErrorException
   */
  WxCpLivingResult.LivingIdResult getUserAllLivingId(@NonNull String userId, String cursor, Integer limit) throws WxErrorException;

  /**
   * 获取观看直播统计
   * 通过该接口可以获取所有观看直播的人员统计
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/living/get_watch_stat?access_token=ACCESS_TOKEN
   *
   * @param livingId
   * @param nextKey
   * @return
   * @throws WxErrorException
   */
  WxCpSchoolWatchStat getWatchStat(@NonNull String livingId, String nextKey) throws WxErrorException;

  /**
   * 获取未观看直播统计
   * 通过该接口可以获取未观看直播的学生统计，学生的家长必须是已经关注「学校通知」才会纳入统计范围。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/living/get_unwatch_stat?access_token=ACCESS_TOKEN
   *
   * @param livingId
   * @param nextKey
   * @return
   * @throws WxErrorException
   */
  WxCpSchoolUnwatchStat getUnwatchStat(@NonNull String livingId, String nextKey) throws WxErrorException;

  /**
   * 删除直播回放
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/living/delete_replay_data?access_token=ACCESS_TOKEN
   *
   * @param livingId
   * @return
   * @throws WxErrorException
   */
  WxCpLivingResult deleteReplayData(@NonNull String livingId) throws WxErrorException;

}
