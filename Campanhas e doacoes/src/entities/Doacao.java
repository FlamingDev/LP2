package entities;

public class Doacao extends Meta{ // uma doação também tem um item, e uma quantidade
    private String doador;

    public Doacao(Item item, Double quantidade, String doador) {
        super(item, quantidade);
        this.doador = doador;
    }

    public String getDoador() {
        return doador;
    }

    @Override
    public String toString() {
        return "Pessoa: " + doador + "\n" + "Item: " + quantidade + " " + item.getUnidade() + " de " + item.getNome() + '\n';
    }
}
