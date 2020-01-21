package lv.helloit.bootcamp.sweeter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/sweetDB";
    public static final String DB_USERNAME = "sweetDBUser";
    public static final String DB_PASSWORD = "U14C4%!%j^KUH^ldZ0d68fv@eusmy*8R";

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USERNAME);
        ds.setPassword(DB_PASSWORD);
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }
}
