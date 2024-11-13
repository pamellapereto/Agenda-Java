package view;

import java.util.Scanner;

import controller.Contato;
import controller.Endereco;
import controller.Lista;
import controller.Reuniao;
import model.ListaDAO;
import model.ContatoDAO;
import model.ReuniaoDAO;
import model.EnderecoDAO;

public class Main {
    public static void main(String[] args) {
        inputLista();
        //inputContato();
        //inputReuniao();
    }

    private static ListaDAO listaDAO = new ListaDAO();
    private static ContatoDAO contatoDAO = new ContatoDAO();
    private static ReuniaoDAO reuniaoDAO = new ReuniaoDAO();
    private static EnderecoDAO enderecoDAO = new EnderecoDAO();

    private static Scanner scanner = new Scanner(System.in);

    private static void inputLista() {
        System.out.println("-----NOVA LISTA-----");
        System.out.println("Nome: ");
        String nome = scanner.nextLine().trim();

        /*INSERT INTO foo (auto,text)VALUES(NULL,'text');
        # generate ID by inserting NULLINSERT INTO foo2 (id,text)
        VALUES(LAST_INSERT_ID(),'text');  # use ID in second table*/

        //Validar o campo para verificar se ele está vazio.
        if(nome.isEmpty()) {
            System.out.println("Campo nome obrigatório");
        }

        else {
            Lista lista = new Lista(nome);
            boolean sucesso = listaDAO.inserirLista(lista);
            if (sucesso) {
                lista.cadastroSucesso();
            } else {
                lista.cadastroErro();
            }
        }
    }

    private static void inputContato() {
        System.out.println("----- NOVO CONTATO -----");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();
        Contato contato = new Contato(nome, telefone);
        boolean sucesso = contatoDAO.inserirContato(contato);
        if (sucesso) {
            contato.cadastroSucesso();

        } else {
            contato.cadastroErro();
        }
        System.out.println("Deseja cadastrar um endereço para o contato? \n 1-SIM \n 2-NÃO");

        int op = scanner.nextInt();
        switch (op) {
            case 1:
                inputEndereco(4);
                break;
            case 2:
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void inputReuniao() {
        System.out.println("----- NOVA REUNIÃO -----");
        System.out.println("Data: ");
        String dataReuniao = scanner.nextLine();
        System.out.println("Hora: ");
        String horaReuniao = scanner.nextLine();
        System.out.println("Link: ");
        String link = scanner.nextLine();
        Reuniao reuniao = new Reuniao(dataReuniao, horaReuniao, link);
        boolean sucesso = reuniaoDAO.inserirReuniao(reuniao);
        if (sucesso) {
            reuniao.cadastroSucesso();
        } else {
            reuniao.cadastroErro();
        }
    }


    private static void inputEndereco(int idfk) {
        System.out.println("----- NOVO ENDEREÇO -----");
        System.out.println("Rua: ");
        String rua = scanner.nextLine();
        System.out.println("Número: ");
        String numero = scanner.nextLine();
        System.out.println("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Estado: ");
        String estado = scanner.nextLine();
        System.out.println("CEP: ");
        String cep = scanner.nextLine();

        Endereco endereco = new Endereco(rua, numero, cidade, estado, cep, idfk);
        boolean sucesso = enderecoDAO.inserirEndereco(endereco);
        if (sucesso) {
            endereco.cadastroSucesso();
        } else {
            endereco.cadastroErro();
        }
    }
}