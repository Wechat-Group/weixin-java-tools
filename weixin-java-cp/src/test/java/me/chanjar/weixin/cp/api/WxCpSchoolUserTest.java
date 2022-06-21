package me.chanjar.weixin.cp.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.school.user.WxCpCreateParentRequest;
import me.chanjar.weixin.cp.bean.school.user.WxCpUpdateParentRequest;
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
