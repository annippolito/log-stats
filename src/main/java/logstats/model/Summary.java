package logstats.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Summary is an object to encapsulate stats data calculated.
 * At the moment only the execution time and the memory consumed by the service, but to be extended.
 */
@Builder
@Getter
public class Summary {
  private long executionTime;
  private long memoryConsumed;

  /**
   * The toString method to have stats printed out in logs.
   * @return a formatted string. Example: <code>stats:{executionTime:%d ms, memoryConsumed:%d bytes}</code>
   */
  @Override
  public String toString() {
    return String.format("stats:{executionTime:%d ms, memoryConsumed:%d bytes}", executionTime, memoryConsumed);
  }

}
