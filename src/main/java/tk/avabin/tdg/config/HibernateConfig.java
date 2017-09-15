package tk.avabin.tdg.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.security.SecureRandom;

/**
 * @author Avabin
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "tk.avabin.tdg.beans.repositories", entityManagerFactoryRef = "sessionFactory")
public class HibernateConfig {

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("tk.avabin.tdg.beans.entities");
        sessionBuilder.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
        sessionBuilder.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
        sessionBuilder.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        sessionBuilder.setProperty(AvailableSettings.DEFAULT_SCHEMA, "public");
        // sessionBuilder.setProperty(AvailableSettings.SHOW_SQL, "true");
        // sessionBuilder.setProperty(AvailableSettings.FORMAT_SQL, "true");
        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    @Autowired
    public JdbcClientDetailsServiceBuilder jdbcClientDetailsServiceBuilder(DataSource dataSource, SecureRandom random) {
        JdbcClientDetailsServiceBuilder serviceBuilder = new JdbcClientDetailsServiceBuilder();
        serviceBuilder.dataSource(dataSource);
        serviceBuilder.passwordEncoder(new BCryptPasswordEncoder(31, random));
        return serviceBuilder;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(System.getenv("POSTGRES_URL"));
        dataSource.setSchema(System.getenv("POSTGRES_DB"));
        dataSource.setUsername(System.getenv("POSTGRES_USER"));
        dataSource.setPassword(System.getenv("POSTGRES_PASSWORD"));
        return dataSource;
    }

}
