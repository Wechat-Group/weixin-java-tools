package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;

/**
 * <pre>
 * 二维码相关操作接口.
 *
 * 接口A（createWxaCode）加上接口C（createQrcode），总共生成的码数量限制为100,000，请谨慎调用。
 *
 * 文档地址：https://mp.weixin.qq.com/debug/wxadoc/dev/api/qrcode.html
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaQrcodeService {

  /**
   * 接口C: 获取小程序页面二维码.
   * <pre>
   * 适用于需要的码数量较少的业务场景
   * 通过该接口，仅能生成已发布的小程序的二维码。
   * 可以在开发者工具预览时生成开发版的带参二维码。
   * 带参二维码只有 100000 个，请谨慎调用。
   * </pre>
   *
   * @param path  不能为空，最大长度 128 字节
   * @param width 默认430 二维码的宽度
   * @return 文件内容字节数组
   * @throws WxErrorException 异常
   */
  byte[] createQrcodeBytes(String path, int width) throws WxErrorException;

  /**
   * 接口C: 获取小程序页面二维码.
   * <pre>
   * 适用于需要的码数量较少的业务场景
   * 通过该接口，仅能生成已发布的小程序的二维码。
   * 可以在开发者工具预览时生成开发版的带参二维码。
   * 带参二维码只有 100000 个，请谨慎调用。
   * </pre>
   *
   * @param path     不能为空，最大长度 128 字节
   * @param width    默认430 二维码的宽度
   * @param filePath 二维码生成的文件路径，例如: /var/temp
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createQrcode(String path, int width, String filePath) throws WxErrorException;

  /**
   * 接口C: 获取小程序页面二维码.
   * <pre>
   * 适用于需要的码数量较少的业务场景
   * 通过该接口，仅能生成已发布的小程序的二维码。
   * 可以在开发者工具预览时生成开发版的带参二维码。
   * 带参二维码只有 100000 个，请谨慎调用。
   * </pre>
   *
   * @param path  不能为空，最大长度 128 字节
   * @param width 默认430 二维码的宽度
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createQrcode(String path, int width) throws WxErrorException;

  /**
   * 接口C: 获取小程序页面二维码.
   * <pre>
   * 适用于需要的码数量较少的业务场景
   * 通过该接口，仅能生成已发布的小程序的二维码。
   * 可以在开发者工具预览时生成开发版的带参二维码。
   * 带参二维码只有 100000 个，请谨慎调用。
   * </pre>
   *
   * @param path     不能为空，最大长度 128 字节
   * @param filePath 二维码生成的文件路径，例如: /var/temp
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createQrcode(String path, String filePath) throws WxErrorException;

  /**
   * 接口C: 获取小程序页面二维码.
   * <pre>
   * 适用于需要的码数量较少的业务场景
   * 通过该接口，仅能生成已发布的小程序的二维码。
   * 可以在开发者工具预览时生成开发版的带参二维码。
   * 带参二维码只有 100000 个，请谨慎调用。
   * </pre>
   *
   * @param path 不能为空，最大长度 128 字节
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createQrcode(String path) throws WxErrorException;

  /**
   * 接口A: 获取小程序码.
   *
   * @param path      不能为空，最大长度 128 字节
   * @param width     默认430 二维码的宽度
   * @param autoColor 默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
   * @param lineColor autoColor 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
   * @param isHyaline 是否需要透明底色， isHyaline 为true时，生成透明底色的小程序码
   * @return 文件内容字节数组
   * @throws WxErrorException 异常
   */
  byte[] createWxaCodeBytes(String path, int width, boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline)
    throws WxErrorException;

  /**
   * 接口A: 获取小程序码.
   *
   * @param path      不能为空，最大长度 128 字节
   * @param width     默认430 二维码的宽度
   * @param filePath  二维码生成的文件路径，例如: /var/temp
   * @param autoColor 默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
   * @param lineColor autoColor 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
   * @param isHyaline 是否需要透明底色， isHyaline 为true时，生成透明底色的小程序码
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCode(String path, int width, String filePath, boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline)
    throws WxErrorException;

  /**
   * 接口A: 获取小程序码.
   *
   * @param path      不能为空，最大长度 128 字节
   * @param width     默认430 二维码的宽度
   * @param autoColor 默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
   * @param lineColor autoColor 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
   * @param isHyaline 是否需要透明底色， isHyaline 为true时，生成透明底色的小程序码
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCode(String path, int width, boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline)
    throws WxErrorException;

  /**
   * 接口A: 获取小程序码.
   *
   * @param path     不能为空，最大长度 128 字节
   * @param width    默认430 二维码的宽度
   * @param filePath 二维码生成的文件路径，例如: /var/temp
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCode(String path, int width, String filePath) throws WxErrorException;

  /**
   * 接口A: 获取小程序码.
   *
   * @param path  不能为空，最大长度 128 字节
   * @param width 默认430 二维码的宽度
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCode(String path, int width) throws WxErrorException;

  /**
   * 接口A: 获取小程序码.
   *
   * @param path     不能为空，最大长度 128 字节
   * @param filePath 二维码生成的文件路径，例如: /var/temp
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCode(String path, String filePath) throws WxErrorException;

  /**
   * 接口A: 获取小程序码.
   *
   * @param path 不能为空，最大长度 128 字节
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCode(String path) throws WxErrorException;

  /**
   * 接口B: 获取小程序码（永久有效、数量暂无限制）.
   * <pre>
   * 通过该接口生成的小程序码，永久有效，数量暂无限制。
   * 用户扫描该码进入小程序后，将统一打开首页，开发者需在对应页面根据获取的码中 scene 字段的值，再做处理逻辑。
   * 使用如下代码可以获取到二维码中的 scene 字段的值。
   * 调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx 进行模拟，开发工具模拟时的 scene 的参数值需要进行 urlencode
   * </pre>
   *
   * @param scene      最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，
   *                   其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
   * @param page       必须是已经发布的小程序页面，例如 "pages/index/index" ,如果不填写这个字段，默认跳主页面
   * @param envVersion 默认"release" 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"
   * @param width      默认430 二维码的宽度
   * @param autoColor  默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
   * @param lineColor  autoColor 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
   * @param isHyaline  是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
   * @return 文件内容字节数组
   * @throws WxErrorException 异常
   */
  byte[] createWxaCodeUnlimitBytes(String scene, String page, String envVersion, int width, boolean autoColor,
                                   WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException;

  /**
   * 接口B: 获取小程序码（永久有效、数量暂无限制）.
   * <pre>
   * 通过该接口生成的小程序码，永久有效，数量暂无限制。
   * 用户扫描该码进入小程序后，将统一打开首页，开发者需在对应页面根据获取的码中 scene 字段的值，再做处理逻辑。
   * 使用如下代码可以获取到二维码中的 scene 字段的值。
   * 调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx 进行模拟，开发工具模拟时的 scene 的参数值需要进行 urlencode
   * </pre>
   *
   * @param scene      最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，
   *                   其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
   * @param page       必须是已经发布的小程序页面，例如 "pages/index/index" ,如果不填写这个字段，默认跳主页面
   * @param filePath   二维码生成的文件路径，例如: /var/temp
   * @param envVersion 默认"release" 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"
   * @param width      默认430 二维码的宽度
   * @param autoColor  默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
   * @param lineColor  autoColor 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
   * @param isHyaline  是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCodeUnlimit(String scene, String page, String filePath, String envVersion, int width, boolean autoColor,
                            WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException;

  /**
   * 接口B: 获取小程序码（永久有效、数量暂无限制）.
   * <pre>
   * 通过该接口生成的小程序码，永久有效，数量暂无限制。
   * 用户扫描该码进入小程序后，将统一打开首页，开发者需在对应页面根据获取的码中 scene 字段的值，再做处理逻辑。
   * 使用如下代码可以获取到二维码中的 scene 字段的值。
   * 调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx 进行模拟，开发工具模拟时的 scene 的参数值需要进行 urlencode
   * </pre>
   *
   * @param scene      最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，
   *                   其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
   * @param page       必须是已经发布的小程序页面，例如 "pages/index/index" ,如果不填写这个字段，默认跳主页面
   * @param envVersion 默认"release" 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"
   * @param width      默认430 二维码的宽度
   * @param autoColor  默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
   * @param lineColor  autoColor 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
   * @param isHyaline  是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCodeUnlimit(String scene, String page, String envVersion, int width, boolean autoColor,
                            WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException;

  /**
   * 接口B: 获取小程序码（永久有效、数量暂无限制）.
   * <pre>
   * 通过该接口生成的小程序码，永久有效，数量暂无限制。
   * 用户扫描该码进入小程序后，将统一打开首页，开发者需在对应页面根据获取的码中 scene 字段的值，再做处理逻辑。
   * 使用如下代码可以获取到二维码中的 scene 字段的值。
   * 调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx 进行模拟，开发工具模拟时的 scene 的参数值需要进行 urlencode
   * </pre>
   *
   * @param scene    最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，
   *                 其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
   * @param page     必须是已经发布的小程序页面，例如 "pages/index/index" ,如果不填写这个字段，默认跳主页面
   * @param filePath 二维码生成的文件路径，例如: /var/temp
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCodeUnlimit(String scene, String page, String filePath) throws WxErrorException;

  /**
   * 接口B: 获取小程序码（永久有效、数量暂无限制）.
   * <pre>
   * 通过该接口生成的小程序码，永久有效，数量暂无限制。
   * 用户扫描该码进入小程序后，将统一打开首页，开发者需在对应页面根据获取的码中 scene 字段的值，再做处理逻辑。
   * 使用如下代码可以获取到二维码中的 scene 字段的值。
   * 调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx 进行模拟，开发工具模拟时的 scene 的参数值需要进行 urlencode
   * </pre>
   *
   * @param scene 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，
   *              其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
   * @param page  必须是已经发布的小程序页面，例如 "pages/index/index" ,如果不填写这个字段，默认跳主页面
   * @return 文件对象
   * @throws WxErrorException 异常
   */
  File createWxaCodeUnlimit(String scene, String page) throws WxErrorException;

}
