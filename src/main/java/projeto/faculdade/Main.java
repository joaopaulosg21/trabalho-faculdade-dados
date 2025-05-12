package projeto.faculdade;

import java.util.Scanner;

import projeto.faculdade.crud.CrudAlunos;
import projeto.faculdade.dao.AlunoDAO;
import projeto.faculdade.dao.EmprestimoDAO;
import projeto.faculdade.dao.LivroDAO;
import projeto.faculdade.model.Livro;

public class Main {
    public static void main(String[] args) throws Exception {
        menuInicial();
    }

    private static void menuInicial() {
        Scanner sc = new Scanner(System.in);
        String menu = """

                    SISTEMA DE BIBLIOTECA

                Escolha uma opção:
                1 - Menu de alunos
                2 - Menu de livros
                3 - Emprestimo de livro
                4 - Devolução de livro
                5 - Relatorio
                """;
        System.out.println(menu);
        System.out.print("Opção: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1 -> {
                menuDeAlunos(sc);
                break;
            }
            case 2 -> {
                cadastrarLivro(sc);
                break;
            }
            case 3 -> {
                registrarEmprestimo(sc);
            }
            case 0 -> {

            }
        }
    }

    private static void menuDeAlunos(Scanner sc) {
        System.out.println("""
                    MENU DE ALUNOS
                Escolha uma opção:
                1 - Cadastrar aluno
                2 - Buscar aluno
                3 - Editar aluno
                4 - Deletar aluno
                0 - Voltar
                """);
        System.out.print("Opção: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> {
                CrudAlunos.cadastrarAluno(sc);
                break;
            }
            case 2 -> {
                CrudAlunos.buscarAluno(sc);
                break;
            }
            case 3 -> {
                CrudAlunos.atualizarAluno(sc);
                break;
            }
            case 0 -> {
                menuInicial();
            }
        }
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

    private static void registrarEmprestimo(Scanner sc) {
        System.out.print("Digite a matricula do aluno: ");
        String matricula = sc.nextLine();
        AlunoDAO.verificaMatricula(matricula);

        System.out.print("Digite o titulo do livro: ");
        String tituloLivro = sc.nextLine();

        EmprestimoDAO.registrarEmprestimo(matricula, tituloLivro);
    }

}