package zzzz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
public class Egresso implements Serializable, Comparable<Egresso> {
    private String nome;
    private String cpf;
    private int anoConclusao;
    private Contato contato;
    private ArrayList<String> formacoes;
    private Hashtable<Integer,ArrayList<Ocupacao>> ocupacoes;
    private ArrayList<String> depoimentos;

    public Egresso(String nome, String cpf, int anoConclusao, Contato contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.anoConclusao = anoConclusao;
        this.contato = contato;
        this.formacoes = new ArrayList<>();
        this.ocupacoes = new Hashtable<>();
        this.depoimentos = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(int anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ArrayList<String> getFormacoes() {
        return formacoes;
    }

    public Hashtable<Integer, ArrayList<Ocupacao>> getOcupacoes() {
        return ocupacoes;
    }

    public ArrayList<String> getDepoimentos() {
        return depoimentos;
    }

    @Override
    public String toString() {
        return "Egresso " + this.getNome() + " do ano de " + this.getAnoConclusao() +
                "( cpf: " + this.getCpf() + ", contatos: " + this.getContato();
    }

    @Override
    public int compareTo(Egresso o) {
        return this.nome.compareTo(o.getNome());
    }
}
