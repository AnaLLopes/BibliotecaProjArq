package com.bcopstein.ex1biblioeca;
import java.util.List;

public interface CatalogoLivros {
    List<Livro> listarTodos();
    List<String> obterTitulos();
    List<String> obterAutores();
    List<Livro> buscarPorAutor(String nomeAutor);
    Livro buscarPorTitulo(String tituloLivro);
    boolean adicionarLivro(Livro livro);
    boolean excluirLivroPorCodigo(long codigoLivro);
}
