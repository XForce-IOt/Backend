package com.xforce.pethealth;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@EnableJpaAuditing
@SpringBootApplication
public class PethealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PethealthApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void createDatabaseIfNotExist() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            // Verificar si la base de datos ya existe
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            boolean databaseExists = false;
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if ("db_backend".equalsIgnoreCase(databaseName)) {
                    databaseExists = true;
                    break;
                }
            }
            resultSet.close();

            // Si la base de datos no existe, crearla
            if (!databaseExists) {
                conn.createStatement().execute("CREATE DATABASE db_backend");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        }
    }
}
