package me.chanjar.weixin.cp.bean.kf;


import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 添加/删除客服接待人员返回结果
 * @author leiin
 * @date 2022/1/26 4:11 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfServicerOpResp extends WxCpBaseResp {

  private static final long serialVersionUID = -4082459764202987034L;

  @SerializedName("result_list")
  private List<WxCpKfServicerResp> resultList;

  @Data
  @NoArgsConstructor
  public static class WxCpKfServicerResp extends WxCpBaseResp {

    @SerializedName("userid")
    private String userId;
  }

  public static WxCpKfServicerOpResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfServicerOpResp.class);
  }
}
