package logstats.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import logstats.annotation.LogStats;
import logstats.model.Stats;
import logstats.model.Summary;

/**
 * The LogStatsAspect is an Around aspect that processes each method annotated with {@link LogStats}.
 * The {@link LogStatsAspect#calculateStats} method calculate execution time and memory used by the method itself.
 * It is also agnostic from exceptions, so that if any exception is thrown by the wrapped method, this method still is able to calculate statistics without catching the exception.
 * Statistics are logged by Log4j2.
 */
@Aspect
@Log4j2
public class LogStatsAspect {
  @Around("@annotation(logstats.annotation.LogStats)")
  public Object calculateStats(ProceedingJoinPoint joinPoint) throws Throwable {
    final Stats start = Stats.retrieve();
    try {
      return joinPoint.proceed();
    } finally {
      final Stats end = Stats.retrieve();
      final Summary summary = this.calculate(start, end);
      log.info(joinPoint.getSignature().toShortString() + " " + summary);
    }
  }

  /**
   * Method to calculate the diff between two point in time of metrics.
   *
   * @param start the initial measure of Stats
   * @param end the final measure of Stats
   * @return a Summary of calculated Stats
   */
  private Summary calculate(Stats start, Stats end) {
    long executionTime = end.getExecutionTime() - start.getExecutionTime();
    long memoryConsumed = end.getMemoryUsage().getUsed() - start.getMemoryUsage().getUsed();
    return Summary.builder()
        .executionTime(executionTime)
        .memoryConsumed(memoryConsumed)
        .build();
  }
}

