package com.lyyexample;

import com.lyyexample.listener.ServiceListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileBatchApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FileBatchApplication.class);
		application.addListeners(new ServiceListener());
		application.run(args);
//		SpringApplication.run(FileBatchApplication.class, args);
	}
}
