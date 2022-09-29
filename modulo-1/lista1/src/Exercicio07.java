import java.util.Scanner;

public class Exercicio07 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o código do produto: ");
    String codigo = scanner.nextLine();
    double valor = 0;

    if (codigo.equals("ABCD")) {
      valor = 5.3;
    } else if (codigo.equals("XYPK")) {
      valor = 6;
    } else if (codigo.equals("KLMP")) {
      valor = 3.2;
    } else if (codigo.equals("QRST")) {
      valor = 2.5;
    }

    if (valor == 0) {
      System.out.println("Código Inválido");
    } else {
      System.out.println("Código: " + codigo + "\nValor: R$ " + valor);
    }
  }
}
