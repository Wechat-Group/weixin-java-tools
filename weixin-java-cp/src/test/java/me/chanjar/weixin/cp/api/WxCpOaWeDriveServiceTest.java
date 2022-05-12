package me.chanjar.weixin.cp.api;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 微盘测试类.
 * 官方文档：https://developer.work.weixin.qq.com/document/path/93654
 *
 * @author Wang_Wong
 */
@Slf4j
public class WxCpOaWeDriveServiceTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  @Test
  public void test() throws Exception {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);

    String createSpace = "{\"userid\":\"USERID\",\"space_name\":\"SPACE_NAME\",\"auth_info\":[{\"type\":1,\"userid\":\"USERID\",\"auth\":2},{\"type\":2,\"departmentid\":2,\"auth\":1}]}";
    WxCpSpaceCreateRequest wxCpSpaceCreateRequest = WxCpSpaceCreateRequest.fromJson(createSpace);
    log.info(wxCpSpaceCreateRequest.toJson());

    String uId = "WangKai";
    String spId = "s.ww45d3e188865aca30.652091685u4h";
    // 空间的文件id
    String fileId = "s.ww45d3e188865aca30.652091685u4h_f.652344507ysDL";

    /**
     * 上传文件
     */
    WxCpFileUploadRequest fileUploadRequest = new WxCpFileUploadRequest();
    fileUploadRequest.setUserId(uId);
    fileUploadRequest.setSpaceId(spId);
    fileUploadRequest.setFatherId(spId);
    fileUploadRequest.setFileName("第一个文件");

    // 将文件转成base64字符串
    File file = new File("D:/info.log.2022-05-07.0.log");
    FileInputStream inputFile = new FileInputStream(file);
    byte[] buffer = new byte[(int)file.length()];
    inputFile.read(buffer);
    inputFile.close();
    String encodeBase64Content = new BASE64Encoder().encode(buffer);
    fileUploadRequest.setFileBase64Content(encodeBase64Content);

    WxCpFileUpload fileUpload = cpService.getOaWeDriveService().fileUpload(fileUploadRequest);
    log.info("上传文件为：{}", fileUpload.toJson());

    /**
     * 获取文件列表
     */
    WxCpFileListRequest fileListRequest = new WxCpFileListRequest();
    fileListRequest.setUserId(uId);
    fileListRequest.setSpaceId(spId);
    fileListRequest.setFatherId(spId);
    fileListRequest.setSortType(1);
    fileListRequest.setStart(0);
    fileListRequest.setLimit(100);

    WxCpFileList fileList = cpService.getOaWeDriveService().fileList(fileListRequest);
    log.info("获取文件列表为：{}", fileList.toJson());

    /**
     * 权限管理
     */
    WxCpSpaceSettingRequest spaceSettingRequest = new WxCpSpaceSettingRequest();
    spaceSettingRequest.setUserId(uId);
    spaceSettingRequest.setSpaceId(spId);
//    spaceSettingRequest.setEnableWatermark(false);
    spaceSettingRequest.setAddMemberOnlyAdmin(true);
    spaceSettingRequest.setEnableShareUrl(false);
    spaceSettingRequest.setShareUrlNoApprove(true);
    spaceSettingRequest.setShareUrlNoApproveDefaultAuth(2);

    WxCpBaseResp spaceSetting = cpService.getOaWeDriveService().spaceSetting(spaceSettingRequest);
    log.info("权限管理信息为：{}", spaceSetting.toJson());

    /**
     * 获取邀请链接
     */
    WxCpSpaceShare spaceShare = cpService.getOaWeDriveService().spaceShare(uId, spId);
    log.info("获取邀请链接信息为：{}", spaceShare.toJson());

    /**
     * 获取空间信息
     */
    WxCpSpaceInfo data = cpService.getOaWeDriveService().spaceInfo(uId, spId);
    log.info("获取空间信息为：{}", data.toJson());

    /**
     * 移除成员/部门
     */
    WxCpSpaceAclDelRequest spaceAclDelRequest = new WxCpSpaceAclDelRequest();
    spaceAclDelRequest.setUserId(uId);
    spaceAclDelRequest.setSpaceId(spId);

    // 被移除的空间成员信息
    WxCpSpaceAclDelRequest.AuthInfo delAuthInfo = new WxCpSpaceAclDelRequest.AuthInfo();
    delAuthInfo.setType(1);
    delAuthInfo.setUserId("MiaoMiu99");

    List<WxCpSpaceAclDelRequest.AuthInfo> delAuthInfoList = new ArrayList<>();
    delAuthInfoList.add(delAuthInfo);

    spaceAclDelRequest.setAuthInfo(delAuthInfoList);
    WxCpBaseResp spaceAclDel = cpService.getOaWeDriveService().spaceAclDel(spaceAclDelRequest);
    log.info("移除成员/部门，返回数据为：{}", spaceAclDel.toJson());

    /**
     * 添加成员/部门
     * https://developer.work.weixin.qq.com/document/path/93656
     */
    WxCpSpaceAclAddRequest spaceAclAddRequest = new WxCpSpaceAclAddRequest();
    spaceAclAddRequest.setUserId(uId);
    spaceAclAddRequest.setSpaceId(spId);

    List<WxCpSpaceAclAddRequest.AuthInfo> authInfoList = new ArrayList<>();
    // 被添加的空间成员信息
    WxCpSpaceAclAddRequest.AuthInfo authInfo = new WxCpSpaceAclAddRequest.AuthInfo();
    authInfo.setAuth(2);
    authInfo.setType(1);
    authInfo.setUserId("MiaoMiu99");

    authInfoList.add(authInfo);
    spaceAclAddRequest.setAuthInfo(authInfoList);

    WxCpBaseResp wxCpBaseResp = cpService.getOaWeDriveService().spaceAclAdd(spaceAclAddRequest);
    log.info("添加成员/部门，返回数据为：{}", wxCpBaseResp.toJson());

    /**
     * 获取空间信息
     */
    WxCpSpaceInfo spaceInfo = cpService.getOaWeDriveService().spaceInfo("WangKai", "s.ww45d3e188865aca30.652091685u4h");
    log.info("获取空间信息，spaceInfo信息为：{}", spaceInfo.toJson());

    /**
     * 新建空间
     */
    WxCpSpaceCreateRequest request = new WxCpSpaceCreateRequest();
    request.setUserId("WangKai");
    request.setSpaceName("测试云盘Three");

    WxCpSpaceCreateData spaceCreateData = cpService.getOaWeDriveService().spaceCreate(request);
    log.info("空间id为：{}", spaceCreateData.getSpaceId()); //
    log.info(spaceCreateData.toJson());

    /**
     * 重命名空间
     */
    WxCpSpaceRenameRequest wxCpSpaceRenameRequest = new WxCpSpaceRenameRequest();
    wxCpSpaceRenameRequest.setUserId("WangKai");
    wxCpSpaceRenameRequest.setSpaceId(spaceCreateData.getSpaceId());
    wxCpSpaceRenameRequest.setSpaceName("测试云盘Four");
    WxCpBaseResp baseResp = cpService.getOaWeDriveService().spaceRename(wxCpSpaceRenameRequest);
    log.info("重命名成功：{}", baseResp.toJson());

    /**
     * 解散空间
     */
    WxCpBaseResp thisResp = cpService.getOaWeDriveService().spaceDismiss("WangKai", spaceCreateData.getSpaceId());
    log.info("解散成功：{}", thisResp.toJson());

  }

}
