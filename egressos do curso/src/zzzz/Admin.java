package zzzz;

import java.util.ArrayList;

public class Admin extends Usuario {
    private ArrayList<Coordenador> coordenadores;

    public Admin(String login, String senha) {
        super(login, senha);
        coordenadores = new ArrayList<>();
    }

    public ArrayList<Coordenador> getCoordenadores() {
        return coordenadores;
    }

    public void setCoordenadores(ArrayList<Coordenador> coordenadores) {
        this.coordenadores = coordenadores;
    }
}
