package projeto.faculdade.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projeto.faculdade.dto.RegisterAlunoDTO;
import projeto.faculdade.util.DbConnection;

public class AlunoDAO {

    private static final String INSERT = "INSERT INTO alunos(nome_aluno,matricula,data_nascimento) VALUES (?,?,?)";

    public static void registrar(RegisterAlunoDTO registerAlunoDTO) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setString(1, registerAlunoDTO.nome());
            preparedStatement.setString(2, registerAlunoDTO.matricula());
            preparedStatement.setDate(3, Date.valueOf(registerAlunoDTO.dataNascimento()));

            preparedStatement.executeUpdate();
            
            System.out.println("Aluno registrado com sucesso!!");
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
