package projeto.faculdade.model;

import java.time.LocalDate;

public class Emprestimo {
    
    private int id;

    private int id_aluno;

    private int id_livro;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    private LocalDate devolvidoEm;

    public Emprestimo(int id, int id_aluno, int id_livro, LocalDate dataEmprestimo, LocalDate dataDevolucao, LocalDate devolvidoEm) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvidoEm = devolvidoEm;
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

    public LocalDate getDevolvidoEm() {
        return devolvidoEm;
    }

    public void setDevolvidoEm(LocalDate devolvidoEm) {
        this.devolvidoEm = devolvidoEm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Emprestimo{");
        sb.append("id=").append(id);
        sb.append(", id_aluno=").append(id_aluno);
        sb.append(", id_livro=").append(id_livro);
        sb.append(", dataEmprestimo=").append(dataEmprestimo);
        sb.append(", dataDevolucao=").append(dataDevolucao);
        sb.append(", devolvidoEm=").append(devolvidoEm);
        sb.append('}');
        return sb.toString();
    }


    
    
}
