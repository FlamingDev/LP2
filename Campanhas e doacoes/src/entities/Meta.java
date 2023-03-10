package entities;

import java.util.Objects;

public class Meta {
    protected Item item;
    protected Double quantidade;

    public Meta(Item item, Double quantidade) {
        if (quantidade < 0){
            throw new IllegalArgumentException("Quantidade inválida para a doação");
        }
        this.item = item;
        this.quantidade = quantidade;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString(){
        return quantidade + " " + item.getUnidade() + " de " + item.getNome();
    }

    @Override
    public boolean equals(Object o) { // se duas metas possuem o mesmo item, então são a mesma meta
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meta meta = (Meta) o;
        return item.equals(meta.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}
