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

    public static void buscarAluno(Scanner sc) {
        System.out.print("Digite a matricula do aluno: ");
        String matricula = sc.nextLine();
        AlunoDAO.buscar(matricula);
    }

    public static void atualizarAluno(Scanner sc) {
        System.out.print("Digite a matricula do aluno: ");
        String matricula = sc.nextLine();

        Aluno aluno = AlunoDAO.buscar(matricula);

        System.out.println("CASO NÃO QUEIRA ALTERAR O DADO BASTA DEIXAR VAZIO!!!");
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        if (nome.isBlank() || nome.isEmpty()) {
            nome = aluno.getNome();
        }

        System.out.print("Data de Nascimento: ");
        String data = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (data.isBlank() || data.isEmpty()) {
            data = aluno.getDataNascimento().format(formatter);
        }
        LocalDate dataNascimento = LocalDate.parse(data, formatter);

        aluno.setNome(nome);
        aluno.setDataNascimento(dataNascimento);

        AlunoDAO.atualizar(aluno);

    }

    public static void deletarAluno(Scanner sc) {
        System.out.print("Digite a matricula do aluno para excluir : ");
        String matricula = sc.nextLine();
        Aluno aluno = AlunoDAO.buscar(matricula);

        AlunoDAO.deletar(aluno);
    }
}
