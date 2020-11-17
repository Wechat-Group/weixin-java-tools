package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.cloud.*;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.gson.JsonArray;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-22
 */
@Guice(modules = ApiTestModule.class)
public class WxMaCloudServiceImplTest {

  private static final String COLLECTION = "geo";
  @Inject
  private WxMaService wxMaService;

  @BeforeTest
  public void before() {
    /**
     * 用以解决：javax.net.ssl.SSLHandshakeException: PKIX path building failed
     * 参考：https://www.cnblogs.com/cloudapps/p/5022544.html
     */
    String mpCert = ClassLoader.getSystemResource("wx-mp-jssecacerts").getPath();
    String maCert = ClassLoader.getSystemResource("wx-ma-jssecacerts").getPath();
    System.setProperty("javax.net.ssl.trustStore", mpCert + "," + maCert);
    String property = System.getProperty("javax.net.ssl.trustStore");
    System.out.println("javax.net.ssl.trustStore=" + property);
  }


  @Test
  public void testInvokeCloudFunction() throws WxErrorException {
    final String result = this.wxMaService.getCloudService().invokeCloudFunction("login", "{}");
    assertThat(result).isNotNull();
  }

  @Test
  public void testAddList() throws WxErrorException {
    List<Map> stuList = new ArrayList<>();

    Map<String, Object> product1 = new HashMap<>();
    product1.put("description", "item1");
    product1.put("price", BigDecimal.valueOf(1.2));
    product1.put("due", new Date());

    /**
     * 等价于new db.Geo.Point(113, 23)
     * 参见：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-sdk-api/database/geo/Geo.Point.html
     */
    Map<String, Object> point = new HashMap<>();
    point.put("type", "Point");
    point.put("coordinates", new Integer[]{113, 23});

    Map<String, Object> product2 = new HashMap<>();
    product2.put("tags", new String[]{"cloud", "database"});
    product2.put("location", point);
    product2.put("done", false);

    stuList.add(product1);
    stuList.add(product2);
    List<String> idList = this.wxMaService.getCloudService().add(COLLECTION, stuList);

    System.out.println(idList.size());
    assertThat(idList).isNotEmpty();
  }

  @Test
  public void testAddObject() throws WxErrorException {
    /**
     * 等价于new db.Geo.Point(113, 23)
     * 参见：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-sdk-api/database/geo/Geo.Point.html
     */
    Map<String, Object> point = new HashMap<>();
    point.put("type", "Point");
    point.put("coordinates", new Integer[]{113, 23});

    Map<String, Object> product = new HashMap<>();
    product.put("description", "item1");
    product.put("price", BigDecimal.valueOf(1.2));
    product.put("due", new Date());
    product.put("tags", new String[]{"cloud", "database"});
    product.put("location", point);
    product.put("done", false);

    String id = this.wxMaService.getCloudService().add(COLLECTION, product);

    System.out.println(id);
    assertThat(id).isNotBlank();
  }


  @Test
  public void testDatabaseAdd() throws WxErrorException {
    JsonArray array = this.wxMaService.getCloudService().databaseAdd("db.collection(\"geo\").add({\n" +
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
  public void testDelete() throws WxErrorException {
    StringBuilder whereParamSb = new StringBuilder();
    whereParamSb.append("{")
      // 等于
      .append("_id: _.eq('79a2c43f5e7e9e8e001a120e494d51b8'),")
      // in
      .append("age: _.in([0, 1, 2, 3]),")
      // 小于
      .append("due: _.lt('Mar 29, 2020 8:47:07 AM'),")
      // 存在属性
      .append("price: _.exists(true)")
      .append("}");

    final int result = this.wxMaService.getCloudService().delete(
      COLLECTION, whereParamSb.toString());
    assertThat(result).isEqualTo(0);
  }

  @Test
  public void testDatabaseDelete() throws WxErrorException {
    final int result = this.wxMaService.getCloudService().databaseDelete(
      "db.collection(\"geo\").doc(\"056120a7-c89e-4616-95bf-dfc9a11daa3b\").remove()");
    assertThat(result).isEqualTo(0);
  }

  @Test
  public void testUpdate() throws WxErrorException {
    StringBuilder whereParamSb = new StringBuilder();
    whereParamSb.append("{")
      // in
      .append("age: _.in([0, 1, 2, 3]),")
      // 小于
      .append("due: _.lt('Mar 29, 2020 8:47:07 AM'),")
      // 存在属性
      .append("price: _.exists(true)")
      .append("}");

    StringBuilder updateSb = new StringBuilder();
    updateSb.append("{age: _.inc(1)}");

    final WxCloudDatabaseUpdateResult result = this.wxMaService.getCloudService().update(COLLECTION,
      whereParamSb.toString(), updateSb.toString());

    assertThat(result).isNotNull();
    assertThat(result.getMatched()).isGreaterThan(0);
    assertThat(result.getId()).isEmpty();
    assertThat(result.getModified()).isGreaterThan(0);
  }

  @Test
  public void testDatabaseUpdate() throws WxErrorException {
    final WxCloudDatabaseUpdateResult result = this.wxMaService.getCloudService().databaseUpdate(
      "db.collection(\"geo\").where({description:\"item1\"}).update({data:{age: _.inc(1)}})");
    assertThat(result).isNotNull();
    assertThat(result.getMatched()).isGreaterThan(0);
    assertThat(result.getId()).isEmpty();
    assertThat(result.getModified()).isGreaterThan(0);
  }

  @Test
  public void testQuery() throws WxErrorException {
    StringBuilder whereParamSb = new StringBuilder();
    whereParamSb.append("{")
      // in
      .append("age: _.in([0, 1, 2, 3]),")
      // 小于
      .append("due: _.lt('Mar 29, 2020 8:47:07 AM'),")
      // 存在属性
      .append("price: _.exists(true)")
      .append("}");

//    // Hutool创建有序map,返回LinkedHashMap
//    Map<String, Integer> map2 = MapUtil.newHashMap(true);
//    map2.put("_id", "asc");
//    map2.put("price", "desc");

    // 有序map
    ImmutableSortedMap<String, String> orderBy = new ImmutableSortedMap
      .Builder<String, String>(Ordering.natural())
      .put("_id", "asc")
      .put("price", "desc")
      .build();

    final WxCloudDatabaseQueryResult result = this.wxMaService.getCloudService().query(COLLECTION,
      whereParamSb.toString(), orderBy, 20, 20);
    assertThat(result).isNotNull();
    assertThat(result.getPager()).isNotNull();
    assertThat(result.getPager().getLimit()).isEqualTo(10);
    assertThat(result.getPager().getOffset()).isEqualTo(1);
    assertThat(result.getPager().getTotal()).isGreaterThan(0);
    assertThat(result.getData().length).isGreaterThan(0);
  }

  @Test
  public void testDatabaseQuery() throws WxErrorException {
    final WxCloudDatabaseQueryResult result = this.wxMaService.getCloudService().databaseQuery(
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
    final JsonArray result = this.wxMaService.getCloudService().databaseAggregate(
      "db.collection(\"geo\").aggregate().match({tags:\"cloud\"}).limit(10).end()");
    assertThat(result).isNotNull();
  }

  @Test
  public void testCount() throws WxErrorException {
    StringBuilder whereParamSb = new StringBuilder();
    whereParamSb.append("{")
      // in
      .append("age: _.in([0, 1, 2, 3]),")
      // 小于
      .append("due: _.lt('Mar 29, 2020 8:47:07 AM'),")
      // 存在属性
      .append("price: _.exists(true)")
      .append("}");
    final Long result = this.wxMaService.getCloudService().count(COLLECTION, whereParamSb.toString());
    assertThat(result).isGreaterThan(0);
  }

  @Test
  public void testDatabaseCount() throws WxErrorException {
    final Long result = this.wxMaService.getCloudService().databaseCount(
      "db.collection(\"geo\").where({done:false}).count()");
    assertThat(result).isGreaterThan(0);
  }

  @Test
  public void testUpdateIndex() throws WxErrorException {
    this.wxMaService.getCloudService().updateIndex(COLLECTION,
      Lists.newArrayList(new WxCloudDatabaseCreateIndexRequest()
        .setName("drop_index")
        .setUnique(true)
        .setKeys(Lists.newArrayList(new WxCloudDatabaseCreateIndexRequest.IndexKey().setDirection("2dsphere").setName("test"))
        )),
      Lists.newArrayList("add_index2"));
  }

  @Test
  public void testDatabaseMigrateImport() throws WxErrorException {
    final Long result = this.wxMaService.getCloudService().databaseMigrateImport(COLLECTION, "test.json",
      1, true, 1);
    assertThat(result).isGreaterThan(0);
  }

  @Test
  public void testDatabaseMigrateExport() throws WxErrorException {
    final Long result = this.wxMaService.getCloudService().databaseMigrateExport("test.json", 1,
      "const Point = db.Geo.Point;db.collection('geo').where({age: _.gt(1)}).limit(10).skip(1).orderBy('age','asc')" +
        ".orderBy('name', 'desc')" +
        ".field({ name: true }).get()");
    assertThat(result).isGreaterThan(0);
  }

  @Test
  public void testDatabaseMigrateQueryInfo() throws WxErrorException {
    final WxCloudCloudDatabaseMigrateQueryInfoResult result = this.wxMaService.getCloudService()
      .databaseMigrateQueryInfo(476629L);
    assertThat(result).isNotNull();
    System.out.println(result.getFileUrl());
  }

  @Test
  public void testUploadFile() throws WxErrorException {
    final WxCloudUploadFileResult result = this.wxMaService.getCloudService().uploadFile("E:\\MyDocs\\Desktop" +
      "\\test.json");

    assertThat(result).isNotNull();
    assertThat(result.getAuthorization()).isNotNull();
    assertThat(result.getToken()).isNotNull();
    assertThat(result.getUrl()).isNotNull();
    assertThat(result.getFileId()).isNotNull();
    assertThat(result.getCosFileId()).isNotNull();

  }

  @Test
  public void testBatchDownloadFile() throws WxErrorException {
    final WxCloudBatchDownloadFileResult result = this.wxMaService.getCloudService().batchDownloadFile(
      new String[]{"cloud://rcn.7263-rcn-1258788140/Snipaste_2019-11-13_00-21-27.png", "cloud://rcn" +
        ".7263-rcn-1258788140/avatar.jpg"},
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
    final WxCloudBatchDeleteFileResult result = this.wxMaService.getCloudService().batchDeleteFile(
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
    this.wxMaService.getCloudService().databaseCollectionAdd("test_add_collection");
  }

  @Test
  public void testDatabaseCollectionDelete() throws WxErrorException {
    this.wxMaService.getCloudService().databaseCollectionAdd("test_delete_collection");
    this.wxMaService.getCloudService().databaseCollectionDelete("test_delete_collection");
  }

  @Test
  public void testDatabaseCollectionGet() throws WxErrorException {
    final WxCloudDatabaseCollectionGetResult result = this.wxMaService.getCloudService().databaseCollectionGet(
      null, null);

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
