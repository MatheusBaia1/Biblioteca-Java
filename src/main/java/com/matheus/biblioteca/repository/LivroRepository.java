package com.matheus.biblioteca.repository;
import com.matheus.biblioteca.model.Livro;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Livro> listarTodos() throws SQLException, IOException {
        List<Livro> livros = new ArrayList<>();
        Connection conexao = ConnectionFactory.getConexao();

        String sql = "SELECT * FROM livros";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Livro livro = new Livro(rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"));
            livro.setId(rs.getInt("id"));
            livro.setDisponivel(rs.getBoolean("disponivel"));
            livros.add(livro);
        }

        conexao.close();
        return livros;
    }
}
