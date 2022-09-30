import java.util.Scanner;

public class Exercicio07 {
    public static void main(String[] args) {
        String[] mercados = {"mercado01", "mercado02", "mercado03"};
        float[] precoFinal = new float[3];
        float[][] precoProdutos = new float[9][3];

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Digite o preco do produto 0" + (i + 1) + " no mercado 0" + (j + 1) + ": ");
                float precoCache = Float.parseFloat(scanner.nextLine());
                precoProdutos[i][j] = precoCache;
            }
        }

        for (int i = 0; i < 3; i++) {
            float precoFinalCache = 0;

            for (int j = 0; j < 9; j++) {
                precoFinalCache += precoProdutos[j][i];
            }

            precoFinal[i] = precoFinalCache;
        }

        float mercadoMaisBarato = 0;
        int mercadoMaisBaratoIndex = 0;

        for (int i = 0; i < 3; i++) {
            if (i == 0 || precoFinal[i] < mercadoMaisBarato) {
                mercadoMaisBarato = precoFinal[i];
                mercadoMaisBaratoIndex = i;
            }
        }

        System.out.println("Mercado mais barato para suas compras: " + mercados[mercadoMaisBaratoIndex]);
    }
}

