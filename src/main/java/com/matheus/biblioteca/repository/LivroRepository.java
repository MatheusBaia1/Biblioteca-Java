package com.matheus.biblioteca.repository;
import com.matheus.biblioteca.model.Livro;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroRepository {
    public void salvar(Livro livro) throws SQLException, IOException {
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "INSERT INTO livros (titulo, autor, isbn, disponivel) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setString(3, livro.getIsbn());
        stmt.setBoolean(4, livro.isDisponivel());
        stmt.executeUpdate();
        conexao.close();
    }
}
