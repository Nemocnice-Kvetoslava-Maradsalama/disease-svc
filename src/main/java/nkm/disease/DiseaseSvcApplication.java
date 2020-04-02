package nkm.disease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@EntityScan({"nkm.disease.model"})
@ComponentScan(basePackages = "nkm.disease.controller")
@ComponentScan(basePackages = "nkm.disease.dao")
@ComponentScan(basePackages = "nkm.disease.service")
@Import({SpringConfig.class})
public class DiseaseSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiseaseSvcApplication.class, args);
    }

    @Bean(name = "transactionManager")
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
