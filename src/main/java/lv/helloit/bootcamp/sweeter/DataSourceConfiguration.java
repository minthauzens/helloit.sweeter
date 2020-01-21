package lv.helloit.bootcamp.sweeter;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/sweetDB";
    public static final String DB_USERNAME = "sweetDBUser";
    public static final String DB_PASSWORD = "U14C4%!%j^KUH^ldZ0d68fv@eusmy*8R";

    @Bean
    DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USERNAME);
        ds.setPassword(DB_PASSWORD);
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setMaxTotal(3);
        return ds;
    }
}
