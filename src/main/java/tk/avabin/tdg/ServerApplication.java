package tk.avabin.tdg;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Base64Utils;
import tk.avabin.tdg.beans.Entities.User;
import tk.avabin.tdg.beans.Services.PasswordEncryptionService;
import tk.avabin.tdg.beans.Services.SaltGeneratorService;
import tk.avabin.tdg.beans.Services.UserService;
import tk.avabin.tdg.config.AppConfig;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@SpringBootApplication
@Log
public class ServerApplication {

	public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        PasswordEncryptionService passwordEncryptionService = ctx.getBean(PasswordEncryptionService.class);
        SaltGeneratorService saltGeneratorService = ctx.getBean(SaltGeneratorService.class);
        User u = userService.getByUsername("Avabin");
        if(u != null) {
            log.info("Admin account exists");
        } else {
            log.info("Admin account does not exists!");
            String saltString = null;
            try {
                saltString = saltGeneratorService.nextSaltAsString();
                u = ctx.getBean(User.class);
                u.setUsername("Admin");
                u.setEmail("admin@this");
                u.setSalt(saltString);
                try {
                    u.setPassword(passwordEncryptionService.getEncryptedPassAsB64String("testpass", saltString));
                    userService.saveOrUpdate(u);
                    log.info("Admin account created!");
                } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
