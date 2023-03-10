package zzzz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class Curso implements Serializable{
    private String nome;
    private Hashtable<String,Egresso> egressos;

    public Curso(String nome) {
        this.nome = nome;
        egressos = new Hashtable<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Hashtable<String, Egresso> getEgressos() {
        return egressos;
    }

    public void setEgressos(Hashtable<String, Egresso> egressos) {
        this.egressos = egressos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nome='" + nome + '\'' +
                ", egressos=" + egressos +
                '}';
    }
}