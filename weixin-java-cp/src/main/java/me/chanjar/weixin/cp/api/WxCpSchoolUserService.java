package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.school.user.WxCpCreateParentRequest;
import me.chanjar.weixin.cp.bean.school.user.WxCpUpdateParentRequest;

import java.util.List;

/**
 * 企业微信家校沟通相关接口.
 * https://developer.work.weixin.qq.com/document/path/91638
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date: 2022/6/18 9:10
 */
public interface WxCpSchoolUserService {

  /**
   * 创建学生
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/create_student?access_token=ACCESS_TOKEN
   *
   * @param studentUserId
   * @param name
   * @param departments
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp createStudent(@NonNull String studentUserId, @NonNull String name, @NonNull List<Integer> departments) throws WxErrorException;

  /**
   * 删除学生
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/delete_student?access_token=ACCESS_TOKEN&userid=USERID
   *
   * @param studentUserId
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp deleteStudent(@NonNull String studentUserId) throws WxErrorException;

  /**
   * 更新学生
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/update_student?access_token=ACCESS_TOKEN
   *
   * @param studentUserId
   * @param newStudentUserId
   * @param name
   * @param departments
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp updateStudent(@NonNull String studentUserId, String newStudentUserId, String name, List<Integer> departments) throws WxErrorException;

  /**
   * 创建家长
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/create_parent?access_token=ACCESS_TOKEN
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp createParent(@NonNull WxCpCreateParentRequest request) throws WxErrorException;

  /**
   * 更新家长
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/update_parent?access_token=ACCESS_TOKEN
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp updateParent(@NonNull WxCpUpdateParentRequest request) throws WxErrorException;

  /**
   * 删除家长
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/delete_parent?access_token=ACCESS_TOKEN&userid=USERID
   *
   * @param userId
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp deleteParent(@NonNull String userId) throws WxErrorException;

  /**
   * 设置家校通讯录自动同步模式
   * 企业和第三方可通过此接口修改家校通讯录与班级标签之间的自动同步模式，注意，一旦设置禁止自动同步，将无法再次开启。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/set_arch_sync_mode?access_token=ACCESS_TOKEN
   *
   * @param archSyncMode 家校通讯录同步模式：1-禁止将标签同步至家校通讯录，2-禁止将家校通讯录同步至标签，3-禁止家校通讯录和标签相互同步
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp setArchSyncMode(@NonNull Integer archSyncMode) throws WxErrorException;

}
