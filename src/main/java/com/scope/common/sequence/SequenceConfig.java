package com.scope.common.sequence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SequenceConfig Created with IntelliJ IDEA.
 * User: juvenile
 * Date: 28/07/2018
 * Time: 02:57
 * Description: SequenceConfig
 */
@Configuration
@Slf4j
public class SequenceConfig {
    @Value("#{T(Math).random()*10}")
    private Double workerId;

    @Value("#{T(Math).random()*10}")
    private Double dataCenterId;

    @Bean
    public Sequence sequence() {
        return new Sequence(workerId, dataCenterId);
    }
}
