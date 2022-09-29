import java.util.Scanner;

public class Exercicio01 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o seu nome: ");
    String nome = scanner.nextLine();

    System.out.print("Digite a sua idade: ");
    int idade = Integer.parseInt(scanner.nextLine());

    System.out.print("Digite a sua cidade: ");
    String cidade = scanner.nextLine();

    System.out.print("Digite o seu estado: ");
    String estado = scanner.nextLine();

    System.out.print(
        "Olá seu nome é "
            + nome
            + ", você tem "
            + idade
            + " anos, é da cidade de "
            + cidade
            + ", situada no estado de "
            + estado
            + ".");
  }
}
