package dd.projects.ddshop;

import ch.qos.logback.core.net.SyslogOutputStream;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import org.apache.catalina.mapper.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class DdshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdshopApplication.class, args);
		User u = new User(1,"f","f","f","f","f",new Address(),new Address());
		UserMapper userMapper=new UserMapper();
		UserDTO userDTO = userMapper.toDTO(u);
		System.out.println(userDTO);
	}

}
