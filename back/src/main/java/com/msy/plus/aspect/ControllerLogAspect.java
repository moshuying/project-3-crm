package com.msy.plus.aspect;

import com.msy.plus.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;

import static com.msy.plus.core.constant.ProjectConstant.CONTROLLER_PACKAGE;

/**
 * Controller log aspect
 *
 * @author MoShuying
 * @date 2018/07/13
 */
@Aspect
@Slf4j
@Component
public class ControllerLogAspect {
  private LocalDateTime startTime;

  @Pointcut("execution(* " + CONTROLLER_PACKAGE + "..*.*(..))")
  public void controllers() {}

  /**
   * before controller handling, log something
   *
   * @param joinPoint controller join point
   */
  @Before("controllers()")
  public void doBefore(final JoinPoint joinPoint) {
    log.debug("===========================================================");
    log.debug("================  Controller Log Start  ===================");
    log.debug("===========================================================");
    this.startTime = LocalDateTime.now();
    final ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (Optional.ofNullable(attributes).isPresent()) {
      final HttpServletRequest request = attributes.getRequest();
      log.debug("==> Request: [{}]{}", request.getMethod(), request.getRequestURL());
      log.debug("==> From IP: {}", IpUtils.getIpAddress());
    }
    log.debug(
        "==>  Method: {}",
        joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName());
    log.debug("==>    Args: {}", Arrays.toString(joinPoint.getArgs()));
  }

  /**
   * after controller handling, return result
   *
   * @param result origin result
   */
  @AfterReturning(pointcut = "controllers()", returning = "result")
  public void doAfterReturning(final Object result) {
    // 处理请求的时间差
    final long difference = ChronoUnit.MILLIS.between(this.startTime, LocalDateTime.now());
    log.debug("==>   Spend: {}s", difference / 1000.0);
    log.debug("==>  Return: {}", result);
    log.debug("================  Controller Log End  =====================");
  }

  /**
   * log when throwing error
   *
   * @param e error
   */
  @AfterThrowing(pointcut = "controllers()", throwing = "e")
  public static void doAfterThrowing(final Throwable e) {
    log.debug("==> Exception: {}", e.toString());
    e.printStackTrace();
    log.debug("================  Controller Log End  =====================");
  }
}
