package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import projeto.faculdade.exceptions.ValidationException;
import projeto.faculdade.util.DbConnection;

public class EmprestimoDAO {

    public static void registrarEmprestimo(String matricula, String tituloLivro) {
        String verificarEstoque = "SELECT quantidade_estoque FROM livros WHERE titulo = ?";
        String registrarEmprestimo = "INSERT INTO emprestimos(id_aluno, id_livro, data_devolucao) VALUES (?,?,?)";
        String atualizarEstoque = "UPDATE livros SET quantidade_estoque = quantidade_estoque - 1 WHERE id_livro = ?";
        int idAluno = AlunoDAO.getAlunoId(matricula);
        int idLivro = getLivroId(tituloLivro);
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement estoqueStmt = con.prepareStatement(verificarEstoque);
            estoqueStmt.setString(1, tituloLivro);
            ResultSet rs = estoqueStmt.executeQuery();

            while(rs.next()) {
                int quantidadeEstoque = rs.getInt("quantidade_estoque");
                if(quantidadeEstoque == 0) {
                    throw new ValidationException("Livro não possui estoque");
                }
            }
            PreparedStatement emprestimoStmt = con.prepareStatement(registrarEmprestimo);
            emprestimoStmt.setInt(1,idAluno);
            emprestimoStmt.setInt(2,idLivro);
            emprestimoStmt.setDate(3, Date.valueOf(LocalDate.now().plusDays(7)));
            emprestimoStmt.executeUpdate();

            PreparedStatement atualizarEstoqueStmt = con.prepareStatement(atualizarEstoque);
            atualizarEstoqueStmt.setInt(1,idLivro);
            atualizarEstoqueStmt.executeUpdate();
            System.out.println("Emprestimo registrado no sistema com sucesso!");

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


    private static int getLivroId(String titulo) {
        String idLivroQuery =  "SELECT id_livro FROM livros WHERE titulo = ?";
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
}
