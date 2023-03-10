package zzzz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CoordenadorSrv extends UsuarioSrv implements Serializable{
    private Coordenador coordenador;
    public CoordenadorSrv(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    @Override
    public boolean fazerLogin(String senha) {
        if (senha.equals(coordenador.getSenha())){
            coordenador.setLogado(true);
            return true;
        }
        return false;
    }

    @Override
    public void fazerLogout() {
        coordenador.setLogado(false);
    }

    public boolean cadastrarEgresso(Egresso egresso){
        if (this.coordenador.isLogado()){
            for (String nome: this.coordenador.getCurso().getEgressos().keySet()){
                for (Egresso e: this.coordenador.getCurso().getEgressos().values()){
                    if (egresso.getNome().equals(nome) || egresso.getCpf().equals(e.getCpf())){
                        return false;
                    }
                }
            }
            this.coordenador.getCurso().getEgressos().put(egresso.getNome(), egresso);
            return true;
        }
        return false;
    }

    public Egresso removerEgresso(String nome){
        if (this.coordenador.isLogado()){
            return this.coordenador.getCurso().getEgressos().remove(nome);
        }
        return null;
    }
    public Egresso consultarEgresso(String nome){
        if (this.coordenador.isLogado())
            return this.coordenador.getCurso().getEgressos().get(nome);
        return null;
    }

    public void listarEgressos() {
        int i = 1;
        if (this.coordenador.isLogado()) {
            ArrayList<Egresso> temp = new ArrayList<>();
            for (Egresso e : this.getCoordenador().getCurso().getEgressos().values()) {
                temp.add(e);
            }
            Collections.sort(temp);
            for (Egresso e : temp) {
                System.out.println("[" + i + "] " + "Egresso: " + e.getNome());
                i++;
            }
        }
    }

    public String listarEgressos(int ano){
        int i = 1; String s = "";
        if (this.coordenador.isLogado()){
            ArrayList<Egresso> temp = new ArrayList<>();
            for (Egresso e: this.getCoordenador().getCurso().getEgressos().values()){
                if (e.getAnoConclusao() == ano) {
                    temp.add(e);
                }
            }
            if (temp.isEmpty()){
                System.out.println("Não foi encontrado nenhum egresso nesse ano");
            }
            else{
                Collections.sort(temp);
                for (Egresso e: temp){
                    s += "[" + i + "] " + "Egresso: " + e.getNome() + ", " + "Ano: " + e.getAnoConclusao();
                    System.out.println(s);
                    i++;
                }
                return s;
            }
        }
        return null;
    }

    public String listarEgressos(int anoInicio, int anoFim){
        int i = 1; String s = "";
        if (this.coordenador.isLogado()){
            ArrayList<Egresso> temp = new ArrayList<>();
            for (Egresso e: this.getCoordenador().getCurso().getEgressos().values()){
                if (e.getAnoConclusao() >= anoInicio && e.getAnoConclusao() <= anoFim){
                    temp.add(e);
                }
            }
            if (temp.isEmpty()){
                System.out.println("Nenhum egresso foi encontrado nesse intervalo de tempo");
            }
            else{
                Collections.sort(temp);
                for (Egresso e: temp){
                    System.out.println("[" + i + "] " + "Egresso: " + e.getNome() + ", Posição: " + e.getOcupacoes());
                    s += "[" + i + "] " + "Egresso: " + e.getNome() + ", Posição: " + e.getOcupacoes();
                    s += "\n";
                    i++;
                }
                return s;
            }
        }
        return null;
    }

    public void cadastrarFormacao(Egresso egresso, String formacao){
        if (this.coordenador.isLogado()){
            if (coordenador.getCurso().getEgressos().contains(egresso)){
                egresso.getFormacoes().add(formacao);
            }
        }
    }

    public boolean cadastrarOcupacao(Egresso egresso, Ocupacao ocupacao){
        ArrayList<Ocupacao> temp = egresso.getOcupacoes().get(ocupacao.getAnoInicio());
        if (this.coordenador.isLogado()){
            if (this.coordenador.getCurso().getEgressos().contains(egresso)){
                if (temp == null){
                    temp = new ArrayList<>();
                }
                temp.add(ocupacao);
                egresso.getOcupacoes().put(ocupacao.getAnoInicio(), temp);
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarDepoimento(Egresso egresso, String texto){
        if (this.coordenador.isLogado()){
            if (this.coordenador.getCurso().getEgressos().contains(egresso)){
                egresso.getDepoimentos().add(texto);
                return true;
            }
        }
        return false;
    }

    public boolean egressosIsEmpty(){
        return coordenador.getCurso().getEgressos().isEmpty();
    }

    public Coordenador getCoordenador() {
        return this.coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
}
