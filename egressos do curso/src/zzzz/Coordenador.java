package zzzz;

import java.io.Serializable;

public class Coordenador extends Usuario implements Serializable {
    private Curso curso;

    public Coordenador(String login, String senha, Curso curso) {
        super(login, senha);
        this.curso = curso;
    }
    public Curso getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "Coordenador de login " + this.getLogin() + " do curso de " + this.getCurso().getNome() + "( senha = " + this.getSenha() + "\n";
    }
}
