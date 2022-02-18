## open-telemetry agent 配置

[中文文档](./README-zh.md)

The agent is highly configurable.

One option is to pass configuration properties via the D flag. In this example a service name and zipkin exporter for
traces are configured:

```shell
java -javaagent:path/to/opentelemetry-javaagent.jar \
     -Dotel.service.name=your-service-name \
     -Dotel.traces.exporter=zipkin \
     -jar myapp.jar
```

You can also use environment variables to configure the agent:

```shell
OTEL_SERVICE_NAME=your-service-name \
OTEL_TRACES_EXPORTER=zipkin \
java -javaagent:path/to/opentelemetry-javaagent.jar \
     -jar myapp.jar
```

You can also supply a Java properties file and load configuration values

```shell
java -javaagent:path/to/opentelemetry-javaagent.jar \
     -Dotel.javaagent.configuration-file=path/to/properties/file.properties \
     -jar myapp.jar
```

or

```shell
OTEL_JAVAAGENT_CONFIGURATION_FILE=path/to/properties/file.properties \
java -javaagent:path/to/opentelemetry-javaagent.jar \
     -jar myapp.jar
```

### Jeager exporter

The Jaeger exporter. This exporter uses gRPC for its communications protocol.

| System property                   | Environment variable              | Description                                                                                        |
|-----------------------------------|-----------------------------------|----------------------------------------------------------------------------------------------------|
| otel.traces.exporter=jaeger       | OTEL_TRACES_EXPORTER=jaeger       | Select the Jaeger exporter                                                                         |
| otel.exporter.jaeger.endpoint     | OTEL_EXPORTER_JAEGER_ENDPOINT     | The Jaeger gRPC endpoint to connect to. Default is `http://localhost:14250`.                       |
| otel.exporter.jaeger.timeout      | OTEL_EXPORTER_JAEGER_TIMEOUT      | The maximum waiting time, in milliseconds, allowed to send each batch. Default is `10000`.         |

```shell
java -javaagent:/Users/icefox/Documents/OpenTelemetry/opentelemetry-javaagent-v.1.11.0.jar \
     -Dotel.javaagent.configuration-file=/Users/icefox/Documents/icefox/spring-open-telemetry/config/agent-jaeger.properties \
     -jar myapp.jar
```

### Zipkin exporter

The Zipkin exporter. It sends JSON in [Zipkin format](https://zipkin.io/zipkin-api/#/default/post_spans) to a specified
HTTP URL.

| System property               | Environment variable          | Description                                                                                                           |
|-------------------------------|-------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| otel.traces.exporter=zipkin   | OTEL_TRACES_EXPORTER=zipkin   | Select the Zipkin exporter                                                                                            |
| otel.exporter.zipkin.endpoint | OTEL_EXPORTER_ZIPKIN_ENDPOINT | The Zipkin endpoint to connect to. Default is `http://localhost:9411/api/v2/spans`. Currently only HTTP is supported. |

```shell
java -javaagent:/Users/icefox/Documents/OpenTelemetry/opentelemetry-javaagent-v.1.11.0.jar \
     -Dotel.javaagent.configuration-file=/Users/icefox/Documents/icefox/spring-open-telemetry/config/agent-zipkin.properties \
     -jar myapp.jar
```

### Logging exporter

The logging exporter prints the name of the span along with its attributes to stdout. It's mainly used for testing and
debugging.

| System property               | Environment variable          | Description                                                          |
|-------------------------------|-------------------------------|----------------------------------------------------------------------|
| otel.traces.exporter=logging  | OTEL_TRACES_EXPORTER=logging  | Select the logging exporter for tracing                              |
| otel.metrics.exporter=logging | OTEL_METRICS_EXPORTER=logging | Select the logging exporter for metrics                              |
| otel.logs.exporter=logging    | OTEL_LOGS_EXPORTER=logging    | Select the logging exporter for logs                                 |
| otel.exporter.logging.prefix  | OTEL_EXPORTER_LOGGING_PREFIX  | An optional string printed in front of the span name and attributes. |

```shell
java -javaagent:/Users/icefox/Documents/OpenTelemetry/opentelemetry-javaagent-v.1.11.0.jar \
     -Dotel.javaagent.configuration-file=/Users/icefox/Documents/icefox/spring-open-telemetry/config/agent-logging.properties \
     -jar myapp.jar
```

![iamge-example](./images/img.png)

### Troubleshooting

You can pass the -Dotel.javaagent.debug=true parameter to the agent to see debug logs. Note that these are quite
verbose.

```shell
-Dotel.javaagent.debug=true 
```

TransmittableThreadLocal (TTL)

```shell
-Xbootclasspath/a:/Users/icefox/Documents/github/yunplusplus/spring-open-telemetry-samples/lib/transmittable-thread-local-2.12.4.jar
```

### propagation

zikpin  [b3-propagation](https://github.com/openzipkin/b3-propagation) \
jaeger  [propagation-format](https://www.jaegertracing.io/docs/1.31/client-libraries/#propagation-format) \
w3c     [trace-context](https://github.com/w3c/trace-context) (https://w3c.github.io/trace-context/)
