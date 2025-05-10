package projeto.faculdade.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import projeto.faculdade.dao.AlunoDAO;
import projeto.faculdade.model.Aluno;

public class CrudAlunos {
    public static void cadastrarAluno(Scanner sc) {
        System.out.print("Digite o nome do aluno: ");
        String nome = sc.nextLine();

        System.out.print("Digite a matricula do aluno: ");
        String matricula = sc.nextLine();

        System.out.print("Digite a data de nascimento do aluno (no formato: dd/mm/yyyy): ");
        String data = sc.nextLine();
        LocalDate dataNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        AlunoDAO.registrar(new Aluno(0, nome, matricula, dataNascimento));
        sc.close();
    }
}
