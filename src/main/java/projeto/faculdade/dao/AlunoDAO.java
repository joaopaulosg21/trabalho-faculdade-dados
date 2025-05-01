package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projeto.faculdade.model.Aluno;
import projeto.faculdade.util.DbConnection;

public class AlunoDAO {

    private static final String INSERT = "INSERT INTO alunos(nome_aluno,matricula,data_nascimento) VALUES (?,?,?)";

    public static void registrar(Aluno aluno) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getMatricula());
            preparedStatement.setDate(3, Date.valueOf(aluno.getDataNascimento()));

            preparedStatement.executeUpdate();
            
            System.out.println("Aluno registrado com sucesso!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
