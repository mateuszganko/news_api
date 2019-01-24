package org.news.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication(
        scanBasePackages= "org.news.core"
)
public class CoreApplication {

    private final static Logger log = LoggerFactory.getLogger(CoreApplication.class);

    public static void main(String[] args){

        log.info("Starting SpringBoot application with args: {}" , Arrays.asList(args).toString().replace(",","").replace("[","").replace("]",""));

        SpringApplicationBuilder builder = new SpringApplicationBuilder(CoreApplication.class);
        builder.logStartupInfo(true);
        builder.headless(true);
        builder.registerShutdownHook(true);
        builder.application();
        ConfigurableApplicationContext ctx = builder.run(args);

        Environment env = ctx.getEnvironment();
        String port = env.getProperty("server.port");

        log.info("Started SpringBoot application running on port: " + port);
    }

}
