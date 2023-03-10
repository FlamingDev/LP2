package entities;

import java.util.Objects;

public class Item {
    private String nome;
    private String unidade;

    public Item(String nome, String unidade) {
        this.nome = nome;
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @Override
    public boolean equals(Object o) { // se dois itens possuem o mesmo nome, então são iguais
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return nome.equalsIgnoreCase(item.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString(){
        return nome;
    }
}
