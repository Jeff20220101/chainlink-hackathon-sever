package com.scope;

import com.scope.common.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/7 10:22
 * Description: HackathonApplication
 */
@SpringBootApplication
        (
                scanBasePackages = "com.scope",
                exclude = {SecurityAutoConfiguration.class}
        )
@EnableTransactionManagement
@MapperScan("com.scope.mapper")
@EnableScheduling
public class HackathonApplication {
    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(timeZone);
        SpringApplication.run(HackathonApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}