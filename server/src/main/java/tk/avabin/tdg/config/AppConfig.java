package tk.avabin.tdg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tk.avabin.tdg.beans.Controllers.PingRestController;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.SampleBean;

/**
 * Created by Avabin on 06.03.2017.
 */
@Configuration
public class AppConfig {

    @Bean(name = "pingRestController")
    @Scope("singleton")
    public PingRestController getPingRestController() {
        return new PingRestController();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public SampleBean sampleBean() {
        return new SampleBean();
    }

    @Bean(name = "user")
    @Scope("prototype")
    public User getUser() {
        return new User();
    }

    @Bean(name = "Item")
    @Scope("prototype")
    public Item getItem() {
        return new Item();
    }

    @Bean(name = "RPGSession")
    @Scope("prototype")
    public RPGSession getRpgSession() {
        return new RPGSession();
    }

    @Bean(name = "spell")
    @Scope("prototype")
    public Spell getSpell() {
        return new Spell();
    }

    @Bean(name = "character")
    @Scope("prototype")
    public Character getCharacter() {
        return new Character();
    }
}
