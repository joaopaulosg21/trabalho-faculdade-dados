package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import projeto.faculdade.exceptions.ValidationException;
import projeto.faculdade.model.Livro;
import projeto.faculdade.util.DbConnection;

public class LivroDAO {
    private static final String INSERT = "INSERT INTO livros(titulo,autor,ano_publicacao,quantidade_estoque) VALUES (?,?,?,?)";

    public static void registrar(Livro livro) {
        try (Connection con = DbConnection.getConnection()) {
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

    public static void verificaTitulo(String titulo) {
        String verificaTitulo = "SELECT * FROM livros WHERE titulo = ?";
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement tituloStmt = con.prepareStatement(verificaTitulo);
            tituloStmt.setString(1, titulo);

            ResultSet rs = tituloStmt.executeQuery();
            if (!rs.next()) {
                throw new ValidationException("Titulo invalido, digite um titulo registrado no sistema!");
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }
    }

    public static int getLivroId(String titulo) {
        String idLivroQuery = "SELECT id_livro FROM livros WHERE titulo = ?";
        verificaTitulo(titulo);
        int idLivro = -1;
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement idLivroStmt = con.prepareStatement(idLivroQuery);
            idLivroStmt.setString(1, titulo);

            ResultSet rs = idLivroStmt.executeQuery();
            while (rs.next()) {
                idLivro = rs.getInt("id_livro");
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }
        return idLivro;
    }

    public static String getTituloLivro(int idLivro) {
        String idLivroQuery = "SELECT titulo FROM livros WHERE id_livro = ?";
        String tituloLivro = "";
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement idLivroStmt = con.prepareStatement(idLivroQuery);
            idLivroStmt.setInt(1, idLivro);

            ResultSet rs = idLivroStmt.executeQuery();
            while (rs.next()) {
                tituloLivro = rs.getString("titulo");
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }
        return tituloLivro;
    }

    public static Livro buscar(String titulo) {
        String queryBuscarLivro = "SELECT * FROM livros WHERE titulo = ?";
        verificaTitulo(titulo);
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement tituloStmt = con.prepareStatement(queryBuscarLivro);
            tituloStmt.setString(1, titulo);

            ResultSet rs = tituloStmt.executeQuery();
            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano_publicacao"), rs.getInt("quantidade_estoque"));

                System.out.println(livro);
                return livro;
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }

        return null;
    }

    public static void atualizar(Livro livro) {
        String atualizarLivro = "UPDATE livros SET titulo = ?, autor=?, ano_publicacao=?, quantidade_estoque=? WHERE id_livro = ?";

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(atualizarLivro);
            preparedStatement.setString(1, livro.getTitulo());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setInt(3, livro.getAnoPublicacao());
            preparedStatement.setInt(4, livro.getQuantidadeEstoque());
            preparedStatement.setInt(5, livro.getId());

            preparedStatement.executeUpdate();

            System.out.println("Livro atualizado com sucesso!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void deletar(Livro livro) {
        String deletarLivro = "DELETE FROM livros WHERE id_livro = ?";

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(deletarLivro);
            preparedStatement.setInt(1, livro.getId());

            preparedStatement.executeUpdate();

            System.out.println("Livro deletado com sucesso!!");
        } catch (SQLIntegrityConstraintViolationException exc) {
            System.out.println("Livro possui emprestimos de alunos associados a ele, não é possivel a exclusão!!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
