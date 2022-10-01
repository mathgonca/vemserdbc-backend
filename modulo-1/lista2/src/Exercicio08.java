import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numeros = {26, 44, 75, 12, 55, 34, 91, 9, 34, 3};

        System.out.println("Array de números:");
        System.out.println("----------------------------");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }

        System.out.println("\n\nEscolha um número:");
        System.out.println("----------------------------");
        int numeroEscolhido = Integer.parseInt(scanner.nextLine());

        int repeticoesNoArray = 0;
        int numerosMenores = 0;
        int numerosMaiores = 0;

        for (int numero : numeros) {
            if (numero == numeroEscolhido) {
                repeticoesNoArray++;
            }

            if (numero > numeroEscolhido) {
                numerosMaiores++;
            }

            if (numero < numeroEscolhido) {
                numerosMenores++;
            }
        }

        System.out.println("Quantas vezes o número digitado existe no vetor: " + repeticoesNoArray);
        System.out.println("Quantos números menores que o número digitado: " + numerosMenores);
        System.out.println("Quantos números maiores que o número digitado: " + numerosMaiores);
    }
}
