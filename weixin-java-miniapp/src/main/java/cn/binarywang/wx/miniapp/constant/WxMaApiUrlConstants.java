package cn.binarywang.wx.miniapp.constant;

import lombok.experimental.UtilityClass;

/**
 * 小程序接口地址常量.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2021-01-28
 */
@UtilityClass
public class WxMaApiUrlConstants {
  public interface Analysis {
    String GET_DAILY_SUMMARY_TREND_URL = "https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend";
    String GET_DAILY_VISIT_TREND_URL = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend";
    String GET_WEEKLY_VISIT_TREND_URL = "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyvisittrend";
    String GET_MONTHLY_VISIT_TREND_URL = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend";
    String GET_VISIT_DISTRIBUTION_URL = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitdistribution";
    String GET_DAILY_RETAIN_INFO_URL = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyretaininfo";
    String GET_WEEKLY_RETAIN_INFO_URL = "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyretaininfo";
    String GET_MONTHLY_RETAIN_INFO_URL = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyretaininfo";
    String GET_VISIT_PAGE_URL = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitpage";
    String GET_USER_PORTRAIT_URL = "https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait";
  }

  public interface Cloud {
    String INVOKE_CLOUD_FUNCTION_URL = "https://api.weixin.qq.com/tcb/invokecloudfunction?env=%s&name=%s";
    String DATABASE_COLLECTION_GET_URL = "https://api.weixin.qq.com/tcb/databasecollectionget";
    String DATABASE_COLLECTION_DELETE_URL = "https://api.weixin.qq.com/tcb/databasecollectiondelete";
    String DATABASE_COLLECTION_ADD_URL = "https://api.weixin.qq.com/tcb/databasecollectionadd";
    String GET_QCLOUD_TOKEN_URL = "https://api.weixin.qq.com/tcb/getqcloudtoken";
    String BATCH_DELETE_FILE_URL = "https://api.weixin.qq.com/tcb/batchdeletefile";
    String BATCH_DOWNLOAD_FILE_URL = "https://api.weixin.qq.com/tcb/batchdownloadfile";
    String UPLOAD_FILE_URL = "https://api.weixin.qq.com/tcb/uploadfile";
    String DATABASE_MIGRATE_QUERY_INFO_URL = "https://api.weixin.qq.com/tcb/databasemigratequeryinfo";
    String DATABASE_MIGRATE_EXPORT_URL = "https://api.weixin.qq.com/tcb/databasemigrateexport";
    String DATABASE_MIGRATE_IMPORT_URL = "https://api.weixin.qq.com/tcb/databasemigrateimport";
    String UPDATE_INDEX_URL = "https://api.weixin.qq.com/tcb/updateindex";
    String DATABASE_COUNT_URL = "https://api.weixin.qq.com/tcb/databasecount";
    String DATABASE_AGGREGATE_URL = "https://api.weixin.qq.com/tcb/databaseaggregate";
    String DATABASE_QUERY_URL = "https://api.weixin.qq.com/tcb/databasequery";
    String DATABASE_UPDATE_URL = "https://api.weixin.qq.com/tcb/databaseupdate";
    String DATABASE_DELETE_URL = "https://api.weixin.qq.com/tcb/databasedelete";
    String DATABASE_ADD_URL = "https://api.weixin.qq.com/tcb/databaseadd";
  }

  public interface Msg {
    String KEFU_MESSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    String TEMPLATE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
    String SUBSCRIBE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
    String UNIFORM_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send";
    String ACTIVITY_ID_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create";
    String UPDATABLE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/updatablemsg/send";
  }

  public interface Code {
    /**
     * 为授权的小程序帐号上传小程序代码.
     */
    String COMMIT_URL = "https://api.weixin.qq.com/wxa/commit";
    String GET_QRCODE_URL = "https://api.weixin.qq.com/wxa/get_qrcode";
    String GET_CATEGORY_URL = "https://api.weixin.qq.com/wxa/get_category";
    String GET_PAGE_URL = "https://api.weixin.qq.com/wxa/get_page";
    String SUBMIT_AUDIT_URL = "https://api.weixin.qq.com/wxa/submit_audit";
    String GET_AUDIT_STATUS_URL = "https://api.weixin.qq.com/wxa/get_auditstatus";
    String GET_LATEST_AUDIT_STATUS_URL = "https://api.weixin.qq.com/wxa/get_latest_auditstatus";
    String RELEASE_URL = "https://api.weixin.qq.com/wxa/release";
    String CHANGE_VISIT_STATUS_URL = "https://api.weixin.qq.com/wxa/change_visitstatus";
    String REVERT_CODE_RELEASE_URL = "https://api.weixin.qq.com/wxa/revertcoderelease";
    String GET_SUPPORT_VERSION_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion";
    String SET_SUPPORT_VERSION_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/setweappsupportversion";
    String UNDO_CODE_AUDIT_URL = "https://api.weixin.qq.com/wxa/undocodeaudit";
  }

  public interface Express {
    /**
     * 获取支持的快递公司列表
     */
    String ALL_DELIVERY_URL = "https://api.weixin.qq.com/cgi-bin/express/business/delivery/getall";
    /**
     * 获取所有绑定的物流账号
     */
    String ALL_ACCOUNT_URL = "https://api.weixin.qq.com/cgi-bin/express/business/account/getall";
    /**
     * 绑定、解绑物流账号
     */
    String BIND_ACCOUNT_URL = "https://api.weixin.qq.com/cgi-bin/express/business/account/bind";
    /**
     * 获取电子面单余额
     */
    String GET_QUOTA_URL = "https://api.weixin.qq.com/cgi-bin/express/business/quota/get";
    /**
     * 配置面单打印员
     */
    String UPDATE_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/update";
    /**
     * 获取打印员
     */
    String GET_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/getall";
    /**
     * 生成运单
     */
    String ADD_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/add";
    /**
     * 批量获取运单数据
     */
    String BATCH_GET_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/batchget";
    /**
     * 取消运单
     */
    String CANCEL_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/cancel";
    /**
     * 获取运单数据
     */
    String GET_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/get";
    /**
     * 查询运单轨迹
     */
    String GET_PATH_URL = "https://api.weixin.qq.com/cgi-bin/express/business/path/get";
    /**
     * 模拟快递公司更新订单状态
     */
    String TEST_UPDATE_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/test_update_order";
  }

  public interface ImgProc {
    /**
     * 二维码/条码识别
     */
    String QRCODE = "https://api.weixin.qq.com/cv/img/qrcode?img_url=%s";
    /**
     * 二维码/条码识别(文件)
     */
    String FILE_QRCODE = "https://api.weixin.qq.com/cv/img/qrcode";
    /**
     * 图片高清化
     */
    String SUPER_RESOLUTION = "https://api.weixin.qq.com/cv/img/superresolution?img_url=%s";
    /**
     * 图片高清化(文件)
     */
    String FILE_SUPER_RESOLUTION = "https://api.weixin.qq.com/cv/img/superresolution";
    /**
     * 图片智能裁剪
     */
    String AI_CROP = "https://api.weixin.qq.com/cv/img/aicrop?img_url=%s&ratios=%s";
    /**
     * 图片智能裁剪(文件)
     */
    String FILE_AI_CROP = "https://api.weixin.qq.com/cv/img/aicrop?ratios=%s";
  }

  public interface Jsapi {
    /**
     * 获得jsapi_ticket的url
     */
    String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
  }

  public interface Broadcast {
    String GET_LIVE_INFO = "https://api.weixin.qq.com/wxa/business/getliveinfo";

    /**
     * 直播间管理相关接口
     */
    interface Room {
      String CREATE_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/create";
      String ADD_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/room/addgoods";
      String DELETE_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/deleteroom";
      String EDIT_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/editroom";
      String GET_PUSH_URL = "https://api.weixin.qq.com/wxaapi/broadcast/room/getpushurl";
      String GET_SHARED_CODE = "https://api.weixin.qq.com/wxaapi/broadcast/room/getsharedcode";
      String ADD_ASSISTANT = "https://api.weixin.qq.com/wxaapi/broadcast/room/addassistant";
      String MODIFY_ASSISTANT = "https://api.weixin.qq.com/wxaapi/broadcast/room/modifyassistant";
      String REMOVE_ASSISTANT = "https://api.weixin.qq.com/wxaapi/broadcast/room/removeassistant";
      String GET_ASSISTANT_LIST = "https://api.weixin.qq.com/wxaapi/broadcast/room/getassistantlist";
    }

    /**
     * 直播商品管理相关接口
     */
    interface Goods {
      String ADD_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/add";
      String RESET_AUDIT_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/resetaudit";
      String AUDIT_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/audit";
      String DELETE_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/delete";
      String UPDATE_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/update";
      String GET_GOODS_WARE_HOUSE = "https://api.weixin.qq.com/wxa/business/getgoodswarehouse";
      String GET_APPROVED_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/goods/getapproved";
    }

    /**
     * 小程序直播成员管理接口
     */
    interface Role {
      String ADD_ROLE = "https://api.weixin.qq.com/wxaapi/broadcast/role/addrole";
      String DELETE_ROLE = "https://api.weixin.qq.com/wxaapi/broadcast/role/deleterole";
      String LIST_BY_ROLE = "https://api.weixin.qq.com/wxaapi/broadcast/role/getrolelist";
    }
  }

  public interface Media {
    String MEDIA_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?type=%s";
    String MEDIA_GET_URL = "https://api.weixin.qq.com/cgi-bin/media/get";
  }

  public interface Plugin {
    String PLUGIN_URL = "https://api.weixin.qq.com/wxa/plugin";
  }

  public interface Qrcode {
    String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode";
    String GET_WXACODE_URL = "https://api.weixin.qq.com/wxa/getwxacode";
    String GET_WXACODE_UNLIMIT_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";
  }

  public interface Run {

  }

  public interface Scheme {
    String GENERATE_SCHEME_URL = "https://api.weixin.qq.com/wxa/generatescheme";
  }

  public interface Link {
    String GENERATE_URLLINK_URL = "https://api.weixin.qq.com/wxa/generate_urllink";
  }

  public interface ShortLink {
    String GENERATE_SHORT_LINK_URL = "https://api.weixin.qq.com/wxa/genwxashortlink";
  }

  public interface SecCheck {
    String IMG_SEC_CHECK_URL = "https://api.weixin.qq.com/wxa/img_sec_check";
    String MSG_SEC_CHECK_URL = "https://api.weixin.qq.com/wxa/msg_sec_check";
    String MEDIA_CHECK_ASYNC_URL = "https://api.weixin.qq.com/wxa/media_check_async";
  }

  public interface Setting {
    /**
     * 修改服务器地址：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489138143_WPbOO&token=&lang=zh_CN
     * access_token 为 authorizer_access_token
     */
    String MODIFY_DOMAIN_URL = "https://api.weixin.qq.com/wxa/modify_domain";
    String SET_WEB_VIEW_DOMAIN_URL = "https://api.weixin.qq.com/wxa/setwebviewdomain";
    /**
     * 小程序成员管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140588_nVUgx&token=&lang=zh_CN
     * access_token 为 authorizer_access_token
     */
    String BIND_TESTER_URL = "https://api.weixin.qq.com/wxa/bind_tester";
    String UNBIND_TESTER_URL = "https://api.weixin.qq.com/wxa/unbind_tester";
  }

  public interface Share {

  }

  public interface Subscribe {
    /**
     * 获取模板标题下的关键词列表.
     */
    String GET_PUB_TEMPLATE_TITLE_LIST_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles";
    /**
     * 获取模板标题下的关键词列表.
     */
    String GET_PUB_TEMPLATE_KEY_WORDS_BY_ID_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatekeywords";
    /**
     * 组合模板并添加至帐号下的个人模板库.
     */
    String TEMPLATE_ADD_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate";
    /**
     * 获取当前帐号下的个人模板列表.
     */
    String TEMPLATE_LIST_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate";
    /**
     * 删除帐号下的某个模板.
     */
    String TEMPLATE_DEL_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/deltemplate";
    /**
     * 获取小程序账号的类目
     */
    String GET_CATEGORY_URL = "https://api.weixin.qq.com/wxaapi/newtmpl/getcategory";
    /**
     * 发送订阅消息
     */
    String SUBSCRIBE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
  }

  public interface User {
    String SET_USER_STORAGE = "https://api.weixin.qq.com/wxa/set_user_storage?appid=%s&signature=%s&openid=%s&sig_method=%s";
  }

  public interface Ocr {
    String IDCARD = "https://api.weixin.qq.com/cv/ocr/idcard?img_url=%s";
    String FILEIDCARD = "https://api.weixin.qq.com/cv/ocr/idcard";
    String BANK_CARD = "https://api.weixin.qq.com/cv/ocr/bankcard?img_url=%s";
    String FILE_BANK_CARD = "https://api.weixin.qq.com/cv/ocr/bankcard";
    String DRIVING = "https://api.weixin.qq.com/cv/ocr/driving?img_url=%s";
    String FILE_DRIVING = "https://api.weixin.qq.com/cv/ocr/driving";
    String DRIVING_LICENSE = "https://api.weixin.qq.com/cv/ocr/drivinglicense?img_url=%s";
    String FILE_DRIVING_LICENSE = "https://api.weixin.qq.com/cv/ocr/drivinglicense";
    String BIZ_LICENSE = "https://api.weixin.qq.com/cv/ocr/bizlicense?img_url=%s";
    String FILE_BIZ_LICENSE = "https://api.weixin.qq.com/cv/ocr/bizlicense";
    String COMM = "https://api.weixin.qq.com/cv/ocr/comm?img_url=%s";
    String FILE_COMM = "https://api.weixin.qq.com/cv/ocr/comm";
  }

  public interface Shop {
    interface Spu {
      String SPU_ADD_URL = "https://api.weixin.qq.com/shop/spu/add";
      String SPU_DEL_URL = "https://api.weixin.qq.com/shop/spu/del";
      String SPU_GET_URL = "https://api.weixin.qq.com/shop/spu/get";
      String SPU_GET_LIST_URL = "https://api.weixin.qq.com/shop/spu/get_list";
      String SPU_UPDATE_URL = "https://api.weixin.qq.com/shop/spu/update";
      String SPU_UPDATE_WITHOUT_URL = "https://api.weixin.qq.com/shop/spu/update_without_audit";
      String SPU_LISTING_URL = "https://api.weixin.qq.com/shop/spu/listing";
      String SPU_DELISTING_URL = "https://api.weixin.qq.com/shop/spu/delisting";
      String DEL_AUDIT_URL = "https://api.weixin.qq.com/shop/spu/del_audit";
    }

    interface Order {
      String ORDER_CHECK_SCENE = "https://api.weixin.qq.com/shop/scene/check";
      String ORDER_ADD = "https://api.weixin.qq.com/shop/order/add";
      String ORDER_PAY = "https://api.weixin.qq.com/shop/order/pay";
      String ORDER_GET = "https://api.weixin.qq.com/shop/order/get";
    }

    interface Register {
      String REGISTER_APPLY = "https://api.weixin.qq.com/shop/register/apply";
      String REGISTER_CHECK = "https://api.weixin.qq.com/shop/register/check";
      String REGISTER_FINISH_ACCESS_INFO = "https://api.weixin.qq.com/shop/register/finish_access_info";
      String REGISTER_APPLY_SCENE = "https://api.weixin.qq.com/shop/register/apply_scene";
    }

    interface Account {
      String GET_CATEGORY_LIST = "https://api.weixin.qq.com/shop/account/get_category_list";
      String GET_BRAND_LIST = "https://api.weixin.qq.com/shop/account/get_brand_list";
      String UPDATE_INFO = "https://api.weixin.qq.com/shop/account/update_info";
      String GET_INFO = "https://api.weixin.qq.com/shop/account/get_info";
    }

    interface Cat {
      String GET_CAT = "https://api.weixin.qq.com/shop/cat/get";
    }

    interface Img {
      String IMG_UPLOAD = "https://api.weixin.qq.com/shop/img/upload";
    }

    interface Audit {
      String AUDIT_BRAND = "https://api.weixin.qq.com/shop/audit/audit_brand";
      String AUDIT_CATEGORY = "https://api.weixin.qq.com/shop/audit/audit_category";
      String AUDIT_RESULT = "https://api.weixin.qq.com/shop/audit/result";
      String GET_MINIAPP_CERTIFICATE = "https://api.weixin.qq.com/shop/audit/get_miniapp_certificate";
    }

    interface Delivery {
      String GET_COMPANY_LIST = "https://api.weixin.qq.com/shop/delivery/get_company_list";
      String DELIVERY_SEND = "https://api.weixin.qq.com/shop/delivery/send";
      String DELIVERY_RECEIVE  = "https://api.weixin.qq.com/shop/delivery/recieve";
    }

    interface Aftersale {
      String AFTERSALE_ADD = "https://api.weixin.qq.com/shop/aftersale/add";
      String AFTERSALE_GET = "https://api.weixin.qq.com/shop/aftersale/get";
      String AFTERSALE_UPDATE  = "https://api.weixin.qq.com/shop/aftersale/update";
    }
  }

  /**
   * 电子发票报销方
   */
  public interface Invoice{

    /**
     * 报销方查询报销发票信息
     */
    String GET_INVOICE_INFO = "https://api.weixin.qq.com/card/invoice/reimburse/getinvoiceinfo";

    /**
     * 报销方批量查询报销发票信息
     */
    String GET_INVOICE_BATCH = "https://api.weixin.qq.com/card/invoice/reimburse/getinvoicebatch";

    /**
     * 报销方更新发票状态
     */
    String UPDATE_INVOICE_STATUS = "https://api.weixin.qq.com/card/invoice/reimburse/updateinvoicestatus";

    /**
     * 报销方批量更新发票状态
     */
    String UPDATE_STATUS_BATCH = "https://api.weixin.qq.com/card/invoice/reimburse/updatestatusbatch";
  }

  public interface Internet{
    String GET_USER_ENCRYPT_KEY = "https://api.weixin.qq.com/wxa/business/getuserencryptkey";
  }

  /**
   * 设备订阅消息
   */
  public interface DeviceSubscribe {
    /**
     * 获取设备票据
     */
    String GET_SN_TICKET_URL = "https://api.weixin.qq.com/wxa/getsnticket";
    /**
     * 发送设备订阅消息
     */
    String SEND_DEVICE_SUBSCRIBE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/device/subscribe/send";
  }
}
