import java.util.Scanner;

public class Exercicio05 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite a base do retângulo: ");
    int base = Integer.parseInt(scanner.nextLine());

    System.out.print("Digite a altura do retângulo: ");
    int altura = Integer.parseInt(scanner.nextLine());

    float areaDoRetangulo = base * altura;
    System.out.println("Aréa do retângulo = " + areaDoRetangulo);
  }
}
