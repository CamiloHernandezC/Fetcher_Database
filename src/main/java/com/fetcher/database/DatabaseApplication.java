package com.fetcher.database;

import java.sql.SQLException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DatabaseApplication {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}
	
	@PostConstruct
	private void initDb() {
		log.info("****** Creating and inserting into Users table ******");
	    String sqlStatements[] = {
	    		"drop table users_roles if exists",
	    		"drop table books if exists",
			    "drop table users if exists",
			    "CREATE TABLE users ("
			    		+ "id serial ,"
			    		+ "name varchar(32) ,"
			    		+ "username varchar(32) ,"
			    		+ "password varchar(254) ,"
			    		+ "author_pseudonym varchar(255))",
				"CREATE TABLE users_roles ("
						+ "user_id bigint(20) NOT NULL,"
						+ "roles_id bigint(20) NOT NULL)",
				"CREATE TABLE roles ("
						+ "id bigint(11) NOT NULL,"
						+ "name varchar(32) NOT NULL)",
				"CREATE TABLE books ("
						+ "id bigint(20) NOT NULL,"
						+ "description varchar(255) DEFAULT NULL,"
						+ "image_path varchar(255) DEFAULT NULL,"
						+ "price double NOT NULL,"
						+ "title varchar(255) DEFAULT NULL,"
						+ "author_id bigint(20) DEFAULT NULL)",
				"INSERT INTO users (id, name, username, password, author_pseudonym) VALUES ('1', 'camilo', 'camilo', '$2a$12$m6Lhnbj4BBUpzziv80gY.OIiobqM0eFaIXjLHz/tWM5JnceVEAWnC', 'pseudonimo')",
				"INSERT INTO users (id, name, username, password, author_pseudonym) VALUES ('2', 'esteban', 'otroUsername', '$2a$12$m6Lhnbj4BBUpzziv80gY.OIiobqM0eFaIXjLHz/tWM5JnceVEAWnC', 'otro pseudonimo')",
				
				"INSERT INTO books (id, description, image_path, price, title, author_id) VALUES ('1', 'primera descripcion', 'img/book1.png', '1807', 'el titulo', '1')",
				"INSERT INTO books (id, description, image_path, price, title, author_id) VALUES ('2', 'segunda descripcion', 'img/book2.png', '5000', 'otro titulo', '2')",
	    };

	    Arrays.asList(sqlStatements).forEach(sql -> {
			jdbcTemplate.execute(sql);
	    });
	}
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
	    return Server.createTcpServer(
	      "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}

}
