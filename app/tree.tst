[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------------< household:plan-app >-------------------------
[INFO] Building plan-app 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.1.1:tree (default-cli) @ plan-app ---
[INFO] household:plan-app:jar:0.0.1-SNAPSHOT
[INFO] +- household:household-messaging:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:shopping-list-rest:jar:0.0.1-SNAPSHOT:compile
[INFO] |  +- household:shopping-list-domain:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-webflux:jar:2.2.0.RELEASE:compile
[INFO] |     +- org.springframework.boot:spring-boot-starter-json:jar:2.2.0.RELEASE:compile
[INFO] |     |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.10.0:compile
[INFO] |     |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.10.0:compile
[INFO] |     |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.10.0:compile
[INFO] |     +- org.springframework.boot:spring-boot-starter-reactor-netty:jar:2.2.0.RELEASE:compile
[INFO] |     |  +- io.projectreactor.netty:reactor-netty:jar:0.9.0.RELEASE:compile
[INFO] |     |  |  +- io.netty:netty-codec-http:jar:4.1.42.Final:compile
[INFO] |     |  |  |  +- io.netty:netty-common:jar:4.1.42.Final:compile
[INFO] |     |  |  |  +- io.netty:netty-buffer:jar:4.1.42.Final:compile
[INFO] |     |  |  |  +- io.netty:netty-transport:jar:4.1.42.Final:compile
[INFO] |     |  |  |  |  \- io.netty:netty-resolver:jar:4.1.42.Final:compile
[INFO] |     |  |  |  \- io.netty:netty-codec:jar:4.1.42.Final:compile
[INFO] |     |  |  +- io.netty:netty-codec-http2:jar:4.1.42.Final:compile
[INFO] |     |  |  +- io.netty:netty-handler:jar:4.1.42.Final:compile
[INFO] |     |  |  +- io.netty:netty-handler-proxy:jar:4.1.42.Final:compile
[INFO] |     |  |  |  \- io.netty:netty-codec-socks:jar:4.1.42.Final:compile
[INFO] |     |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.42.Final:compile
[INFO] |     |  |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.42.Final:compile
[INFO] |     |  |  \- io.projectreactor.addons:reactor-pool:jar:0.1.0.RELEASE:compile
[INFO] |     |  \- org.glassfish:jakarta.el:jar:3.0.3:compile
[INFO] |     +- org.springframework:spring-webflux:jar:5.2.0.RELEASE:compile
[INFO] |     \- org.synchronoss.cloud:nio-multipart-parser:jar:1.1.0:compile
[INFO] |        \- org.synchronoss.cloud:nio-stream-storage:jar:1.1.3:compile
[INFO] +- household:shopping-list-mq:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- org.springframework:spring-context:jar:5.2.0.RELEASE:compile
[INFO] |     \- org.springframework:spring-expression:jar:5.2.0.RELEASE:compile
[INFO] +- household:shopping-list-persistence:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- org.postgresql:postgresql:jar:42.2.8:runtime
[INFO] +- household:cleaning-plan-rest:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- household:cleaning-plan-domain:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:cleaning-plan-mq:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:cleaning-plan-persistence:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:food-plan-rest:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- household:food-plan-domain:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:food-plan-mq:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:food-plan-persistence:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:cookbook-rest:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- household:cookbook-domain:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:cookbook-mq:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:cookbook-persistence:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:user-rest:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- household:user-domain:jar:0.0.1-SNAPSHOT:compile
[INFO] |     \- org.reactivestreams:reactive-streams:jar:1.0.3:compile
[INFO] +- household:user-mq:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:user-persistence:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- io.projectreactor:reactor-core:jar:3.3.0.RELEASE:compile
[INFO] +- household:household-rest:jar:0.0.1-SNAPSHOT:compile
[INFO] |  \- household:household-domain:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:household-mq:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:household-persistence:jar:0.0.1-SNAPSHOT:compile
[INFO] +- household:plan-web:jar:0.0.1-SNAPSHOT:runtime
[INFO] +- org.springframework.boot:spring-boot-starter-freemarker:jar:2.2.0.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:2.2.0.RELEASE:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:2.2.0.RELEASE:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:2.2.0.RELEASE:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.2.0.RELEASE:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.2.3:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.12.1:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.12.1:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.28:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:1.3.5:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:1.25:runtime
[INFO] |  +- org.freemarker:freemarker:jar:2.3.29:compile
[INFO] |  \- org.springframework:spring-context-support:jar:5.2.0.RELEASE:compile
[INFO] +- org.springframework.boot:spring-boot-starter-data-jpa:jar:2.2.0.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-aop:jar:2.2.0.RELEASE:compile
[INFO] |  |  \- org.aspectj:aspectjweaver:jar:1.9.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-jdbc:jar:2.2.0.RELEASE:compile
[INFO] |  |  +- com.zaxxer:HikariCP:jar:3.4.1:compile
[INFO] |  |  \- org.springframework:spring-jdbc:jar:5.2.0.RELEASE:compile
[INFO] |  +- jakarta.activation:jakarta.activation-api:jar:1.2.1:compile
[INFO] |  +- jakarta.persistence:jakarta.persistence-api:jar:2.2.3:compile
[INFO] |  +- jakarta.transaction:jakarta.transaction-api:jar:1.3.3:compile
[INFO] |  +- org.hibernate:hibernate-core:jar:5.4.6.Final:compile
[INFO] |  |  +- org.jboss.logging:jboss-logging:jar:3.4.1.Final:compile
[INFO] |  |  +- org.javassist:javassist:jar:3.24.0-GA:compile
[INFO] |  |  +- net.bytebuddy:byte-buddy:jar:1.10.1:compile
[INFO] |  |  +- antlr:antlr:jar:2.7.7:compile
[INFO] |  |  +- org.jboss:jandex:jar:2.0.5.Final:compile
[INFO] |  |  +- com.fasterxml:classmate:jar:1.5.0:compile
[INFO] |  |  +- org.dom4j:dom4j:jar:2.1.1:compile
[INFO] |  |  +- org.hibernate.common:hibernate-commons-annotations:jar:5.1.0.Final:compile
[INFO] |  |  \- org.glassfish.jaxb:jaxb-runtime:jar:2.3.2:compile
[INFO] |  |     +- org.glassfish.jaxb:txw2:jar:2.3.2:compile
[INFO] |  |     +- com.sun.istack:istack-commons-runtime:jar:3.0.8:compile
[INFO] |  |     +- org.jvnet.staxex:stax-ex:jar:1.8.1:compile
[INFO] |  |     \- com.sun.xml.fastinfoset:FastInfoset:jar:1.2.16:compile
[INFO] |  +- org.springframework.data:spring-data-jpa:jar:2.2.0.RELEASE:compile
[INFO] |  |  +- org.springframework.data:spring-data-commons:jar:2.2.0.RELEASE:compile
[INFO] |  |  +- org.springframework:spring-orm:jar:5.2.0.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-tx:jar:5.2.0.RELEASE:compile
[INFO] |  \- org.springframework:spring-aspects:jar:5.2.0.RELEASE:compile
[INFO] +- org.springframework.hateoas:spring-hateoas:jar:1.0.0.RELEASE:compile
[INFO] |  +- org.springframework:spring-aop:jar:5.2.0.RELEASE:compile
[INFO] |  +- org.springframework:spring-beans:jar:5.2.0.RELEASE:compile
[INFO] |  +- org.springframework:spring-core:jar:5.2.0.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:5.2.0.RELEASE:compile
[INFO] |  +- org.springframework:spring-web:jar:5.2.0.RELEASE:compile
[INFO] |  +- org.springframework.plugin:spring-plugin-core:jar:2.0.0.RELEASE:compile
[INFO] |  +- com.jayway.jsonpath:json-path:jar:2.4.0:compile
[INFO] |  |  \- net.minidev:json-smart:jar:2.3:compile
[INFO] |  |     \- net.minidev:accessors-smart:jar:1.2:compile
[INFO] |  |        \- org.ow2.asm:asm:jar:5.0.4:compile
[INFO] |  \- org.slf4j:slf4j-api:jar:1.7.28:compile
[INFO] +- org.springframework.boot:spring-boot-starter-security:jar:2.2.0.RELEASE:compile
[INFO] |  +- org.springframework.security:spring-security-config:jar:5.2.0.RELEASE:compile
[INFO] |  \- org.springframework.security:spring-security-web:jar:5.2.0.RELEASE:compile
[INFO] +- org.springframework.cloud:spring-cloud-stream:jar:2.1.0.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:2.2.0.RELEASE:compile
[INFO] |  |  +- jakarta.validation:jakarta.validation-api:jar:2.0.1:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:9.0.27:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:6.0.17.Final:compile
[INFO] |  +- org.springframework:spring-messaging:jar:5.2.0.RELEASE:compile
[INFO] |  +- org.springframework.integration:spring-integration-core:jar:5.2.0.RELEASE:compile
[INFO] |  +- org.springframework.integration:spring-integration-jmx:jar:5.2.0.RELEASE:compile
[INFO] |  +- org.springframework:spring-tuple:jar:1.0.0.RELEASE:compile
[INFO] |  |  +- com.esotericsoftware:kryo-shaded:jar:3.0.3:compile
[INFO] |  |  |  \- com.esotericsoftware:minlog:jar:1.3.0:compile
[INFO] |  |  \- com.fasterxml.jackson.core:jackson-databind:jar:2.10.0:compile
[INFO] |  |     +- com.fasterxml.jackson.core:jackson-annotations:jar:2.10.0:compile
[INFO] |  |     \- com.fasterxml.jackson.core:jackson-core:jar:2.10.0:compile
[INFO] |  +- org.springframework.integration:spring-integration-tuple:jar:1.0.0.RELEASE:compile
[INFO] |  +- org.springframework.retry:spring-retry:jar:1.2.4.RELEASE:compile
[INFO] |  \- org.springframework.cloud:spring-cloud-function-context:jar:2.0.0.RELEASE:compile
[INFO] |     \- org.springframework.cloud:spring-cloud-function-core:jar:2.0.0.RELEASE:compile
[INFO] +- org.springframework.cloud:spring-cloud-stream-binder-rabbit:jar:2.1.0.RELEASE:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-stream-binder-rabbit-core:jar:2.1.0.RELEASE:compile
[INFO] |  |  +- org.apache.httpcomponents:httpclient:jar:4.5.10:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.12:compile
[INFO] |  |  |  \- commons-codec:commons-codec:jar:1.13:compile
[INFO] |  |  \- com.rabbitmq:http-client:jar:2.1.0.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-amqp:jar:2.2.0.RELEASE:compile
[INFO] |  |  \- org.springframework.amqp:spring-rabbit:jar:2.2.0.RELEASE:compile
[INFO] |  |     +- com.rabbitmq:amqp-client:jar:5.7.3:compile
[INFO] |  |     \- org.springframework.amqp:spring-amqp:jar:2.2.0.RELEASE:compile
[INFO] |  \- org.springframework.integration:spring-integration-amqp:jar:5.2.0.RELEASE:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.10:compile (optional) 
[INFO] +- org.springframework.boot:spring-boot-starter-test:jar:2.2.0.RELEASE:test
[INFO] |  +- org.springframework.boot:spring-boot-test:jar:2.2.0.RELEASE:test
[INFO] |  +- org.springframework.boot:spring-boot-test-autoconfigure:jar:2.2.0.RELEASE:test
[INFO] |  +- jakarta.xml.bind:jakarta.xml.bind-api:jar:2.3.2:compile
[INFO] |  +- org.junit.vintage:junit-vintage-engine:jar:5.5.2:test
[INFO] |  |  +- org.apiguardian:apiguardian-api:jar:1.1.0:test
[INFO] |  |  +- org.junit.platform:junit-platform-engine:jar:1.5.2:test
[INFO] |  |  \- junit:junit:jar:4.12:test
[INFO] |  +- org.mockito:mockito-junit-jupiter:jar:3.1.0:test
[INFO] |  +- org.assertj:assertj-core:jar:3.6.2:test
[INFO] |  +- org.hamcrest:hamcrest:jar:2.1:test
[INFO] |  +- org.mockito:mockito-core:jar:3.1.0:test
[INFO] |  |  +- net.bytebuddy:byte-buddy-agent:jar:1.10.1:test
[INFO] |  |  \- org.objenesis:objenesis:jar:2.6:compile
[INFO] |  +- org.skyscreamer:jsonassert:jar:1.5.0:test
[INFO] |  |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO] |  +- org.springframework:spring-test:jar:5.2.0.RELEASE:test
[INFO] |  \- org.xmlunit:xmlunit-core:jar:2.6.3:test
[INFO] +- org.springframework.security:spring-security-test:jar:5.2.0.RELEASE:test
[INFO] |  \- org.springframework.security:spring-security-core:jar:5.2.0.RELEASE:compile
[INFO] +- org.junit.jupiter:junit-jupiter:jar:5.5.2:test
[INFO] |  +- org.junit.jupiter:junit-jupiter-api:jar:5.5.2:test
[INFO] |  |  +- org.opentest4j:opentest4j:jar:1.2.0:test
[INFO] |  |  \- org.junit.platform:junit-platform-commons:jar:1.5.2:test
[INFO] |  +- org.junit.jupiter:junit-jupiter-params:jar:5.5.2:test
[INFO] |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.5.2:test
[INFO] \- com.h2database:h2:jar:1.4.199:test
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.657 s
[INFO] Finished at: 2019-11-12T11:43:36+01:00
[INFO] ------------------------------------------------------------------------
