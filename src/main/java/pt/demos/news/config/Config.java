package pt.demos.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class Config implements AsyncConfigurer {

    @Bean(name = "asyncExecutor")
    public AsyncTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Set the number of threads in the pool
        executor.setMaxPoolSize(10); // Set the maximum number of threads in the pool
        executor.setQueueCapacity(20); // Set the capacity of the queue
        executor.initialize();
        return executor;
    }
}

