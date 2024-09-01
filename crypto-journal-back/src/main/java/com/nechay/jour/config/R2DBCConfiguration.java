package com.nechay.jour.config;

import com.nechay.jour.utils.AppProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryOptionsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static io.r2dbc.postgresql.PostgresqlConnectionFactoryProvider.OPTIONS;

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories(basePackages="com.nechay.jour")
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {

    @Value("${" + AppProperties.DB.HOST + "}") private String dbHost;
    @Value("${" + AppProperties.DB.PORT + "}") private Integer dbPort;
    @Value("${" + AppProperties.DB.NAME + "}") private String dbName;
    @Value("${" + AppProperties.DB.USERNAME + "}") private String dbUsername;
    @Value("${" + AppProperties.DB.PASSWORD + "}") private String dbPassword;

    @Bean
    public ConnectionFactoryOptionsBuilderCustomizer postgresCustomizer() {
        Map<String, String> options = new HashMap<>();
        options.put("lock_timeout", "30s");
        options.put("statement_timeout", "60s");
        return (builder) -> builder.option(OPTIONS, options);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
            .host(dbHost)
            .port(dbPort)
            .username(dbUsername)
            .password(dbPassword)
            .database(dbName)
            .build());
    }

    @Bean
    public SpringLiquibase liquibase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName,
            dbUsername, dbPassword);
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
        liquibase.setDataSource(new SingleConnectionDataSource(connection, false));
        return liquibase;
    }
}
