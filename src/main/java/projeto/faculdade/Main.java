package projeto.faculdade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import projeto.faculdade.dao.AlunoDAO;
import projeto.faculdade.dao.LivroDAO;
import projeto.faculdade.model.Aluno;
import projeto.faculdade.model.Livro;

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
        sc.nextLine();
        switch (option) {
            case 1 -> {
                cadastrarAluno(sc);
                break;
            }
            case 2 -> {
                cadastrarLivro(sc);
                break;
            }
        }
    }

    private static void cadastrarAluno(Scanner sc) {
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

    private static void cadastrarLivro(Scanner sc) {
        System.out.print("Digite o titulo do livro: ");
        String titulo = sc.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = sc.nextLine();

        System.out.print("Digite o ano de publicação do livro: ");
        int anoPublicacao = sc.nextInt();

        System.out.print("Digite a quantidade de estoque: ");
        int quantidade = sc.nextInt();

        LivroDAO.registrar(new Livro(0, titulo, autor, anoPublicacao, quantidade));
        sc.close();
    }
}