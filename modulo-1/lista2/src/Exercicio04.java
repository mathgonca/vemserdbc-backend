import java.util.Scanner;

public class Exercicio04 {
    public static void main(String[] args) {
//        int[] teste = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        int[] valores = new int[20];
        int valor;

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 20; i++) {
            System.out.print("Digite o valor " + (i + 1) + ": ");
            valor = Integer.parseInt(scanner.nextLine());

            valores[i] = valor;
        }

        for (int i = valores.length - 1; i >= 0; i--) {
            System.out.print(valores[i] + " ");
        }
    }
}
