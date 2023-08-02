package me.chanjar.weixin.cp.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.school.user.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 企业微信家校沟通相关接口.
 * https://developer.work.weixin.qq.com/document/path/91638
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on : 2022/6/18 9:10
 */
@Slf4j
public class WxCpSchoolUserTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  /**
   * Test.
   *
   * @throws WxErrorException the wx error exception
   */
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
    log.info("list:{}", list);

    final String userId = "WangKai";
    final String exUserId = "wmOQpTDwAAJFHrryZ8I8ALLEZuLHIUKA";


    /**
     * 获取部门家长详情
     *
     * https://developer.work.weixin.qq.com/document/path/92446
     */
    WxCpListParentResult userListParent = cpService.getSchoolUserService().getUserListParent(1);

    String jsonUserListParentResult = "{\n" +
      "    \"errcode\": 0,\n" +
      "    \"errmsg\": \"ok\",\n" +
      "    \"parents\": [\n" +
      "        {\n" +
      "            \"parent_userid\": \"zhangsan_parent\",\n" +
      "            \"mobile\": \"18900000000\",\n" +
      "            \"is_subscribe\": 1,\n" +
      "\t\t\t\"external_userid\":\"xxx\",\n" +
      "            \"children\": [\n" +
      "                {\n" +
      "                    \"student_userid\": \"zhangsan\",\n" +
      "                    \"relation\": \"爸爸\",\n" +
      "                    \"name\": \"张三\"\n" +
      "                }\n" +
      "            ]\n" +
      "        },\n" +
      "\t\t{\n" +
      "            \"parent_userid\": \"lisi_parent\",\n" +
      "            \"mobile\": \"18900000001\",\n" +
      "            \"is_subscribe\": 0,\n" +
      "            \"children\": [\n" +
      "                {\n" +
      "                    \"student_userid\": \"lisi\",\n" +
      "                    \"relation\": \"妈妈\",\n" +
      "                    \"name\": \"李四\"\n" +
      "                }\n" +
      "            ]\n" +
      "        }\n" +
      "    ]\n" +
      "}";

    WxCpListParentResult wxCpListParentResult = WxCpListParentResult.fromJson(jsonUserListParentResult);
    assertThat(wxCpListParentResult.toJson()).isEqualTo(GsonParser.parse(jsonUserListParentResult).toString());


    /**
     * 获取部门成员详情
     *
     * https://developer.work.weixin.qq.com/document/path/92043
     */
    WxCpUserListResult userList = cpService.getSchoolUserService().getUserList(1, 0);

    String jsonUserListResult = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"students\":[\n" +
      "\t\t{\n" +
      "\t\t\t\"student_userid\": \"zhangsan\",\n" +
      "\t\t\t\"name\": \"张三\",\n" +
      "\t\t\t\"department\": [1, 2],\n" +
      "\t\t\t\"parents\": [\n" +
      "\t\t\t\t{\n" +
      "\t\t\t\t\t\"parent_userid\": \"zhangsan_parent1\",\n" +
      "\t\t\t\t\t\"relation\": \"爸爸\",\n" +
      "\t\t\t\t\t\"mobile\": \"18000000001\",\n" +
      "\t\t\t\t\t\"is_subscribe\": 1,\n" +
      "\t\t\t\t\t\"external_userid\":\"xxx\"\n" +
      "\t\t\t\t},\n" +
      "\t\t\t\t{\n" +
      "\t\t\t\t\t\"parent_userid\": \"zhangsan_parent2\",\n" +
      "\t\t\t\t\t\"relation\": \"妈妈\",\n" +
      "\t\t\t\t\t\"mobile\": \"18000000002\",\n" +
      "\t\t\t\t\t\"is_subscribe\": 0\n" +
      "\t\t\t\t}\n" +
      "\t\t\t]\n" +
      "\t\t},\n" +
      "\t\t{\n" +
      "\t\t\t\"student_userid\": \"lisi\",\n" +
      "\t\t\t\"name\": \"李四\",\n" +
      "\t\t\t\"department\": [4, 5],\n" +
      "\t\t\t\"parents\": [\n" +
      "\t\t\t\t{\n" +
      "\t\t\t\t\t\"parent_userid\": \"lisi_parent1\",\n" +
      "\t\t\t\t\t\"relation\": \"爷爷\",\n" +
      "\t\t\t\t\t\"mobile\": \"18000000003\",\n" +
      "\t\t\t\t\t\"is_subscribe\": 1,\n" +
      "\t\t\t\t\t\"external_userid\":\"xxx\"\n" +
      "\t\t\t\t},\n" +
      "\t\t\t\t{\n" +
      "\t\t\t\t\t\"parent_userid\": \"lisi_parent2\",\n" +
      "\t\t\t\t\t\"relation\": \"妈妈\",\n" +
      "\t\t\t\t\t\"mobile\": \"18000000004\",\n" +
      "\t\t\t\t\t\"is_subscribe\": 1,\n" +
      "\t\t\t\t\t\"external_userid\":\"xxx\"\n" +
      "\t\t\t\t}\n" +
      "\t\t\t]\n" +
      "\t\t}\n" +
      "\t]\n" +
      "}";

    WxCpUserListResult wxCpUserListResult = WxCpUserListResult.fromJson(jsonUserListResult);
    assertThat(wxCpUserListResult.toJson()).isEqualTo(GsonParser.parse(jsonUserListResult).toString());

    /**
     * 读取学生或家长
     *
     * https://developer.work.weixin.qq.com/document/path/92337
     */
    WxCpUserResult userResult = cpService.getSchoolUserService().getUser(userId);

    String jsonUserResult = "{\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"ok\",\n" +
      "\t\"user_type\": 1,\n" +
      "\t\"student\":{\n" +
      "\t\t\"student_userid\": \"zhangsan\",\n" +
      "\t\t\"name\": \"张三\",\n" +
      "\t\t\"department\": [1, 2],\n" +
      "\t\t\"parents\":[\n" +
      "\t\t\t{\n" +
      "\t\t\t\t\"parent_userid\": \"zhangsan_parent1\",\n" +
      "\t\t\t\t\"relation\": \"爸爸\",\n" +
      "\t\t\t\t\"mobile\":\"18000000000\",\n" +
      "\t\t\t\t\"is_subscribe\": 1,\n" +
      "\t\t\t\t\"external_userid\":\"xxxxx\"\n" +
      "\t\t\t},\n" +
      "\t\t\t{\n" +
      "\t\t\t\t\"parent_userid\": \"zhangsan_parent2\",\n" +
      "\t\t\t\t\"relation\": \"妈妈\",\n" +
      "\t\t\t\t\"mobile\": \"18000000001\",\n" +
      "\t\t\t\t\"is_subscribe\": 0\n" +
      "\t\t\t}\n" +
      "\t\t]\n" +
      "    },\n" +
      "\t\"parent\":{\n" +
      "\t\t\"parent_userid\": \"zhangsan_parent2\",\n" +
      "\t\t\"mobile\": \"18000000003\",\n" +
      "\t\t\"is_subscribe\": 1,\n" +
      "\t\t\"external_userid\":\"xxxxx\",\n" +
      "\t\t\"children\":[\n" +
      "\t\t\t{\n" +
      "\t\t\t\t\"student_userid\": \"zhangsan\",\n" +
      "\t\t\t\t\"relation\": \"妈妈\"\n" +
      "\t\t\t},\n" +
      "\t\t\t{\n" +
      "\t\t\t\t\"student_userid\": \"lisi\",\n" +
      "\t\t\t\t\"relation\": \"伯母\"\n" +
      "\t\t\t}\n" +
      "\t\t]\n" +
      "\t}\n" +
      "}";

    WxCpUserResult wxCpUserResult = WxCpUserResult.fromJson(jsonUserResult);
    assertThat(wxCpUserResult.toJson()).isEqualTo(GsonParser.parse(jsonUserResult).toString());


    /**
     * 批量更新家长
     *
     * https://developer.work.weixin.qq.com/document/path/92336
     */
    String batchUpdateParentRequestParam = "{\n" +
      "    \"parents\":[\n" +
      "        {   \n" +
      "            \"parent_userid\": \"zhangsan_baba\",\n" +
      "\t\t\t\"new_parent_userid\":\"zhangsan_baba_new\",\n" +
      "            \"mobile\": \"10000000000\",\n" +
      "            \"children\":[\n" +
      "                {   \n" +
      "                    \"student_userid\": \"zhangsan\",\n" +
      "                    \"relation\": \"爸爸\"\n" +
      "                }   \n" +
      "            ]   \n" +
      "        },  \n" +
      "        {   \n" +
      "            \"parent_userid\": \"lisi_mama\",\n" +
      "            \"mobile\": \"10000000001\",\n" +
      "            \"children\":[\n" +
      "                {\n" +
      "                    \"student_userid\": \"lisi\",\n" +
      "                    \"relation\": \"妈妈\"\n" +
      "                }   \n" +
      "            ]   \n" +
      "        }   \n" +
      "    ]   \n" +
      "}";
    WxCpBatchUpdateParentRequest batchUpdateParentRequest =
      WxCpBatchUpdateParentRequest.fromJson(batchUpdateParentRequestParam);
    WxCpBatchResultList batchUpdateParentResult =
      cpService.getSchoolUserService().batchUpdateParent(batchUpdateParentRequest);


    /**
     * 批量删除家长
     *
     * https://developer.work.weixin.qq.com/document/path/92335
     */
    WxCpBatchResultList batchDeleteParentResult = cpService.getSchoolUserService().batchDeleteParent("abc", userId);


    /**
     * 批量创建家长 封装请求参数
     *
     * https://developer.work.weixin.qq.com/document/path/92334
     */
    var child1 = WxCpBatchCreateParentRequest.Children.builder()
      .relation("爸爸")
      .studentUserId("zhangsan")
      .build();
    var child2 = WxCpBatchCreateParentRequest.Children.builder()
      .relation("伯父")
      .studentUserId("lisi")
      .build();
    var child3 = WxCpBatchCreateParentRequest.Children.builder()
      .relation("爸爸")
      .studentUserId("lisi")
      .build();
    var child4 = WxCpBatchCreateParentRequest.Children.builder()
      .relation("伯父")
      .studentUserId("zhangsan")
      .build();

    List<WxCpBatchCreateParentRequest.Children> childrenList1 = Lists.newArrayList();
    childrenList1.add(child1);
    childrenList1.add(child2);

    List<WxCpBatchCreateParentRequest.Children> childrenList2 = Lists.newArrayList();
    childrenList2.add(child3);
    childrenList2.add(child4);

    var zhangsanParent = WxCpBatchCreateParentRequest.Parent.builder()
      .parentUserId("zhangsan_parent_userid")
      .mobile("18000000000")
      .toInvite(false)
      .children(childrenList1)
      .build();
    var lisiParent = WxCpBatchCreateParentRequest.Parent.builder()
      .parentUserId("lisi_parent_userid")
      .mobile("18000000001")
      .children(childrenList2)
      .build();

    List<WxCpBatchCreateParentRequest.Parent> batchCreateParent = Lists.newArrayList();
    batchCreateParent.add(zhangsanParent);
    batchCreateParent.add(lisiParent);
    WxCpBatchCreateParentRequest wxCpBatchCreateParentRequest = WxCpBatchCreateParentRequest.builder()
      .parents(batchCreateParent)
      .build();

    // 请求参数json
    String batchCreateParentRequestParam = "{\n" +
      "\t\"parents\":[\n" +
      "\t\t{\n" +
      "\t\t\t\"parent_userid\": \"zhangsan_parent_userid\",\n" +
      "    \t\t\"mobile\": \"18000000000\",\n" +
      "\t\t\t\"to_invite\": false,\n" +
      "\t\t\t\"children\":[\n" +
      "\t\t\t\t{\n" +
      "\t\t\t\t\t\"student_userid\": \"zhangsan\",\n" +
      "    \t\t        \"relation\": \"爸爸\"\n" +
      "    \t\t    },\n" +
      "    \t\t    {\n" +
      "\t\t\t\t\t\"student_userid\": \"lisi\",\n" +
      "    \t\t        \"relation\": \"伯父\"\n" +
      "    \t\t    }\n" +
      "    \t\t]\n" +
      "\t\t},\n" +
      "\t\t{\n" +
      "\t\t\t\"parent_userid\": \"lisi_parent_userid\",\n" +
      "    \t\t\"mobile\": \"18000000001\",\n" +
      "\t\t\t\"children\":[\n" +
      "\t\t\t\t{\n" +
      "\t\t\t\t\t\"student_userid\": \"lisi\",\n" +
      "    \t\t        \"relation\": \"爸爸\"\n" +
      "    \t\t    },\n" +
      "    \t\t    {\n" +
      "\t\t\t\t\t\"student_userid\": \"zhangsan\",\n" +
      "    \t\t        \"relation\": \"伯父\"\n" +
      "    \t\t    }\n" +
      "    \t\t]\n" +
      "\t\t}\n" +
      "\t]\n" +
      "}";
    assertThat(wxCpBatchCreateParentRequest.toJson()).isEqualTo(GsonParser.parse(batchCreateParentRequestParam).toString());

    WxCpBatchResultList batchCreateParentResult =
      cpService.getSchoolUserService().batchCreateParent(wxCpBatchCreateParentRequest);

    // 返回结果
    String batchResultStr = "{\n" +
      "\t\"errcode\": 1,\n" +
      "\t\"errmsg\": \"invalid parent_userid: lisi_parent_userid\",\n" +
      "\t\"result_list\": [\n" +
      "\t\t{\n" +
      "\t\t\t\"parent_userid\": \"lisi_parent_userid\",\n" +
      "\t\t\t\"errcode\": 1,\n" +
      "\t\t\t\"errmsg\": \"invalid parent_userid: lisi_parent_userid\",\n" +
      "\t\t}\n" +
      "\t]\n" +
      "}";
    assertThat(batchCreateParentResult.toJson()).isEqualTo(GsonParser.parse(batchResultStr).toString());


    /**
     * 获取家校访问用户身份
     *
     * https://developer.work.weixin.qq.com/document/path/95791
     */
    WxCpOauth2UserInfo schoolUserInfo = cpService.getSchoolUserService().getSchoolUserInfo("abc");
    assertThat(schoolUserInfo).isNotNull();

    WxCpOauth2UserInfo oauth2UserInfo = cpService.getSchoolUserService().getUserInfo("abc");
    assertThat(oauth2UserInfo).isNotNull();

    WxCpOauth2UserInfo userInfo = cpService.getOauth2Service().getUserInfo("abc");
    assertThat(userInfo).isNotNull();


    // 返回值
    String batchResult = "{\n" +
      "\t\"errcode\": 1,\n" +
      "\t\"errmsg\": \"invalid student_userid: zhangsan\",\n" +
      "\t\"result_list\": [\n" +
      "\t\t{\n" +
      "\t\t\t\"student_userid\": \"zhangsan\",\n" +
      "\t\t\t\"errcode\": 1,\n" +
      "\t\t\t\"errmsg\": \"invalid student_userid: zhangsan\"\n" +
      "\t\t}\n" +
      "\t]\n" +
      "}";

    WxCpBatchResultList batchResultList = WxCpBatchResultList.fromJson(batchResult);
    log.info("batchResultList: {}", batchResultList.toJson());


    /**
     * 批量更新学生
     * https://developer.work.weixin.qq.com/document/path/92330
     *
     * 请求方式：POST（HTTPS）
     * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_update_student?access_token=ACCESS_TOKEN
     */
    String batchUpdateStudent = "{\n" +
      "\t\"students\":[\n" +
      "        {\n" +
      "\t\t\t\"student_userid\": \"zhangsan\",\n" +
      "\t\t\t\"new_student_userid\":\"zhangsan_new\",\n" +
      "\t\t\t\"name\": \"张三\",\n" +
      "\t\t\t\"department\": [1, 2]\n" +
      "\t\t},\n" +
      "        {\n" +
      "\t\t\t\"student_userid\": \"lisi\",\n" +
      "\t\t\t\"name\": \"李四\",\n" +
      "\t\t\t\"department\": [3, 4]\n" +
      "\t\t}\n" +
      "     ]\n" +
      "}";
    WxCpBatchUpdateStudentRequest batchUpdateStudentRequest =
      WxCpBatchUpdateStudentRequest.fromJson(batchUpdateStudent);
    WxCpBatchResultList list3 = cpService.getSchoolUserService().batchUpdateStudent(batchUpdateStudentRequest);
    log.info("list3: {}", list3.toJson());

    /**
     * 批量删除学生
     * https://developer.work.weixin.qq.com/document/path/92329
     *
     * 请求方式：POST（HTTPS）
     * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_delete_student?access_token=ACCESS_TOKEN
     */
    String batchDeleteStudent = "{\n" +
      "\t\"useridlist\": [\"zhangsan\", \"lisi\"]\n" +
      "}\n";
    WxCpBatchDeleteStudentRequest batchDeleteStudentRequest =
      WxCpBatchDeleteStudentRequest.fromJson(batchDeleteStudent);
    WxCpBatchResultList list2 = cpService.getSchoolUserService().batchDeleteStudent(batchDeleteStudentRequest);
    log.info("list2: {}", list2.toJson());

    /**
     * 批量创建学生
     * https://developer.work.weixin.qq.com/document/path/92328
     */
    String batchCreateStudent = "{\n" +
      "\t\"students\":[\n" +
      "        {\n" +
      "\t\t\t\"student_userid\": \"zhangsan\",\n" +
      "\t\t\t\"name\": \"张三\",\n" +
      "\t\t\t\"department\": [1, 2]\n" +
      "\t\t},\n" +
      "        {\n" +
      "\t\t\t\"student_userid\": \"lisi\",\n" +
      "\t\t\t\"name\": \"李四\",\n" +
      "\t\t\t\"department\": [3, 4]\n" +
      "\t\t}\n" +
      "     ]\n" +
      "}";
    WxCpBatchCreateStudentRequest batchCreateStudentRequest =
      WxCpBatchCreateStudentRequest.fromJson(batchCreateStudent);
    WxCpBatchResultList list1 = cpService.getSchoolUserService().batchCreateStudent(batchCreateStudentRequest);
    log.info("list1: {}", list1.toJson());


//    String changeContact = WxCpConsts.EventType.CHANGE_CONTACT;
    /**
     * 增加变更事件类型：
     */
//    WxCpConsts.EventType.CHANGE_SCHOOL_CONTACT;
//    WxCpConsts.SchoolContactChangeType.DELETE_STUDENT;
//    WxCpConsts.SchoolContactChangeType.CREATE_DEPARTMENT;

    /**
     * 测试家校通讯录变更回调
     * https://developer.work.weixin.qq.com/document/path/92052
     *
     * 新增学生事件
     * 当学校在家校通讯录中新增学生时，回调此事件。
     */
    String createStudentXml = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[change_school_contact]]></Event>\n" +
      "\t<ChangeType><![CDATA[create_student]]></ChangeType>\n" +
      "\t<Id><![CDATA[xiaoming]]></Id>\n" +
      "</xml>";

    /**
     * 家长取消关注事件
     * 当家长取消关注家校通知时，回调此事件。
     */
    String unSubscribeXml = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[change_school_contact]]></Event>\n" +
      "\t<ChangeType><![CDATA[unsubscribe]]></ChangeType>\n" +
      "\t<Id><![CDATA[xiaoming]]></Id>\n" +
      "</xml>";

    /**
     * 创建部门事件
     * 当学校在家校通讯录中创建部门时，回调此事件。
     */
    String createDepartmentXml = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[change_school_contact]]></Event>\n" +
      "\t<ChangeType><![CDATA[create_deparmtment]]></ChangeType>\n" +
      "\t<Id><![CDATA[1]]></Id>\n" +
      "</xml>";

    /**
     * 删除部门事件
     * 当学校删除家校通讯录部门时，回调此事件。
     */
    String deleteDepartmentXml = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "\t<CreateTime>1403610513</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[change_school_contact]]></Event>\n" +
      "\t<ChangeType><![CDATA[delete_deparmtment]]></ChangeType>\n" +
      "\t<Id><![CDATA[1]]></Id>\n" +
      "</xml>";

//    WxCpXmlMessage.fromXml(createStudentXml);
    final WxCpXmlMessage createStudentMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, createStudentXml);
    Map<String, Object> map1 = XmlUtils.xml2Map(createStudentXml);
    createStudentMsg.setAllFieldsMap(map1);
    log.info("createStudentMsg:{}", WxCpGsonBuilder.create().toJson(createStudentMsg));

    final WxCpXmlMessage unSubscribeMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, unSubscribeXml);
    Map<String, Object> map2 = XmlUtils.xml2Map(unSubscribeXml);
    unSubscribeMsg.setAllFieldsMap(map2);
    log.info("unSubscribeMsg:{}", WxCpGsonBuilder.create().toJson(unSubscribeMsg));

    final WxCpXmlMessage createDepartmentMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, createDepartmentXml);
    createDepartmentMsg.setAllFieldsMap(XmlUtils.xml2Map(createDepartmentXml));
    log.info("createDepartmentMsg:{}", WxCpGsonBuilder.create().toJson(createDepartmentMsg));

    final WxCpXmlMessage deleteDepartmentMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, deleteDepartmentXml);
    deleteDepartmentMsg.setAllFieldsMap(XmlUtils.xml2Map(deleteDepartmentXml));
    log.info("deleteDepartmentMsg:{}", WxCpGsonBuilder.create().toJson(deleteDepartmentMsg));


    /**
     * 获取可使用的家长范围
     * https://developer.work.weixin.qq.com/document/path/94895
     */
    String str8 = "{\n" +
      "   \"errcode\": 0,\n" +
      "   \"errmsg\": \"ok\",\n" +
      "   \"allow_scope\": {\n" +
      "       \"students\": [\n" +
      "             {\"userid\": \"student1\"},\n" +
      "             {\"userid\": \"student2\"}\n" +
      "       ],\n" +
      "\t   \"departments\": [1, 2]\n" +
      "    }\n" +
      "}";
    WxCpAllowScope cpAllowScope = WxCpAllowScope.fromJson(str8);
    log.info("cpAllowScope:{}", cpAllowScope.toJson());

    WxCpAllowScope allowScope = cpService.getSchoolUserService().getAllowScope(100000);
    log.info("allowScope:{}", allowScope);

    /**
     * 外部联系人openid转换
     * https://developer.work.weixin.qq.com/document/path/92323
     */
    String openId = cpService.getSchoolUserService().convertToOpenId("wmOQpTDwAAh_sKvmJBJ4FQ0iYAcbppFA");
    log.info("openId:{}", openId);

    /**
     * 家校沟通 获取外部联系人详情
     * https://developer.work.weixin.qq.com/document/path/92322
     */
    String str7 = "{\"errcode\":0,\"errmsg\":\"ok\",\"external_contact\":{\"external_userid\":\"woAAAA\"," +
      "\"name\":\"李四\",\"position\":\"Mangaer\",\"avatar\":\"http://p.qlogo.cn/bizmail/IcsdgagqefergqerhewSdage/0\"," +
      "\"corp_name\":\"腾讯\",\"corp_full_name\":\"腾讯科技有限公司\",\"type\":2,\"gender\":1,\"unionid\":\"unAAAAA\"," +
      "\"is_subscribe\":1,\"subscriber_info\":{\"tag_id\":[\"TAG_ID1\",\"TAG_ID2\"]," +
      "\"remark_mobiles\":[\"10000000000\",\"10000000001\"],\"remark\":\"李小明-爸爸\"}," +
      "\"external_profile\":{\"external_attr\":[{\"type\":0,\"name\":\"文本名称\",\"text\":{\"value\":\"文本\"}}," +
      "{\"type\":1,\"name\":\"网页名称\",\"web\":{\"url\":\"http://www.test.com\",\"title\":\"标题\"}},{\"type\":2," +
      "\"name\":\"测试app\",\"miniprogram\":{\"appid\":\"wxAAAAA\",\"pagepath\":\"/index\",\"title\":\"my " +
      "miniprogram\"}}]}},\"follow_user\":[{\"userid\":\"rocky\",\"remark\":\"李部长\",\"description\":\"对接采购事物\"," +
      "\"createtime\":1525779812,\"tags\":[{\"group_name\":\"标签分组名称\",\"tag_name\":\"标签名称\",\"type\":1}]," +
      "\"remark_corp_name\":\"腾讯科技\",\"remark_mobiles\":[10000000003,10000000004]},{\"userid\":\"tommy\"," +
      "\"remark\":\"李总\",\"description\":\"采购问题咨询\",\"createtime\":1525881637,\"state\":\"外联二维码1\"}]}";
    WxCpExternalContact wxCpExternalContact = WxCpExternalContact.fromJson(str7);
    log.info("wxCpExternalContact:{}", wxCpExternalContact.toJson());

//    cpService.getExternalContactService().getExternalContact();
    WxCpExternalContact externalContact = cpService.getSchoolUserService().getExternalContact(exUserId);
    log.info("externalContact:{}", externalContact.toJson());

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
    String str5 = "{\"errcode\":0,\"errmsg\":\"ok\",\"departments\":[{\"name\":\"一年级\",\"parentid\":1,\"id\":2," +
      "\"type\":2,\"register_year\":2018,\"standard_grade\":1,\"order\":1," +
      "\"department_admins\":[{\"userid\":\"zhangsan\",\"type\":1},{\"userid\":\"lisi\",\"type\":2}]," +
      "\"is_graduated\":0},{\"name\":\"一年级一班\",\"parentid\":1,\"id\":3,\"type\":1," +
      "\"department_admins\":[{\"userid\":\"zhangsan\",\"type\":3,\"subject\":\"语文\"},{\"userid\":\"lisi\"," +
      "\"type\":4,\"subject\":\"数学\"}],\"open_group_chat\":1,\"group_chat_id\":\"group_chat_id\"}]}";
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
    String str4 = "{\"name\":\"一年级\",\"parentid\":5,\"id\":2,\"register_year\":2018,\"standard_grade\":1,\"order\":1," +
      "\"new_id\":100,\"department_admins\":[{\"op\":0,\"userid\":\"zhangsan\",\"type\":3,\"subject\":\"语文\"}," +
      "{\"op\":1,\"userid\":\"lisi\",\"type\":4,\"subject\":\"数学\"}]}";
    WxCpUpdateDepartmentRequest wxCpUpdateDepartmentRequest = WxCpUpdateDepartmentRequest.fromJson(str4);
    log.info("wxCpUpdateParentRequest:{}", wxCpUpdateDepartmentRequest.toJson());

    WxCpBaseResp updateDepartment = cpService.getSchoolUserService().updateDepartment(wxCpUpdateDepartmentRequest);
    log.info("updateDepartment:{}", updateDepartment.toJson());

    /**
     * 创建部门
     * https://developer.work.weixin.qq.com/document/path/92340
     */
    String str3 = "{\"name\":\"一年级\",\"parentid\":5,\"id\":2,\"type\":1,\"register_year\":2018,\"standard_grade\":1," +
      "\"order\":1,\"department_admins\":[{\"userid\":\"zhangsan\",\"type\":4,\"subject\":\"语文\"}," +
      "{\"userid\":\"lisi\",\"type\":3,\"subject\":\"数学\"}]}";
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
    String str2 = "{\"parent_userid\":\"zhangsan_parent_userid\",\"new_parent_userid\":\"NEW_ID\"," +
      "\"mobile\":\"18000000000\",\"children\":[{\"student_userid\":\"zhangsan\",\"relation\":\"爸爸\"}," +
      "{\"student_userid\":\"lisi\",\"relation\":\"伯父\"}]}";
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
    String str1 = "{\"parent_userid\":\"zhangsan_parent_userid\",\"mobile\":\"10000000000\",\"to_invite\":false," +
      "\"children\":[{\"student_userid\":\"zhangsan\",\"relation\":\"爸爸\"},{\"student_userid\":\"lisi\"," +
      "\"relation\":\"伯父\"}]}";
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
