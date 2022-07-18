package dd.projects.ddshop;

import ch.qos.logback.core.net.SyslogOutputStream;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;


@SpringBootApplication
@RequiredArgsConstructor
public class DdshopApplication {
	private final MessageSource messageSource;
	public static void main(String[] args) {
		SpringApplication.run(DdshopApplication.class, args);
	}
//	@PostConstruct
//	public void postConstruct() {
//		System.out.println("Running Message Property Data");
//		System.out.println(messageSource.getMessage("api.error.user.not.found", null, Locale.ENGLISH));
//		System.out.println(messageSource.getMessage("api.error.user.already.registered", null, Locale.ENGLISH));
//		System.out.println(messageSource.getMessage("api.response.user.creation.successful", null, Locale.ENGLISH));
//		System.out.println(messageSource.getMessage("api.response.user.update.successful", null, Locale.ENGLISH));
//		System.out.println("End Message Property Data");
//	}

}
