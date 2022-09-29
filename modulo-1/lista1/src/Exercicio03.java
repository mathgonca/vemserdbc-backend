import java.util.Scanner;

public class Exercicio03 {
  public static void main(String[] args) {
    final int PORTUGUES = 1;
    final int INGLES = 2;

    Scanner scanner = new Scanner(System.in);

    System.out.println("Português -> Inglês - Digite 1\nInglês -> Português - Digite - 2");
    int opcao = Integer.parseInt(scanner.nextLine());

    String traducao = null;

    if (opcao == PORTUGUES) {
      System.out.println("Digite a palavra: ");
      String palavra = scanner.nextLine();

      switch (palavra) {
        case "Cachorro":
          traducao = "Dog";
          break;
        case "Tempo":
          traducao = "Time";
          break;
        case "Amor":
          traducao = "Love";
          break;
        case "Cidade":
          traducao = "City";
          break;
        case "Feliz":
          traducao = "Happy";
          break;
        case "Triste":
          traducao = "Sad";
          break;
        case "Deveria":
          traducao = "Should";
          break;
        case "Poderia":
          traducao = "Could";
          break;
        default:
          System.out.println("Essa palavra não válida.");
      }
    } else if (opcao == INGLES) {
      System.out.println("Digite a palavra: ");
      String palavra = scanner.nextLine();

      switch (palavra) {
        case "Dog":
          traducao = "Cachorro";
          break;
        case "Time":
          traducao = "Tempo";
          break;
        case "Love":
          traducao = "Amor";
          break;
        case "City":
          traducao = "Cidade";
          break;
        case "Happy":
          traducao = "Feliz";
          break;
        case "Sad":
          traducao = "Triste";
          break;
        case "Should":
          traducao = "Deveria";
          break;
        case "Could":
          traducao = "Poderia";
          break;
        default:
          System.out.println("Essa palavra não válida.");
      }
    }

    System.out.println("Traducao: " + traducao);
  }
}
