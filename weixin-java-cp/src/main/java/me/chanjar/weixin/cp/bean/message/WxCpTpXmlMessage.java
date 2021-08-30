package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.xml.IntegerArrayConverter;
import me.chanjar.weixin.common.util.xml.StringArrayConverter;
import me.chanjar.weixin.common.util.xml.XmlUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 回调推送的message
 * https://work.weixin.qq.com/api/doc#90001/90143/90612
 *
 * @author zhenjun cai
 */
@XStreamAlias("xml")
@Slf4j
@Data
public class WxCpTpXmlMessage implements Serializable {
  private static final long serialVersionUID = 6031833682211475786L;

  /**
   * 使用dom4j解析的存放所有xml属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  @XStreamAlias("SuiteId")
  protected String suiteId;

  @XStreamAlias("InfoType")
  protected String infoType;

  @XStreamAlias("TimeStamp")
  protected String timeStamp;

  @XStreamAlias("SuiteTicket")
  protected String suiteTicket;

  @XStreamAlias("AuthCode")
  protected String authCode;

  @XStreamAlias("AuthCorpId")
  protected String authCorpId;

  @XStreamAlias("ChangeType")
  protected String changeType;

  @XStreamAlias("UserID")
  protected String userID;

  @XStreamAlias("OpenUserID")
  protected String openUserID;

  @XStreamAlias("Department")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] department;

  @XStreamAlias("MainDepartment")
  @XStreamConverter(value = IntConverter.class)
  protected Integer mainDepartment;

  @XStreamAlias("IsLeaderInDept")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] isLeaderInDept;

  @XStreamAlias("Mobile")
  protected String mobile;

  @XStreamAlias("Position")
  protected String position;

  @XStreamAlias("Gender")
  @XStreamConverter(value = IntConverter.class)
  protected Integer gender;

  @XStreamAlias("Email")
  protected String email;

  @XStreamAlias("Status")
  protected String status;

  @XStreamAlias("Avatar")
  protected String avatar;

  @XStreamAlias("Alias")
  protected String alias;

  @XStreamAlias("Telephone")
  protected String telephone;

  @XStreamAlias("Id")
  protected String id;

  @XStreamAlias("Name")
  protected String name;

  @XStreamAlias("ParentId")
  protected String parentId;

  @XStreamAlias("Order")
  @XStreamConverter(value = IntConverter.class)
  protected Integer order;

  @XStreamAlias("TagId")
  @XStreamConverter(value = IntConverter.class)
  protected Integer tagId;

  @XStreamAlias("AddUserItems")
  @XStreamConverter(value = StringArrayConverter.class)
  protected String[] addUserItems;

  @XStreamAlias("DelUserItems")
  @XStreamConverter(value = StringArrayConverter.class)
  protected String[] delUserItems;

  @XStreamAlias("AddPartyItems")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] addPartyItems;

  @XStreamAlias("DelPartyItems")
  @XStreamConverter(value = IntegerArrayConverter.class)
  protected Integer[] delPartyItems;

  //ref: https://work.weixin.qq.com/api/doc/90001/90143/90585
  @XStreamAlias("ServiceCorpId")
  protected String serviceCorpId;

  @XStreamAlias("RegisterCode")
  protected String registerCode;

  @XStreamAlias("ContactSync")
  protected ContactSync contactSync;

  @XStreamAlias("AuthUserInfo")
  protected AuthUserInfo authUserInfo;

  @XStreamAlias("TemplateId")
  protected String templateId;

  @XStreamAlias("CreateTime")
  protected Long createTime;

  @XStreamAlias("ToUserName")
  protected String toUserName;

  @XStreamAlias("FromUserName")
  protected String fromUserName;

  @XStreamAlias("MsgType")
  protected String msgType;

  @XStreamAlias("Event")
  protected String event;

  @XStreamAlias("BatchJob")
  protected BatchJob batchJob;

  @XStreamAlias("ExternalUserID")
  protected String externalUserID;

  @XStreamAlias("WelcomeCode")
  protected String welcomeCode;

  @XStreamAlias("FromUser")
  protected String fromUser;

  @XStreamAlias("Content")
  protected String content;

  @XStreamAlias("MsgId")
  protected String msgId;

  @XStreamAlias("AgentID")
  protected String agentID;

  @XStreamAlias("PicUrl")
  protected String picUrl;

  @XStreamAlias("MediaId")
  protected String mediaId;

  @XStreamAlias("Format")
  private String format;

  @XStreamAlias("ThumbMediaId")
  private String thumbMediaId;

  @XStreamAlias("Location_X")
  private Double locationX;

  @XStreamAlias("Location_Y")
  private Double locationY;

  @XStreamAlias("Scale")
  private Double scale;

  @XStreamAlias("Label")
  private String label;

  @XStreamAlias("Title")
  private String title;

  @XStreamAlias("Description")
  private String description;

  @XStreamAlias("Url")
  private String url;

  @XStreamAlias("EventKey")
  private String eventKey;

  @XStreamAlias("Latitude")
  private Double latitude;

  @XStreamAlias("Longitude")
  private Double longitude;

  @XStreamAlias("Precision")
  private Double precision;

  @XStreamAlias("AppType")
  private String appType;

  /**
   * 接替失败的原因, customer_refused-客户拒绝， customer_limit_exceed-接替成员的客户数达到上限
   */
  @XStreamAlias("FailReason")
  private String failReason;

  @XStreamAlias("ScanCodeInfo")
  private WxCpXmlMessage.ScanCodeInfo scanCodeInfo;

  @XStreamAlias("SendPicsInfo")
  private WxCpXmlMessage.SendPicsInfo sendPicsInfo;

  @XStreamAlias("SendLocationInfo")
  private WxCpXmlMessage.SendLocationInfo sendLocationInfo;

  @XStreamAlias("ApprovalInfo")
  private ApprovalInfo approvalInfo = new ApprovalInfo();

  @XStreamAlias("TaskId")
  private String taskId;

  @Data
  @XStreamAlias("ContactSync")
  public static class ContactSync implements Serializable {
    private static final long serialVersionUID = 6031833682211475786L;

    @XStreamAlias("AccessToken")
    protected String accessToken;

    @XStreamAlias("ExpiresIn")
    protected Integer expiresIn;
  }

  @Data
  @XStreamAlias("AuthUserInfo")
  public static class AuthUserInfo implements Serializable {

    private static final long serialVersionUID = -2918558288134346848L;

    @XStreamAlias("UserId")
    protected String userId;
  }

  @Data
  @XStreamAlias("BatchJob")
  public static class BatchJob implements Serializable {
    private static final long serialVersionUID = 6031833682211475786L;

    @XStreamAlias("JobId")
    protected String JobId;

    @XStreamAlias("JobType")
    protected String jobType;

    @XStreamAlias("ErrCode")
    @XStreamConverter(value = IntConverter.class)
    protected Integer errCode;

    @XStreamAlias("ErrMsg")
    protected String errMsg;
  }

  @Data
  @XStreamAlias("ApprovalInfo")
  public static class ApprovalInfo implements Serializable {
    private static final long serialVersionUID = 6031833682211475786L;

    @XStreamAlias("ThirdNo")
    protected Long thirdNo;

    @XStreamAlias("OpenSpName")
    protected String openSpName;

    @XStreamAlias("OpenTemplateId")
    protected Integer openTemplateId;

    @XStreamAlias("OpenSpStatus")
    protected Integer openSpStatus;

    @XStreamAlias("ApplyTime")
    protected Long applyTime;

    @XStreamAlias("ApplyUserName")
    protected String applyUserName;

    @XStreamAlias("ApplyUserId")
    protected Integer applyUserId;

    @XStreamAlias("ApplyUserParty")
    protected String applyUserParty;

    @XStreamAlias("ApplyUserImage")
    protected String applyUserImage;

    @XStreamAlias("ApprovalNodes")
    protected List<ApprovalNode> approvalNodes;

    @XStreamAlias("NotifyNodes")
    protected List<NotifyNode> notifyNodes;

    @XStreamAlias("approverstep")
    protected Integer approverstep;

    //自建/第三方应用调用审批流程引擎，状态通知
    //ref: https://work.weixin.qq.com/api/doc/90001/90143/90376#审批状态通知事件
    //1.自建/第三方应用调用审批流程引擎发起申请之后，审批状态发生变化时
    //2.自建/第三方应用调用审批流程引擎发起申请之后，在“审批中”状态，有任意审批人进行审批操作时
    @Data
    @XStreamAlias("ApprovalNode")
    public static class ApprovalNode implements Serializable {
      private static final long serialVersionUID = 6031833682211475786L;

      @XStreamAlias("NodeStatus")
      protected Integer nodeStatus;

      @XStreamAlias("NodeAttr")
      protected Integer nodeAttr;

      @XStreamAlias("NodeType")
      protected Integer nodeType;

      @XStreamAlias("Items")
      protected List<Item> items;

      @Data
      @XStreamAlias("Item")
      public static class Item implements Serializable {
        private static final long serialVersionUID = 6031833682211475786L;

        @XStreamAlias("ItemName")
        protected String itemName;
        @XStreamAlias("ItemUserId")
        protected Integer itemUserId;
        @XStreamAlias("ItemImage")
        protected String itemImage;
        @XStreamAlias("ItemStatus")
        protected Integer itemStatus;
        @XStreamAlias("ItemSpeech")
        protected String itemSpeech;
        @XStreamAlias("ItemOpTime")
        protected Long itemOpTime;
      }
    }

    @Data
    @XStreamAlias("NotifyNode")
    public static class NotifyNode implements Serializable {
      private static final long serialVersionUID = 6031833682211475786L;

      @XStreamAlias("ItemName")
      protected String itemName;
      @XStreamAlias("ItemUserId")
      protected Integer itemUserId;
      @XStreamAlias("ItemImage")
      protected String itemImage;
    }
  }


  public static WxCpTpXmlMessage fromXml(String xml) {
    final WxCpTpXmlMessage xmlPackage = XmlUtil.toObject(xml, WxCpTpXmlMessage.class);
    xmlPackage.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return xmlPackage;
  }

}
