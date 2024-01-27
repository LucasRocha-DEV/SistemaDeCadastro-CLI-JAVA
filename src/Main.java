import java.io.File;
import java.io.FilenameFilter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1 - Cadastrar o usuário");
            System.out.println("2 - Listar todos usuários cadastrados");
            System.out.println("3 - Cadastrar nova pergunta no formulário");
            System.out.println("4 - Deletar pergunta do formulário");
            System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    Usuario usuario = cadastrarUsuario();
                    imprimirUsuario(usuario);
                    salvarUsuarioEmArquivo(usuario, 1);
                    break;
                case 2:
                    listarUsuariosCadastrados();
                    break;
                case 3:
                    System.out.print("Digite a nova pergunta: ");
                    scanner.nextLine();  // Consume newline left-over
                    String pergunta = scanner.nextLine();
                    adicionarPergunta(pergunta);
                    break;
                case 4:
                    System.out.print("Digite o número da pergunta a ser deletada: ");
                    int numeroPergunta = scanner.nextInt();
                    deletarPergunta(numeroPergunta);
                    break;
                case 5:
                    // Implementar pesquisa de usuário
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    public static Usuario cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual seu nome completo?");
        String nome = scanner.nextLine();

        System.out.println("Qual seu email de contato?");
        String email = scanner.nextLine();

        System.out.println("Qual sua idade?");
        int idade = scanner.nextInt();

        System.out.println("Qual sua altura?");
        double altura = scanner.nextDouble();

        return new Usuario(nome, email, idade, altura);
    }

    public static void imprimirUsuario(Usuario usuario) {
        System.out.println(usuario);
    }

    public static void salvarUsuarioEmArquivo(Usuario usuario, int id) {
        try {
            PrintWriter writer = new PrintWriter(id + "-" + usuario.nome.toUpperCase() + ".TXT", "UTF-8");
            writer.println(usuario);
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar o usuário em um arquivo.");
            e.printStackTrace();
        }
    }

    public static void listarUsuariosCadastrados() {
        File dir = new File(".");
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".TXT");
            }
        });

        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                System.out.println(scanner.nextLine());
                scanner.close();
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao ler o arquivo do usuário.");
                e.printStackTrace();
            }
        }
    }
    public static void adicionarPergunta(String pergunta) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("formulario.txt"), true));
            writer.append(pergunta + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao adicionar a pergunta ao formulário.");
            e.printStackTrace();
        }
    }

    public static void deletarPergunta(int numeroPergunta) {
        if (numeroPergunta <= 4) {
            System.out.println("Não é possível deletar as 4 primeiras perguntas.");
            return;
        }

        List<String> perguntas = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("formulario.txt"));
            while (scanner.hasNextLine()) {
                perguntas.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o formulário.");
            e.printStackTrace();
        }

        if (numeroPergunta - 1 < perguntas.size()) {
            perguntas.remove(numeroPergunta - 1);
        } else {
            System.out.println("Não existe pergunta com o número fornecido.");
            return;
        }

        try {
            PrintWriter writer = new PrintWriter("formulario.txt", "UTF-8");
            for (String pergunta : perguntas) {
                writer.println(pergunta);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao reescrever o formulário.");
            e.printStackTrace();
        }
    }
}