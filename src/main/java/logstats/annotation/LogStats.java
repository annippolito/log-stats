package logstats.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The LogStats annotation can be used to annotate each method for which it is needed to calculate stats.
 *
 * Example of usage:
 * <pre>
 * import logstats.annotation.LogStats;
 * public class Service {
 *   {@code @LogStats}
 *   public void method() {
 *   }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogStats {

}
