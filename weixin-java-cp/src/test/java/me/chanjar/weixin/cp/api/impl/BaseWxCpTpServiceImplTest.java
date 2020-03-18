package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpTpService;
import me.chanjar.weixin.cp.bean.WxCpTpCorp;
import me.chanjar.weixin.cp.bean.WxCpTpPermanentCodeInfo;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import org.testng.annotations.Test;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.GET_PERMANENT_CODE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * 测试代码.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-18
 */
public class BaseWxCpTpServiceImplTest {
  private WxCpTpService tpService = spy(new WxCpTpServiceImpl());

  @Test
  public void testCheckSignature() {
  }

  @Test
  public void testGetSuiteAccessToken() {
  }

  @Test
  public void testGetSuiteTicket() {
  }

  @Test
  public void testTestGetSuiteTicket() {
  }

  @Test
  public void testJsCode2Session() {
  }

  @Test
  public void testGetCorpToken() {
  }

  @Test
  public void testGetPermanentCode() throws WxErrorException {
    String returnJson = "{\n" +
      "    \"errcode\":0 ,\n" +
      "    \"errmsg\":\"ok\" ,\n" +
      "    \"access_token\": \"xxxxxx\", \n" +
      "    \"expires_in\": 7200, \n" +
      "    \"permanent_code\": \"xxxx\", \n" +
      "    \"dealer_corp_info\": \n" +
      "    {\n" +
      "        \"corpid\": \"xxxx\",\n" +
      "        \"corp_name\": \"name\"\n" +
      "    },\n" +
      "    \"auth_corp_info\": \n" +
      "    {\n" +
      "        \"corpid\": \"xxxx\",\n" +
      "        \"corp_name\": \"name\",\n" +
      "        \"corp_type\": \"verified\",\n" +
      "        \"corp_square_logo_url\": \"yyyyy\",\n" +
      "        \"corp_user_max\": 50,\n" +
      "        \"corp_agent_max\": 30,\n" +
      "        \"corp_full_name\":\"full_name\",\n" +
      "        \"verified_end_time\":1431775834,\n" +
      "        \"subject_type\": 1,\n" +
      "        \"corp_wxqrcode\": \"zzzzz\",\n" +
      "        \"corp_scale\": \"1-50人\",\n" +
      "        \"corp_industry\": \"IT服务\",\n" +
      "        \"corp_sub_industry\": \"计算机软件/硬件/信息服务\",\n" +
      "        \"location\":\"广东省广州市\"\n" +
      "    },\n" +
      "    \"auth_info\":\n" +
      "    {\n" +
      "        \"agent\" :\n" +
      "        [\n" +
      "            {\n" +
      "                \"agentid\":1,\n" +
      "                \"name\":\"NAME\",\n" +
      "                \"round_logo_url\":\"xxxxxx\",\n" +
      "                \"square_logo_url\":\"yyyyyy\",\n" +
      "                \"appid\":1,\n" +
      "                \"privilege\":\n" +
      "                {\n" +
      "                    \"level\":1,\n" +
      "                    \"allow_party\":[1,2,3],\n" +
      "                    \"allow_user\":[\"zhansan\",\"lisi\"],\n" +
      "                    \"allow_tag\":[1,2,3],\n" +
      "                    \"extra_party\":[4,5,6],\n" +
      "                    \"extra_user\":[\"wangwu\"],\n" +
      "                    \"extra_tag\":[4,5,6]\n" +
      "                }\n" +
      "            },\n" +
      "            {\n" +
      "                \"agentid\":2,\n" +
      "                \"name\":\"NAME2\",\n" +
      "                \"round_logo_url\":\"xxxxxx\",\n" +
      "                \"square_logo_url\":\"yyyyyy\",\n" +
      "                \"appid\":5\n" +
      "            }\n" +
      "        ]\n" +
      "    },\n" +
      "    \"auth_user_info\":\n" +
      "    {\n" +
      "        \"userid\":\"aa\",\n" +
      "        \"name\":\"xxx\",\n" +
      "        \"avatar\":\"http://xxx\"\n" +
      "    }\n" +
      "}\n";

    final WxCpTpConfigStorage configStorage = new WxCpTpDefaultConfigImpl();
    tpService.setWxCpTpConfigStorage(configStorage);

    JsonObject jsonObject = new JsonObject();
    String authCode = "";
    jsonObject.addProperty("auth_code", authCode);
    doReturn(returnJson).when(tpService).post(configStorage.getApiUrl(GET_PERMANENT_CODE), jsonObject.toString());

    final WxCpTpCorp tpCorp = tpService.getPermanentCode(authCode);
    assertThat(tpCorp.getPermanentCode()).isEqualTo("xxxx");

    final WxCpTpPermanentCodeInfo tpPermanentCodeInfo = tpService.getPermanentCodeInfo(authCode);
    assertThat(tpPermanentCodeInfo.getAuthInfo().getAgent().get(0).getAgentid()).isEqualTo(1);

  }

  @Test
  public void testGet() {
  }

  @Test
  public void testPost() {
  }

  @Test
  public void testExecute() {
  }

  @Test
  public void testExecuteInternal() {
  }

  @Test
  public void testSetWxCpTpConfigStorage() {
  }

  @Test
  public void testSetRetrySleepMillis() {
  }

  @Test
  public void testSetMaxRetryTimes() {
  }

  @Test
  public void testGetTmpDirFile() {
  }

  @Test
  public void testSetTmpDirFile() {
  }

  @Test
  public void testGetRequestHttp() {
  }
}
