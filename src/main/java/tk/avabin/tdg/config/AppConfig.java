package tk.avabin.tdg.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import tk.avabin.tdg.beans.SampleBean;
import tk.avabin.tdg.beans.Services.Implementations.Base64SerializableProcessorServiceImpl;

import javax.sql.DataSource;
import java.security.SecureRandom;

/**
 * Created by Avabin on 06.03.2017.
 */
@Configuration
public class AppConfig {
    @Bean(name = "base64Processor")
    public Base64SerializableProcessorServiceImpl getBase64Processor() {
        return new Base64SerializableProcessorServiceImpl();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public SampleBean sampleBean() {
        return new SampleBean();
    }

    @Bean(name = "JSONObjectMapper")
    @Primary
    public ObjectMapper getJSONObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper;
    }
    @Bean
    @Scope(scopeName = "prototype")
    public SecureRandom getSecureRandom() {
        return new SecureRandom();
    }

    @Bean
    @Autowired
    public JdbcClientDetailsServiceBuilder jdbcClientDetailsServiceBuilder(DataSource dataSource, SecureRandom random) {
        JdbcClientDetailsServiceBuilder serviceBuilder = new JdbcClientDetailsServiceBuilder();
        serviceBuilder.dataSource(dataSource);
        serviceBuilder.passwordEncoder(new BCryptPasswordEncoder(31, random));
        return serviceBuilder;
    }
}
