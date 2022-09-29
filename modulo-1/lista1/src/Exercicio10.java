import java.util.Scanner;

public class Exercicio10 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Digite o numero de verificacao: ");
    String numeroDeIdentificacao = scanner.nextLine();

    System.out.println("Digite a nota 1: ");
    float nota1 = Float.parseFloat(scanner.nextLine());

    System.out.println("Digite a nota 2: ");
    float nota2 = Float.parseFloat(scanner.nextLine());

    System.out.println("Digite a nota 3");
    float nota3 = Float.parseFloat(scanner.nextLine());

    System.out.println("Digite a nota media dos exercicios: ");
    float mediaExercicios = Float.parseFloat(scanner.nextLine());

    float mediaDeAproveitamento = (nota1 + nota2 * 2 + nota3 * 3 + mediaExercicios) / 7;

    char conceito = 0;

    if (mediaDeAproveitamento >= 9) {
      conceito = 'A';
    } else if (mediaExercicios > 7.5 && mediaExercicios < 9) {
      conceito = 'B';
    } else if (mediaExercicios > 6 && mediaExercicios < 7.5) {
      conceito = 'C';
    } else if (mediaExercicios > 4 && mediaExercicios < 6) {
      conceito = 'D';
    } else if (mediaExercicios < 4) {
      conceito = 'E';
    }

    String mensagem = null;

    if (conceito == 'A' || conceito == 'B' || conceito == 'C') {
      mensagem = "Aprovado";
    } else if (conceito == 'D' || conceito == 'E') {
      mensagem = "Reprovado";
    }

    System.out.println(
        "Nota 1: "
            + nota1
            + "\nNota 2: "
            + nota2
            + "\nNota 3: "
            + nota3
            + "\nMedia dos exercicio: "
            + mediaExercicios
            + "\nMedia Aproveitamento: "
            + mediaDeAproveitamento
            + "\nConceito: "
            + conceito
            + "\nSituacao: "
            + mensagem);
  }
}
