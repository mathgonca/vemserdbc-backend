import java.util.Scanner;

public class Exercicio02 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Escolha um estado:\n1 - RS\n2 - SC\n3 - PR");
    int estado = Integer.parseInt(scanner.nextLine());

    int cidade = 0;

    switch (estado) {
      case 1:
        System.out.println("1 - Canoas 2 - POA");
        cidade = Integer.parseInt(scanner.nextLine());
        switch (cidade) {
          case 1:
            System.out.println("Canoas");
            System.out.println("População: 348,208 (2020)\nPrincipal festa: Não tem\nIDH: 0,815");
            break;
          case 2:
            System.out.println("POA");
            System.out.println("População: 1.492.530\nPrincipal festa: Feira do Livro\nIDH: 0,805");
            break;
        }
        break;
      case 2:
        System.out.println("1 - Floripa 2 - Blumenal");
        cidade = Integer.parseInt(scanner.nextLine());
        switch (cidade) {
          case 1:
            System.out.println("Floripa");
            System.out.println(
                "População: 508,826 (2020)\nPrincipal festa: Oktobertanz, em São Pedro de Alcântara.\nIDH: 0,847");
            break;
          case 2:
            System.out.println("Blumenau");
            System.out.println("População: 361,855 (2020)\nPrincipal festa: Oktoberfest (Blumenau)\nIDH: 0,806");
            break;
        }
        break;
      case 3:
        System.out.println("1- Curitiba 2 - Londrina");
        cidade = Integer.parseInt(scanner.nextLine());
        switch (cidade) {
          case 1:
            System.out.println("Curitiba");
            System.out.println("População: 1.963.726\nPrincipal festa: Festa Nossa Senhora do Rocio\nIDH: 0,823");
            break;
          case 2:
            System.out.println("Londrina");
            System.out.println("População: 575,377 (Jul 1, 2020)\nPrincipal festa: Fandango\nIDH: 0,778");
            break;
        }
        break;
      default:
        System.out.println("Opcao invalida!");
    }
  }
}
