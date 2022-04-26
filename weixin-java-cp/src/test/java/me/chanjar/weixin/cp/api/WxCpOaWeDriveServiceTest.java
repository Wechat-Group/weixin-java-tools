package me.chanjar.weixin.cp.api;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.WxCpSpaceCreateData;
import me.chanjar.weixin.cp.bean.oa.wedrive.WxCpSpaceCreateRequest;
import me.chanjar.weixin.cp.bean.oa.wedrive.WxCpSpaceRenameRequest;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;

import java.io.InputStream;

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
