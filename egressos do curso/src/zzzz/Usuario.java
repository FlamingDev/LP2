package zzzz;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private String login;
    private String senha;

    private boolean logado;
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.logado = false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}
