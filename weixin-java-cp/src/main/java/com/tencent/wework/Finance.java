package com.tencent.wework;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 企业微信会话内容存档Finance类
 * 注意：
 * 此类必须配置在com.tencent.wework路径底下，否则会报错：java.lang.UnsatisfiedLinkError: com.xxx.Finance.NewSdk()
 * <p>
 * Q：JAVA版本的sdk报错UnsatisfiedLinkError？
 * A：请检查是否修改了sdk的包名。
 * <p>
 * 官方文档：
 * https://developer.work.weixin.qq.com/document/path/91552
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on  2022-01-17
 */
@Slf4j
public class Finance {

  private static volatile long sdk = -1L;
  private static Finance finance = null;
  private static final String SO_FILE = "so";
  private static final String DLL_FILE = "dll";

    /**
     * New sdk long.
     *
     * @return the long
     */
    public native static long NewSdk();

    /**
     * 初始化函数
     * Return值=0表示该API调用成功
     *
     * @param sdk    the sdk
     * @param corpid the corpid
     * @param secret the secret
     * @return 返回是否初始化成功  0   - 成功 !=0 - 失败
     */
    public native static int Init(long sdk, String corpid, String secret);

    /**
     * 拉取聊天记录函数
     * Return值=0表示该API调用成功
     *
     * @param sdk      the sdk
     * @param seq      the seq
     * @param limit    the limit
     * @param proxy    the proxy
     * @param passwd   the passwd
     * @param timeout  the timeout
     * @param chatData the chat data
     * @return 返回是否调用成功  0   - 成功 !=0 - 失败
     */
    public native static int GetChatData(long sdk, long seq, long limit, String proxy, String passwd, long timeout, long chatData);

    /**
     * 拉取媒体消息函数
     * Return值=0表示该API调用成功
     *
     * @param sdk       the sdk
     * @param indexbuf  the indexbuf
     * @param sdkField  the sdk field
     * @param proxy     the proxy
     * @param passwd    the passwd
     * @param timeout   the timeout
     * @param mediaData the media data
     * @return 返回是否调用成功  0   - 成功 !=0 - 失败
     */
    public native static int GetMediaData(long sdk, String indexbuf, String sdkField, String proxy, String passwd, long timeout, long mediaData);

    /**
     * Decrypt data int.
     *
     * @param sdk         the sdk
     * @param encrypt_key the encrypt key
     * @param encrypt_msg the encrypt msg
     * @param msg         the msg
     * @return 返回是否调用成功  0   - 成功 !=0 - 失败
     * @brief 解析密文
     */
    public native static int DecryptData(long sdk, String encrypt_key, String encrypt_msg, long msg);

    /**
     * Destroy sdk.
     *
     * @param sdk the sdk
     */
    public native static void DestroySdk(long sdk);

    /**
     * New slice long.
     *
     * @return the long
     */
    public native static long NewSlice();

    /**
     * Free slice.
     *
     * @param slice the slice
     * @return
     * @brief 释放slice ，和NewSlice成对使用
     */
    public native static void FreeSlice(long slice);

    /**
     * Get content from slice string.
     *
     * @param slice the slice
     * @return 内容 string
     * @brief 获取slice内容
     */
    public native static String GetContentFromSlice(long slice);

    /**
     * Get slice len int.
     *
     * @param slice the slice
     * @return 内容 int
     * @brief 获取slice内容长度
     */
    public native static int GetSliceLen(long slice);

    /**
     * New media data long.
     *
     * @return the long
     */
    public native static long NewMediaData();

    /**
     * Free media data.
     *
     * @param mediaData the media data
     */
    public native static void FreeMediaData(long mediaData);

    /**
     * Get out index buf string.
     *
     * @param mediaData the media data
     * @return outindex string
     * @brief 获取mediadata outindex
     */
    public native static String GetOutIndexBuf(long mediaData);

    /**
     * Get data byte [ ].
     *
     * @param mediaData the media data
     * @return data byte [ ]
     * @brief 获取mediadata data数据
     */
    public native static byte[] GetData(long mediaData);

    /**
     * Get index len int.
     *
     * @param mediaData the media data
     * @return the int
     */
    public native static int GetIndexLen(long mediaData);

    /**
     * Get data len int.
     *
     * @param mediaData the media data
     * @return the int
     */
    public native static int GetDataLen(long mediaData);

    /**
     * Is media data finish int.
     *
     * @param mediaData the media data
     * @return 1完成 、0未完成
     * @brief 判断mediadata是否结束
     */
    public native static int IsMediaDataFinish(long mediaData);

    /**
     * 判断Windows环境
     *
     * @return boolean
     */
    public static boolean isWindows() {
    String osName = System.getProperties().getProperty("os.name");
    log.info("Loading System Libraries, Current OS Version Is: {}", osName);
    return osName.toUpperCase().contains("WINDOWS");
  }

    /**
     * 加载系统类库
     *
     * @param libFiles   类库配置文件
     * @param prefixPath 类库文件的前缀路径
     */
    public Finance(List<String> libFiles, String prefixPath) {
    boolean isWindows = Finance.isWindows();
    for (String file : libFiles) {
      String suffix = file.substring(file.lastIndexOf(".") + 1);
      if (isWindows) {
        // 加载dll文件
        if (suffix.equalsIgnoreCase(DLL_FILE)) {
          System.load(prefixPath + file);
        }
      } else {
        // 加载so文件
        if (suffix.equalsIgnoreCase(SO_FILE)) {
          System.load(prefixPath + file);
        }
      }
    }

  }

    /**
     * 初始化类库文件
     *
     * @param libFiles   the lib files
     * @param prefixPath the prefix path
     * @return finance
     */
    public synchronized static Finance loadingLibraries(List<String> libFiles, String prefixPath) {
    if (finance != null) {
      return finance;
    }
    finance = new Finance(libFiles, prefixPath);
    return finance;
  }

    /**
     * 单例sdk
     *
     * @return long
     */
    public synchronized static long SingletonSDK() {
    if (sdk > 0) {
      return sdk;
    }
    sdk = Finance.NewSdk();
    return sdk;
  }

    /**
     * 销毁sdk,保证线程可见性
     *
     * @param destroySDK the destroy sdk
     * @return
     */
    public synchronized static void DestroySingletonSDK(long destroySDK) {
    sdk = 0L;
    Finance.DestroySdk(destroySDK);
  }

}
