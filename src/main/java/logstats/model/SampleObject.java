package logstats.model;

import java.io.IOException;

import logstats.annotation.LogStats;
import logstats.aspect.LogStatsAspect;

/**
 * SampleObject is a sample class to be used also for testing purpose.
 * So that the {@link LogStatsAspect#calculateStats} annotation can be easily tested on different kind of methods.
 */
public class SampleObject {

  /**
   * An example of annotation on a void method with no parameters.
   */
  @LogStats
  public void voidMethod() {
    //empty
  }

  /**
   * An example of annotation on a method that returns an object.
   *
   * @return a sample object
   */
  @LogStats
  public SampleObject returnObjectMethod() {
    return new SampleObject();
  }

  /**
   * An example of annotation on a method that returns an object and has parameters.
   *
   * @param i is a sample integer
   * @param s is a sample String
   * @return a sample String
   */
  @LogStats
  public String methodWithParameters(int i, String s) {
    return s + i;
  }

  /**
   * An example of annotation on a method that throws an exception.
   *
   * @throws IOException a sample exception
   */
  @LogStats
  public void methodThrowingException() throws IOException {
    throw new IOException();
  }

}
