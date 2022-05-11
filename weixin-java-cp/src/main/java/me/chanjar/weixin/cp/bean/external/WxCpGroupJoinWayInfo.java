package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 *客户群「加入群聊」对象
 * @author Jc
 */
@Data
@NoArgsConstructor
public class WxCpGroupJoinWayInfo  implements Serializable {
  private static final long serialVersionUID = 5621905029624794129L;
  @SerializedName("join_way")
  private JoinWay joinWay;
  @Getter
  @Setter
  public static  class JoinWay implements Serializable {
    private static final long serialVersionUID = 5621905029624794122L;

    /**
     * 联系方式的配置id
     */
    @SerializedName("config_id")
    private String configId;
    /**
     * 场景。
     * 1 - 群的小程序插件
     * 2 - 群的二维码插件
     */
    @SerializedName("scene")
    private Integer scene;
    /**
     * 联系方式的备注信息，用于助记，超过30个字符将被截断
     */
    @SerializedName("remark")
    private String remark;
    /**
     * 当群满了后，是否自动新建群。0-否；1-是。 默认为1
     */
    @SerializedName("auto_create_room")
    private Integer autoCreateRoom;
    /**
     * 自动建群的群名前缀，当auto_create_room为1时有效。最长40个utf8字符
     */
    @SerializedName("room_base_name")
    private String roomBaseName;
    /**
     * 自动建群的群起始序号，当auto_create_room为1时有效
     */
    @SerializedName("room_base_id")
    private Integer roomBaseId;
    /**
     * 使用该配置的客户群ID列表，支持5个。
     */
    @SerializedName("chat_id_list")
    private List<String> chatIdList;
    /**
     * 联系二维码的URL，仅在配置为群二维码时返回
     */
    @SerializedName("qr_code")
    private String qrCode;
    /**
     企业自定义的state参数，用于区分不同的入群渠道。不超过30个UTF-8字符
     如果有设置此参数，在调用获取客户群详情接口时会返回每个群成员对应的该参数值
     */
    @SerializedName("state")
    private Integer state;

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
    public static WxCpGroupJoinWayInfo.JoinWay fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpGroupJoinWayInfo.JoinWay.class);
    }
  }

  public static WxCpGroupJoinWayInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupJoinWayInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
