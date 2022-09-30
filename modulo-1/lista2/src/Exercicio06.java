import java.util.Scanner;

public class Exercicio06 {
    public static void main(String[] args) {
        String[] disciplinas = new String[4];
        float[][] notas = new float[4][4];
        float[] mediaDisciplinas = new float[4];

        Scanner scanner = new Scanner(System.in);

        String disciplina;
        float mediaDisciplina = 0;

        for (int i = 0; i < 4; i++) {
            float notaSoma = 0;

            System.out.print("Digite o nome da disciplina: ");
            disciplina = scanner.nextLine();
            disciplinas[i] = disciplina;

            for (int j = 0; j < 4; j++) {
                System.out.print("Digite a nota " + (j + 1) + ": ");
                float notaCache = Float.parseFloat(scanner.nextLine());
                notas[i][j] = notaCache;

                notaSoma += notaCache;
            }

            mediaDisciplinas[i] = notaSoma / 4;
        }

        for (float medias: mediaDisciplinas) {
            mediaDisciplina += medias;
        }

        mediaDisciplina /= 4;

        for (int i = 0; i < 4; i++) {
            System.out.println(disciplinas[i] + " média: " + mediaDisciplinas[i]);
        }

        float mediaGeral = 0;

        for (float media: mediaDisciplinas) {
            mediaGeral += media;
        }

        mediaGeral /= 4;

        System.out.println("Média geral: " + mediaGeral);
    }
}
