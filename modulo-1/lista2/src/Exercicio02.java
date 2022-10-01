import java.util.Random;
import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int palpite = 0;

        Random random = new Random();
        int numero = random.nextInt(10) + 1;

        System.out.println("Olá, pensei em um número! Vamos ver se você consegue adivinhar.");
        System.out.println("---------------------------------------------------------------");
        do {
            System.out.print("Adivinhe o número: ");
            palpite = Integer.parseInt(scanner.nextLine());

            String dica = null;

            if (numero == palpite) {
                System.out.println("Acertou!");
            } else {
                if (palpite < numero) {
                    dica = "Mais que " + palpite + ".";
                }

                if (palpite > numero) {
                    dica = "Menos que " + palpite + ".";
                }

                System.out.println("Errou! " + dica);
            }
        } while (numero != palpite);
    }
}
