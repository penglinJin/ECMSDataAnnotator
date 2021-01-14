package cjlu.skyline.ecms_data_annotator.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AnnotatorThirdPartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnotatorThirdPartyApplication.class, args);
	}

}
