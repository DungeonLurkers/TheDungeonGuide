package tk.avabin.tdg;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tk.avabin.tdg.beans.Character;
import tk.avabin.tdg.beans.User;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Log
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User usr = (User) ctx.getBean("user");
        usr.setId(1L);
        usr.setEmail("test@gmail.com");
        usr.setUsername("Admin");
        log.info(usr.toString());
        Set<Character> chars = new HashSet();
        for (int i = 0; i < 5; i++) {
            Character c = (Character) ctx.getBean("character");
            c.setId((i + 1L));
            c.setName("Char nr " + c.getId());
            chars.add(c);
        }
        usr.setCharacters(chars);
        log.info(usr.toString());
    }
}
