package com.matheus.biblioteca.repository;

import com.matheus.biblioteca.model.Emprestimo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
