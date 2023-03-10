package application;

import controller.Entidade;
import entities.Campanha;
import entities.Doacao;
import entities.Item;
import entities.Meta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/* Autor do código: Breno Roberto Reis Vidigal */
public class Program {
    public static int lerInteiro(){
        int num;
        Scanner input = new Scanner(System.in);
        try{
            num = Integer.parseInt(input.nextLine());
            return num;
        }
        catch(NumberFormatException e){
            return -1;
        }
    }

    public static double lerDouble(){
        double num;
        Scanner input = new Scanner(System.in);
        try{
            num = Double.parseDouble(input.nextLine());
            return num;
        }
        catch(NumberFormatException e){
            return -1;
        }
    }


    public static void main(String[] args) {
        int opc;
        Scanner input = new Scanner(System.in);
        Map<String, Item> itens = new Hashtable<>();
        System.out.print("Informe o nome da entidade: ");
        Entidade entidade = new Entidade(input.nextLine());

        do{
            System.out.println("Entidade " + entidade.getNome());
            System.out.println("[1] Cadastrar nova campanha");
            System.out.println("[2] Consultar campanhas existentes");
            System.out.println("[3] Fazer doação à entidade");
            System.out.println("[4] Fechar campanha");
            System.out.println("[5] Visualizar doações");
            System.out.println("[0] Sair");
            System.out.print("Sua opção: ");
            opc = lerInteiro();

            switch (opc){
                case 1:
                    Campanha campanha;
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("Informe o nome da campanha: ");
                    String nomeCampanha = input.nextLine();
                    System.out.println("Data prazo: (dia/mes/ano) ");
                    String data = input.nextLine();

                    try{
                        campanha = new Campanha(nomeCampanha, LocalDate.parse(data, fmt));
                    }
                    catch(DateTimeParseException e){
                        System.out.println("Formato inválido para data.");
                        break;
                    }

                    entidade.abrirCampanha(campanha);
                    System.out.println("Deseja cadastrar quantas metas?");
                    int opc_metas = lerInteiro();

                    for (int i = 0; i < opc_metas; i++){
                        System.out.println("Informe os dados do item #" + (i + 1));
                        System.out.println("Nome: ");
                        String nomeItem = input.nextLine();
                        System.out.println("Quantidade da meta: ");
                        double quantidade = lerDouble();
                        System.out.println("Unidade: ");
                        String unidade = input.nextLine();
                        Item item = new Item(nomeItem.toLowerCase(), unidade);
                        itens.put(nomeItem.toLowerCase(), item);

                        try{
                            campanha.addMeta(new Meta(item,quantidade));
                        }
                        catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 2:
                    try{
                        entidade.listarCampanhas();
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    Item item; double quantidade;
                    System.out.println("Nome do doador: ");
                    String nomeDoador = input.nextLine();
                    System.out.println("Precisamos dos seguintes itens ");

                    try{
                        entidade.listarItens();
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Digite o nome do item que deseja doar: ");
                    String nomeItem = input.nextLine();
                    item = itens.get(nomeItem.toLowerCase());

                    if (item != null){
                        System.out.print("Quantidade " + "(em " + item.getUnidade() + ") : ");
                        quantidade = lerDouble();
                    }
                    else{
                        System.out.println("Item inválido");
                        break;
                    }
                    try{
                        entidade.doar(new Doacao(item, quantidade, nomeDoador.toUpperCase()));
                    }
                    catch(IllegalAccessError e){
                        System.out.println("ERRO: " + e.getMessage());
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;

                case 4:
                    try{
                        entidade.listarCampanhas();
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Escolha uma campanha que já esteja completa para removê-la: ");
                    int opc_fechar = lerInteiro();

                    try{
                        entidade.fecharCampanha(entidade.getCampanhas().get(opc_fechar));
                        System.out.println("Campanha fechada com sucesso");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Escolha uma campanha válida!");
                    }
                    break;
                case 5:
                    try{
                        entidade.listarDoacoes();
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
            }
        }while (opc != 0);
    }
}
