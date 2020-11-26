/*
 * package sys.app.its.configuration;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.boot.jdbc.DataSourceBuilder; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.stereotype.Component;
 * 
 * @Component public class DataSourceConfiguration {
 * 
 * @SuppressWarnings("rawtypes")
 * 
 * @Bean(name = "newDataSource") public DataSource getDataSource() {
 * DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
 * dataSourceBuilder.driverClassName("org.postgresql.Driver");
 * dataSourceBuilder.url("jdbc:postgresql://127.0.0.1:5432/itsystemdb");
 * dataSourceBuilder.username("jimboy02");
 * dataSourceBuilder.password("password123"); return dataSourceBuilder.build();
 * }
 * 
 * }
 */