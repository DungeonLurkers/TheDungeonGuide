package tk.avabin.tdg;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tk.avabin.tdg.beans.Entities.RPGSession;
import tk.avabin.tdg.config.AppConfig;

@SpringBootApplication
@Log
public class ServerApplication {

	public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        RPGSession rpgSession = ctx.getBean(RPGSession.class);
    }
}
