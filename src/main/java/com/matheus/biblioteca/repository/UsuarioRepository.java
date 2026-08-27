package com.matheus.biblioteca.repository;

import com.matheus.biblioteca.model.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    public void salvar(Usuario usuario) throws SQLException, IOException {
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.executeUpdate();
        stmt.close();
    }
    public List<Usuario> listarTodos() throws SQLException, IOException {
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Usuario> usuarios = new ArrayList<>();
        while (rs.next()) {
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            Usuario usuario = new Usuario(nome, email);
            usuario.setId(rs.getInt("id"));
            usuarios.add(usuario);
        }
        stmt.close();
        conexao.close();
        return usuarios;
    }
    public void remover (Integer id) throws SQLException, IOException {
        Connection conexao = ConnectionFactory.getConexao();
        String sql = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
}
