package projeto.faculdade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import projeto.faculdade.dao.AlunoDAO;
import projeto.faculdade.model.Aluno;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("""

                    SISTEMA DE BIBLIOTECA

                Escolha uma opção:
                1 - Cadastrar aluno
                2 - Cadastrar livro
                3 - Emprestimo de livro
                4 - Devolução de livro
                5 - Relatorio
                """);
        System.out.print("Opção: ");
        int option = sc.nextInt();

        switch (option) {
            case 1 -> {
                cadastrarAluno(sc);
                break;
            }
        }

        sc.close();
    }

    private static void cadastrarAluno(Scanner sc) {
        System.out.print("Digite o nome do aluno: ");
        String nome = sc.next();

        System.out.print("Digite a matricula do aluno: ");
        String matricula = sc.next();

        System.out.print("Digite a data de nascimento do aluno (no formato: dd/mm/yyyy): ");
        String data = sc.next();
        LocalDate dataNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        AlunoDAO.registrar(new Aluno(0, nome, matricula, dataNascimento));
    }
}