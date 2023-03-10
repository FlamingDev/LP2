package zzzz;

import java.io.Serializable;

public class Ocupacao implements Serializable{
    private float salario;
    private int anoInicio;
    private String descricao;

    private String lugar;
    public Ocupacao(float salario, int anoInicio, String descricao, String lugar) {
        this.salario = salario;
        this.anoInicio = anoInicio;
        this.descricao = descricao;
        this.lugar = lugar;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString(){
        return descricao + " em " + lugar + " salário: " + salario;
    }

}
