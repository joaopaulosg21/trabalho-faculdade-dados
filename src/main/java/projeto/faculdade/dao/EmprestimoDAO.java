package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import projeto.faculdade.exceptions.ValidationException;
import projeto.faculdade.model.Aluno;
import projeto.faculdade.model.Emprestimo;
import projeto.faculdade.util.DbConnection;

public class EmprestimoDAO {

    public static void registrarEmprestimo(String matricula, String tituloLivro) {
        String verificarEstoque = "SELECT quantidade_estoque FROM livros WHERE titulo = ?";
        String registrarEmprestimo = "INSERT INTO emprestimos(id_aluno, id_livro, data_devolucao) VALUES (?,?,?)";
        String atualizarEstoque = "UPDATE livros SET quantidade_estoque = quantidade_estoque - 1 WHERE id_livro = ?";
        Aluno aluno = AlunoDAO.getAlunoId(matricula);
        int idAluno = aluno.getId();
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String matriculaAluno = AlunoDAO.getMatricula(emp.getId_aluno());
                int empId = emp.getId();
                String nomeLivro = LivroDAO.getTituloLivro(emp.getId_livro());
                String dataEmprestimo = emp.getDataEmprestimo().format(formatter);
                String dataDevolucao = emp.getDataDevolucao().format(formatter);
                String devolvidoEm = emp.getDevolvidoEm() != null ? emp.getDevolvidoEm().format(formatter) : "Não devolvido";
            
                System.out.println("""
                        Emprestimos id: %d,
                        Matricula do aluno: %s
                        Titulo livro: %s,
                        Data do emprestimo: %s,
                        Data prevista de devolução: %s,
                        Data em que foi devolvido: %s
                        """.formatted(empId,matriculaAluno,nomeLivro,dataEmprestimo,dataDevolucao,devolvidoEm));
            });
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void devolucao(Aluno aluno, int idLivro) {
        Emprestimo emprestimo = buscarEmprestimoPorId(aluno.getId(), idLivro);
        if(emprestimo == null) {
            throw new ValidationException("Aluno não possui emprestimos desse livro");
        }

        if (emprestimo.getDevolvidoEm() != null) {
            throw new ValidationException("Devolução já foi feita para esse determinado empréstimo");
        }

        String updateEmprestimo = "UPDATE emprestimos SET devolvido_em = ? WHERE id_aluno = ? AND id_livro = ?";
        String atualizarEstoque = "UPDATE livros SET quantidade_estoque = quantidade_estoque + 1 WHERE id_livro = ?";
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(updateEmprestimo);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(2, aluno.getId());
            preparedStatement.setInt(3, idLivro);
            preparedStatement.executeUpdate();

            PreparedStatement atualizarEstoqueStmt = con.prepareStatement(atualizarEstoque);
            atualizarEstoqueStmt.setInt(1, idLivro);
            atualizarEstoqueStmt.executeUpdate();

            System.out.println("Devolução concluida com sucesso!");
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }
    }

    public static Emprestimo buscarEmprestimoPorId(int idAluno, int idLivro) {
        String selectEmprestimo = "SELECT * FROM emprestimos WHERE id_aluno = ? AND id_livro = ? ORDER BY id_emprestimo DESC";

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(selectEmprestimo);
            preparedStatement.setInt(1, idAluno);
            preparedStatement.setInt(2, idLivro);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id_aluno = rs.getInt("id_aluno");
                Date devolvidoEm = rs.getDate("devolvido_em");
                Emprestimo emprestimo = new Emprestimo(rs.getInt("id_emprestimo"),
                        id_aluno, rs.getInt("id_livro"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        rs.getDate("data_devolucao").toLocalDate(),
                        devolvidoEm != null ? devolvidoEm.toLocalDate() : null);
                return emprestimo;
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }

        return null;
    }
}
