package me.chanjar.weixin.cp.api;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;

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
  public void test() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);

    String createSpace = "{\"userid\":\"USERID\",\"space_name\":\"SPACE_NAME\",\"auth_info\":[{\"type\":1,\"userid\":\"USERID\",\"auth\":2},{\"type\":2,\"departmentid\":2,\"auth\":1}]}";
    WxCpSpaceCreateRequest wxCpSpaceCreateRequest = WxCpSpaceCreateRequest.fromJson(createSpace);
    log.info(wxCpSpaceCreateRequest.toJson());


    /**
     * 添加成员/部门
     * https://developer.work.weixin.qq.com/document/path/93656
     */
    WxCpSpaceAclAddRequest spaceAclAddRequest = new WxCpSpaceAclAddRequest();
    String uId = "WangKai";
    String spId = "s.ww45d3e188865aca30.652091685u4h";
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
