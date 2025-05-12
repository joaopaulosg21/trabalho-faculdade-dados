package projeto.faculdade.crud;

import java.util.Scanner;

import projeto.faculdade.dao.LivroDAO;
import projeto.faculdade.model.Livro;

public class CrudLivros {
    public static void cadastrarLivro(Scanner sc) {
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

    public static void buscarLivro(Scanner sc) {
        System.out.print("Digite o titulo do livro: ");
        String titulo = sc.nextLine();

        LivroDAO.buscar(titulo);
    }
}
