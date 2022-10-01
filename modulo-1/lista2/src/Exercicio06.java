public class Exercicio06 {
    public static void main(String[] args) {
        float[][] notas = {{5, 6, 7, 8}, {5, 6, 7, 8}, {5, 6, 7, 8}, {5, 6, 7, 8}, {5, 6, 7, 8}};
        float[] mediaDisciplinas = new float[4];
        float somaDasMedias = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mediaDisciplinas[i] += notas[j][i];
            }

            mediaDisciplinas[i] /= 5;
            somaDasMedias += mediaDisciplinas[i];

            System.out.println("Média disciplina 0" + (i + 1) + ": " + mediaDisciplinas[i]);
        }

        float mediaGeral = somaDasMedias / mediaDisciplinas.length;

        System.out.println("--------------------------");
        System.out.println("Média geral: " + mediaGeral);
    }
}
