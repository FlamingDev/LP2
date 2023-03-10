package zzzz;

public class AdminSrv extends UsuarioSrv{
    private final Admin admin; 

    public AdminSrv(Admin admin) {
        this.admin = admin;
    }

    @Override
    public boolean fazerLogin(String senha) {
        if (senha.equals(admin.getSenha())){
            admin.setLogado(true);
            return true;
        }
        return false;
    }

    @Override
    public void fazerLogout() {
        admin.setLogado(false);
    }
    public boolean cadastrarCoordenador(Coordenador coordenador){
        /* Retorna true se, e somente se o admin estiver logado
        *  e não existir outro usuário com aquele login */
        if (this.admin.isLogado()){
            if (!coordenador.getLogin().equals(admin.getLogin())){
                for (Coordenador c: admin.getCoordenadores()){
                    if (c.getLogin().equals(coordenador.getLogin())){
                        return false;
                    }
                }
                admin.getCoordenadores().add(coordenador);
                return true;
            }
        }
        return false;
    }

    public boolean apagarCoordenador(Coordenador coordenador){
        if (this.admin.isLogado()){
            return admin.getCoordenadores().remove(coordenador);
        }
        return false;
    }

    public void listarCoordenadores(){
        int i = 1;
        for (Coordenador c: this.admin.getCoordenadores()){
            if (!c.isLogado()) {
                System.out.print(i + ": " + c);
                i++;
            }
        }
    }

    public boolean coordenadoresIsEmpty(){
        return this.admin.getCoordenadores().isEmpty();
    }

    public Admin getAdmin() {
        return admin;
    }
}
