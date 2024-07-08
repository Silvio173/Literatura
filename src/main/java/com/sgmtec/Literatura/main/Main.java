package com.sgmtec.Literatura.main;

import com.sgmtec.Literatura.model.*;
import com.sgmtec.Literatura.repository.AuthorRepository;
import com.sgmtec.Literatura.repository.BookRepository;
import com.sgmtec.Literatura.service.AuthorService;
import com.sgmtec.Literatura.service.ConsumoApi;
import com.sgmtec.Literatura.service.ConverteDados;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
   private final String ENDERECO= "https://gutendex.com/books/?search=";
    private final AuthorService authorService;
    private final AuthorRepository repository;
    private final BookRepository bookRepository;
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private List<Book> listBooks = new ArrayList<>();
    private List<Author> listAuthors = new ArrayList<>();


    public Main(AuthorService authorService, AuthorRepository repository, BookRepository bookRepository) {
        this.authorService = authorService;
        this.repository = repository;
        this.bookRepository = bookRepository;        
    }

    private Scanner scs = new Scanner(System.in);
    public void menuView(){
        var opcao = -1;
        while (opcao != 9){
            var menu = """
                    1 - buscar livro por Autor\s
                    2 - listar livros registrados
                    3 - listar autores registrados\s
                    4 - listar autores vivos em um determinado ano\s
                    9 - Sair                    
                    """;
            System.out.println(menu);
            opcao = scs.nextInt();
            scs.nextLine();
            switch (opcao){
                case 1:
                    buscarLivroPorAutor();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    System.out.println("Entre com o ano inicial : ");
                    int startYear = scs.nextInt();
                    scs.nextLine();
                    System.out.println("Entre com o ano final : ");
                    int endYear = scs.nextInt();
                    scs.nextLine();
                    listarAutoresVivosEmUmDeterminadoAno(startYear,endYear);
                    break;
                case 5:
                    break;
                case 9:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }
        System.exit(0);
    }


    private void buscarLivroPorAutor() {

        Author author = new Author();
        List<Book> bookList = new ArrayList<>();

        System.out.println("Por favor informe o nome do autor :");
        String nomeAutor = scs.nextLine().replace(" ", "%20");

        String urlFull= ENDERECO + nomeAutor;
        Records dados = getDadosBook(urlFull);
        dados.getRecords().stream()
                .filter(n -> n.authors().get(0).name().equals(nomeAutor.replace("%20", " ")))
                .forEach(book -> {
            String nomeAuthorTemp = book.authors().get(0).name();
            if (!buscaAutor(nomeAuthorTemp)) {
                author.setName(book.authors().get(0).name());
                author.setBirthYear(book.authors().get(0).birthYyear());
                author.setDeadthYear(book.authors().get(0).deathYear());
                Book books = new Book(book.title(), Languages.PT);
                bookList.add(books);
                author.setBooks(bookList);
                System.out.println("Autor cadastrado = " + book.authors().get(0).name());
            }
        });
        repository.save(author);
    }

    public Boolean buscaAutor(String nomeAuthor) {
        Optional<Author> author = repository.findByNameContainingIgnoreCase(nomeAuthor);
        if(author.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    private Records getDadosBook(String urlFull) {
            var json = consumo.obterDados(urlFull);
            Records dados = conversor.obterDados(json, Records.class);
            return dados;
    }
    @Transactional
    public void listarLivrosRegistrados() {
        listBooks = bookRepository.findAll();
        listBooks
                .stream()
                .forEach(System.out::println);
    }
    @Transactional
    public void listarAutoresRegistrados(){
        listAuthors = repository.findAll();
        listAuthors
                .stream()
                .forEach(System.out::println);
    }

    @Transactional
    public void  listarAutoresVivosEmUmDeterminadoAno(int startYear,int endYear){
        listAuthors = repository.listarAutoresVivosEmUmDeterminadoAno(startYear,endYear);
        listAuthors
                .stream()
                .forEach(System.out::println);
    }
}
