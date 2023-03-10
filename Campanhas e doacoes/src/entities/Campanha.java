package entities;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* A implementação da interface comparable é pra dizer que uma campanha A é maior que B
   se diasAteOPrazo de A > diasAteOPrazo de B, e assim dar prioridade pra campanha que tiver
   esse valor menor, uma data prazo menor. */

public class Campanha implements Comparable<Campanha> {
    private String titulo;
    private Set<Meta> metas; // não será permitido metas iguais em uma campanha ( metas com itens iguais )
    private List<Doacao> doacoes;
    private LocalDate dataPrazo;

    public Campanha(String titulo, LocalDate dataPrazo) throws DateTimeException {
        if (dataPrazo.isBefore(LocalDate.now())){
            throw new DateTimeException("As datas de prazo de campanhas precisam ser futuras");
        }
        this.titulo = titulo;
        this.dataPrazo = dataPrazo;
        metas = new HashSet<>();
        doacoes = new ArrayList<>();
    }

    // informa o andamento no formato -- x doações/ y necessárias --
    public void andamento(){
        for (Meta m : metas){
            double quant = 0;
            for (Doacao d : doacoes){
                if (d.getItem().equals(m.getItem())){ // se os itens forem iguais, encontramos uma doação
                    quant += d.quantidade;
                }
            }
            System.out.println("\t" + m + " - " + quant + "/" + m.getQuantidade());
        }
    }

    public boolean isAllMetaBatida() throws IllegalStateException{
        if (metas.isEmpty()){
            throw new IllegalStateException("A campanha não tem essa meta registrada");
        }
        if (doacoes.isEmpty()){
            return false;
        }

        for (Meta m : metas){
            double totDoacoes = 0;
            for (Doacao d : doacoes){
                if (d.getItem().equals(m.getItem())){
                    totDoacoes += d.quantidade;
                }
            }
            if (totDoacoes < m.quantidade){
                return false;
            }
        }
        return true; // só retornará verdadeiro se as doações forem maiores ou iguais a meta, analisando todas as metas
    }

    /* função auxiliar para verificar se uma determinada meta já foi atingida,
       se sim a campanha não aceitará mais doações daquele item  */
    public boolean isMetaBatida(Meta meta) throws IllegalStateException{
        if (metas.isEmpty() || !metas.contains(meta)){
            throw new IllegalStateException("A campanha não tem essa meta registrada");
        }

        double sum = 0;
        for (Doacao d : doacoes){
            if (d.getItem().equals(meta.getItem())){
                sum += d.quantidade;
            }
        }

        if (sum < meta.quantidade){
            return false;
        }
        return true;
    }
    public Long diasAteOPrazo(){
        return Duration.between(LocalDate.now().atStartOfDay(),dataPrazo.atStartOfDay()).toDays();
    }

    public void estenderPrazo(LocalDate dataPrazo) throws IllegalArgumentException{
        if (dataPrazo.isBefore(this.dataPrazo)){
            throw new IllegalArgumentException("A data para extender o prazo precisa ser posterior à data atual");
        }
        this.dataPrazo = dataPrazo;
    }
    public void addMeta(Meta meta) throws IllegalArgumentException{
        boolean repetido = !metas.add(meta); // se o conjunto metas já tem aquela meta, ela não será adicionada
        if (repetido){
            throw new IllegalArgumentException("ERRO: A meta já existe na campanha");
        }
    }

    public void removerMeta(Meta meta){
        metas.remove(meta);
    }

    public void addDoacao(Doacao doacao){
        doacoes.add(doacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Meta> getMetas() {
        return metas;
    }

    public LocalDate getDataPrazo() {
        return dataPrazo;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    @Override
    public String toString(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s = titulo + "\n";
        s += "Necessita-se dos seguintes itens: \n";

        for (Meta m : metas){
            s += m.toString() + "\n";
        }

        s += "\nPrazo de vencimento: " + dataPrazo.format(fmt) + "\n";
        return s;
    }

    @Override
    public int compareTo(Campanha c) {
        return this.diasAteOPrazo().compareTo(c.diasAteOPrazo());
    }
}
