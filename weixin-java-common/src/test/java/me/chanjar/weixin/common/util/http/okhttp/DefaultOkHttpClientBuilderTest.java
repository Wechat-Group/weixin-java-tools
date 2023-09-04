package me.chanjar.weixin.common.util.http.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultOkHttpClientBuilderTest {
  @Test
  public void testBuild() throws Exception {
    DefaultOkHttpClientBuilder builder1 = DefaultOkHttpClientBuilder.get();
    DefaultOkHttpClientBuilder builder2 = DefaultOkHttpClientBuilder.get();
    Assert.assertSame(builder1, builder2, "DefaultOkHttpClientBuilder为单例,获取到的对象应该相同");
    List<DefaultOkHttpClientBuilderTest.TestThread> threadList = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      DefaultOkHttpClientBuilderTest.TestThread thread = new DefaultOkHttpClientBuilderTest.TestThread();
      thread.start();
      threadList.add(thread);
    }
    for (DefaultOkHttpClientBuilderTest.TestThread testThread : threadList) {
      testThread.join();
      Assert.assertNotEquals(-1, testThread.getRespState(), "请求响应code不应为-1");
    }

    for (int i = 1; i < threadList.size(); i++) {
      DefaultOkHttpClientBuilderTest.TestThread thread1 = threadList.get(i - 1);
      DefaultOkHttpClientBuilderTest.TestThread thread2 = threadList.get(i);
      Assert.assertSame(
        thread1.getClient(),
        thread2.getClient(),
        "DefaultOkHttpClientBuilderTest为单例,并持有了相同的OkHttpClient"
      );
    }
  }

  public static class TestThread extends Thread {
    private OkHttpClient client;
    private int respState = -1;

    @Override
    public void run() {
      client = DefaultOkHttpClientBuilder.get().build();
      Request request = new Request.Builder()
        .url("http://www.sina.com.cn/")
        .build();
      try (Response response = client.newCall(request).execute()) {
        respState = response.code();
      } catch (IOException e) {
        // ignore
      }
    }

    public OkHttpClient getClient() {
      return client;
    }

    public int getRespState() {
      return respState;
    }
  }
}
