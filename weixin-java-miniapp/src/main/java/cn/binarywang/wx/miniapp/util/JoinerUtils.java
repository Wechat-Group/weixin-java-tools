/**
 * Copyright (c) 2019,sunnybs.
 * All Rights Reserved.
 * <p>
 * Project Name:yigou
 */
package cn.binarywang.wx.miniapp.util;

import com.google.common.base.Joiner;

/**
 * ClassName: JoinerUtils <br/>
 * Description: 字符串连接器 <br/>
 * Date: 2019年10月18日 下午1:42:59 <br/>
 *
 * @author <a href="https://gitee.com/esunego_hunan/weixin-java-tools">wsp</a>
 */

public class JoinerUtils {
    private static final String NULL = "null";

    /**
     * <p>
     * 空白连接器，忽略null
     * </p>
     *
     * <pre>
     * JoinerUtils.blankJoiner.join("a", "b", "c");
     * <b>result : </b>abc
     * 
     * JoinerUtils.blankJoiner.join("a", null, "c");
     * <b>result : </b>ac
     * </pre>
     */
    public static final Joiner blankJoiner = Joiner.on("").skipNulls();
    /**
     * <p>
     * 空白连接器
     * </p>
     *
     * <pre>
     * JoinerUtils.blankJoinerWithNull.join("a", "b", "c");
     * <b>result : </b>abc
     * 
     * JoinerUtils.blankJoinerWithNull.join("a", null, "c");
     * <b>result : </b>anullc
     * </pre>
     */
    public static final Joiner blankJoinerWithNull = Joiner.on("").useForNull(NULL);

    /**
     * <p>
     * 空格连接器，忽略null
     * </p>
     *
     * <pre>
     * JoinerUtils.spaceJoiner.join("a", "b", "c");
     * <b>result : </b>a b c
     * 
     * JoinerUtils.spaceJoiner.join("a", null, "c");
     * <b>result : </b>a c
     * </pre>
     */
    public static final Joiner spaceJoiner = Joiner.on(" ").skipNulls();
    /**
     * <p>
     * 空格连接器
     * </p>
     *
     * <pre>
     * JoinerUtils.spaceJoinerWithNull.join("a", "b", "c");
     * <b>result : </b>a b c
     * 
     * JoinerUtils.spaceJoinerWithNull.join("a", null, "c");
     * <b>result : </b>a null c
     * </pre>
     */
    public static final Joiner spaceJoinerWithNull = Joiner.on(" ").useForNull(NULL);

  /**
   * <p>
   * 逗号分隔符连接器，忽略null
   * </p>
   *
   * <pre>
   * JoinerUtils.commaJoiner.join("a", "b", "c");
   * <b>result : </b>a,b,c
   *
   * JoinerUtils.commaJoiner.join("a", null, "c");
   * <b>result : </b>a,c
   * </pre>
   */
  public static final Joiner commaJoiner = Joiner.on(",").skipNulls();
  /**
   * <p>
   * 逗号分隔符连接器
   * </p>
   *
   * <pre>
   * JoinerUtils.commaJoinerWithNull.join("a", "b", "c");
   * <b>result : </b>a,b,c
   *
   * JoinerUtils.commaJoinerWithNull.join("a", null, "c");
   * <b>result : </b>a,null,c
   * </pre>
   */
  public static final Joiner commaJoinerWithNull = Joiner.on(",").useForNull(NULL);

  /**
   * <p>
   * 等号分隔符连接器，忽略null
   * </p>
   *
   * <pre>
   * JoinerUtils.equalJoiner.join("a", "b", "c");
   * <b>result : </b>a=b=c
   *
   * JoinerUtils.equalJoiner.join("a", null, "c");
   * <b>result : </b>a=c
   * </pre>
   */
  public static final Joiner equalJoiner = Joiner.on("=").skipNulls();
  /**
   * <p>
   * 等号分隔符连接器
   * </p>
   *
   * <pre>
   * JoinerUtils.equalJoinerWithNull.join("a", "b", "c");
   * <b>result : </b>a=b=c
   *
   * JoinerUtils.equalJoinerWithNull.join("a", null, "c");
   * <b>result : </b>a=null=c
   * </pre>
   */
  public static final Joiner equalJoinerWithNull = Joiner.on("=").useForNull(NULL);

    /**
     * <p>
     * 竖线分隔符连接器，忽略null
     * </p>
     *
     * <pre>
     * JoinerUtils.vLineJoiner.join("a", "b", "c");
     * <b>result : </b>a|b|c
     * 
     * JoinerUtils.vLineJoiner.join("a", null, "c");
     * <b>result : </b>a|c
     * </pre>
     */
    public static final Joiner vLineJoiner = Joiner.on("|").skipNulls();
    /**
     * <p>
     * 竖线分隔符连接器
     * </p>
     *
     * <pre>
     * JoinerUtils.vLineJoinerWithNull.join("a", "b", "c");
     * <b>result : </b>a|b|c
     * 
     * JoinerUtils.vLineJoinerWithNull.join("a", null, "c");
     * <b>result : </b>a|null|c
     * </pre>
     */
    public static final Joiner vLineJoinerWithNull = Joiner.on("|").useForNull(NULL);

    /**
     * <p>
     * 中横线分隔符连接器，忽略null
     * </p>
     *
     * <pre>
     * JoinerUtils.hLineJoiner.join("a", "b", "c");
     * <b>result : </b>a-b-c
     * 
     * JoinerUtils.hLineJoiner.join("a", null, "c");
     * <b>result : </b>a-c
     * </pre>
     */
    public static final Joiner hLineJoiner = Joiner.on("-").skipNulls();
    /**
     * <p>
     * 中横线分隔符连接器
     * </p>
     *
     * <pre>
     * JoinerUtils.hLineJoinerWithNull.join("a", "b", "c");
     * <b>result : </b>a-b-c
     * 
     * JoinerUtils.hLineJoinerWithNull.join("a", null, "c");
     * <b>result : </b>a-null-c
     * </pre>
     */
    public static final Joiner hLineJoinerWithNull = Joiner.on("-").useForNull(NULL);

    /**
     * <p>
     * 下划线分隔符连接器，忽略null
     * </p>
     *
     * <pre>
     * JoinerUtils.underlineJoiner.join("a", "b", "c");
     * <b>result : </b>a_b_c
     * 
     * JoinerUtils.underlineJoiner.join("a", null, "c");
     * <b>result : </b>a_c
     * </pre>
     */
    public static final Joiner underlineJoiner = Joiner.on("_").skipNulls();
    /**
     * <p>
     * 下划线分隔符连接器
     * </p>
     *
     * <pre>
     * JoinerUtils.underlineJoinerWithNull.join("a", "b", "c");
     * <b>result : </b>a_b_c
     * 
     * JoinerUtils.underlineJoinerWithNull.join("a", null, "c");
     * <b>result : </b>a_null_c
     * </pre>
     */
    public static final Joiner underlineJoinerWithNull = Joiner.on("_").useForNull(NULL);

    /**
     * <p>
     * 斜线分隔符连接器，忽略null
     * </p>
     *
     * <pre>
     * JoinerUtils.pathJoiner.join("a", "b", "c");
     * <b>result : </b>a/b/c
     * 
     * JoinerUtils.pathJoiner.join("a", null, "c");
     * <b>result : </b>a/c
     * </pre>
     */
    public static final Joiner pathJoiner = Joiner.on("/").skipNulls();
    /**
     * <p>
     * 斜线分隔符连接器
     * </p>
     *
     * <pre>
     * JoinerUtils.pathJoinerWithNull.join("a", "b", "c");
     * <b>result : </b>a/b/c
     * 
     * JoinerUtils.pathJoinerWithNull.join("a", null, "c");
     * <b>result : </b>a/null/c
     * </pre>
     */
    public static final Joiner pathJoinerWithNull = Joiner.on("/").useForNull(NULL);
}
