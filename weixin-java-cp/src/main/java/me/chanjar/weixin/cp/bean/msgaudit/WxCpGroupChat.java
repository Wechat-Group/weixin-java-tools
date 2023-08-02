package me.chanjar.weixin.cp.bean.msgaudit;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 内部群信息
 *
 * @author Wang_Wong
 */
@Data
public class WxCpGroupChat implements Serializable {

  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("roomname")
  private String roomName;

  @SerializedName("creator")
  private String creator;

  @SerializedName("room_create_time")
  private Long roomCreateTime;

  @SerializedName("notice")
  private String notice;

  private List<Member> members;

  /**
   * The type Member.
   */
  @Getter
  @Setter
  public static class Member implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("memberid")
    private String memberId;

    @SerializedName("jointime")
    private Long joinTime;

    /**
     * From json member.
     *
     * @param json the json
     * @return the member
     */
    public Member fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Member.class);
    }

  }

  /**
   * From json wx cp group chat.
   *
   * @param json the json
   * @return the wx cp group chat
   */
  public static WxCpGroupChat fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupChat.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
