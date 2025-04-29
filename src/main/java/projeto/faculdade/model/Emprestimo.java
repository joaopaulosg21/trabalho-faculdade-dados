package projeto.faculdade.model;

import java.time.LocalDate;

public class Emprestimo {
    
    private int id;

    private int id_aluno;

    private int id_livro;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    public Emprestimo(int id, int id_aluno, int id_livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    
}
