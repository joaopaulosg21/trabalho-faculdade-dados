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

    public static void atualizarLivro(Scanner sc) {
        System.out.print("Digite o titulo do livro: ");
        String titulo = sc.nextLine();

        Livro livro = LivroDAO.buscar(titulo);
        System.out.println("CASO NÃO QUEIRA ALTERAR O DADO BASTA DEIXAR VAZIO!!!");

        System.out.print("Digite o titulo do livro: ");
        String novoTitulo = sc.nextLine();

        if (novoTitulo.isBlank() || novoTitulo.isEmpty()) {
            novoTitulo = livro.getTitulo();
        }

        System.out.print("Digite o autor do livro: ");
        String autor = sc.nextLine();

        if (autor.isBlank() || autor.isEmpty()) {
            autor = livro.getAutor();
        }

        System.out.print("Digite o ano de publicação do livro: ");
        String ano = sc.nextLine();
        int anoPublicacao = (ano.equalsIgnoreCase("") ? 0 : Integer.parseInt(ano));
        if (anoPublicacao == 0) {
            anoPublicacao = livro.getAnoPublicacao();
        }

        System.out.print("Digite a quantidade de estoque: ");
        String qtd = sc.nextLine();
        int quantidade = (qtd.equalsIgnoreCase("") ? 0 : Integer.parseInt(qtd));

        if (quantidade == 0) {
            quantidade = livro.getQuantidadeEstoque();
        }

        livro.setTitulo(novoTitulo);
        livro.setAutor(autor);
        livro.setAnoPublicacao(anoPublicacao);
        livro.setQuantidadeEstoque(quantidade);

        LivroDAO.atualizar(livro);
    }

    public static void deletarLivro(Scanner sc) {
        System.out.print("Digite o titulo do livro: ");
        String titulo = sc.nextLine();

        Livro livro = LivroDAO.buscar(titulo);
        LivroDAO.deletar(livro);
    }
}
