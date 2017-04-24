package tk.avabin.tdg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tk.avabin.tdg.beans.Base64Processor;
import tk.avabin.tdg.beans.SampleBean;

/**
 * Created by Avabin on 06.03.2017.
 */
@Configuration
public class AppConfig {
    @Bean(name = "base64Processor")
    public Base64Processor getBase64Proccesor() {
        return new Base64Processor();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public SampleBean sampleBean() {
        return new SampleBean();
    }
}
