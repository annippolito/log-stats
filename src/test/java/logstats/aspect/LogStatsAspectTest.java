package logstats.aspect;

import java.io.IOException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LogStatsAspectTest {

  @Mock
  private ProceedingJoinPoint joinPoint;

  @Mock
  private Signature signature;

  private LogStatsAspect aspect;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    aspect = new LogStatsAspect();
  }

  @Test
  void testCalculateExecutionTimeAndMemory() throws Throwable {
    when(joinPoint.proceed()).thenReturn("result");
    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.toShortString()).thenReturn("Class.method(..)");

    Assertions.assertDoesNotThrow(()-> aspect.calculateStats(joinPoint));

    verify(joinPoint).proceed();
    verify(signature).toShortString();
  }

  @Test
  void testCalculateExecutionTimeAndMemoryPropagateException() throws Throwable {
    when(joinPoint.proceed()).thenThrow(IOException.class);
    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.toShortString()).thenReturn("Class.method(..)");

    Assertions.assertThrows(IOException.class, ()-> aspect.calculateStats(joinPoint));

    verify(joinPoint).proceed();
    verify(signature).toShortString();
  }
}
