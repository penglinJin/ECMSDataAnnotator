package cjlu.skyline.ecms_data_annotator.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "cjlu.skyline.ecms_data_annotator.api.feign")
@EnableDiscoveryClient
@MapperScan("cjlu.skyline.ecms_data_annotator.api.dao")
@SpringBootApplication
public class AnnotatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnotatorApiApplication.class, args);
	}

}
