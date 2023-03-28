package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type File.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2021-08-23
 */
@Data
@Accessors(chain = true)
public class File implements Serializable {
  private static final long serialVersionUID = 2794189478198329090L;

  @SerializedName("media_id")
  private String mediaId;
}
