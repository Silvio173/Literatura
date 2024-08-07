package com.sgmtec.Literatura.service;

import com.sgmtec.Literatura.model.Author;
import com.sgmtec.Literatura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public Long saveAndReturnId(Author author){
        Author savedAuthor = repository.save(author);
        return savedAuthor.getId();
    }
}
