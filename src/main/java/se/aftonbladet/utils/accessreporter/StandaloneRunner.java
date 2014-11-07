package se.aftonbladet.utils.accessreporter;

import org.springframework.boot.SpringApplication;

public class StandaloneRunner {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Config.class, args);
	}
}
