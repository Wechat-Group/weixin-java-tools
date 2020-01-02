package me.chanjar.weixin.cp.bean.oa.applydata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author element
 */
@Data
public class ContentValue implements Serializable {

  private static final long serialVersionUID = -5607678965965065261L;

  private String text;

  @SerializedName("new_number")
  private Integer newNumber;

  @SerializedName("new_money")
  private Integer newMoney;

  private ContentValue.Date date;

  private ContentValue.Selector selector;

  private List<ContentValue.Member> members;

  private List<ContentValue.Department> departments;

  private List<ContentValue.File> files;

  private List<ContentValue.Child> children;

  @Data
  public static class Date implements Serializable {

    private static final long serialVersionUID = -6181554080062231138L;
    private String type;

    @SerializedName("s_timestamp")
    private Long timestamp;
  }

  @Data
  public static class Selector implements Serializable {

    private static final long serialVersionUID = 7305458759126951773L;
    private String type;
    private List<Option> options;

    @Data
    public static class Option implements Serializable {

      private static final long serialVersionUID = -3471071106328280252L;
      private String key;

      @SerializedName("value")
      private List<ContentTitle> values;
    }

  }

  @Data
  public static class Member implements Serializable {

    private static final long serialVersionUID = 1316551341955496067L;
    @SerializedName("userid")
    private String userId;
    private String name;
  }

  @Data
  public static class Department implements Serializable {

    private static final long serialVersionUID = -2513762192924826234L;

    @SerializedName("openapi_id")

    private String openApiId;
    private String name;
  }

  @Data
  public static class File implements Serializable {

    private static final long serialVersionUID = 3890971381800855142L;
    @SerializedName("file_id")
    private String fileId;


  }

  @Data
  public static class Child implements Serializable {

    private static final long serialVersionUID = -3500102073821161558L;
    private List<Content> list;
  }

}
