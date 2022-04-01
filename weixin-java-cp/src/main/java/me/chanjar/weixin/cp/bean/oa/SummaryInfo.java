package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 摘要行信息，用于定义某一行摘要显示的内容.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-07-19
 */
@Data
@Accessors(chain = true)
public class SummaryInfo implements Serializable {
  private static final long serialVersionUID = 8262265774851382414L;

  /**
   * 摘要行信息，用于定义某一行摘要显示的内容
   */
  @SerializedName("summary_info")
  private List<SummaryInfoData> summaryInfoData;

  @Data
  @Accessors(chain = true)
  public static class SummaryInfoData implements Serializable {
    private static final long serialVersionUID = 5314161929610113856L;

    /**
     * 摘要行显示文字，用于记录列表和消息通知的显示，不要超过20个字符
     */
    private String text;

    /**
     * 摘要行显示语言
     */
    private String lang;
  }
}
