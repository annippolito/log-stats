package logstats.aspect;

import java.io.IOException;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logstats.model.SampleObject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogStatsAspectIntegrationTest {

  private final LogCaptor logCaptor = LogCaptor.forClass(LogStatsAspect.class);
  private SampleObject sampleObject;

  @BeforeEach
  public void init() {
    sampleObject = new SampleObject();
  }

  @Test
  void testVoidMethod() {
    sampleObject.voidMethod();

    assertThat(logCaptor.getLogs())
        .asString()
        .contains("SampleObject.voidMethod")
        .contains("executionTime")
        .contains("memoryConsumed");
  }

  @Test
  void testReturningObjectMethod() {
    SampleObject obj = sampleObject.returnObjectMethod();

    assertNotNull(obj);
    assertThat(logCaptor.getLogs())
        .asString()
        .contains("SampleObject.returnObjectMethod")
        .contains("executionTime")
        .contains("memoryConsumed");
  }

  @Test
  void testMethodWithParameters() {
    String output = sampleObject.methodWithParameters(1, "a");

    assertNotNull(output);
    assertThat(logCaptor.getLogs())
        .asString()
        .contains("SampleObject.methodWithParameters")
        .contains("executionTime")
        .contains("memoryConsumed");
  }

  @Test
  void testMethodThrowingExceptionShouldBeCalculated() {
    Assertions.assertThrows(IOException.class, ()-> sampleObject.methodThrowingException());

    assertThat(logCaptor.getLogs())
        .asString()
        .contains("SampleObject.methodThrowingException")
        .contains("executionTime")
        .contains("memoryConsumed");
  }

}
