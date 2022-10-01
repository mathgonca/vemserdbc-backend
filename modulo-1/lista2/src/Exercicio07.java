import java.util.Scanner;

public class Exercicio07 {
    public static void main(String[] args) {
        // Mercado 01 mais barato
        float[][] produtos = {{4, 5, 6}, {4, 5, 6}, {4, 5, 6}, {4, 5, 6}, {4, 5, 6}, {4, 5, 6}, {4, 5, 6}, {4, 5, 6}, {4, 5, 6}, {4, 5, 6}};

        // Mercado 02 mais barato
        // float[][] produtos = {{5, 4, 6}, {5, 4, 6}, {5, 4, 6}, {5, 4, 6}, {5, 4, 6}, {5, 4, 6}, {5, 4, 6}, {5, 4, 6}, {5, 4, 6}, {5, 4, 6}};

        // Mercado 03 mais barato
        // float[][] produtos = {{6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}};


        float[] precoTotal = new float[3];

        float menorPrecoTotal = 0;
        int indexMenorPreco = 0;

        for (int i = 0; i < 3; i++) {


            for (int j = 0; j < 10; j++) {
                precoTotal[i] += produtos[j][i];
            }

            if (i == 0 || precoTotal[i] < menorPrecoTotal) {
                menorPrecoTotal = precoTotal[i];
                indexMenorPreco = i + 1;
            }
        }

        System.out.println("O mercado mais barato é o mercado: Mercado 0" + indexMenorPreco);
    }
}
