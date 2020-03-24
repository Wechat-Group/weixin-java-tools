package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.cloud.*;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-22
 */
@Guice(modules = ApiTestModule.class)
public class WxMaCloudServiceImplTest {
  @Inject
  private WxMaService wxMaService;

  @Test
  public void testInvokeCloudFunction() throws WxErrorException {
    final String result = this.wxMaService.getCloudService().invokeCloudFunction("rcn", "login", "{}");
    assertThat(result).isNotNull();
  }

  @Test
  public void testDatabaseAdd() throws WxErrorException {
    JsonArray array = this.wxMaService.getCloudService().databaseAdd("rcn", "db.collection(\"geo\").add({\n" +
      "      data: [{\n" +
      "      description: \"item1\",\n" +
      "      due:\n" +
      "      new Date(\"2019-09-09\"),\n" +
      "        tags: [\n" +
      "          \"cloud\",\n" +
      "          \"database\"\n" +
      "        ],\n" +
      "      location:\n" +
      "      new db.Geo.Point(113, 23),\n" +
      "        done:false\n" +
      "    },\n" +
      "    {\n" +
      "      description: \"item2\",\n" +
      "      due:\n" +
      "      new Date(\"2019-09-09\"),\n" +
      "        tags: [\n" +
      "          \"cloud\",\n" +
      "          \"database\"\n" +
      "        ],\n" +
      "      location:\n" +
      "      new db.Geo.Point(113, 23),\n" +
      "        done:false\n" +
      "    }\n" +
      "      ]\n" +
      "  })");

    System.out.println(array);
    assertThat(array).isNotEmpty();
  }

  @Test
  public void testDatabaseDelete() throws WxErrorException {
    final int result = this.wxMaService.getCloudService().databaseDelete("rcn",
      "db.collection(\"geo\").doc(\"056120a7-c89e-4616-95bf-dfc9a11daa3b\").remove()");
    assertThat(result).isEqualTo(0);
  }

  @Test
  public void testDatabaseUpdate() throws WxErrorException {
    final WxCloudDatabaseUpdateResult result = this.wxMaService.getCloudService().databaseUpdate("rcn",
      "db.collection(\"geo\").where({description:\"item1\"}).update({data:{age: _.inc(1)}})");
    assertThat(result).isNotNull();
    assertThat(result.getMatched()).isGreaterThan(0);
    assertThat(result.getId()).isEmpty();
    assertThat(result.getModified()).isGreaterThan(0);
  }

  @Test
  public void testDatabaseQuery() throws WxErrorException {
    final WxCloudDatabaseQueryResult result = this.wxMaService.getCloudService().databaseQuery("rcn",
      "db.collection(\"geo\").where({done:false}).limit(10).skip(1).get()");
    assertThat(result).isNotNull();
    assertThat(result.getPager()).isNotNull();
    assertThat(result.getPager().getLimit()).isEqualTo(10);
    assertThat(result.getPager().getOffset()).isEqualTo(1);
    assertThat(result.getPager().getTotal()).isGreaterThan(0);
    assertThat(result.getData().length).isGreaterThan(0);
  }

  @Test
  public void testDatabaseAggregate() throws WxErrorException {
    final JsonArray result = this.wxMaService.getCloudService().databaseAggregate("rcn",
      "db.collection(\"geo\").aggregate().match({tags:\"cloud\"}).limit(10).end()");
    assertThat(result).isNotNull();
  }

  @Test
  public void testDatabaseCount() throws WxErrorException {
    final Long result = this.wxMaService.getCloudService().databaseCount("rcn",
      "db.collection(\"geo\").where({done:false}).count()");
    assertThat(result).isGreaterThan(0);
  }

  @Test
  public void testUpdateIndex() throws WxErrorException {
    this.wxMaService.getCloudService().updateIndex("rcn", "geo",
      Lists.newArrayList(new WxCloudDatabaseCreateIndexRequest()
        .setName("drop_index")
        .setUnique(true)
        .setKeys(Lists.newArrayList(new WxCloudDatabaseCreateIndexRequest.IndexKey().setDirection("2dsphere").setName("test"))
        )),
      Lists.newArrayList("add_index2"));
  }

  @Test
  public void testDatabaseMigrateImport() throws WxErrorException {
    final Long result = this.wxMaService.getCloudService().databaseMigrateImport("rcn", "geo", "test.json",
      1, true, 1);
    assertThat(result).isGreaterThan(0);
  }

  @Test
  public void testDatabaseMigrateExport() throws WxErrorException {
    final Long result = this.wxMaService.getCloudService().databaseMigrateExport("rcn", "test.json", 1,
      "const Point = db.Geo.Point;db.collection('geo').where({age: _.gt(1)}).limit(10).skip(1).orderBy('age','asc').orderBy('name', 'desc')" +
        ".field({ name: true }).get()");
    assertThat(result).isGreaterThan(0);
  }

  @Test
  public void testDatabaseMigrateQueryInfo() throws WxErrorException {
    final WxCloudCloudDatabaseMigrateQueryInfoResult result = this.wxMaService.getCloudService()
      .databaseMigrateQueryInfo("rcn", 476629L);
    assertThat(result).isNotNull();
    System.out.println(result.getFileUrl());
  }

  @Test
  public void testUploadFile() throws WxErrorException {
    final WxCloudUploadFileResult result = this.wxMaService.getCloudService().uploadFile("rcn", "E:\\MyDocs\\Desktop\\test.json");

    assertThat(result).isNotNull();
    assertThat(result.getAuthorization()).isNotNull();
    assertThat(result.getToken()).isNotNull();
    assertThat(result.getUrl()).isNotNull();
    assertThat(result.getFileId()).isNotNull();
    assertThat(result.getCosFileId()).isNotNull();

  }

  @Test
  public void testBatchDownloadFile() throws WxErrorException {
    final WxCloudBatchDownloadFileResult result = this.wxMaService.getCloudService().batchDownloadFile("rcn",
      new String[]{"cloud://rcn.7263-rcn-1258788140/Snipaste_2019-11-13_00-21-27.png", "cloud://rcn.7263-rcn-1258788140/avatar.jpg"},
      new long[]{7200, 6800});

    assertThat(result).isNotNull();
    assertThat(result.getFileList()).isNotNull();
    assertThat(result.getFileList().size()).isGreaterThan(0);
    assertThat(result.getFileList().get(0).getDownloadUrl()).isNotNull();
    assertThat(result.getFileList().get(0).getErrMsg()).isEqualTo("ok");
    assertThat(result.getFileList().get(0).getStatus()).isEqualTo(0);
    assertThat(result.getFileList().get(0).getFileId()).isNotNull();

  }

  @Test
  public void testBatchDeleteFile() throws WxErrorException {
    final WxCloudBatchDeleteFileResult result = this.wxMaService.getCloudService().batchDeleteFile("rcn",
      new String[]{"cloud://rcn.7263-rcn-1258788140/test.json"});

    assertThat(result).isNotNull();
    assertThat(result.getFileList()).isNotNull();
    assertThat(result.getFileList().size()).isGreaterThan(0);
    assertThat(result.getFileList().get(0).getErrMsg()).isEqualTo("ok");
    assertThat(result.getFileList().get(0).getStatus()).isEqualTo(0);
    assertThat(result.getFileList().get(0).getFileId()).isNotNull();
  }

  @Test
  public void testGetQcloudToken() throws WxErrorException {
    final WxCloudGetQcloudTokenResult result = this.wxMaService.getCloudService().getQcloudToken(1800);

    assertThat(result).isNotNull();
    assertThat(result.getSecretId()).isNotNull();
    assertThat(result.getSecretKey()).isNotNull();
    assertThat(result.getToken()).isNotNull();
    assertThat(result.getExpiredTime()).isNotNull();
  }

  @Test
  public void testDatabaseCollectionAdd() throws WxErrorException {
    this.wxMaService.getCloudService().databaseCollectionAdd("rcn","test_add_collection");
  }

  @Test
  public void testDatabaseCollectionDelete() throws WxErrorException {
    this.wxMaService.getCloudService().databaseCollectionAdd("rcn","test_delete_collection");
    this.wxMaService.getCloudService().databaseCollectionDelete("rcn","test_delete_collection");
  }

  @Test
  public void testDatabaseCollectionGet() throws WxErrorException {
    final WxCloudDatabaseCollectionGetResult result = this.wxMaService.getCloudService().databaseCollectionGet("rcn", null, null);

    assertThat(result).isNotNull();
    assertThat(result.getPager()).isNotNull();
    assertThat(result.getPager().getLimit()).isEqualTo(10);
    assertThat(result.getPager().getOffset()).isEqualTo(0);
    assertThat(result.getPager().getTotal()).isGreaterThan(0);

    assertThat(result.getCollections().length).isGreaterThan(0);
    assertThat(result.getCollections()[0].getCount()).isGreaterThan(0);
    assertThat(result.getCollections()[0].getName()).isNotNull();
    assertThat(result.getCollections()[0].getSize()).isGreaterThan(0);
    assertThat(result.getCollections()[0].getIndexCount()).isGreaterThan(0);
    assertThat(result.getCollections()[0].getIndexSize()).isGreaterThan(0);
  }
}
