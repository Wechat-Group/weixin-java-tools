package cn.binarywang.wx.miniapp.bean.security;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 */
@Data
@Builder
public class WxMaMsgSecCheckCheckResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 1903247824980080974L;
  /**
   * result : {"suggest":"risky","label":20001}
   * detail : [{"strategy":"content_model","errcode":0,"suggest":"risky","label":20006,"prob":90},{"strategy":"keyword","errcode":0,"suggest":"pass","label":20006,"level":20,"keyword":"命中的关键词1"},{"strategy":"keyword","errcode":0,"suggest":"risky","label":20006,"level":90,"keyword":"命中的关键词2"}]
   * trace_id : 60ae120f-371d5872-7941a05b
   */
  @SerializedName("result")
  private ResultBean result;
  @SerializedName("trace_id")
  private String traceId;
  @SerializedName("detail")
  private List<DetailBean> detail;

  @Data
  @Builder
  public static class ResultBean implements Serializable {
    /**
     * suggest : risky
     * label : 20001
     */

    @SerializedName("suggest")
    private String suggest;
    @SerializedName("label")
    private String label;
  }

  @Data
  @Builder
  public static class DetailBean implements Serializable {
    /**
     * strategy : content_model
     * errcode : 0
     * suggest : risky
     * label : 20006
     * prob : 90
     * level : 20
     * keyword : 命中的关键词1
     */

    @SerializedName("strategy")
    private String strategy;
    @SerializedName("errcode")
    private Integer errcode;
    @SerializedName("suggest")
    private String suggest;
    @SerializedName("label")
    private String label;
    @SerializedName("prob")
    private Integer prob;
    @SerializedName("level")
    private String level;
    @SerializedName("keyword")
    private String keyword;
  }
}
