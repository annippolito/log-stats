package logstats.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StatsTest {

  @Test
  void testRetrieve() {
    Stats stats = Stats.retrieve();

    Assertions.assertNotNull(stats.getMemoryUsage());
    Assertions.assertTrue(stats.getExecutionTime() > 0);
  }
}