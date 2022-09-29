import java.util.Scanner;

public class Exercicio08 {
  public static void main(String[] args) {
    final int GERENTE = 101;
    final int ENGENHEIRO = 102;
    final int TECNICO = 103;

    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o seu cargo: ");
    int cargo = Integer.parseInt(scanner.nextLine());

    System.out.print("Digite o seu salario: ");
    double salario = Double.parseDouble(scanner.nextLine());

    double aumento;

    if(cargo == GERENTE) {
      aumento = salario * 10 / 100;
    } else if (cargo == ENGENHEIRO) {
      aumento = salario * 20 / 100;
    } else if (cargo == TECNICO) {
      aumento= salario * 30 / 100;
    } else {
      aumento = salario * 40 / 100;
    }

    double salarioNovo = salario + aumento;
    System.out.println("Salario Antigo: R$ " + salario + "\nSalario novo: R$ " + salarioNovo + "\nDiferença: R$ " + aumento);
  }
}
