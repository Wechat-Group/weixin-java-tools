package me.chanjar.weixin.cp.constant;


import lombok.experimental.UtilityClass;

/**
 * <pre>
 *  企业微信api地址常量类
 *  Created by BinaryWang on 2019-06-02.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@UtilityClass
public final class WxCpApiPathConsts {
  public static final String DEFAULT_CP_BASE_URL = "https://qyapi.weixin.qq.com";

  public static final String GET_JSAPI_TICKET = "/cgi-bin/get_jsapi_ticket";
  public static final String GET_AGENT_CONFIG_TICKET = "/cgi-bin/ticket/get?&type=agent_config";
  public static final String GET_CALLBACK_IP = "/cgi-bin/getcallbackip";
  public static final String BATCH_REPLACE_PARTY = "/cgi-bin/batch/replaceparty";
  public static final String BATCH_REPLACE_USER = "/cgi-bin/batch/replaceuser";
  public static final String BATCH_GET_RESULT = "/cgi-bin/batch/getresult?jobid=";
  public static final String JSCODE_TO_SESSION = "/cgi-bin/miniprogram/jscode2session";
  public static final String GET_TOKEN = "/cgi-bin/gettoken?corpid=%s&corpsecret=%s";
  public static final String WEBHOOK_SEND = "/cgi-bin/webhook/send?key=";

  /**
   * 消息推送相关接口
   * https://work.weixin.qq.com/api/doc/90000/90135/90235
   */
  @UtilityClass
  public static class Message {
    /**
     * 发送应用消息
     */
    public static final String MESSAGE_SEND = "/cgi-bin/message/send";

    /**
     * 查询应用消息发送统计
     */
    public static final String GET_STATISTICS = "/cgi-bin/message/get_statistics";

    /**
     * 互联企业发送应用消息
     */
    public static final String LINKEDCORP_MESSAGE_SEND = "/cgi-bin/linkedcorp/message/send";
  }

  @UtilityClass
  public static class Agent {
    public static final String AGENT_GET = "/cgi-bin/agent/get?agentid=%d";
    public static final String AGENT_SET = "/cgi-bin/agent/set";
    public static final String AGENT_LIST = "/cgi-bin/agent/list";
  }

  @UtilityClass
  public static class WorkBench {
    public static final String WORKBENCH_TEMPLATE_SET = "/cgi-bin/agent/set_workbench_template";
    public static final String WORKBENCH_TEMPLATE_GET = "/cgi-bin/agent/get_workbench_template";
    public static final String WORKBENCH_DATA_SET = "/cgi-bin/agent/set_workbench_data";
  }

  @UtilityClass
  public static class OAuth2 {
    public static final String GET_USER_INFO = "/cgi-bin/user/getuserinfo?code=%s&agentid=%d";
    public static final String GET_USER_DETAIL = "/cgi-bin/user/getuserdetail";
    public static final String URL_OAUTH2_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
  }

  @UtilityClass
  public static class Chat {
    public static final String APPCHAT_CREATE = "/cgi-bin/appchat/create";
    public static final String APPCHAT_UPDATE = "/cgi-bin/appchat/update";
    public static final String APPCHAT_GET_CHATID = "/cgi-bin/appchat/get?chatid=";
    public static final String APPCHAT_SEND = "/cgi-bin/appchat/send";
  }

  @UtilityClass
  public static class Department {
    public static final String DEPARTMENT_CREATE = "/cgi-bin/department/create";
    public static final String DEPARTMENT_UPDATE = "/cgi-bin/department/update";
    public static final String DEPARTMENT_DELETE = "/cgi-bin/department/delete?id=%d";
    public static final String DEPARTMENT_LIST = "/cgi-bin/department/list";
  }

  @UtilityClass
  public static class Media {
    public static final String MEDIA_GET = "/cgi-bin/media/get";
    public static final String MEDIA_UPLOAD = "/cgi-bin/media/upload?type=";
    public static final String IMG_UPLOAD = "/cgi-bin/media/uploadimg";
    public static final String JSSDK_MEDIA_GET = "/cgi-bin/media/get/jssdk";
  }

  @UtilityClass
  public static class Menu {
    public static final String MENU_CREATE = "/cgi-bin/menu/create?agentid=%d";
    public static final String MENU_DELETE = "/cgi-bin/menu/delete?agentid=%d";
    public static final String MENU_GET = "/cgi-bin/menu/get?agentid=%d";
  }

  @UtilityClass
  public static class Oa {
    public static final String GET_CHECKIN_DATA = "/cgi-bin/checkin/getcheckindata";
    public static final String GET_CHECKIN_OPTION = "/cgi-bin/checkin/getcheckinoption";
    public static final String GET_APPROVAL_INFO = "/cgi-bin/oa/getapprovalinfo";
    public static final String GET_APPROVAL_DETAIL = "/cgi-bin/oa/getapprovaldetail";
    public static final String GET_DIAL_RECORD = "/cgi-bin/dial/get_dial_record";
    public static final String GET_TEMPLATE_DETAIL = "/cgi-bin/oa/gettemplatedetail";
    public static final String APPLY_EVENT = "/cgi-bin/oa/applyevent";

    public static final String CALENDAR_ADD = "/cgi-bin/oa/calendar/add";
    public static final String CALENDAR_UPDATE = "/cgi-bin/oa/calendar/update";
    public static final String CALENDAR_GET = "/cgi-bin/oa/calendar/get";
    public static final String CALENDAR_DEL = "/cgi-bin/oa/calendar/del";
  }

  @UtilityClass
  public static class Tag {
    public static final String TAG_CREATE = "/cgi-bin/tag/create";
    public static final String TAG_UPDATE = "/cgi-bin/tag/update";
    public static final String TAG_DELETE = "/cgi-bin/tag/delete?tagid=%s";
    public static final String TAG_LIST = "/cgi-bin/tag/list";
    public static final String TAG_GET = "/cgi-bin/tag/get?tagid=%s";
    public static final String TAG_ADD_TAG_USERS = "/cgi-bin/tag/addtagusers";
    public static final String TAG_DEL_TAG_USERS = "/cgi-bin/tag/deltagusers";
  }

  @UtilityClass
  public static class TaskCard {
    public static final String UPDATE_TASK_CARD = "/cgi-bin/message/update_taskcard";
  }

  @UtilityClass
  public static class Tp {
    public static final String JSCODE_TO_SESSION = "/cgi-bin/service/miniprogram/jscode2session";
    public static final String GET_CORP_TOKEN = "/cgi-bin/service/get_corp_token";
    public static final String GET_PERMANENT_CODE = "/cgi-bin/service/get_permanent_code";
    public static final String GET_SUITE_TOKEN = "/cgi-bin/service/get_suite_token";
    public static final String GET_PROVIDER_TOKEN = "/cgi-bin/service/get_provider_token";
    public static final String GET_PREAUTH_CODE = "/cgi-bin/service/get_pre_auth_code";
    public static final String GET_AUTH_INFO = "/cgi-bin/service/get_auth_info";
    public static final String GET_AUTH_CORP_JSAPI_TICKET = "/cgi-bin/get_jsapi_ticket";
    public static final String GET_SUITE_JSAPI_TICKET = "/cgi-bin/ticket/get";
    public static final String GET_USERINFO3RD = "/cgi-bin/service/getuserinfo3rd";
    public static final String GET_USERDETAIL3RD = "/cgi-bin/service/getuserdetail3rd";
  }

  @UtilityClass
  public static class User {
    public static final String USER_AUTHENTICATE = "/cgi-bin/user/authsucc?userid=";
    public static final String USER_CREATE = "/cgi-bin/user/create";
    public static final String USER_UPDATE = "/cgi-bin/user/update";
    public static final String USER_DELETE = "/cgi-bin/user/delete?userid=";
    public static final String USER_BATCH_DELETE = "/cgi-bin/user/batchdelete";
    public static final String USER_GET = "/cgi-bin/user/get?userid=";
    public static final String USER_LIST = "/cgi-bin/user/list?department_id=";
    public static final String USER_SIMPLE_LIST = "/cgi-bin/user/simplelist?department_id=";
    public static final String BATCH_INVITE = "/cgi-bin/batch/invite";
    public static final String USER_CONVERT_TO_OPENID = "/cgi-bin/user/convert_to_openid";
    public static final String USER_CONVERT_TO_USERID = "/cgi-bin/user/convert_to_userid";
    public static final String GET_USER_ID = "/cgi-bin/user/getuserid";
    public static final String GET_EXTERNAL_CONTACT = "/cgi-bin/crm/get_external_contact?external_userid=";
    public static final String GET_JOIN_QR_CODE = "/cgi-bin/corp/get_join_qrcode?size_type=";
  }

  @UtilityClass
  public static class ExternalContact {
    @Deprecated
    public static final String GET_EXTERNAL_CONTACT = "/cgi-bin/crm/get_external_contact?external_userid=";

    public static final String ADD_CONTACT_WAY = "/cgi-bin/externalcontact/add_contact_way";
    public static final String GET_CONTACT_WAY = "/cgi-bin/externalcontact/get_contact_way";
    public static final String UPDATE_CONTACT_WAY = "/cgi-bin/externalcontact/update_contact_way";
    public static final String DEL_CONTACT_WAY = "/cgi-bin/externalcontact/del_contact_way";
    public static final String CLOSE_TEMP_CHAT = "/cgi-bin/externalcontact/close_temp_chat";
    public static final String GET_FOLLOW_USER_LIST = "/cgi-bin/externalcontact/get_follow_user_list";
    public static final String GET_CONTACT_DETAIL = "/cgi-bin/externalcontact/get?external_userid=";
    public static final String GET_CONTACT_DETAIL_BATCH = "/cgi-bin/externalcontact/batch/get_by_user?";
    public static final String UPDATE_REMARK = "/cgi-bin/externalcontact/remark";
    public static final String LIST_EXTERNAL_CONTACT = "/cgi-bin/externalcontact/list?userid=";
    public static final String LIST_UNASSIGNED_CONTACT = "/cgi-bin/externalcontact/get_unassigned_list";
    public static final String TRANSFER_UNASSIGNED_CONTACT = "/cgi-bin/externalcontact/transfer";
    public static final String GROUP_CHAT_LIST = "/cgi-bin/externalcontact/groupchat/list";
    public static final String GROUP_CHAT_INFO = "/cgi-bin/externalcontact/groupchat/get";
    public static final String LIST_USER_BEHAVIOR_DATA = "/cgi-bin/externalcontact/get_user_behavior_data";
    public static final String LIST_GROUP_CHAT_DATA = "/cgi-bin/externalcontact/groupchat/statistic";
    public static final String ADD_MSG_TEMPLATE = "/cgi-bin/externalcontact/add_msg_template";
    public static final String SEND_WELCOME_MSG = "/cgi-bin/externalcontact/send_welcome_msg";

    public static final String GET_CORP_TAG_LIST = "/cgi-bin/externalcontact/get_corp_tag_list";
    public static final String ADD_CORP_TAG = "/cgi-bin/externalcontact/add_corp_tag";
    public static final String EDIT_CORP_TAG = "/cgi-bin/externalcontact/edit_corp_tag";
    public static final String DEL_CORP_TAG = "/cgi-bin/externalcontact/del_corp_tag";
    public static final String MARK_TAG = "/cgi-bin/externalcontact/mark_tag";
  }
}
