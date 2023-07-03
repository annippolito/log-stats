package logstats.model;

import java.io.IOException;

import logstats.annotation.LogStats;
import logstats.aspect.LogStatsAspect;

/**
 * SampleObject is a sample class to be used also for testing purpose.
 * So that the {@link LogStatsAspect#calculateStats} annotation can be easily tested on different kind of methods.
 */
public class SampleObject {

  @LogStats
  public void voidMethod() {
    //empty
  }

  @LogStats
  public SampleObject returnObjectMethod() {
    return new SampleObject();
  }

  @LogStats
  public String methodWithParameters(int i, String a) {
    return a + i;
  }

  @LogStats
  public void methodThrowingException() throws IOException {
    throw new IOException();
  }

}
