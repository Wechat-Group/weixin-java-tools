## 如何使用 `WxJava` 进行小程序云开发

[云开发（CloudBase）](https://www.cloudbase.net/)是基于Serverless架构构建的一站式后端云服务，涵盖函数、数据库、存储、CDN等服务，免后端运维，支持小程序、Web和APP开发。
其中，小程序·云开发是微信和腾讯云联合推出的云端一体化解决方案，基于云开发可以免鉴权调用微信所有开放能力，在微信开发者工具中即可开通使用。

### 一、  引入`maven`依赖


```
<dependency>
    <groupId>com.github.binarywang</groupId>
    <artifactId>weixin-java-miniapp</artifactId>
    <version>3.7.1.B</version>
</dependency>
```


### 二、  构造配置类，填入相关参数

```
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid(...); // 微信小程序的appid
    config.setSecret(...); // 微信小程序的Secret
    config.setToken(...); // 微信小程序消息服务器配置的token,如果程序不涉及相关功能，可以忽略
    config.setAesKey(...); // 微信小程序消息服务器配置的EncodingAESKey，同上，如果不涉及，可以忽略
    config.setMsgDataFormat(...); // 消息数据格式，可以为XML或者JSON
```


### 三、  构造service类，关联上述配置
 
```
   WxMaService wxMaService= new WxMaServiceImpl();

   wxMaService.setWxMaConfig(config);
```

### 四、  根据小程序前端需要调用相应的方法：

目前 `WxJava` 已支持当前所有接口（当然如果官方后续加入新接口，则还未在最新版本中实现，会考虑在之后版本中加入），所有已支持的接口列表可以参考在线`JavaDoc`：http://binary.ac.cn/weixin-java-miniapp-javadoc/cn/binarywang/wx/miniapp/api/WxMaCloudService.html


以触发云函数接口为例，可以采用如下方式调用 `invokeCloudFunction` 方法：


```
String result = wxMaService.getCloudService().invokeCloudFunction("rcn", "login", "{}"); // 拿到result之后，可以在后续加入自己的处理逻辑代码
```


更多方法调用实例可以参考 `WxJava` 源码中的单元测试类：`cn.binarywang.wx.miniapp.api.impl.WxMaCloudServiceImplTest`


另外，顺便在此列出当前已支持云开发的方法如下：

|  接口描述    | 方法名     |
| ---- | ---- |
| 删除文件 | batchDeleteFile(String env, String[] fileIds)|
| 获取文件下载链接 | batchDownloadFile(String env, String[] fileIds, long[] maxAges)|
| 数据库插入记录 | databaseAdd(String env, String query)|
| 数据库聚合记录 | databaseAggregate(String env, String query)|
| 新增集合 | databaseCollectionAdd(String env, String collectionName)|
| 删除集合 | databaseCollectionDelete(String env, String collectionName)|
| 获取特定云环境下集合信息 | databaseCollectionGet(String env, Long limit, Long offset)|
| 统计集合记录数或统计查询语句对应的结果记录数 | databaseCount(String env, String query)|
| 数据库删除记录 | databaseDelete(String env, String query)|
| 数据库导出 | databaseMigrateExport(String env, String filePath, int fileType, String query)|
| 数据库导入 | databaseMigrateImport(String env, String collectionName, String filePath, int fileType, boolean stopOnError, int conflictMode)|
| 数据库迁移状态查询 | databaseMigrateQueryInfo(String env, Long jobId)|
| 数据库查询记录 | databaseQuery(String env, String query)|
| 数据库更新记录 | databaseUpdate(String env, String query)|
| 获取腾讯云API调用凭证 | getQcloudToken(long lifeSpan)|
| 触发云函数 | invokeCloudFunction(String env, String name, String body)|
| 变更数据库索引 | updateIndex(String env, String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes, List<String> dropIndexNames)|
| 获取文件上传链接 | uploadFile(String env, String path)|

### 五、  `Spring` 框架整合：

可以参考https://github.com/binarywang/weixin-java-miniapp-demo 此项目整合 `Spring` 开发。
此项目基于 `Spring Boot` ，如果需要支持`Spring MVC`项目，适当改造即可。
