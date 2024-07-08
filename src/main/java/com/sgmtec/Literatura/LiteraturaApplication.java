package com.sgmtec.Literatura;

import com.sgmtec.Literatura.main.Main;
import com.sgmtec.Literatura.repository.AuthorRepository;
import com.sgmtec.Literatura.repository.BookRepository;
import com.sgmtec.Literatura.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	@Autowired
	// AuthorRepository repository;
	AuthorRepository repository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorService authorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(authorService, repository,bookRepository);
		main.menuView();
    }
}
