package tk.avabin.tdg.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Avabin
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "tk.avabin.tdg.beans", entityManagerFactoryRef = "sessionFactory")
public class HibernateConfig {

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("tk.avabin.tdg.beans.Entities");
        sessionBuilder.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
        sessionBuilder.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
        sessionBuilder.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        // sessionBuilder.setProperty(AvailableSettings.SHOW_SQL, "true");
        // sessionBuilder.setProperty(AvailableSettings.FORMAT_SQL, "true");
        return sessionBuilder.buildSessionFactory();
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(System.getenv("DATABASE_URL_T"));
        dataSource.setUsername(System.getenv("DATABASE_USERNAME"));
        dataSource.setPassword(System.getenv("DATABASE_PASSWORD"));
        return dataSource;
    }

}
