package projeto.faculdade;

import java.util.Scanner;

import projeto.faculdade.crud.CrudAlunos;
import projeto.faculdade.crud.CrudEmprestimos;
import projeto.faculdade.crud.CrudLivros;

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
                3 - Menu de emprestimos
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
                menuDeLivros(sc);
                break;
            }
            case 3 -> {
                menuDeEmprestimos(sc);
                break;
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
            case 4 -> {
                CrudAlunos.deletarAluno(sc);
                break;
            }
            case 0 -> {
                menuInicial();
            }
        }
    }

    private static void menuDeLivros(Scanner sc) {
        System.out.println("""
                    MENU DE LIVROS
                Escolha uma opção:
                1 - Cadastrar livro
                2 - Buscar livro
                3 - Editar livro
                4 - Deletar livro
                0 - Voltar
                """);
        System.out.print("Opção: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> {
                CrudLivros.cadastrarLivro(sc);
                break;
            }
            case 2 -> {
                CrudLivros.buscarLivro(sc);
                break;
            }
            case 3 -> {
                CrudLivros.atualizarLivro(sc);
                break;
            }
            case 4 -> {
                CrudLivros.deletarLivro(sc);
                break;
            }
            case 0 -> {
                menuInicial();
            }
        }
    }

    
    private static void menuDeEmprestimos(Scanner sc) {
        System.out.println("""
                    MENU DE EMPRESTIMOS
                Escolha uma opção:
                1 - Registrar emprestimos
                2 - Devolução
                3 - Relatorios basicos
                0 - Voltar
                """);
        System.out.print("Opção: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> {
                CrudEmprestimos.registrarEmprestimo(sc);
                break;
            }
            case 2 -> {
                CrudEmprestimos.devolucao(sc);
                break;
            }
            case 3 -> {
                CrudEmprestimos.relatoriosBasicos();
                break;
            }
            case 0 -> {
                menuInicial();
            }
        }
    }
}