package com.bcopstein.ex1biblioeca;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BibliotecaController {

    private final CatalogoLivros acervo;

    @Autowired
    public BibliotecaController(CatalogoLivros acervo) {
        this.acervo = acervo;
    }

    @GetMapping("/")
    public String saudacaoInicial() {
        return "Seja bem-vindo à nossa biblioteca digital!";
    }

    @GetMapping("/livros")
    public List<Livro> listarTodosOsLivros() {
        return acervo.getAll();
    }

    @GetMapping("/autores")
    public List<String> listarAutoresDisponiveis() {
        return acervo.getAutores();
    }

    @GetMapping("/buscarPorAutor")
    public List<Livro> buscarLivrosPorAutor(@RequestParam("autor") String nomeAutor) {
        return acervo.getLivrosDoAutor(nomeAutor);
    }

    @GetMapping("/buscarPorAutorEAno/{autor}/{ano}")
    public List<Livro> buscarLivrosPorAutorEAno(
            @PathVariable("autor") String nomeAutor,
            @PathVariable("ano") int anoPublicacao) {
        return acervo.getLivrosDoAutor(nomeAutor)
                     .stream()
                     .filter(livro -> livro.getAno() == anoPublicacao)
                     .toList();
    }

    @PostMapping("/adicionarLivro")
    public boolean adicionarNovoLivro(@RequestBody Livro novoLivro) {
        return acervo.cadastraLivroNovo(novoLivro);
    }
}
