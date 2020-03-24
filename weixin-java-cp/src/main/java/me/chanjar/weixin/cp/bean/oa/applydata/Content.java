package me.chanjar.weixin.cp.bean.oa.applydata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author element
 */
@Data
public class Content implements Serializable {
  private static final long serialVersionUID = 8456821731930526935L;

  private String control;

  private String id;

  @SerializedName("title")
  private List<ContentTitle> titles;

  private ContentValue value;
}
