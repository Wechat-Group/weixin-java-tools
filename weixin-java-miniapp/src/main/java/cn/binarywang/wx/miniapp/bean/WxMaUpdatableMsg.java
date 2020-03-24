package cn.binarywang.wx.miniapp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 动态消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-02-17
 */
@Data
@Accessors(chain = true)
public class WxMaUpdatableMsg implements Serializable {
  private static final long serialVersionUID = 6231957192034798165L;

  /**
   * 动态消息的 ID，通过 updatableMessage.createActivityId 接口获取
   */
  @SerializedName("activity_id")
  private String activityId;

  /**
   * 动态消息修改后的状态
   * 0	未开始
   * 1	已开始
   */
  @SerializedName("target_state")
  private Integer targetState;

  /**
   * 动态消息对应的模板信息
   */
  @SerializedName("template_info")
  private TemplateInfo templateInfo;

  @Data
  @Accessors(chain = true)
  public static class TemplateInfo implements Serializable {
    private static final long serialVersionUID = -9218473401759062841L;

    /**
     * 模板中需要修改的参数
     */
    @SerializedName("parameter_list")
    private List<Parameter> parameterList;
  }

  @Data
  @Accessors(chain = true)
  public static class Parameter implements Serializable {
    private static final long serialVersionUID = 7444716050341038046L;

    /**
     * 要修改的参数名
     * <pre>
     * 合法值：
     * member_count	target_state = 0 时必填，文字内容模板中 member_count 的值
     * room_limit	target_state = 0 时必填，文字内容模板中 room_limit 的值
     * path	target_state = 1 时必填，点击「进入」启动小程序时使用的路径。对于小游戏，没有页面的概念，可以用于传递查询字符串（query），如 "?foo=bar"
     * version_type	target_state = 1 时必填，点击「进入」启动小程序时使用的版本。
     * 有效参数值为：develop（开发版），trial（体验版），release（正式版）
     * </pre>
     */
    private String name;

    /**
     * 修改后的参数值
     */
    private String value;
  }
}
