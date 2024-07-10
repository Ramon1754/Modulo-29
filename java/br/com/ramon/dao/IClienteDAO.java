package br.com.ramon.dao;

import br.com.ramon.domain.Cliente;

import java.sql.SQLException;

public interface IClienteDAO {

    public Integer cadastrar(Cliente cliente) throws SQLException, Exception;

    Cliente consultar(String codigo) throws Exception;

    Integer excluir(Cliente clienteBD) throws SQLException, Exception;
}
