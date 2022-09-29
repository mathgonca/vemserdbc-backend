import java.util.Scanner;

public class Exercicio08 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o seu cargo: ");
    String cargo = scanner.nextLine();

    System.out.print("Digite o seu salario: ");
    double salario = Double.parseDouble(scanner.nextLine());

    double aumento;

    if(cargo.equals("Gerente")) {
      aumento = salario * 10 / 100;
    } else if (cargo.equals("Engenheiro")) {
      aumento = salario * 20 / 100;
    } else if (cargo.equals("Tecnico")) {
      aumento= salario * 30 / 100;
    } else {
      aumento = salario * 40 / 100;
    }

    double salarioNovo = salario + aumento;
    System.out.println("Salario Antigo: R$ " + salario + "\nSalario novo: R$ " + salarioNovo + "\nDiferença: R$ " + aumento);
  }
}
