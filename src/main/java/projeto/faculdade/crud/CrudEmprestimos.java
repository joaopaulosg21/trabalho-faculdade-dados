package projeto.faculdade.crud;

import java.util.Scanner;

import projeto.faculdade.dao.AlunoDAO;
import projeto.faculdade.dao.EmprestimoDAO;

public class CrudEmprestimos {

    public static void registrarEmprestimo(Scanner sc) {
        System.out.print("Digite a matricula do aluno: ");
        String matricula = sc.nextLine();
        AlunoDAO.verificaMatricula(matricula);

        System.out.print("Digite o titulo do livro: ");
        String tituloLivro = sc.nextLine();

        EmprestimoDAO.registrarEmprestimo(matricula, tituloLivro);
    }

    public static void relatoriosBasicos() {
        EmprestimoDAO.relatoriosBasicos();
    }
}
