import java.util.Scanner;

public class Exercicio05 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite a base do triângulo: ");
    int base = Integer.parseInt(scanner.nextLine());

    System.out.print("Digite a altura do triângulo: ");
    int altura = Integer.parseInt(scanner.nextLine());

    float areaDoTriangulo = (base * altura) / 2;
    System.out.println("Aréa do triângulo = " + areaDoTriangulo);
  }
}
