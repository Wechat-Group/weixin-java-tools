package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author Hipple
 * @description 获取账号所有可以设置的类目
 * @since 2019/1/26 18:43
 */
@Data
public class WxFastMaCanSetCategoryResult extends WxOpenResult {
  private static final long serialVersionUID = - 2469386233538980102L;

  @SerializedName ("category_list")
  private CategoryListBean categoryList;

  @Data
  public static class CategoryListBean {
    private List<CategoriesBean> categories;

    @Data
    public static class CategoriesBean {

      private int father;
      private int id;
      private int level;
      private String name;
      private QualifyBean qualify;
      @SerializedName ("sensitive_type")
      private int sensitiveType;
      private List<Integer> children;

      @Data
      public static class QualifyBean {
        @SerializedName ("exter_list")
        private List<ExterListBean> exterList;

        @Data
        public static class ExterListBean {
          @SerializedName ("inner_list")
          private List<InnerListBean> innerList;

          @Data
          public static class InnerListBean {
            private String name;
            private String url;
          }
        }
      }
    }
  }
}
