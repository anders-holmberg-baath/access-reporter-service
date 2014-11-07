package se.aftonbladet.utils.accessreporter;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableIntegration
@ComponentScan("se.aftonbladet.utils")
@ImportResource("classpath:integration-beans.xml")
public class Config {
	@Bean
	public VelocityEngine velocityEngine() throws IOException {
		VelocityEngineFactoryBean factoryBean = new VelocityEngineFactoryBean();
		Properties properties = new Properties();
		properties.put("resource.loader", "class");
		properties.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		factoryBean.setVelocityProperties(properties);

		return factoryBean.createVelocityEngine();
	}

	@Bean
	public JavaMailSender mailSender() {
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProperties.setProperty("mail.smtp.auth", "true");

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(javaMailProperties);
		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername("anders.holmbergbaath@schibsted.se");
		mailSender.setPassword("password");
		mailSender.setPort(587);
		mailSender.setProtocol("smtp");

		return mailSender;
	}

	@Bean
	public TaskExecutor executor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(3);
		taskExecutor.setMaxPoolSize(3);
		taskExecutor.setQueueCapacity(10);
		return taskExecutor;
	}
}
