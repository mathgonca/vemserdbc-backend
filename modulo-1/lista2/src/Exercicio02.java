import java.util.Random;
import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int palpite = 0;

        Random random = new Random();
        int numero = random.nextInt(10) + 1;

        do {
            System.out.print("Adivinhe o número: ");
            palpite = Integer.parseInt(scanner.nextLine());

            if (numero == palpite) {
                System.out.println("Acertou!");
            }

            if (palpite < numero) {
                System.out.println("Mais");
            }

            if (palpite > numero) {
                System.out.println("Menos");
            }

        } while (numero != palpite);
    }

}
