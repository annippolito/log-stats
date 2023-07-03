package logstats.model;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

import lombok.Getter;

/**
 * Stats object is a measure of statistics at a point in time.
 * So each time that a Stats is retrieved {@link Stats#retrieve()} a new instance is created with the current measure.
 */
@Getter
public class Stats {
  private final long executionTime;
  private final MemoryUsage memoryUsage;

  public static Stats retrieve() {
    return new Stats();
  }

  private Stats() {
    executionTime = System.currentTimeMillis();
    memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
  }

}
