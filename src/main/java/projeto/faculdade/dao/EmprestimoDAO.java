package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import projeto.faculdade.exceptions.ValidationException;
import projeto.faculdade.model.Emprestimo;
import projeto.faculdade.util.DbConnection;

public class EmprestimoDAO {

    public static void registrarEmprestimo(String matricula, String tituloLivro) {
        String verificarEstoque = "SELECT quantidade_estoque FROM livros WHERE titulo = ?";
        String registrarEmprestimo = "INSERT INTO emprestimos(id_aluno, id_livro, data_devolucao) VALUES (?,?,?)";
        String atualizarEstoque = "UPDATE livros SET quantidade_estoque = quantidade_estoque - 1 WHERE id_livro = ?";

        int idAluno = AlunoDAO.getAlunoId(matricula);
        int idLivro = LivroDAO.getLivroId(tituloLivro);
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement estoqueStmt = con.prepareStatement(verificarEstoque);
            estoqueStmt.setString(1, tituloLivro);
            ResultSet rs = estoqueStmt.executeQuery();

            while (rs.next()) {
                int quantidadeEstoque = rs.getInt("quantidade_estoque");
                if (quantidadeEstoque == 0) {
                    throw new ValidationException("Livro não possui estoque");
                }
            }
            PreparedStatement emprestimoStmt = con.prepareStatement(registrarEmprestimo);
            emprestimoStmt.setInt(1, idAluno);
            emprestimoStmt.setInt(2, idLivro);
            emprestimoStmt.setDate(3, Date.valueOf(LocalDate.now().plusDays(7)));
            emprestimoStmt.executeUpdate();

            PreparedStatement atualizarEstoqueStmt = con.prepareStatement(atualizarEstoque);
            atualizarEstoqueStmt.setInt(1, idLivro);
            atualizarEstoqueStmt.executeUpdate();
            System.out.println("Emprestimo registrado no sistema com sucesso!");

        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void relatoriosBasicos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String selectEmprestimos = "SELECT * FROM emprestimos ORDER BY data_emprestimo DESC";

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(selectEmprestimos);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Date devolvidoEm = rs.getDate("devolvido_em");
                Emprestimo emprestimo = new Emprestimo(rs.getInt("id_emprestimo"),
                        rs.getInt("id_aluno"), rs.getInt("id_livro"),
                        rs.getDate("data_emprestimo").toLocalDate(), 
                        rs.getDate("data_devolucao").toLocalDate(),
                        devolvidoEm != null ? devolvidoEm.toLocalDate() : null);
                emprestimos.add(emprestimo);
            }

            emprestimos.forEach(emp -> {
                System.out.println(emp);
            });
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
