package me.chanjar.weixin.cp.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.school.user.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 企业微信家校沟通相关接口.
 * https://developer.work.weixin.qq.com/document/path/91638
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date: 2022/6/18 9:10
 */
@Slf4j
public class WxCpSchoolUserTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  @Test
  public void test() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);

    List<Integer> list = Lists.newArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    log.info("list:{}", list.toString());

    final String userId = "WangKai";


    /**
     * 获取关注「学校通知」的模式
     * 可通过此接口获取家长关注「学校通知」的模式：“可扫码填写资料加入”或“禁止扫码填写资料加入”
     * https://developer.work.weixin.qq.com/document/path/92290
     */
    Integer subscribeMode = cpService.getSchoolUserService().getSubscribeMode();
    log.info("subscribeMode:{}", subscribeMode);

    /**
     * 管理「学校通知」的关注模式
     * 设置关注「学校通知」的模式
     * https://developer.work.weixin.qq.com/document/path/92290
     */
    WxCpBaseResp setSubscribeMode = cpService.getSchoolUserService().setSubscribeMode(1);
    log.info("setSubscribeMode:{}", setSubscribeMode.toJson());

    /**
     * 获取「学校通知」二维码
     * https://developer.work.weixin.qq.com/document/path/92320
     */
    String str6 = "{\n" +
      "   \"errcode\": 0,\n" +
      "   \"errmsg\": \"ok\",\n" +
      "   \"qrcode_big\":\"http://p.qpic.cn/wwhead/XXXX\",\n" +
      "   \"qrcode_middle\":\"http://p.qpic.cn/wwhead/XXXX\",\n" +
      "   \"qrcode_thumb\":\"http://p.qpic.cn/wwhead/XXXX\"\n" +
      "}";

    WxCpSubscribeQrCode cpSubscribeQrCode = WxCpSubscribeQrCode.fromJson(str6);
    log.info("cpSubscribeQrCode:{}", cpSubscribeQrCode.toJson());

    WxCpSubscribeQrCode subscribeQrCode = cpService.getSchoolUserService().getSubscribeQrCode();
    log.info("subscribeQrCode:{}", subscribeQrCode.toJson());

    /**
     * 修改自动升年级的配置
     * https://developer.work.weixin.qq.com/document/path/92949
     */
    WxCpSetUpgradeInfo wxCpSetUpgradeInfo = cpService.getSchoolUserService().setUpgradeInfo(1594090969L, 2);
    log.info("wxCpSetUpgradeInfo:{}", wxCpSetUpgradeInfo.toJson());

    /**
     * 获取部门列表
     * https://developer.work.weixin.qq.com/document/path/92343
     */
    String str5 = "{\"errcode\":0,\"errmsg\":\"ok\",\"departments\":[{\"name\":\"一年级\",\"parentid\":1,\"id\":2,\"type\":2,\"register_year\":2018,\"standard_grade\":1,\"order\":1,\"department_admins\":[{\"userid\":\"zhangsan\",\"type\":1},{\"userid\":\"lisi\",\"type\":2}],\"is_graduated\":0},{\"name\":\"一年级一班\",\"parentid\":1,\"id\":3,\"type\":1,\"department_admins\":[{\"userid\":\"zhangsan\",\"type\":3,\"subject\":\"语文\"},{\"userid\":\"lisi\",\"type\":4,\"subject\":\"数学\"}],\"open_group_chat\":1,\"group_chat_id\":\"group_chat_id\"}]}";
    WxCpDepartmentList wxCpDepartmentList = WxCpDepartmentList.fromJson(str5);
    log.info("wxCpDepartmentList:{}", wxCpDepartmentList.toJson());

    WxCpDepartmentList departmentList = cpService.getSchoolUserService().listDepartment(7);
    log.info("departmentList:{}", departmentList.toJson());

    /**
     * 删除部门
     * https://developer.work.weixin.qq.com/document/path/92342
     */
    WxCpBaseResp deleteDepartment = cpService.getSchoolUserService().deleteDepartment(7);
    log.info("deleteDepartment:{}", deleteDepartment.toJson());

    /**
     * 更新部门
     * https://developer.work.weixin.qq.com/document/path/92341
     */
    String str4 = "{\"name\":\"一年级\",\"parentid\":5,\"id\":2,\"register_year\":2018,\"standard_grade\":1,\"order\":1,\"new_id\":100,\"department_admins\":[{\"op\":0,\"userid\":\"zhangsan\",\"type\":3,\"subject\":\"语文\"},{\"op\":1,\"userid\":\"lisi\",\"type\":4,\"subject\":\"数学\"}]}";
    WxCpUpdateDepartmentRequest wxCpUpdateDepartmentRequest = WxCpUpdateDepartmentRequest.fromJson(str4);
    log.info("wxCpUpdateParentRequest:{}", wxCpUpdateDepartmentRequest.toJson());

    WxCpBaseResp updateDepartment = cpService.getSchoolUserService().updateDepartment(wxCpUpdateDepartmentRequest);
    log.info("updateDepartment:{}", updateDepartment.toJson());

    /**
     * 创建部门
     * https://developer.work.weixin.qq.com/document/path/92340
     */
    String str3 = "{\"name\":\"一年级\",\"parentid\":5,\"id\":2,\"type\":1,\"register_year\":2018,\"standard_grade\":1,\"order\":1,\"department_admins\":[{\"userid\":\"zhangsan\",\"type\":4,\"subject\":\"语文\"},{\"userid\":\"lisi\",\"type\":3,\"subject\":\"数学\"}]}";
    WxCpCreateDepartmentRequest wxCpCreateDepartmentRequest = WxCpCreateDepartmentRequest.fromJson(str3);
    log.info("wxCpCreateDepartmentRequest:{}", wxCpCreateDepartmentRequest.toJson());

    WxCpCreateDepartmentRequest createDepartmentRequest = new WxCpCreateDepartmentRequest();
    createDepartmentRequest.setParentId(5);
    createDepartmentRequest.setName("一年级");
    createDepartmentRequest.setId(2);
    createDepartmentRequest.setType(1);
    createDepartmentRequest.setRegisterYear(2018);
    createDepartmentRequest.setStandardGrade(1);
    createDepartmentRequest.setOrder(1);

    var departmentAdmin = new WxCpCreateDepartmentRequest.DepartmentAdmin();
    departmentAdmin.setUserId(userId);
    departmentAdmin.setType(4);
    departmentAdmin.setSubject("英语");
    List<WxCpCreateDepartmentRequest.DepartmentAdmin> createDepartList = Lists.newArrayList();
    createDepartList.add(departmentAdmin);

    createDepartmentRequest.setDepartmentAdmins(createDepartList);
    WxCpCreateDepartment createDepartment = cpService.getSchoolUserService().createDepartment(createDepartmentRequest);
    log.info("createDepartment:{}", createDepartment.toJson());

    /**
     * 更新家长
     * https://developer.work.weixin.qq.com/document/path/92333
     */
    String str2 = "{\"parent_userid\":\"zhangsan_parent_userid\",\"new_parent_userid\":\"NEW_ID\",\"mobile\":\"18000000000\",\"children\":[{\"student_userid\":\"zhangsan\",\"relation\":\"爸爸\"},{\"student_userid\":\"lisi\",\"relation\":\"伯父\"}]}";
    WxCpUpdateParentRequest updateParentRequest1 = WxCpUpdateParentRequest.fromJson(str2);
    log.info("updateParentRequest1：{}", updateParentRequest1.toJson());

    WxCpUpdateParentRequest updateParentRequest = new WxCpUpdateParentRequest();
    updateParentRequest.setParentUserId("zhangsan");
    updateParentRequest.setMobile("17324399999");
    updateParentRequest.setNewParentUserId("wangkai");

    var child = new WxCpUpdateParentRequest.Children();
    child.setStudentUserId("zhangguiyuan");
    child.setRelation("伯父");

    List<WxCpUpdateParentRequest.Children> childList = Lists.newArrayList();
    childList.add(child);
    updateParentRequest.setChildren(childList);

    WxCpBaseResp updateParent = cpService.getSchoolUserService().updateParent(updateParentRequest);
    log.info("updateParent：{}", updateParent.toJson());

    /**
     * 删除家长
     * https://developer.work.weixin.qq.com/document/path/92332
     */
    WxCpBaseResp deleteParent = cpService.getSchoolUserService().deleteParent("zhangsan");
    log.info("deleteParent：{}", deleteParent.toJson());

    /**
     * 创建家长
     * https://developer.work.weixin.qq.com/document/path/92331
     */
    String str1 = "{\"parent_userid\":\"zhangsan_parent_userid\",\"mobile\":\"10000000000\",\"to_invite\":false,\"children\":[{\"student_userid\":\"zhangsan\",\"relation\":\"爸爸\"},{\"student_userid\":\"lisi\",\"relation\":\"伯父\"}]}";
    WxCpCreateParentRequest createParentRequest1 = WxCpCreateParentRequest.fromJson(str1);
    log.info("createParentRequest1：{}", createParentRequest1.toJson());

    WxCpCreateParentRequest createParentRequest = new WxCpCreateParentRequest();
    createParentRequest.setParentUserId("wangkai");
    createParentRequest.setMobile("17324398888");
    createParentRequest.setToInvite(false);

    var children1 = new WxCpCreateParentRequest.Children();
    children1.setStudentUserId("zhangguiyuan");
    children1.setRelation("伯父");

    List<WxCpCreateParentRequest.Children> children = Lists.newArrayList();
    children.add(children1);
    createParentRequest.setChildren(children);

    WxCpBaseResp createParent = cpService.getSchoolUserService().createParent(createParentRequest);
    log.info("createParent：{}", createParent.toJson());

    /**
     * 设置家校通讯录自动同步模式
     * 企业和第三方可通过此接口修改家校通讯录与班级标签之间的自动同步模式，注意，一旦设置禁止自动同步，将无法再次开启。
     */
    WxCpBaseResp setArchSyncMode = cpService.getSchoolUserService().setArchSyncMode(2);
    log.info("setArchSyncMode：{}", setArchSyncMode.toJson());

    /**
     * 更新学生
     */
    WxCpBaseResp updateStudent = cpService.getSchoolUserService().updateStudent("WangKai", "wangkai", "王", list);
    log.info("updateStudent：{}", updateStudent.toJson());

    /**
     * 删除学生
     */
    WxCpBaseResp deleteStudent = cpService.getSchoolUserService().deleteStudent("WangKai");
    log.info("deleteStudent：{}", deleteStudent.toJson());

    /**
     * 创建学生
     */
    WxCpBaseResp student = cpService.getSchoolUserService().createStudent("WangKai", "王", list);
    log.info("student：{}", student.toJson());

  }

}
