package sys.app.its;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sys.app.its.security.AppProperties;

@SpringBootApplication
public class ItsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItsApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
	
	/*
	 * @Bean(name = "newDataSource") public DataSource getDataSource() {
	 * 
	 * @SuppressWarnings("rawtypes") DataSourceBuilder dataSourceBuilder =
	 * DataSourceBuilder.create();
	 * dataSourceBuilder.driverClassName("org.postgresql.Driver");
	 * dataSourceBuilder.url("jdbc:postgresql://127.0.0.1:5432/itsystemdb");
	 * dataSourceBuilder.username("jimboy02");
	 * dataSourceBuilder.password("password123"); return dataSourceBuilder.build();
	 * }
	 */

}
