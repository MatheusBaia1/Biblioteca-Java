package com.matheus.biblioteca.repository;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {
    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;

    public EmprestimoRepository(LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }
    public void salvar(Emprestimo emprestimo) throws SQLException, IOException {
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao_prevista, data_devolucao_real) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, emprestimo.getLivro().getId());
        stmt.setInt(2, emprestimo.getUsuario().getId());
        stmt.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
        stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDataDevolucaoPrevista()));

        if (emprestimo.getDataDevolucaoReal() != null) {
            stmt.setDate(5, java.sql.Date.valueOf(emprestimo.getDataDevolucaoReal()));
        } else {
            stmt.setNull(5, java.sql.Types.DATE);
        }
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
    public List<Emprestimo> listarTodos() throws SQLException, IOException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "SELECT * FROM emprestimos";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int livroId = rs.getInt("livro_id");
            int usuarioId = rs.getInt("usuario_id");

            Livro livro = livroRepository.listarTodos().stream()
                    .filter(l -> l.getId().equals(livroId))
                    .findFirst()
                    .orElse(null);

            Usuario usuario = usuarioRepository.listarTodos().stream()
                    .filter(u -> u.getId().equals(usuarioId))
                    .findFirst()
                    .orElse(null);

            LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
            LocalDate dataDevolucaoPrevista = rs.getDate("data_devolucao_prevista").toLocalDate();

            java.sql.Date dataRealSql = rs.getDate("data_devolucao_real");
            LocalDate dataDevolucaoReal = (dataRealSql != null) ? dataRealSql.toLocalDate() : null;

            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucaoPrevista, dataDevolucaoReal);
            emprestimo.setId(rs.getInt("id"));

            emprestimos.add(emprestimo);
        }
        conexao.close();
        return emprestimos;
    }
    public void atualizar (Emprestimo emprestimo) throws SQLException, IOException {
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "UPDATE emprestimos SET data_devolucao_real = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        if (emprestimo.getDataDevolucaoPrevista() != null) {
            stmt.setDate(1, java.sql.Date.valueOf(emprestimo.getDataDevolucaoReal()));
        } else {
            stmt.setNull(1, java.sql.Types.DATE);
        }
        stmt.setInt(2, emprestimo.getId());
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
    public void remover(Integer id) throws SQLException, IOException {
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "DELETE FROM emprestimos WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
}
