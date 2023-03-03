package me.chanjar.weixin.cp.bean.corpgroup;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.bean.corpgroup
 * @Description: 获取应用共享信息返回类
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 9:02 PM
 */
@Data
public class WxCpCorpGroupCorpListAppShareInfoResp implements Serializable {
  private static final long serialVersionUID = 7165788382879237583L;
  @SerializedName("ending")
  private int ending;
  @SerializedName("corp_list")
  private List<WxCpCorpGroupCorp> corpList;
  @SerializedName("next_cursor")
  private String nextCursor;
}
