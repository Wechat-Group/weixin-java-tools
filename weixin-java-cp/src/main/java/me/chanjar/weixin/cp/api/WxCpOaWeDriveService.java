package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.*;

import java.util.List;

/**
 * 企业微信微盘相关接口.
 * https://developer.work.weixin.qq.com/document/path/93654
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on  2022-04-22
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
   * @return spaceid （空间id）
   * @throws WxErrorException the wx error exception
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
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp spaceRename(@NonNull WxCpSpaceRenameRequest request) throws WxErrorException;

  /**
   * 解散空间
   * 该接口用于解散已有空间，需要以空间管理员身份来解散。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_dismiss?access_token=ACCESS_TOKEN
   *
   * @param userId  the user id
   * @param spaceId the space id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp spaceDismiss(@NonNull String userId, @NonNull String spaceId) throws WxErrorException;

  /**
   * 获取空间信息
   * 该接口用于获取空间成员列表、信息、权限等信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_info?access_token=ACCESS_TOKEN
   *
   * @param userId  the user id
   * @param spaceId the space id
   * @return wx cp space info
   * @throws WxErrorException the wx error exception
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
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp spaceAclAdd(@NonNull WxCpSpaceAclAddRequest request) throws WxErrorException;

  /**
   * 移除成员/部门
   * 该接口用于对指定空间移除成员/部门，操作者需要有移除权限。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_acl_del?access_token=ACCESS_TOKEN
   *
   * @param request 移除成员/部门请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp spaceAclDel(@NonNull WxCpSpaceAclDelRequest request) throws WxErrorException;

  /**
   * 权限管理
   * 该接口用于修改空间权限，需要传入userid，修改权限范围继承传入用户的权限范围。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_setting?access_token=ACCESS_TOKEN
   *
   * @param request 权限管理请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp spaceSetting(@NonNull WxCpSpaceSettingRequest request) throws WxErrorException;

  /**
   * 获取邀请链接
   * 该接口用于获取空间邀请分享链接。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/space_share?access_token=ACCESS_TOKEN
   *
   * @param userId  the user id
   * @param spaceId the space id
   * @return wx cp space share
   * @throws WxErrorException the wx error exception
   */
  WxCpSpaceShare spaceShare(@NonNull String userId, @NonNull String spaceId) throws WxErrorException;

  /**
   * 获取文件列表
   * 该接口用于获取指定地址下的文件列表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_list?access_token=ACCESS_TOKEN
   *
   * @param request 获取文件列表请求参数
   * @return wx cp file list
   * @throws WxErrorException the wx error exception
   */
  WxCpFileList fileList(@NonNull WxCpFileListRequest request) throws WxErrorException;

  /**
   * 上传文件
   * 该接口用于向微盘中的指定位置上传文件。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_upload?access_token=ACCESS_TOKEN
   *
   * @param request 上传文件请求参数
   * @return wx cp file upload
   * @throws WxErrorException the wx error exception
   */
  WxCpFileUpload fileUpload(@NonNull WxCpFileUploadRequest request) throws WxErrorException;

  /**
   * 下载文件
   * 该接口用于下载文件，请求的userid需有下载权限。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_download?access_token=ACCESS_TOKEN
   *
   * @param userId the user id
   * @param fileId the file id
   * @return wx cp file download
   * @throws WxErrorException the wx error exception
   */
  WxCpFileDownload fileDownload(@NonNull String userId, @NonNull String fileId) throws WxErrorException;

  /**
   * 重命名文件
   * 该接口用于对指定文件进行重命名。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_rename?access_token=ACCESS_TOKEN
   *
   * @param userId  the user id
   * @param fileId  the file id
   * @param newName the new name
   * @return wx cp file rename
   * @throws WxErrorException the wx error exception
   */
  WxCpFileRename fileRename(@NonNull String userId, @NonNull String fileId, @NonNull String newName) throws WxErrorException;

  /**
   * 新建文件/微文档
   * 该接口用于在微盘指定位置新建文件、微文档。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_create?access_token=ACCESS_TOKEN
   *
   * @param userId   操作者userid
   * @param spaceId  空间spaceid
   * @param fatherId 父目录fileid, 在根目录时为空间spaceid
   * @param fileType 文件类型, 1:文件夹 3:微文档(文档) 4:微文档(表格)
   * @param fileName 文件名字
   * @return wx cp file create
   * @throws WxErrorException the wx error exception
   */
  WxCpFileCreate fileCreate(@NonNull String userId, @NonNull String spaceId,
                            @NonNull String fatherId, @NonNull Integer fileType, @NonNull String fileName) throws WxErrorException;

  /**
   * 移动文件
   * 该接口用于将文件移动到指定位置。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_move?access_token=ACCESS_TOKEN
   *
   * @param request 移动文件的请求参数
   * @return wx cp file move
   * @throws WxErrorException the wx error exception
   */
  WxCpFileMove fileMove(@NonNull WxCpFileMoveRequest request) throws WxErrorException;

  /**
   * 删除文件
   * 该接口用于删除指定文件。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_delete?access_token=ACCESS_TOKEN
   *
   * @param userId 操作者userid
   * @param fileId 文件fileid列表
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp fileDelete(@NonNull String userId, @NonNull List<String> fileId) throws WxErrorException;

  /**
   * 新增指定人
   * 该接口用于对指定文件添加指定人/部门。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_acl_add?access_token=ACCESS_TOKEN
   *
   * @param request 新增指定人请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp fileAclAdd(@NonNull WxCpFileAclAddRequest request) throws WxErrorException;

  /**
   * 删除指定人
   * 该接口用于删除指定文件的指定人/部门。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_acl_del?access_token=ACCESS_TOKEN
   *
   * @param request 请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp fileAclDel(@NonNull WxCpFileAclDelRequest request) throws WxErrorException;

  /**
   * 分享设置
   * 该接口用于文件的分享设置。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_setting?access_token=ACCESS_TOKEN
   *
   * @param userId    the user id
   * @param fileId    the file id
   * @param authScope the auth scope
   * @param auth      the auth
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp fileSetting(@NonNull String userId, @NonNull String fileId, @NonNull Integer authScope, Integer auth) throws WxErrorException;

  /**
   * 获取分享链接
   * 该接口用于获取文件的分享链接。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_share?access_token=ACCESS_TOKEN
   *
   * @param userId the user id
   * @param fileId the file id
   * @return wx cp file share
   * @throws WxErrorException the wx error exception
   */
  WxCpFileShare fileShare(@NonNull String userId, @NonNull String fileId) throws WxErrorException;

  /**
   * 文件信息
   * 该接口用于获取指定文件的信息。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: https://qyapi.weixin.qq.com/cgi-bin/wedrive/file_info?access_token=ACCESS_TOKEN
   *
   * @param userId the user id
   * @param fileId the file id
   * @return wx cp file info
   * @throws WxErrorException the wx error exception
   */
  WxCpFileInfo fileInfo(@NonNull String userId, @NonNull String fileId) throws WxErrorException;

}
