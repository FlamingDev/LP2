package zzzz;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    public static int lerInteiro(){
        Scanner entrada = new Scanner(System.in);
        int inteiro;
        try{
            inteiro = Integer.parseInt(entrada.nextLine());
            return inteiro;
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    public static float lerFloat(){
        Scanner entrada = new Scanner(System.in);
        float numero;
        try{
            numero = Integer.parseInt(entrada.nextLine());
            return numero;
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    /* ao final do programa, registra o total de coordenadores registrados num arquivo binario
    *  como a alteração no arquivo é feita de forma sobrescrita e no final da execução, tanto um
    *  coordenador quanto um egresso poderão ser removidos e o arquivo constará com essas alterações */
    public static void gravarCoordenadores(ArrayList<Coordenador> coordenadores){
        try{
            ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("coordenadores.dat"));
            escritor.writeObject(coordenadores);
            escritor.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Nao foi possivel abrir o arquivo");
        }
        catch (IOException e){
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    public static void gravarAdmin(Admin adm){
        try{
            ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("admin.dat"));
            escritor.writeObject(adm);
            escritor.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Nao foi possivel abrir o arquivo");
        }
        catch (IOException e){
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    // lê o arquivo binario de coordenadores previamente registrados e joga no atributo adm.coordenadores
    public static ArrayList<Coordenador> lerCoordenadores() throws IOException, ClassNotFoundException{
        ArrayList<Coordenador> temp;
        ObjectInputStream leitor = new ObjectInputStream(new FileInputStream("coordenadores.dat"));
        temp = (ArrayList<Coordenador>) leitor.readObject();
        leitor.close();
        return temp;
    }

    public static Admin lerAdmin() throws IOException, ClassNotFoundException {
        Admin temp;
        ObjectInputStream leitor = new ObjectInputStream(new FileInputStream("admin.dat"));
        temp = (Admin) leitor.readObject();
        leitor.close();
        return temp;
    }

    public static void gravarCoordenadoresTexto(ArrayList<Coordenador> coordenadores){
        try{
            BufferedWriter escritor = new BufferedWriter(new FileWriter("coordenadores.txt"));
            escritor.write(coordenadores.toString());
            escritor.newLine();
            escritor.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // produz um arquivo texto de todos os egressos registrados em determinado curso
    public static void gravarEgressosTexto(Hashtable<String,Egresso> egressos, String nomeCurso){
        try{
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeCurso + "Egressos.txt"));
            for (Egresso e: egressos.values()){
                escritor.write(e.toString());
                escritor.newLine();
            }
            escritor.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        CoordenadorSrv cordSrv;
        Admin adm = null;
        AdminSrv admSrv;
        int ano, opc , opc_login, opc_coord;
        String s, login, senha = "" , nome, cpf, telefone, descricao, lugar;
        float salario;
        Egresso temp;
        Scanner entrada = new Scanner(System.in);

        try{
            adm = lerAdmin();
        }
        catch (IOException e){
            System.out.println("Criando novo arquivo para o administrador");
            System.out.println("Fazendo cadastro como administrador do sistema");
            System.out.print("Informe seu login: ");
            login = entrada.nextLine();
            System.out.print("Informe sua senha: ");
            senha = entrada.nextLine();
            adm = new Admin(login,senha);
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        finally {
            admSrv= new AdminSrv(adm);
        }

        try{
            adm.setCoordenadores(lerCoordenadores()); // lendo o arquivo prévio de coordenadores
        }
        catch (IOException e){ // Se o arquivo não existir ou tiver vazio, cria um novo espaço para registro de coordenadores
            System.out.println("Criando novo arquivo de coordenadores");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        do{
            admSrv.fazerLogin(senha); // sempre que o usuario entra nesse menu o admin é obrigatoriamente logado
            System.out.println("Logado como " + adm.getLogin());
            System.out.println("----- Menu do administrador -----");
            System.out.println("[1] Cadastrar coordenador");
            System.out.println("[2] Apagar coordenador");
            System.out.println("[3] Fazer login em outro usuario");
            System.out.println("[0] Salvar e sair");
            System.out.print("Sua opção: ");
            opc = lerInteiro();

            switch (opc){
                case 1:
                    System.out.print("Informe o login do usuario: ");
                    login = entrada.nextLine();
                    System.out.print("Senha: ");
                    senha = entrada.nextLine();
                    System.out.print("Nome do curso: ");
                    Curso curso = new Curso(entrada.nextLine());
                    Coordenador coord = new Coordenador(login,senha,curso);
                    if (admSrv.cadastrarCoordenador(coord)){
                        System.out.println("Coordenador do curso de " + coord.getCurso().getNome() + " cadastrado com sucesso");
                    }
                    else{
                        System.out.println("ERRO: Usuário já existente");
                    }
                    break;
                case 2:
                    int opc_remove;
                    if (!admSrv.coordenadoresIsEmpty()){
                        System.out.println("Escolha o coordenador para removê-lo");
                        admSrv.listarCoordenadores();
                        System.out.println("0: Voltar");
                        System.out.print("Sua opção: ");
                        opc_remove = lerInteiro();
                        /* volta ao menu do ADM se houver erro de leitura, o usuario escolher voltar
                         * ou se for escolhido um numero inexistente de opção de coordenador
                        */
                        if (opc_remove <= 0 || opc_remove > adm.getCoordenadores().size()){
                            break;
                        }
                        else{
                            admSrv.apagarCoordenador(adm.getCoordenadores().get(opc_remove-1));
                            System.out.println("Usuário removido com sucesso");
                        }
                    }
                    else{
                        System.out.println("Nenhum coordenador registrado");
                    }
                    break;
                case 3:
                    if (!admSrv.coordenadoresIsEmpty()){
                        do {
                            System.out.println("Escolha um dos usuarios cadastrados para fazer login: ");
                            admSrv.listarCoordenadores();
                            /* Se o usuario ainda estiver logado no ADM ele pode retornar ao menu,
                            *  senão, ele terá que fazer login de novo (pois deslogou do adm no momento
                            *  que logou em um coordenador) */
                            if (adm.isLogado()){
                                System.out.println("0: Voltar");
                            }
                            else{
                                System.out.println("0: Fazer login no administrador");
                            }
                            System.out.print("Sua opção: ");
                            opc_login = lerInteiro();
                            // como o admin ainda esta logado ele nao precisa inserir sua senha
                            if (adm.isLogado() && opc_login <= 0 || opc_login > adm.getCoordenadores().size()){
                                break;
                            }
                            // se estiver logado em outra conta, o admin precisará fazer login de novo para retornar
                            else if (!adm.isLogado() && opc_login <= 0){
                                System.out.print("Senha do administrador " + adm.getLogin() + ": ");
                                senha = entrada.nextLine();
                                if (admSrv.fazerLogin(senha)){ // se o login no administrador foi efetuado com sucesso
                                    break;
                                }
                                else{
                                    opc_login = 0;
                                    System.out.println("ERRO: Senha incorreta. Tente novamente");
                                    continue;
                                }
                            }
                            cordSrv = new CoordenadorSrv(adm.getCoordenadores().get(opc_login-1));
                            System.out.print("Senha do usuário " + cordSrv.getCoordenador().getLogin() + ": ");
                            senha = entrada.nextLine();
                            /* Se o usuario errar a senha, retorna à listagem dos coordenadores disponíveis para logar
                            *  Se ele acertar, o coordenador faz o login e o admin desloga */
                            if (!cordSrv.fazerLogin(senha)){
                                System.out.println("ERRO: Senha incorreta. Tente novamente");
                                continue;
                            }
                            else{
                                admSrv.fazerLogout();
                            }
                            do {
                                System.out.print("Logado como " + cordSrv.getCoordenador().getLogin());
                                System.out.println(" do curso de " + cordSrv.getCoordenador().getCurso().getNome());
                                System.out.println("----- Menu do coordenador -----");
                                System.out.println("[1] Cadastrar egresso");
                                System.out.println("[2] Remover egresso");
                                System.out.println("[3] Consultar informações de egresso");
                                System.out.println("[4] Editar informações de egresso");
                                System.out.println("[5] Cadastrar formação");
                                System.out.println("[6] Cadastrar ocupação");
                                System.out.println("[7] Cadastrar depoimento");
                                System.out.println("[8] Gerar relatório");
                                System.out.println("[0] Sair");
                                System.out.print("Sua opção: ");
                                opc_coord = lerInteiro();

                                switch (opc_coord){
                                    case 1:
                                        Egresso egresso;
                                        System.out.print("Nome do egresso: ");
                                        nome = entrada.nextLine();
                                        System.out.print("Identificação (cpf): ");
                                        cpf = entrada.nextLine();
                                        System.out.print("Ano de conclusão do curso: ");
                                        ano = lerInteiro();
                                        System.out.print("Telefone: ");
                                        telefone = entrada.nextLine();
                                        Contato ctt = new Contato(telefone);
                                        egresso = new Egresso(nome,cpf,ano,ctt);
                                        if (ano != -1){
                                            if (cordSrv.cadastrarEgresso(egresso)){
                                                System.out.println("Egresso cadastrado com sucesso");
                                            }
                                            else{
                                                System.out.println("ERRO: Egresso já cadastrado");
                                            }
                                        }
                                        else{
                                            System.out.println("ERRO: dados inválidos");
                                        }
                                        break;
                                    case 2:
                                        if (!cordSrv.egressosIsEmpty()){
                                            cordSrv.listarEgressos();
                                            System.out.print("Informe o nome do egresso para removê-lo: ");
                                            nome = entrada.nextLine();
                                            temp = cordSrv.removerEgresso(nome);
                                            if (temp != null){
                                                System.out.println("Egresso " + temp.getNome() + " removido com sucesso");
                                            }
                                            else{
                                                System.out.println("ERRO: Egresso não existente");
                                            }
                                        }
                                        else{
                                            System.out.println("ERRO: Nenhum egresso registrado");
                                        }
                                        break;
                                    case 3:
                                        cordSrv.listarEgressos();
                                        System.out.print("Nome do egresso: ");
                                        nome = entrada.nextLine();
                                        temp = cordSrv.consultarEgresso(nome);
                                        if (temp != null){
                                            System.out.println(temp);
                                            System.out.println("Formações: " + temp.getFormacoes());
                                            System.out.println("Depoimentos: " + temp.getDepoimentos());
                                            System.out.println("Ocupações: " + temp.getOcupacoes());
                                        }
                                        else{
                                            System.out.println("ERRO: Egresso não encontrado");
                                        }
                                        break;
                                    case 4:
                                        int opcEdit;
                                        if (!cordSrv.egressosIsEmpty()){
                                            System.out.println("Informe o nome do egresso para editar suas informações: ");
                                            cordSrv.listarEgressos();
                                            nome = entrada.nextLine();
                                            temp = cordSrv.consultarEgresso(nome);
                                            if (temp != null){
                                                System.out.println("Editar: \n[1] nome\n[2] CPF\n[3] Ano de conclusão\n[4] contato\n");
                                                System.out.print("Sua opção: ");
                                                opcEdit = lerInteiro();
                                                if (opcEdit == 1){
                                                    temp.setNome(entrada.nextLine());
                                                }
                                                else if (opcEdit == 2){
                                                    temp.setCpf(entrada.nextLine());
                                                }
                                                else if (opcEdit == 3){
                                                    temp.setAnoConclusao(lerInteiro());
                                                }
                                                else if (opcEdit == 4){
                                                    System.out.println("[1] editar telefone\n[2] Adicionar rede social\n[3] Adicionar e-mail");
                                                    opcEdit = lerInteiro();
                                                    if (opcEdit == 1){
                                                        temp.getContato().setTelefone(entrada.nextLine());
                                                    }
                                                    else if (opcEdit == 2){
                                                        temp.getContato().setRede(entrada.nextLine());
                                                    }
                                                    else if (opcEdit == 3){
                                                        temp.getContato().setEmail(entrada.nextLine());
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("ERRO: Egresso não encontrado");
                                            }
                                        }
                                        else{
                                            System.out.println("ERRO: Nenhum egresso registrado");
                                        }
                                        break;
                                    case 5:
                                        if (!cordSrv.egressosIsEmpty()){
                                            cordSrv.listarEgressos();
                                            System.out.print("Informe o nome do egresso: ");
                                            nome = entrada.nextLine();
                                            System.out.print("Descrição da formação: ");
                                            descricao = entrada.nextLine();
                                            temp = cordSrv.consultarEgresso(nome);
                                            if (temp != null){
                                                cordSrv.cadastrarFormacao(temp,descricao);
                                                System.out.println("Formação cadastrada com sucesso");
                                            }
                                            else{
                                                System.out.println("ERRO: Egresso não encontrado");
                                            }
                                        }
                                        else{
                                            System.out.println("ERRO: Nenhum egresso registrado");
                                        }
                                        break;
                                    case 6:
                                        if (!cordSrv.egressosIsEmpty()){
                                            cordSrv.listarEgressos();
                                            System.out.print("Informe o nome do egresso: ");
                                            nome = entrada.nextLine();
                                            System.out.print("Descrição da posição: ");
                                            descricao = entrada.nextLine();
                                            System.out.print("Ano de início: ");
                                            ano = lerInteiro();
                                            System.out.print("Salario: ");
                                            salario = lerFloat();
                                            System.out.print("Lugar(empresa ou local): ");
                                            lugar = entrada.nextLine();
                                            if (ano != -1 && salario != -1){
                                                Ocupacao ocup = new Ocupacao(salario,ano,descricao,lugar);
                                                temp = cordSrv.consultarEgresso(nome);
                                                if (temp != null){
                                                    cordSrv.cadastrarOcupacao(temp,ocup);
                                                    System.out.println("Ocupação cadastrada com sucesso");
                                                }
                                                else{
                                                    System.out.println("ERRO: Egresso não encontrado");
                                                }
                                            }
                                            else{
                                                System.out.println("ERRO: Dados inválidos");
                                            }
                                        }
                                        else{
                                            System.out.println("ERRO: Nenhum egresso registrado");
                                        }
                                        break;

                                    case 7:
                                        String texto;
                                        if (!cordSrv.egressosIsEmpty()){
                                            cordSrv.listarEgressos();
                                            System.out.print("Informe o nome do aluno: ");
                                            nome = entrada.nextLine();
                                            temp = cordSrv.consultarEgresso(nome);
                                            if (temp != null){
                                                System.out.println("Cadastrando depoimento para o egresso " + temp.getNome());
                                                System.out.println("Depoimento: ");
                                                texto = entrada.nextLine();
                                                if (cordSrv.cadastrarDepoimento(temp,texto)){
                                                    System.out.println("Depoimento cadastrado com sucesso");
                                                }
                                            }
                                            else{
                                                System.out.println("ERRO: Egresso não encontrado");
                                            }
                                        }
                                        else{
                                            System.out.println("ERRO: Nenhum egresso registrado");
                                        }
                                        break;
                                    case 8:
                                        PDFGen gen;
                                        int opc_relatorio, anoInicio, anoFim;
                                        if (!cordSrv.egressosIsEmpty()){
                                            System.out.println("[1] Listagem de Egressos por ano de conclusão");
                                            System.out.println("[2] Listagem de egressos, posições e salários");
                                            System.out.print("Sua opção: ");
                                            opc_relatorio = lerInteiro();

                                            switch (opc_relatorio){
                                                case 1:
                                                    System.out.print("Ano: ");
                                                    ano = lerInteiro();
                                                    s = cordSrv.listarEgressos(ano);
                                                    gen = new PDFGen(s, cordSrv.getCoordenador().getCurso().getNome());
                                                    break;
                                                case 2:
                                                    System.out.print("Defina o intervalo:\n De ");
                                                    anoInicio = lerInteiro();
                                                    System.out.print("Até ");
                                                    anoFim = lerInteiro();
                                                    s = cordSrv.listarEgressos(anoInicio,anoFim);
                                                    gen = new PDFGen(s, cordSrv.getCoordenador().getCurso().getNome());
                                                    break;
                                            }
                                        }
                                        else{
                                            System.out.println("ERRO: Nenhum egresso registrado");
                                        }
                                        break;
                                }
                            }while (opc_coord != 0);
                            gravarEgressosTexto(cordSrv.getCoordenador().getCurso().getEgressos(),cordSrv.getCoordenador().getCurso().getNome());
                            cordSrv.fazerLogout(); // sempre que o coordenador escolhe Sair, ele desloga
                        }while(opc_login != 0);
                    }
                    else{
                        System.out.println("ERRO: Nenhum coordenador registrado");
                    }
                    break;
            }
        }while (opc != 0);
        gravarCoordenadores(adm.getCoordenadores());
        gravarAdmin(admSrv.getAdmin());
        gravarCoordenadoresTexto(adm.getCoordenadores());
    }
}