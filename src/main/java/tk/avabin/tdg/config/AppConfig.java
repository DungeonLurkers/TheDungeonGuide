package tk.avabin.tdg.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import tk.avabin.tdg.beans.services.implementations.Base64SerializableProcessorServiceImpl;

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
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
