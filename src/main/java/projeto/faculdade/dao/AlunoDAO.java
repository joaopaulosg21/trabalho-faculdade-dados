package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import projeto.faculdade.exceptions.ValidationException;
import projeto.faculdade.model.Aluno;
import projeto.faculdade.util.DbConnection;

public class AlunoDAO {

    public static void registrar(Aluno aluno) {
        String insertAluno = "INSERT INTO alunos(nome_aluno,matricula,data_nascimento) VALUES (?,?,?)";
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(insertAluno);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getMatricula());
            preparedStatement.setDate(3, Date.valueOf(aluno.getDataNascimento()));

            preparedStatement.executeUpdate();

            System.out.println("Aluno registrado com sucesso!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void verificaMatricula(String matricula) {
        String verificaMatricula = "SELECT * FROM alunos WHERE matricula = ?";
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement matriculaStmt = con.prepareStatement(verificaMatricula);
            matriculaStmt.setString(1, matricula);

            ResultSet rs = matriculaStmt.executeQuery();
            if (!rs.next()) {
                throw new ValidationException(
                        "Matricula invalida, digite uma matricula de um aluno registrado no sistema");
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }
    }

    public static Aluno getAlunoId(String matricula) {
        String idAlunoQuery = "SELECT * FROM alunos WHERE matricula = ?";
        verificaMatricula(matricula);
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement idAlunoStmt = con.prepareStatement(idAlunoQuery);
            idAlunoStmt.setString(1, matricula);

            ResultSet rs = idAlunoStmt.executeQuery();
            while (rs.next()) {
                int idAluno = rs.getInt("id_aluno");
                Aluno aluno = new Aluno(idAluno, rs.getString("nome_aluno"),
                        rs.getString("matricula"), rs.getDate("data_nascimento").toLocalDate());
                return aluno;
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }
        return null;
    }

        public static String getMatricula(int idAluno) {
        String idAlunoQuery = "SELECT matricula FROM alunos WHERE id_aluno = ?";
        String matricula = "";
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement idAlunoStmt = con.prepareStatement(idAlunoQuery);
            idAlunoStmt.setInt(1, idAluno);

            ResultSet rs = idAlunoStmt.executeQuery();
            while (rs.next()) {
                matricula = rs.getString("matricula");
                return matricula;
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }
        return null;
    }

    public static Aluno buscar(String matricula) {
        String queryBuscarAluno = "SELECT * FROM alunos WHERE matricula = ?";
        verificaMatricula(matricula);
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement matriculaStmt = con.prepareStatement(queryBuscarAluno);
            matriculaStmt.setString(1, matricula);

            ResultSet rs = matriculaStmt.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getInt("id_aluno"), rs.getString("nome_aluno"),
                        rs.getString("matricula"), rs.getDate("data_nascimento").toLocalDate());

                System.out.println(aluno);
                return aluno;
            }
        } catch (SQLException exc) {
            throw new ValidationException(exc.getMessage());
        }

        return null;
    }

    public static void atualizar(Aluno aluno) {
        String atualizarAluno = "UPDATE alunos SET nome_aluno = ?, data_nascimento=? WHERE id_aluno = ?";

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(atualizarAluno);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setDate(2, Date.valueOf(aluno.getDataNascimento()));
            preparedStatement.setInt(3, aluno.getId());
            preparedStatement.executeUpdate();

            System.out.println("Aluno atualizado com sucesso!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void deletar(Aluno aluno) {
        String deletarAluno = "DELETE FROM alunos WHERE id_aluno = ?";

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(deletarAluno);
            preparedStatement.setInt(1, aluno.getId());

            preparedStatement.executeUpdate();

            System.out.println("Aluno deletado com sucesso!!");
        } catch (SQLIntegrityConstraintViolationException exc) {
            System.out.println("Aluno possui emprestimos de livros associados a ele, não é possivel a exclusão!!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
