package tk.avabin.tdg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tk.avabin.tdg.beans.Character;
import tk.avabin.tdg.beans.SampleBean;
import tk.avabin.tdg.beans.User;

/**
 * Created by Avabin on 06.03.2017.
 */
@Configuration
public class AppConfig {
    @Bean
    @Scope(scopeName = "prototype")
    public SampleBean sampleBean() {
        return new SampleBean();
    }

    @Bean
    @Scope("prototype")
    public User user() {
        return new User();
    }

    @Bean
    @Scope("prototype")
    public Character character() {
        return new Character();
    }
}
