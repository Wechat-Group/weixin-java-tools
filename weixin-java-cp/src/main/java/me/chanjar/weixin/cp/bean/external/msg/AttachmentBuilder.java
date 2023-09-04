package me.chanjar.weixin.cp.bean.external.msg;

import lombok.Builder;

/**
 * @author codecrab
 */
public class AttachmentBuilder {

  @Builder(builderClassName = "ImageBuilder", builderMethodName = "imageBuilder")
  private static Attachment image(String mediaId, String picUrl) {
    Image image = new Image().setMediaId(mediaId).setPicUrl(picUrl);
    return new Attachment().setImage(image);
  }

  @Builder(builderClassName = "VideoBuilder", builderMethodName = "videoBuilder")
  private static Attachment video(String mediaId) {
    Video video = new Video().setMediaId(mediaId);
    return new Attachment().setVideo(video);
  }

  @Builder(builderClassName = "FileBuilder", builderMethodName = "fileBuilder")
  private static Attachment file(String mediaId) {
    File file = new File().setMediaId(mediaId);
    return new Attachment().setFile(file);
  }

  @Builder(builderClassName = "LinkBuilder", builderMethodName = "linkBuilder")
  private static Attachment link(String title, String url, String picUrl, String desc) {
    Link link = new Link().setTitle(title).setPicUrl(picUrl).setUrl(url).setDesc(desc);
    return new Attachment().setLink(link);
  }

  @Builder(builderClassName = "MiniProgramBuilder", builderMethodName = "miniProgramBuilder")
  private static Attachment miniProgram(String title, String picMediaId, String appId, String page) {
    MiniProgram miniProgram = new MiniProgram().setTitle(title).setPicMediaId(picMediaId).setAppid(appId).setPage(page);
    return new Attachment().setMiniProgram(miniProgram);
  }

}
