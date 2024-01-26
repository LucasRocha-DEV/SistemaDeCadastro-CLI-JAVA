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