package com.bcopstein.ex1biblioeca;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AcervoMemoria implements CatalogoLivros {

    private final JdbcTemplate db;

    public AcervoMemoria(JdbcTemplate db) {
        this.db = db;
    }

    @Override
    public List<Livro> getAll() {
        String sql = "SELECT * FROM livros";
        return db.query(sql, (rs, i) -> new Livro(
                rs.getInt("codigo"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("ano")));
    }

    @Override
    public List<String> getTitulos() {
        String query = "SELECT titulo FROM livros";
        return db.query(query, (rs, idx) -> rs.getString("titulo"));
    }

    @Override
    public List<String> getAutores() {
        String query = "SELECT autor FROM livros";
        return db.query(query, (rs, idx) -> rs.getString("autor"));
    }

    @Override
    public List<Livro> getLivrosDoAutor(String nomeAutor) {
        String sql = "SELECT * FROM livros WHERE autor = ?";
        return db.query(sql, new Object[]{nomeAutor}, (rs, i) -> new Livro(
                rs.getInt("codigo"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("ano")));
    }

    @Override
    public Livro getLivroTitulo(String nomeTitulo) {
        String sql = "SELECT * FROM livros WHERE titulo = ?";
        List<Livro> resultados = db.query(sql, new Object[]{nomeTitulo}, (rs, i) -> new Livro(
                rs.getInt("codigo"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("ano")));

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public boolean removeLivro(long idLivro) {
        String sql = "DELETE FROM livros WHERE codigo = ?";
        int linhasAfetadas = db.update(sql, idLivro);
        return linhasAfetadas > 0;
    }

    @Override
    public boolean cadastraLivroNovo(Livro novoLivro) {
        String sql = "INSERT INTO livros (codigo, titulo, autor, ano) VALUES (?, ?, ?, ?)";
        int resultado = db.update(sql,
                novoLivro.codigo(),
                novoLivro.titulo(),
                novoLivro.autor(),
                novoLivro.ano());
        return resultado > 0;
    }
}
