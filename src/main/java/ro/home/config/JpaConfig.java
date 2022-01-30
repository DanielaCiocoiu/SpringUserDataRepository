package ro.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class JpaConfig {

@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
    var entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    var jpaVendorAdapter = new HibernateJpaVendorAdapter();

    entityManagerFactory.setDataSource(dataSource);
    entityManagerFactory.setPackagesToScan("ro.home.model");
    entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);

    entityManagerFactory.setJpaPropertyMap(Map.of(
            "hibernate.show_sql", true,
            "hibernate.hbm2ddl.auto", "update"
    ));
    return entityManagerFactory;
}

}
