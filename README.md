# log-stats
A library to handle stats on execution time and memory usage of methods.
This library is really lightweight, it provides just an annotation: **@LogStats** to be used in your code.

## Usage

### 1. Import dependency in your pom.xml

```xml
<dependency>
  <groupId>io.github.annippolito</groupId>
  <artifactId>log-stats</artifactId>
  <version>1.0.0</version>
</dependency>
```
### 2. Plugin Configuration
Add this plugin configuration in your pom to weave aspectj on your classes.
```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>aspectj-maven-plugin</artifactId>
    <version>1.14.0</version>
    <executions>
        <execution>
            <id>default-compile</id>
            <phase>process-classes</phase>
            <goals>
                <goal>compile</goal>
            </goals>
            <configuration>
                <weaveDirectories>
                    <weaveDirectory>${project.build.directory}/classes</weaveDirectory>
                </weaveDirectories>
            </configuration>
        </execution>
    </executions>
    <configuration>
        <complianceLevel>8</complianceLevel>
        <source>8</source>
        <target>8</target>
        <Xlint>ignore</Xlint>
        <encoding>UTF-8</encoding>
        <excludes>
            <exclude>**/*.java</exclude>
        </excludes>
        <forceAjcCompile>true</forceAjcCompile>
        <aspectLibraries>
            <aspectLibrary>
                <groupId>io.github.annippolito</groupId>
                <artifactId>log-stats</artifactId>
            </aspectLibrary>
        </aspectLibraries>
    </configuration>
</plugin>
```

### 3. Annotate methods you want to be analysed with @LogStats

```Java
import logstats.annotation.LogStats;

public class Service {

  @LogStats
  public void method() {
  }
}
```

## Output
In Logs you will see a summary of stats printed out as in this example:

```
2023-07-03 17:02:53.156  INFO 25872 --- [io-8080-exec-10] s.aspect.LogStatsAspect  : CustomerController.getExternalId(..) logStats:{executionTime:43 ms, memoryConsumed:2097152 bytes}
```

## Compatibility
#### Java version greater than or equals  1.8
#### Maven
