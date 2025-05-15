package projeto.faculdade.crud;

import java.util.Scanner;

import projeto.faculdade.dao.AlunoDAO;
import projeto.faculdade.dao.EmprestimoDAO;
import projeto.faculdade.dao.LivroDAO;
import projeto.faculdade.model.Aluno;

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

    public static void devolucao(Scanner sc) {
        System.out.print("Digite a matricula do aluno: ");
        String matricula = sc.nextLine();

        System.out.print("Digite o titulo do livro: ");
        String tituloLivro = sc.nextLine();

        Aluno aluno = AlunoDAO.getAlunoId(matricula);
        int idAluno = aluno.getId();
        int idLivro = LivroDAO.getLivroId(tituloLivro);

        EmprestimoDAO.devolucao(aluno, idLivro);
    }
}
