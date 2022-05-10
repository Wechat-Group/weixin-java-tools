package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.*;

/**
 * 企业微信微盘相关接口.
 * https://developer.work.weixin.qq.com/document/path/93654
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date 2022-04-22
 */
public interface WxCpOaWeDriveService {

  /**
   * 新建空间
   * 该接口用于在微盘内新建空间，可以指定人创建空间。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_create?access_token=ACCESS_TOKEN
   *
   * @param request 新建空间对应请求参数
   * @return spaceid（空间id）
   * @throws WxErrorException
   */
  WxCpSpaceCreateData spaceCreate(@NonNull WxCpSpaceCreateRequest request) throws WxErrorException;

  /**
   * 重命名空间
   * 该接口用于重命名已有空间，接收userid参数，以空间管理员身份来重命名。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_rename?access_token=ACCESS_TOKEN
   *
   * @param request 重命名空间的请求参数
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp spaceRename(@NonNull WxCpSpaceRenameRequest request) throws WxErrorException;

  /**
   * 解散空间
   * 该接口用于解散已有空间，需要以空间管理员身份来解散。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_dismiss?access_token=ACCESS_TOKEN
   *
   * @param userId
   * @param spaceId
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp spaceDismiss(@NonNull String userId, @NonNull String spaceId) throws WxErrorException;

  /**
   * 获取空间信息
   * 该接口用于获取空间成员列表、信息、权限等信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_info?access_token=ACCESS_TOKEN
   *
   * @param userId
   * @param spaceId
   * @return
   * @throws WxErrorException
   */
  WxCpSpaceInfo spaceInfo(@NonNull String userId, @NonNull String spaceId) throws WxErrorException;

  /**
   * 添加成员/部门
   * 该接口用于对指定空间添加成员/部门，可一次性添加多个。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_acl_add?access_token=ACCESS_TOKEN
   *
   * @param request 添加成员/部门请求参数
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp spaceAclAdd(@NonNull WxCpSpaceAclAddRequest request) throws WxErrorException;

}
