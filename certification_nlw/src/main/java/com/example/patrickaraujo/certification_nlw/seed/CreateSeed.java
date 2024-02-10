package com.example.patrickaraujo.certification_nlw.seed;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateSeed {
    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/adim");
        dataSource.setUsername("postgres");
        dataSource.setPassword("37924");

        CreateSeed createSeed = new CreateSeed((dataSource));
        createSeed.run(args);

    }

    public void run(String... args){
        try{
            executeSqlFile("src/main/resources/create.sql");
        } catch(Exception e){
            return ;
        }
    }

    public void executeSqlFile(String filePath){
        try{
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));

            jdbcTemplate.execute(sqlScript);

            System.out.println("deu tudo certo");
        } catch(Exception e){
            System.out.println("Error: "  + e.getMessage());
        }

    }
}
