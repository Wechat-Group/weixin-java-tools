package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.List;

/**
 * 审批信息
 *
 * @author Gyv12345
 */
@XStreamAlias("ApprovalInfo")
@Data
public class WxCpXmlApprovalInfo implements Serializable {
  private static final long serialVersionUID = 8136329462880646091L;

  /**
   * 审批编号
   */
  @XStreamAlias("SpNo")
  private String spNo;

  /**
   * 审批申请类型名称（审批模板名称）
   */
  @XStreamAlias("SpName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String spName;

  /**
   * 申请单状态：1-审批中；2-已通过；3-已驳回；4-已撤销；6-通过后撤销；7-已删除；10-已支付
   */
  @XStreamAlias("SpStatus")
  private Integer spStatus;

  /**
   * 审批模板id。
   */
  @XStreamAlias("TemplateId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String templateId;
  /**
   * 审批申请提交时间,Unix时间戳
   */
  @XStreamAlias("ApplyTime")
  private Long applyTime;

  /**
   * 申请人信息
   */
  @XStreamAlias("Applyer")
  private Applier applier;

  /**
   * 审批流程信息，可能有多个审批节点。
   */
  @XStreamImplicit(itemFieldName = "SpRecord")
  private List<SpRecord> spRecords;

  /**
   * 抄送信息，可能有多个抄送节点
   * 这回查字典，notifier通知人，Notifyer这不知道是什么
   */
  @XStreamImplicit(itemFieldName = "Notifyer")
  private List<Notifier> notifier;

  /**
   * 审批申请备注信息，可能有多个备注节点
   */
  @XStreamImplicit(itemFieldName = "Comments")
  private List<Comment> comments;

  /**
   * 审批申请单变化类型
   */
  @XStreamAlias("StatuChangeEvent")
  private Integer statusChangeEvent;

  /**
   * 申请人信息
   */
  @XStreamAlias("Applyer")
  @Data
  public static class Applier implements Serializable {
    private static final long serialVersionUID = -979255011922209018L;

    /**
     * 申请人userid
     */
    @XStreamAlias("UserId")
    private String userId;

    /**
     * 申请人所在部门pid
     */
    @XStreamAlias("Party")
    private String party;
  }

  /**
   * 审批流程信息
   */
  @XStreamAlias("SpRecord")
  @Data
  public static class SpRecord implements Serializable {
    private static final long serialVersionUID = 1247535623941881764L;

    /**
     * 审批节点状态：1-审批中；2-已同意；3-已驳回；4-已转审
     */
    @XStreamAlias("SpStatus")
    private String spStatus;

    /**
     * 节点审批方式：1-或签；2-会签
     */
    @XStreamAlias("ApproverAttr")
    private String approverAttr;

    /**
     * 审批节点详情。当节点为标签或上级时，一个节点可能有多个分支
     */
    @XStreamImplicit(itemFieldName = "Details")
    private List<Detail> details;

  }

  /**
   * 审批节点详情
   */
  @XStreamAlias("Details")
  @Data
  public static class Detail implements Serializable {
    private static final long serialVersionUID = -8446107461495047603L;

    /**
     * 分支审批人
     */
    @XStreamAlias("Approver")
    private Approver approver;

    /**
     * 审批意见字段
     */
    @XStreamAlias("Speech")
    private String speech;

    /**
     * 分支审批人审批状态：1-审批中；2-已同意；3-已驳回；4-已转审
     */
    @XStreamAlias("SpStatus")
    private String spStatus;

    /**
     * 节点分支审批人审批操作时间，0为尚未操作
     */
    @XStreamAlias("SpTime")
    private Long spTime;

    /**
     * 节点分支审批人审批意见附件，赋值为media_id具体使用请参考：文档-获取临时素材
     */
    @XStreamImplicit(itemFieldName = "Attach")
    private List<String> attach;
  }

  /**
   * 分支审批人
   */
  @Data
  @XStreamAlias("Approver")
  public static class Approver implements Serializable {
    private static final long serialVersionUID = 7360442444186683191L;

    /**
     * 分支审批人userid
     */
    @XStreamAlias("UserId")
    private String userId;
  }

  /**
   * 抄送信息
   */
  @Data
  @XStreamAlias("Notifyer")
  public static class Notifier implements Serializable {
    private static final long serialVersionUID = -4524071522890013920L;

    /**
     * 节点抄送人userid
     */
    @XStreamAlias("UserId")
    private String userId;
  }

  /**
   * 审批申请备注信息
   */
  @Data
  @XStreamAlias("Comments")
  public static class Comment implements Serializable {
    private static final long serialVersionUID = 6912156206252719485L;

    /**
     * 备注人信息
     */
    @XStreamAlias("CommentUserInfo")
    private CommentUserInfo commentUserInfo;

    /**
     * 备注提交时间
     */
    @XStreamAlias("CommentTime")
    private String commentTime;

    /**
     * 备注文本内容
     */
    @XStreamAlias("CommentContent")
    private String commentContent;

    /**
     * 备注id
     */
    @XStreamAlias("CommentId")
    private String commentId;

    /**
     * 备注意见附件，值是附件media_id
     */
    @XStreamImplicit(itemFieldName="Attach")
    private List<String> attach;
  }

  @Data
  @XStreamAlias("CommentUserInfo")
  private static class CommentUserInfo implements Serializable {
    private static final long serialVersionUID = 5031739716823000947L;

    /**
     * 备注人userid
     */
    @XStreamAlias("UserId")
    private String userId;
  }
}
