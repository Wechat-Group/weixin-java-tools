package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2021-08-23
 */
@Data
public class File implements Serializable {
  private static final long serialVersionUID = 2794189478198329090L;

  @SerializedName("media_id")
  private String mediaId;
}
