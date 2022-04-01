package me.chanjar.weixin.cp.api;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.msgaudit.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 企业微信会话内容存档测试类.
 * 官方文档：https://developer.work.weixin.qq.com/document/path/91360
 *
 * @author Wang_Wong
 * @date 2022-01-17
 */
@Slf4j
public class WxCpMsgAuditTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  // com.binarywang.spring.starter.wxjava.cp.config.WxCpServiceAutoConfiguration
  @Test
  public void test() throws Exception {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);

    /**
     * 配置：
     * <xml>
     *   <corpId>wwa3bexxXXXXXX</corpId>
     *   <agentId>自定义agentId</agentId>
     *   <corpSecret>xIpum7Yt4NMXXXXXXX</corpSecret>
     *   <token>2bSNqXXXXXXXX</token>
     *   <aesKey>MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDZuPVMyVyMvJkdSCZA893B2pggd1r95T8k2QZgz+VejtaDJCbD60mYoW1Uwwlwuqy8W78M6MmXsskn+5XunyR1WJlJGqgi0OMVGYvSfkNb9kD50fM21CGLcN1y4miL9fVNBIsvJmIUeJCNS8TioAVGFvh2EgzjqTR1gHfDwu8If7rfGmTHkPYL8hQxMg/EQ3451JOIBHSa7EQSx64SIoVWEgSDFQjGEpjUiJRfciyyz+nTSkEDgFa9hpyTS6E0c/3Q5lVDFgIwTArC19XBFKb00PbcFuLriOIsTBX4K9XWBtefVXowAdqUVQDH6BNUIK7/iVPQ4L3p+F5DBOrx8I/7AgMBAAECggEBAK53C/nwEX2lU3ynaB/8SuMga274ta1mmmbIkdfaQA65nyOPQJEWZe8szBN0BoiSzgBR9JI/p+srlQ25CLgiRnDSAmMWPU1I3e72fZi7HPcAKakGmEKDUi4OzyVUUDp3aY3B6lZqB4Yn5o2S/b4sRI2ZspfKdxGncSYHP/Far3i6hzq2C1hbyYM6HkHPcrQ+z6ir6GxjLvHXssVJ+/C0HMsVIQAWPyEGbzWozS+EswmQ+itk+7cewiLWbaCSp6lsjHKGTxJwCxRes0nUt2SfkLnIUkDLxB7c6zDQJCn1K2UckCjNBlCWl+oDWLkLQ7UAJ+4IYYSslR4wXzRg8PplW8ECgYEA9VlEprEoG2oSn3HXIMFg0MANViQe89QJQdwd7D5h4FLxXQLItxqmZj77iktlzlICcK9WT9WHRY1AOilsuMaDmY0VH3Z8r/X9BU712KFJqMYH5CNxrqHOya3BG+CclEKToaOTmo9kiOpFAMNSuuWs6gvILJ0CKEmSUo5G9fJu4fkCgYEA4yypHoRZIP0mDdVDeVtdHHcq5JdWF6xbAFs4P57VHG1KDMWouk3IHSeO279gEIwcBAdaLcMMgFfzyQBwcisxjC76oyoZnbSntB7ZMFdPqALKfxIdleLilbASuRKesVAF+OgOx/yp/aQUeLG2pVBivgn2TyGMwjnxznTh9vh+vpMCgYEAmOva7krdRLkIgnjiLXhab8JEjbxVzoQKgRJBVE5NkxQffGmP0RC7Rl9bSQdVnRNgkfu3QGtGtQMlVRscuM6Cl+JnmASyErqvye89LJja4GcN5BRzdvVDflDeXBHThlU4zza1eVCGyQ+7ko4rsnIVJIvTaHs0LQguO2aStBk3I4ECgYAyBsO3VK3L9fNLWItjThtTCWsIq8rpq6reiTf5yqBjgi2sYlqlrDtFMFDlU190RWZl/Lh/G1TFbpjgypf4jEp89Ft9UugRMpc7sw9g9dk0xmiRUwvw1eXP0NZOqysHIPgvt+qJX7qPgHKBoaD3Bpy3/Lmg82Jr4xa8wECCgnZmwQKBgH7hirPs1/HqBrbxS726IZUf9QTmVkyOYIwzuwFYKb/+4caSah+iaXexVux0xS5tchj/6c1dQSKJmlegV8smIb6EEcko7llA1y1P5QFtXtaaRd07tTsv3BKEg496YLRjbxPzgJn6Fsoz3TTdGwESL8Q3I2h0WmVVhmr/rjr+RkWQ</aesKey>
     *
     * 注意：最好先配置lib开头的系统库，再配置sdk类库，配置绝对路径，最好配置为linux路径
     *   windows:
     *   <msgAuditLibPath>D:/WorkSpace/libcrypto-1_1-x64.dll,libssl-1_1-x64.dll,libcurl-x64.dll,WeWorkFinanceSdk.dll,libWeWorkFinanceSdk_Java.so</msgAuditLibPath>
     *   linux:
     *   <msgAuditLibPath>/www/osfile/work_msg_storage/libcrypto-1_1-x64.dll,libssl-1_1-x64.dll,libcurl-x64.dll,WeWorkFinanceSdk.dll,libWeWorkFinanceSdk_Java.so</msgAuditLibPath>
     * </xml>
     */

    /**
     * 建议放到redis，本次请求获取消息记录开始的seq值。首次访问填写0，非首次使用上次企业微信返回的最大seq。允许从任意seq重入拉取。
     */
    long seq = 0L;

    /**
     * 图片，语音，视频，表情，文件，音频存档消息,音频共享文档消息调用  获取媒体消息
     */
    List<String> mediaType = Arrays.asList(new String[]{"image", "voice", "video", "emotion", "file", "meeting_voice_call", "voip_doc_share"});

    // 模拟多次拉取数据，根据seq拉取
    for (int i = 0; i < 3; i++) {
      // 本次请求获取消息记录开始的seq值。首次访问填写0，非首次使用上次企业微信返回的最大seq。允许从任意seq重入拉取。
      WxCpChatDatas chatDatas = cpService.getMsgAuditService().getChatDatas(seq, 10L, null, null, 1000L);
      if (chatDatas != null && chatDatas.getChatData().size() > 0) {

        List<WxCpChatDatas.WxCpChatData> chatdata = chatDatas.getChatData();
        Iterator<WxCpChatDatas.WxCpChatData> iterator = chatdata.iterator();
        while (iterator.hasNext()) {
          WxCpChatDatas.WxCpChatData chatData = iterator.next();
          seq = chatData.getSeq();

          // 数据
//          String msgId = chatData.getMsgId();
//          String encryptChatMsg = chatData.getEncryptChatMsg();
//          String encryptRandomKey = chatData.getEncryptRandomKey();
//          Integer publickeyVer = chatData.getPublickeyVer();

          // 获取明文数据
          final String chatPlainText = cpService.getMsgAuditService().getChatPlainText(chatData);
          final WxCpChatModel wxCpChatModel = WxCpChatModel.fromJson(chatPlainText);
          log.info("明文数据为：{}", wxCpChatModel.toJson());

          // 获取消息数据
          final WxCpChatModel decryptData = cpService.getMsgAuditService().getDecryptData(chatData);
          log.info("获取消息数据为：{}", decryptData.toJson());

          /**
           * 注意：
           * 根据上面返回的文件类型来获取媒体文件，
           * 不同的文件类型，拼接好存放文件的绝对路径，写入文件流，获取媒体文件。（拼接绝对文件路径的原因，以便上传到腾讯云或阿里云对象存储）
           *
           * 目标文件绝对路径+实际文件名，比如：/usr/local/file/20220114/474f866b39d10718810d55262af82662.gif
           */
          String path = "/usr/local/file/";
          String msgType = decryptData.getMsgType();
          if (mediaType.contains(decryptData.getMsgType())) {
            // 文件后缀
            String suffix = "";
            // 文件名md5
            String md5Sum = "";
            // sdkFileId
            String sdkFileId = "";
            switch (msgType) {
              case "image":
                suffix = ".jpg";
                md5Sum = decryptData.getImage().getMd5Sum();
                sdkFileId = decryptData.getImage().getSdkFileId();
                break;
              case "voice":
                suffix = ".amr";
                md5Sum = decryptData.getVoice().getMd5Sum();
                sdkFileId = decryptData.getVoice().getSdkFileId();
                break;
              case "video":
                suffix = ".mp4";
                md5Sum = decryptData.getVideo().getMd5Sum();
                sdkFileId = decryptData.getVideo().getSdkFileId();
                break;
              case "emotion":
                md5Sum = decryptData.getEmotion().getMd5Sum();
                sdkFileId = decryptData.getEmotion().getSdkFileId();
                int type = decryptData.getEmotion().getType();
                switch (type) {
                  case 1:
                    suffix = ".gif";
                    break;
                  case 2:
                    suffix = ".png";
                    break;
                  default:
                    return;
                }
                break;
              case "file":
                md5Sum = decryptData.getFile().getMd5Sum();
                suffix = "." + decryptData.getFile().getFileExt();
                sdkFileId = decryptData.getFile().getSdkFileId();
                break;
              // 音频存档消息
              case "meeting_voice_call":

                md5Sum = decryptData.getVoiceId();
                sdkFileId = decryptData.getMeetingVoiceCall().getSdkFileId();
                for (WxCpChatModel.MeetingVoiceCall.DemoFileData demofiledata : decryptData.getMeetingVoiceCall().getDemoFileData()) {
                  String demoFileDataFileName = demofiledata.getFileName();
                  suffix = demoFileDataFileName.substring(demoFileDataFileName.lastIndexOf(".") + 1);
                }

                break;
              // 音频共享文档消息
              case "voip_doc_share":

                md5Sum = decryptData.getVoipId();
                WxCpFileItem docShare = decryptData.getVoipDocShare();
                String fileName = docShare.getFileName();
                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

                break;
              default:
                return;
            }

            // 拉取媒体文件
            String targetPath = path + md5Sum + suffix;
            cpService.getMsgAuditService().getMediaFile(sdkFileId, null, null, 1000L, targetPath);

          }

        }

      }

    }


    /**
     * 文本
     */
//    String text = "{\"msgid\":\"CAQQluDa4QUY0On2rYSAgAMgzPrShAE=\",\"action\":\"send\",\"from\":\"XuJinSheng\",\"tolist\":[\"icefog\"],\"roomid\":\"\",\"msgtime\":1547087894783,\"msgtype\":\"text\",\"text\":{\"content\":\"test\"}}";
    String text = "{\"msgid\":\"CAQQluDa4QUY0On2rYSAgAMgzPrShAE=\",\"action\":\"send\",\"from\":\"XuJinSheng\",\"tolist\":[\"icefog\"],\"roomid\":\"\",\"msgtime\":1547087894783,\"msgtype\":\"text\",\"text\":{\"content\":\"这是一条引用/回复消息：\\\"\\n------\\n@nick777\"}}";
    WxCpChatModel modelText = WxCpChatModel.fromJson(text);
    log.info("数据为：" + modelText.toJson());


    /**
     * 图片
     */
    String image = "{\"msgid\":\"CAQQvPnc4QUY0On2rYSAgAMgooLa0Q8=\",\"action\":\"send\",\"from\":\"XuJinSheng\",\"tolist\":[\"icefog\"],\"roomid\":\"\",\"msgtime\":0,\"msgtype\":\"image\",\"image\":{\"md5sum\":\"50de8e5ae8ffe4f1df7a93841f71993a\",\"filesize\":70961,\"sdkfileid\":\"CtYBMzA2OTAyMDEwMjA0NjIzMDYwMDIwMTAwMDIwNGI3ZmU0MDZlMDIwMzBmNTliMTAyMDQ1YzliNTQ3NzAyMDQ1YzM3M2NiYzA0MjQ2NjM0MzgzNTM0NjEzNTY1MmQzNDYxMzQzODJkMzQzMTYxNjEyZDM5NjEzOTM2MmQ2MTM2NjQ2NDY0NjUzMDY2NjE2NjM1MzcwMjAxMDAwMjAzMDExNTQwMDQxMDUwZGU4ZTVhZThmZmU0ZjFkZjdhOTM4NDFmNzE5OTNhMDIwMTAyMDIwMTAwMDQwMBI4TkRkZk1UWTRPRGcxTVRBek1ETXlORFF6TWw4eE9UUTVOamN6TkRZMlh6RTFORGN4TWpNNU1ERT0aIGEwNGQwYWUyM2JlYzQ3NzQ5MjZhNWZjMjk0ZTEyNTkz\"}}";
    WxCpChatModel modelImage = WxCpChatModel.fromJson(image);
    log.info("数据为：" + modelImage.toJson());


    /**
     * 撤回消息
     */
    String revoke = "{\"msgid\":\"15775510700152506326_1603875615\",\"action\":\"recall\",\"from\":\"kenshin\",\"tolist\":[\"wmUu0zBgAALV7ZymkcMyxvbTe8YdWxxA\"],\"roomid\":\"\",\"msgtime\":1603875615723,\"msgtype\":\"revoke\",\"revoke\":{\"pre_msgid\":\"14822339130656386894_1603875600\"}}";
    WxCpChatModel modelRevoke = WxCpChatModel.fromJson(revoke);
    log.info("数据为：" + modelRevoke.toJson());


    /**
     * 同意会话聊天内容
     */
    String agree = "{\"msgid\":\"8891446340739254950_1603875826\",\"action\":\"send\",\"from\":\"wmGAgeDQAAvQeaTqWwkMTxGMkvI7OOuQ\",\"tolist\":[\"kenshin\"],\"roomid\":\"\",\"msgtime\":1603875826656,\"msgtype\":\"agree\",\"agree\":{\"userid\":\"wmGAgeDQAAvQeaTqWwkMTxGMkvI7OOuQ\",\"agree_time\":1603875826656}}";
    String disagree = "{\"msgid\":\"17972321270926900092_1603875944\",\"action\":\"send\",\"from\":\"wmErxtDgAA9AW32YyyuYRimKr7D1KWlw\",\"tolist\":[\"kenshin\"],\"roomid\":\"\",\"msgtime\":1603875944122,\"msgtype\":\"disagree\",\"disagree\":{\"userid\":\"wmErxtDgAA9AW32YyyuYRimKr7D1KWlw\",\"disagree_time\":1603875944122}}";
    WxCpChatModel modelAgree = WxCpChatModel.fromJson(agree);
    WxCpChatModel modelDisagree = WxCpChatModel.fromJson(disagree);
    log.info("数据为：" + modelAgree.toJson());
    log.info("数据为：" + modelDisagree.toJson());


    /**
     * 语音
     */
    String voice = "{\"msgid\":\"10958372969718811103_1603875609\",\"action\":\"send\",\"from\":\"wmGAgeDQAAdBjb8CK4ieMPRm7Cqm-9VA\",\"tolist\":[\"kenshin\"],\"roomid\":\"\",\"msgtime\":1603875609704,\"msgtype\":\"voice\",\"voice\":{\"md5sum\":\"9db09c7fa627c9e53f17736c786a74d5\",\"voice_size\":6810,\"play_length\":10,\"sdkfileid\":\"kcyZjZqOXhETGYxajB2Zkp5Rk8zYzh4RVF3ZzZGdXlXNWRjMUoxVGZxbzFTTDJnQ2YxL0NraVcxUUJNK3VUamhEVGxtNklCbjZmMEEwSGRwN0h2cU1GQTU1MDRSMWdTSmN3b25ZMkFOeG5hMS90Y3hTQ0VXRlVxYkR0Ymt5c3JmV2VVcGt6UlNXR1ZuTFRWVGtudXVldDRjQ3hscDBrMmNhMFFXVnAwT3Y5NGVqVGpOcWNQV2wrbUJwV01TRm9xWmNDRVVrcFY5Nk9OUS9GbXIvSmZvOVVZZjYxUXBkWnMvUENkVFQxTHc2N0drb2pJT0FLZnhVekRKZ1FSNDU3ZnZtdmYvTzZDOG9DRXl2SUNIOHc9PRI0TkRkZk56ZzRNVE13TVRjMk5qQTRNak0yTmw4ek5qRTVOalExTjE4eE5qQXpPRGMxTmpBNRogNzM3MDY2NmM2YTc5Njg3NDdhNzU3NDY0NzY3NTY4NjY=\"}}";
    WxCpChatModel modelVoice = WxCpChatModel.fromJson(voice);
    log.info("数据为：" + modelVoice.toJson());


    /**
     * 视频
     */
    String video = "{\"msgid\":\"17955920891003447432_1603875627\",\"action\":\"send\",\"from\":\"kenshin\",\"tolist\":[\"wmGAgeDQAAHuRJbt4ZQI_1cqoQcf41WQ\"],\"roomid\":\"\",\"msgtime\":1603875626823,\"msgtype\":\"video\",\"video\":{\"md5sum\":\"d06fc80c01d6fbffcca3b229ba41eac6\",\"filesize\":15169724,\"play_length\":108,\"sdkfileid\":\"MzAzMjYxMzAzNTYzMzgzMjMyMzQwMjAxMDAwMjA0MDBlNzc4YzAwNDEwZDA2ZmM4MGMwMWQ2ZmJmZmNjYTNiMjI5YmE0MWVhYzYwMjAxMDQwMjAxMDAwNDAwEjhORGRmTVRZNE9EZzFNREEyTlRjM056QXpORjgxTWpZeE9USTBOek5mTVRZd016ZzNOVFl5Tnc9PRogNTIzNGQ1NTQ5N2RhNDM1ZDhlZTU5ODk4NDQ4NzRhNDk=\"}}";
    WxCpChatModel modelVideo = WxCpChatModel.fromJson(video);
    log.info("数据为：" + modelVideo.toJson());


    /**
     * 名片
     */
    String card = "{\"msgid\":\"13714216591700685558_1603875680\",\"action\":\"send\",\"from\":\"kenshin\",\"tolist\":[\"wmGAgeDQAAy2Dtr0F8aK4dTuatfm-5Rg\"],\"roomid\":\"\",\"msgtime\":1603875680377,\"msgtype\":\"card\",\"card\":{\"corpname\":\"微信联系人\",\"userid\":\"wmGAgeDQAAGjFmfnP7A3j2JxQDdLNhSw\"}}";
    WxCpChatModel modelCard = WxCpChatModel.fromJson(card);
    log.info("数据为：" + modelCard.toJson());


    /**
     * 位置
     */
    String location = "{\"msgid\":\"2641513858500683770_1603876152\",\"action\":\"send\",\"from\":\"icefog\",\"tolist\":[\"wmN6etBgAA0sbJ3invMvRxPQDFoq9uWA\"],\"roomid\":\"\",\"msgtime\":1603876152141,\"msgtype\":\"location\",\"location\":{\"longitude\":116.586285899,\"latitude\":39.911125799,\"address\":\"北京市xxx区xxx路xxx大厦x座\",\"title\":\"xxx管理中心\",\"zoom\":15}}";
    WxCpChatModel modelLocation = WxCpChatModel.fromJson(location);
    log.info("数据为：" + modelLocation.toJson());


    /**
     * 表情
     */
    String emotion = "{\"msgid\":\"6623217619416669654_1603875612\",\"action\":\"send\",\"from\":\"icef\",\"tolist\":[\"wmErxtDgAAhteCglUZH2kUt3rq431qmg\"],\"roomid\":\"\",\"msgtime\":1603875611148,\"msgtype\":\"emotion\",\"emotion\":{\"type\":1,\"width\":290,\"height\":290,\"imagesize\":962604,\"md5sum\":\"94c2b0bba52cc456cb8221b248096612\",\"sdkfileid\":\"4eE1ESTVNalE1TnprMFh6RTJNRE00TnpVMk1UST0aIDc0NzI2NjY1NzE3NTc0Nzg2ZDZlNzg2YTY5NjY2MTYx\"}}";
    WxCpChatModel modelEmotion = WxCpChatModel.fromJson(emotion);
    log.info("数据为：" + modelEmotion.toJson());


    /**
     * 文件
     */
    String file = "{\"msgid\":\"18039699423706571225_1603875608\",\"action\":\"send\",\"from\":\"kens\",\"tolist\":[\"wmErxtDgAArDlFIhf76O6w4GxU81al8w\"],\"roomid\":\"\",\"msgtime\":1603875608214,\"msgtype\":\"file\",\"file\":{\"md5sum\":\"18e93fc2ea884df23b3d2d3b8667b9f0\",\"filename\":\"资料.docx\",\"fileext\":\"docx\",\"filesize\":18181,\"sdkfileid\":\"E4ODRkZjIzYjNkMmQzYjg2NjdiOWYwMDIwMTA1MDIwMTAwMDQwMBI4TkRkZk1UWTRPRGcxTURrek9UZzBPVEF6TTE4eE1EUXpOVGcxTlRVNVh6RTJNRE00TnpVMk1EZz0aIDMwMzkzMzY0NjEzNjM3NjY2NDY1NjMzNjYxMzIzNzYx\"}}";
    WxCpChatModel modelFile = WxCpChatModel.fromJson(file);
    log.info("数据为：" + modelFile.toJson());


    /**
     * 链接
     */
    String link = "{\"msgid\":\"11788441727514772650_1603875624\",\"action\":\"send\",\"from\":\"kenshin\",\"tolist\":[\"0000726\"],\"roomid\":\"\",\"msgtime\":1603875624476,\"msgtype\":\"link\",\"link\":{\"title\":\"邀请你加入群聊\",\"description\":\"技术支持群，进入可查看详情\",\"link_url\":\"https://work.weixin.qq.com/wework_admin/external_room/join/exceed?vcode=xxx\",\"image_url\":\"https://wework.qpic.cn/wwpic/xxx/0\"}}";
    WxCpChatModel modelLink = WxCpChatModel.fromJson(link);
    log.info("数据为：" + modelLink.toJson());


    /**
     * 小程序消息
     */
    String weapp = "{\"msgid\":\"11930598857592605935_1603875608\",\"action\":\"send\",\"from\":\"kens\",\"tolist\":[\"wmGAgeDQAAsgQetTQGqRbMxrkodpM3fA\"],\"roomid\":\"\",\"msgtime\":1603875608691,\"msgtype\":\"weapp\",\"weapp\":{\"title\":\"开始聊天前请仔细阅读服务须知事项\",\"description\":\"客户需同意存档聊天记录\",\"username\":\"xxx@app\",\"displayname\":\"服务须知\"}}";
    WxCpChatModel modelWeapp = WxCpChatModel.fromJson(weapp);
    log.info("数据为：" + modelWeapp.toJson());


    /**
     * 会话记录消息
     */
    String chatrecord = "{\"msgid\":\"11354299838102555191_1603875658\",\"action\":\"send\",\"from\":\"ken\",\"tolist\":[\"icef\"],\"roomid\":\"\",\"msgtime\":1603875657905,\"msgtype\":\"chatrecord\",\"chatrecord\":{\"title\":\"群聊\",\"item\":[{\"type\":\"ChatRecordText\",\"msgtime\":1603875610,\"content\":\"{\\\"content\\\":\\\"test\\\"}\",\"from_chatroom\":false},{\"type\":\"ChatRecordText\",\"msgtime\":1603875620,\"content\":\"{\\\"content\\\":\\\"test2\\\"}\",\"from_chatroom\":false}]}}";
    WxCpChatModel modelChatRecord = WxCpChatModel.fromJson(chatrecord);
    log.info("数据为：" + modelChatRecord.toJson());


    /**
     * 填表消息
     */
    String collect = "{\"msgid\":\"2500536226619379797_1576034482\",\"action\":\"send\",\"from\":\"nick\",\"tolist\":[\"XuJinSheng\",\"15108264797\"],\"roomid\":\"wrjc7bDwYAOAhf9quEwRRxyyoMm0QAAA\",\"msgtime\":1576034482344,\"msgtype\":\"collect\",\"collect\":{\"room_name\":\"这是一个群\",\"creator\":\"nick\",\"create_time\":\"2019-12-11 11:21:22\",\"title\":\"这是填表title\",\"details\":[{\"id\":1,\"ques\":\"表项1，文本\",\"type\":\"Text\"},{\"id\":2,\"ques\":\"表项2，数字\",\"type\":\"Number\"},{\"id\":3,\"ques\":\"表项3，日期\",\"type\":\"Date\"},{\"id\":4,\"ques\":\"表项4，时间\",\"type\":\"Time\"}]}}";
    WxCpChatModel modelCollect = WxCpChatModel.fromJson(collect);
    log.info("数据为：" + modelCollect.toJson());


    /**
     * 红包消息
     */
    String redpacket = "{\"msgid\":\"333590477316965370_1603877439\",\"action\":\"send\",\"from\":\"kens\",\"tolist\":[\"1000000444696\"],\"roomid\":\"\",\"msgtime\":1603877439038,\"msgtype\":\"redpacket\",\"redpacket\":{\"type\":1,\"wish\":\"恭喜发财，大吉大利\",\"totalcnt\":1,\"totalamount\":3000}}";
    WxCpChatModel modelRedpacket = WxCpChatModel.fromJson(redpacket);
    log.info("数据为：" + modelRedpacket.toJson());


    /**
     * 会议邀请信息
     */
    String meeting = "{\"msgid\":\"5935786683775673543_1603877328\",\"action\":\"send\",\"from\":\"ken\",\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wr2vOpDgAAN4zVWKbS\",\"msgtime\":1603877328914,\"msgtype\":\"meeting\",\"meeting\":{\"topic\":\"夕会\",\"starttime\":1603877400,\"endtime\":1603881000,\"address\":\"\",\"remarks\":\"\",\"meetingtype\":102,\"meetingid\":1210342560,\"status\":1}}";
    WxCpChatModel modelMeeting = WxCpChatModel.fromJson(meeting);
    log.info("数据为：" + modelMeeting.toJson());


    /**
     * 切换企业日志
     */
    String switchlog = "{\"msgid\":\"125289002219525886280\",\"action\":\"switch\",\"time\":1554119421840,\"user\":\"XuJinSheng\"}";
    WxCpChatModel modelSwitchLog = WxCpChatModel.fromJson(switchlog);
    log.info("数据为：" + modelSwitchLog.toJson());


    /**
     * 在线文档消息
     */
    String docMsg = "{\"msgid\":\"9732089160923053207_1603877765\",\"action\":\"send\",\"from\":\"ken\",\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wrJawBCQAAStr3jxVxEH\",\"msgtime\":1603877765291,\"msgtype\":\"docmsg\",\"doc\":{\"title\":\"测试&演示客户\",\"doc_creator\":\"test\",\"link_url\":\"https://doc.weixin.qq.com/txdoc/excel?docid=xxx\"}}";
    WxCpChatModel modelDocMsg = WxCpChatModel.fromJson(docMsg);
    log.info("数据为：" + modelDocMsg.toJson());


    /**
     * MarkDown格式消息
     */
    String markDown = "{\"msgid\":\"7546287934688259248_1603875715\",\"action\":\"send\",\"from\":\"ken\",\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wr0SfLCgAAgCaCPeM33UNe\",\"msgtime\":1603875715782,\"msgtype\":\"markdown\",\"info\":{\"content\":\"请前往系统查看，谢谢。\"}}";
    WxCpChatModel modelMarkDown = WxCpChatModel.fromJson(markDown);
    log.info("数据为：" + modelMarkDown.toJson());


    /**
     * 图文消息
     */
    String news = "{\"msgid\":\"118732825779547782215\",\"action\":\"send\",\"from\":\"kens\",\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wrErxtDgAA0jgXE5\",\"msgtime\":1603876045165,\"msgtype\":\"news\",\"info\":{\"item\":[{\"title\":\"service \",\"description\":\"test\",\"url\":\"http://xxx\",\"picurl\":\"https://www.qq.com/xxx.jpg\"}]}}";
    WxCpChatModel modelNews = WxCpChatModel.fromJson(news);
    log.info("数据为：" + modelNews.toJson());


    /**
     * 日程消息
     */
    String calendar = "{\"msgid\":\"2345881211604379705_1603877680\",\"action\":\"send\",\"from\":\"ken\",\"tolist\":[\"icef\",\"test\"],\"roomid\":\"wr2LO0CAAAFrTZCGWWAxBA\",\"msgtime\":1603877680795,\"msgtype\":\"calendar\",\"calendar\":{\"title\":\"xxx业绩复盘会\",\"creatorname\":\"test\",\"attendeename\":[\"aaa\",\"bbb\"],\"starttime\":1603882800,\"endtime\":1603886400,\"place\":\"\",\"remarks\":\"\"}}";
    WxCpChatModel modelCalendar = WxCpChatModel.fromJson(calendar);
    log.info("数据为：" + modelCalendar.toJson());


    /**
     * 混合消息
     */
    String mixed = "{\"msgid\":\"DAQQluDa4QUY0On4kYSABAMgzPrShAE=\",\"action\":\"send\",\"from\":\"HeMiao\",\"tolist\":[\"HeChangTian\",\"LiuZeYu\"],\"roomid\":\"wr_tZ2BwAAUwHpYMwy9cIWqnlU3Hzqfg\",\"msgtime\":1577414359072,\"msgtype\":\"mixed\",\"mixed\":{\"item\":[{\"type\":\"text\",\"content\":\"{\\\"content\\\":\\\"你好[微笑]\\\\n\\\"}\"},{\"type\":\"image\",\"content\":\"{\\\"md5sum\\\":\\\"368b6c18c82e6441bfd89b343e9d2429\\\",\\\"filesize\\\":13177,\\\"sdkfileid\\\":\\\"CtYBMzA2OTAyMDEwMjA0NjIzMDYwMDIwMTAwMDWwNDVmYWY4Y2Q3MDIwMzBmNTliMTAyMDQwYzljNTQ3NzAyMDQ1ZTA1NmFlMjA0MjQ2NjM0NjIzNjY2MzYzNTMyMmQzNzYxMzQ2NDJkMzQ2MjYxNjQyZDM4MzMzMzM4MmQ3MTYyMzczMTM4NjM2NDYxMzczMjY2MzkwMjAxMDAwMjAzMDIwMDEwMDQxMDM2OGI2YzE4YzgyZTY0NDFiZmQ4OWIyNDNlOWQyNDI4MDIwMTAyMDIwMTAwMDQwMBI4TkRkZk2UWTRPRGcxTVRneE5URTFNRGc1TVY4eE1UTTFOak0yTURVeFh6RTFOemMwTVRNek5EYz0aIDQzMTY5NDFlM2MxZDRmZjhhMjEwY2M0NDQzZGUXOTEy\\\"}\"}]}}";
    WxCpChatModel modelMixed = WxCpChatModel.fromJson(mixed);
    log.info("获取混合消息，文件对象为：{}", modelMixed.getMixed().getItem().get(0).getContent());

    // 返回文件对象
    WxCpFileItem wxCpFileItem = WxCpFileItem.fromJson(modelMixed.getMixed().getItem().get(1).getContent());
    log.info("获取混合消息，文件对象为：{}", wxCpFileItem.toJson());
    log.info("数据为：" + modelMixed.toJson());


    /**
     * 音频存档消息
     */
    String meetingVoiceCall = "{\"msgid\":\"17952229780246929345_1594197637\",\"action\":\"send\",\"from\":\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\",\"tolist\":[\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\"],\"msgtime\":1594197581203,\"msgtype\":\"meeting_voice_call\",\"voiceid\":\"grb8a4c48a3c094a70982c518d55e40557\",\"meeting_voice_call\":{\"endtime\":1594197635,\"sdkfileid\":\"CpsBKjAqd0xhb2JWRUJldGtwcE5DVTB6UjRUalN6c09vTjVyRnF4YVJ5M24rZC9YcHF3cHRPVzRwUUlaMy9iTytFcnc0SlBkZDU1YjRNb0MzbTZtRnViOXV5WjUwZUIwKzhjbU9uRUlxZ3pyK2VXSVhUWVN2ejAyWFJaTldGSkRJVFl0aUhkcVdjbDJ1L2RPbjJsRlBOamJaVDNnPT0SOE5EZGZNVFk0T0RnMU16YzVNVGt5T1RJMk9GOHhNalk0TXpBeE9EZzJYekUxT1RReE9UYzJNemM9GiA3YTYyNzA3NTY4Nzc2MTY3NzQ2MTY0NzA2ZTc4NjQ2OQ==\",\"demofiledata\":[{\"filename\":\"65eb1cdd3e7a3c1740ecd74220b6c627.docx\",\"demooperator\":\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\",\"starttime\":1594197599,\"endtime\":1594197609}],\"sharescreendata\":[{\"share\":\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\",\"starttime\":1594197624,\"endtime\":1594197624}]}}";
    WxCpChatModel modelMeetingVoiceCall = WxCpChatModel.fromJson(meetingVoiceCall);
    log.info("数据为：" + modelMeetingVoiceCall.toJson());


    /**
     * 音频共享文档消息
     */
    String voipDocShare = "{\"msgid\":\"16527954622422422847_1594199256\",\"action\":\"send\",\"from\":\"18002520162\",\"tolist\":[\"wo137MCgAAYW6pIiKKrDe5SlzEhSgwbA\"],\"msgtime\":1594199235014,\"msgtype\":\"voip_doc_share\",\"voipid\":\"gr2751c98b19300571f8afb3b74514bd32\",\"voip_doc_share\":{\"filename\":\"欢迎使用微盘.pdf.pdf\",\"md5sum\":\"ff893900f24e55e216e617a40e5c4648\",\"filesize\":4400654,\"sdkfileid\":\"CpsBKjAqZUlLdWJMd2gvQ1JxMzd0ZjlpdW5mZzJOOE9JZm5kbndvRmRqdnBETjY0QlcvdGtHSFFTYm95dHM2VlllQXhkUUN5KzRmSy9KT3pudnA2aHhYZFlPemc2aVZ6YktzaVh3YkFPZHlqNnl2L2MvcGlqcVRjRTlhZEZsOGlGdHJpQ2RWSVNVUngrVFpuUmo3TGlPQ1BJemlRPT0SOE5EZGZNVFk0T0RnMU16YzVNVGt5T1RJMk9GODFNelUyTlRBd01qQmZNVFU1TkRFNU9USTFOZz09GiA3YTcwNmQ2Zjc5NjY3MDZjNjY2Zjc4NzI3NTZmN2E2YQ==\"}}";
    WxCpChatModel modelVoipDocShare = WxCpChatModel.fromJson(voipDocShare);
    log.info("数据为：" + modelVoipDocShare.toJson());


    /**
     * 互通红包消息
     */
    String externalRedpacket = "{\"msgid\":\"8632214264349267353_1603786184\",\"action\":\"send\",\"from\":\"woJ7ijBwAAmqwojT8r_DaNMbr_NAvaag\",\"tolist\":[\"woJ7ijBwAA6SjS_sIyPLZtyEPJlT7Cfw\",\"tiny-six768\"],\"roomid\":\"wrJ7ijBwAAG1vly_DzVI72Ghc-PtA5Dw\",\"msgtime\":1603786183955,\"msgtype\":\"external_redpacket\",\"redpacket\":{\"type\":1,\"wish\":\"恭喜发财，大吉大利\",\"totalcnt\":2,\"totalamount\":20}}";
    WxCpChatModel modelExternalRedpacket = WxCpChatModel.fromJson(externalRedpacket);
    log.info("数据为：" + modelExternalRedpacket.toJson());


    /**
     * 视频号消息
     */
    String sphfeed = "{\"msgid\":\"5702551662099334532_1619511584_external\",\"action\":\"send\",\"from\":\"yangzhu1\",\"tolist\":[\"wmJSb5CgAA4aWXWndJspQGpJMDbsMwMA\"],\"roomid\":\"\",\"msgtime\":1619511584444,\"msgtype\":\"sphfeed\",\"sphfeed\":{\"feed_type\":4,\"sph_name\":\"云游天地旅行家\",\"feed_desc\":\"瑞士丨盖尔默缆车，名副其实的过山车~\\n\\n#旅行#风景#热门\"}}";
    WxCpChatModel modelSphFeed = WxCpChatModel.fromJson(sphfeed);
    log.info("数据为：" + modelSphFeed.toJson());


    /**
     * 获取会话内容存档开启成员列表
     */
    List<String> permitUserList = cpService.getMsgAuditService().getPermitUserList(null);
    log.info(permitUserList.toString());


    ArrayList<WxCpCheckAgreeRequest.Info> userList = Lists.newArrayList();
    WxCpCheckAgreeRequest checkAgreeRequest = new WxCpCheckAgreeRequest();
    /**
     * 获取会话同意情况
     */
    WxCpCheckAgreeRequest.Info info = new WxCpCheckAgreeRequest.Info();
    info.setUserid("wangkai");
    info.setExteranalOpenId("wmOQpTDwAAkOscTrtUlSli0YLU2jcpUg");
    if(info != null){
      userList.add(info);
      checkAgreeRequest.setInfo(userList);
    }

    WxCpAgreeInfo wxCpAgreeInfo = cpService.getMsgAuditService().checkSingleAgree(checkAgreeRequest);
    log.info(wxCpAgreeInfo.toJson());


    /**
     * 获取会话内容存档内部群信息
     */
    WxCpGroupChat room = cpService.getMsgAuditService().getGroupChat("wrOQpTDwAAyPl84GBJ40W5eWxWtixSCA");
    log.info(room.toJson());

  }

}
