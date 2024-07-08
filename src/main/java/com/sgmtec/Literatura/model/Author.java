package com.sgmtec.Literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(name = "birth_year")
    private Integer birthYear;
    @Column(name = "deadth_year")
    private Integer deadthYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name, Integer birthYear, Integer deadthYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deadthYear = deadthYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeadthYear() {
        return deadthYear;
    }

    public void setDeadthYear(Integer deadthYear) {
        this.deadthYear = deadthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(b -> b.setAuthor(this));
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "birthYear=" + birthYear +
                ", deadthYear=" + deadthYear +
                ", books=" + books +
                '}';
    }
}
