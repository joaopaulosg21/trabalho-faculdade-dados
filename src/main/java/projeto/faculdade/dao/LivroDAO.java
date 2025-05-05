package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projeto.faculdade.model.Livro;
import projeto.faculdade.util.DbConnection;

public class LivroDAO {
    private static final String INSERT = "INSERT INTO livros(titulo,autor,ano_publicacao,quantidade_estoque) VALUES (?,?,?,?)";

    public static void registrar(Livro livro) {
        try(Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setString(1, livro.getTitulo());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setInt(3, livro.getAnoPublicacao());
            preparedStatement.setInt(4, livro.getQuantidadeEstoque());

            preparedStatement.executeUpdate();
            
            System.out.println("Livro registrado com sucesso!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
