package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.school.WxCpCustomizeHealthInfo;
import me.chanjar.weixin.cp.bean.school.WxCpResultList;

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

}
