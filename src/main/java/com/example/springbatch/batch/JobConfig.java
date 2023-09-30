package com.example.springbatch.batch;

import com.example.springbatch.entity.User;
import com.example.springbatch.entity.UserWage;
import com.example.springbatch.repository.UserRepository;
import com.example.springbatch.repository.UserWageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final UserRepository userRepository;
    private final UserWageRepository userWageRepository;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
            .start(simpleStep1())
            .build();
    }

    @Bean
    public Step simpleStep1() {
        return stepBuilderFactory.get("simpleStep1")
            .tasklet((contribution, chunkContext) -> {
                List<User> users = userRepository.findAll();
                users.forEach(user -> {
                    UserWage userWage = new UserWage(user, user.getSalary() + user.getBonus());
                    userWageRepository.save(userWage);
                });
                log.info(">>>>> This is Step1");
                return RepeatStatus.FINISHED;
            })
            .build();
    }

}
