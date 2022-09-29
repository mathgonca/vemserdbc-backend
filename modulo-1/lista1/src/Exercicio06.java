import java.util.Scanner;

public class Exercicio06 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o total de eleitores: ");
    int totalDeEleitores = Integer.parseInt(scanner.nextLine());

    System.out.print("Digite numero de votos brancos: ");
    int votosBrancos = Integer.parseInt(scanner.nextLine());

    System.out.print("Digite numero de votos nulos: ");
    int votosNulos = Integer.parseInt(scanner.nextLine());

    System.out.print("Digite numero de votos validos: ");
    int votosValidos = Integer.parseInt(scanner.nextLine());

    int votosBrancosPorcentos = (votosBrancos * totalDeEleitores) / 100;
    int votosNulosPorcentos = (votosNulos * totalDeEleitores) / 100;
    int votosValidosPorcentos = (votosValidos * totalDeEleitores) / 100;

    System.out.println(
        "Total de votos brancos = "
            + votosBrancosPorcentos
            + "%\nTotal de votos nulos = "
            + votosNulosPorcentos
            + "%\nTotal de votos validos = "
            + votosValidosPorcentos
            + "%");
  }
}
