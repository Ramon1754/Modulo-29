package br.com.ramon.dao;


import br.com.ramon.domain.Produto;
import java.util.List;

public interface IProdutoDAO {
    void cadastrar(Produto produto) throws Exception;
    void atualizar(Produto produto) throws Exception;
    Produto buscar(int id) throws Exception;
    List<Produto> buscarTodos() throws Exception;
    void excluir(int id) throws Exception;
}

