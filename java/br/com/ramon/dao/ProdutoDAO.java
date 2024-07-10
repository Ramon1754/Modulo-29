package br.com.ramon.dao;


import br.com.ramon.dao.jdbc.ConnectionFactory;
import br.com.ramon.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {
    @Override
    public void cadastrar(Produto produto) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("INSERT INTO produto (nome, preco) VALUES (?, ?)");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    @Override
    public void atualizar(Produto produto) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("UPDATE produto SET nome = ?, preco = ? WHERE id = ?");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    @Override
    public Produto buscar(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM produto WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                return produto;
            }
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        return null;
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        return produtos;
    }

    @Override
    public void excluir(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
}

