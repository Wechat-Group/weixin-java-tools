package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultApacheHttpClientBuilderTest {
  @Test
  public void testBuild() throws Exception {
    DefaultApacheHttpClientBuilder builder1 = DefaultApacheHttpClientBuilder.get();
    DefaultApacheHttpClientBuilder builder2 = DefaultApacheHttpClientBuilder.get();
    Assert.assertSame(builder1, builder2, "DefaultApacheHttpClientBuilder为单例,获取到的对象应该相同");
    List<TestThread> threadList = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      TestThread thread = new TestThread();
      thread.start();
      threadList.add(thread);
    }
    for (TestThread testThread : threadList) {
      testThread.join();
      Assert.assertNotEquals(-1,testThread.getRespState(),"请求响应code不应为-1");
    }

    for (int i = 1; i < threadList.size(); i++) {
      TestThread thread1 = threadList.get(i - 1);
      TestThread thread2 = threadList.get(i);
      Assert.assertSame(
        thread1.getClient(),
        thread2.getClient(),
        "DefaultApacheHttpClientBuilder为单例,并持有了相同的HttpClient"
      );
    }
  }


  public static class TestThread extends Thread {
    private CloseableHttpClient client;
    private int respState = -1;

    @Override
    public void run() {
      client = DefaultApacheHttpClientBuilder.get().build();
      HttpGet httpGet = new HttpGet("http://www.sina.com.cn/");
      try (CloseableHttpResponse resp = client.execute(httpGet)){
        respState = resp.getStatusLine().getStatusCode();
      } catch (IOException ignored) {
      }
    }

    public CloseableHttpClient getClient() {
      return client;
    }

    public int getRespState() {
      return respState;
    }
  }
}
