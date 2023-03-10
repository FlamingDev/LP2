package controller;

import entities.Campanha;
import entities.Doacao;
import entities.Item;
import entities.Meta;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Entidade { // a entidade será a controladora das campanhas, abrindo, fechando ou doando a elas
    private String nome;
    private List<Campanha> campanhas;

    public Entidade(String nome) {
        this.nome = nome;
        campanhas = new ArrayList<>();
    }

    public void doar(Doacao doacao) throws IllegalAccessError, IllegalArgumentException{
        if (campanhas.isEmpty()){
            throw new IllegalAccessError("Não há campanhas disponíveis para fazer a doação");
        }
        // campanhas pode ser entendida como uma fila, onde a prioridade é quem tem o prazo menor
        Queue<Campanha> queue = new PriorityQueue<>(campanhas);

        while (!queue.isEmpty()){
            Campanha temp = queue.poll();
            for (Meta m : temp.getMetas()){
                if (m.getItem().equals(doacao.getItem())){
                    if (!temp.isMetaBatida(m)){
                        // se o item da doação coincidir com o item da meta, e a meta ainda não foi batida, doaremos a essa campanha
                        temp.addDoacao(doacao);
                        System.out.println("Doação dada à campanha " + temp.getTitulo());
                        return;
                    }
                    else{
                        throw new IllegalArgumentException("A meta desse item já foi batida");
                    }
                }
            }
        }
        // não foi encontrado em nenhuma campanha uma meta com aquele item
        throw new IllegalArgumentException("ERRO: A entidade não está aceitando itens desse tipo");
    }

    public void abrirCampanha(Campanha campanha){
        campanhas.add(campanha);
    }

    public void fecharCampanha(Campanha campanha) throws IllegalArgumentException{
        if (!campanhas.contains(campanha)){
            throw new IllegalArgumentException("ERRO: A campanha ainda não foi aberta");
        }
        if (campanha.isAllMetaBatida() || campanha.diasAteOPrazo() <= 0){
            campanhas.remove(campanha);
        }
        else{
            throw new IllegalArgumentException("ERRO: A campanha ainda não foi finalizada!");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void listarCampanhas() throws IllegalArgumentException{
        int i = 0;
        if (campanhas.isEmpty()){
            throw new IllegalArgumentException("Nenhuma campanha registrada");
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Campanha c : campanhas){
            System.out.println(i + ": " + c.getTitulo() + " - Prazo de vencimento: " + c.getDataPrazo().format(fmt));
            if (!c.getMetas().isEmpty()){
                if (c.isAllMetaBatida()){
                    System.out.println("--- CAMPANHA COMPLETA ---");
                }
                c.andamento();
            }
            else{
                System.out.println("A campanha não tem metas registradas");
            }
        }
    }

    public void listarItens() throws IllegalArgumentException{
        Set<Item> itens = new HashSet<>();

        for (Campanha c : campanhas) {
            for (Meta m : c.getMetas()) {
                itens.add(m.getItem());
            }
        }
        if (itens.isEmpty()){
            throw new IllegalArgumentException("A entidade não precisa de doações no momento");
        }
        System.out.println(itens);
    }

    public void listarDoacoes() throws IllegalArgumentException{ // as doações serão ordenadas por nome
        List<Doacao> doacoes = new ArrayList<>();
        for (Campanha c : campanhas){
            doacoes.addAll(c.getDoacoes());
        }
        if (doacoes.isEmpty()){
            throw new IllegalArgumentException("Ainda não recebemos nenhuma doação");
        }
        doacoes.sort(Comparator.comparing(Doacao::getDoador));
        for (Doacao d : doacoes){
            System.out.println(d);
        }
    }
}
