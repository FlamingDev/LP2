package zzzz;

import java.io.Serializable;

public class Contato implements Serializable {
    private String email;
    private String telefone;
    private String rede;

    public Contato(String email, String telefone, String rede) {
        this.email = email;
        this.telefone = telefone;
        this.rede = rede;
    }

    public Contato(String email, String telefone) {
        this.email = email;
        this.telefone = telefone;
    }

    public Contato(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRede() {
        return rede;
    }

    public void setRede(String instagram) {
        this.rede = instagram;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", instagram='" + rede + '\'' +
                '}';
    }
}
