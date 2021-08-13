/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.binarywang.spring.starter.wxjava.base.aspect;

import cn.hutool.core.util.StrUtil;
import com.binarywang.spring.starter.wxjava.base.properties.RequestLogLevel;
import com.binarywang.spring.starter.wxjava.base.properties.RequestLogProperties;
import com.binarywang.spring.starter.wxjava.base.util.ClassUtil;
import com.binarywang.spring.starter.wxjava.base.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Spring boot 控制器 请求日志，方便代码调试
 *
 * @author L.cm
 */
@Slf4j
@Aspect
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RequestLogProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(value = RequestLogLevel.REQ_LOG_PROPS_PREFIX + ".enabled", havingValue = "true")
public class RequestLogAspect {
  private final RequestLogProperties properties;

  private HttpServletRequest getRequest() {
    return Optional.ofNullable(RequestContextHolder.getRequestAttributes()).map(x -> (ServletRequestAttributes) x).map(ServletRequestAttributes::getRequest).orElse(null);
  }

  /**
   * AOP 环切 控制器 R 返回值
   *
   * @param point JoinPoint
   * @return Object
   * @throws Throwable 异常
   */
  @Around(" @annotation(org.springframework.web.bind.annotation.RequestMapping) " +
    "   || @annotation(org.springframework.web.bind.annotation.PostMapping) " +
    "   || @annotation(org.springframework.web.bind.annotation.PutMapping) " +
    "   || @annotation(org.springframework.web.bind.annotation.DeleteMapping) " +
    "   || @annotation(org.springframework.web.bind.annotation.GetMapping) " +
    "&& (@within(org.springframework.stereotype.Controller) " +
    "   || @within(org.springframework.web.bind.annotation.RestController) " +
    "&& execution(! org.springframework.web.servlet.ModelAndView *(..)))")
  public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
    RequestLogLevel level = properties.getLevel();
    boolean hasException = false;
    // 不打印日志，直接返回
    if (RequestLogLevel.NONE == level) {
      return point.proceed();
    }
    HttpServletRequest request = getRequest();
    String requestUrl = request.getRequestURI();
    String requestMethod = request.getMethod();

    // 构建成一条长 日志，避免并发下日志错乱
    StringBuilder beforeReqLog = new StringBuilder(300);
    // 日志参数
    List<Object> beforeReqArgs = new ArrayList<>();
    beforeReqLog.append("\n\n=> =============  Request Start  ================\n");
    // 打印路由
    beforeReqLog.append("=> {}: {}");
    beforeReqArgs.add(requestMethod);
    beforeReqArgs.add(requestUrl);
    // 打印请求参数
    logIngArgs(point, beforeReqLog, beforeReqArgs);
    // 打印请求 headers
    logIngHeaders(request, level, beforeReqLog, beforeReqArgs);
    beforeReqLog.append("=> =============   Request End   ================\n");

    // 打印执行时间
    long startNs = System.nanoTime();
    log.info(beforeReqLog.toString(), beforeReqArgs.toArray());
    // aop 执行后的日志
    StringBuilder afterReqLog = new StringBuilder(200);
    // 日志参数
    List<Object> afterReqArgs = new ArrayList<>();
    afterReqLog.append("\n\n<= =============  Response Start  ================\n");
    try {
      Object result = point.proceed();
      // 打印返回结构体
      if (RequestLogLevel.BODY.lte(level) && result != null) {
        afterReqLog.append("<= Result:  {}\n");
        afterReqArgs.add(JsonUtil.toJson(result));
      }
      return result;
    } catch (Exception e) {
      hasException = true;
      throw e;
    } finally {
      long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
      afterReqLog.append("<= {}: {} ({} ms)\n");
      afterReqArgs.add(requestMethod);
      afterReqArgs.add(requestUrl);
      afterReqArgs.add(tookMs);
      afterReqLog.append("<= =============   Response End   ================\n");
      if (hasException) {
        log.error(beforeReqLog.toString(), beforeReqArgs.toArray());
        log.error(afterReqLog.toString(), afterReqArgs.toArray());
      } else {
        log.info(afterReqLog.toString(), afterReqArgs.toArray());
      }
    }
  }

  /**
   * 记录请求参数
   *
   * @param point         ProceedingJoinPoint
   * @param beforeReqLog  StringBuilder
   * @param beforeReqArgs beforeReqArgs
   */
  public void logIngArgs(ProceedingJoinPoint point, StringBuilder beforeReqLog, List<Object> beforeReqArgs) {
    MethodSignature ms = (MethodSignature) point.getSignature();
    Method method = ms.getMethod();
    Object[] args = point.getArgs();
    // 请求参数处理
    final Map<String, Object> paraMap = new HashMap<>(16);
    // 一次请求只能有一个 request body
    Object requestBodyValue = null;
    for (int i = 0; i < args.length; i++) {
      // 读取方法参数
      MethodParameter methodParam = ClassUtil.getMethodParameter(method, i);
      // PathVariable 参数跳过
      PathVariable pathVariable = methodParam.getParameterAnnotation(PathVariable.class);
      if (pathVariable != null) {
        continue;
      }
      RequestBody requestBody = methodParam.getParameterAnnotation(RequestBody.class);
      String parameterName = methodParam.getParameterName();
      Object value = args[i];
      // 如果是body的json则是对象
      if (requestBody != null) {
        requestBodyValue = value;
        continue;
      }
      // 处理 参数
      if (value instanceof HttpServletRequest) {
        paraMap.putAll(((HttpServletRequest) value).getParameterMap());
        continue;
      } else if (value instanceof WebRequest) {
        paraMap.putAll(((WebRequest) value).getParameterMap());
        continue;
      } else if (value instanceof HttpServletResponse) {
        continue;
      } else if (value instanceof MultipartFile) {
        MultipartFile multipartFile = (MultipartFile) value;
        String name = multipartFile.getName();
        String fileName = multipartFile.getOriginalFilename();
        paraMap.put(name, fileName);
        continue;
      }
      // 参数名
      RequestParam requestParam = methodParam.getParameterAnnotation(RequestParam.class);
      String paraName = parameterName;
      if (requestParam != null && StrUtil.isNotBlank(requestParam.value())) {
        paraName = requestParam.value();
      }
      if (value == null) {
        paraMap.put(paraName, null);
      } else if (ClassUtil.isPrimitiveOrWrapper(value.getClass())) {
        paraMap.put(paraName, value);
      } else if (value instanceof InputStream) {
        paraMap.put(paraName, "InputStream");
      } else if (value instanceof InputStreamSource) {
        paraMap.put(paraName, "InputStreamSource");
      } else if (JsonUtil.canSerialize(value)) {
        // 判断模型能被 json 序列化，则添加
        paraMap.put(paraName, value);
      } else {
        paraMap.put(paraName, "【注意】不能序列化为json");
      }
    }
    // 请求参数
    if (paraMap.isEmpty()) {
      beforeReqLog.append("\n");
    } else {
      beforeReqLog.append("\n=> Parameters: {}\n");
      beforeReqArgs.add(JsonUtil.toJson(paraMap));
    }
    if (requestBodyValue != null) {
      beforeReqLog.append("=> Body: {}\n");
      beforeReqArgs.add(JsonUtil.toJson(requestBodyValue));
    }
  }

  /**
   * 记录请求头
   *
   * @param request       HttpServletRequest
   * @param level         日志级别
   * @param beforeReqLog  StringBuilder
   * @param beforeReqArgs beforeReqArgs
   */
  public void logIngHeaders(HttpServletRequest request, RequestLogLevel level, StringBuilder beforeReqLog,
                            List<Object> beforeReqArgs) {
    // 打印请求头
    if (RequestLogLevel.HEADERS.lte(level)) {
      Enumeration<String> headers = request.getHeaderNames();
      while (headers.hasMoreElements()) {
        String headerName = headers.nextElement();
        String headerValue = request.getHeader(headerName);
        beforeReqLog.append("=> Headers: {}: {}\n");
        beforeReqArgs.add(headerName);
        beforeReqArgs.add(headerValue);
      }
    }
  }

}
