package com.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Bean
    public DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(false)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .setName("studentDb")
                .ignoreFailedDrops(true)
                .addScript("database.sql")
                .build();
    }
}
