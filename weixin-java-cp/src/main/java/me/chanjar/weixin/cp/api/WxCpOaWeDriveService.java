package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.wedrive.WxCpSpaceCreateData;
import me.chanjar.weixin.cp.bean.oa.wedrive.WxCpSpaceCreateRequest;

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
   *
   * @throws WxErrorException
   */
  WxCpSpaceCreateData spaceCreate(@NonNull WxCpSpaceCreateRequest request) throws WxErrorException;

}
