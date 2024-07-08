package com.sgmtec.Literatura.repository;

import com.sgmtec.Literatura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByNameContainingIgnoreCase(String nomeAuthor);
    Optional<Author> findByName(String nomeAutor);

    @Query("SELECT a FROM Author a WHERE a.birthYear >= :startYear AND a.deadthYear <= :endYear ")
    List<Author> listarAutoresVivosEmUmDeterminadoAno(int startYear, int endYear);
}
