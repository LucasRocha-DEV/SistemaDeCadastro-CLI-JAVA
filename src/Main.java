import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

class Usuario {
    String nome;
    String email;
    int idade;
    double altura;

    Usuario(String nome, String email, int idade, double altura) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return nome + "\n" + email + "\n" + idade + "\n" + altura;
    }
}

public class Main {
    public static void main(String[] args) {
        Usuario usuario = cadastrarUsuario();
        imprimirUsuario(usuario);
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
}