import java.util.Scanner;

public class Exercicio05 {
    public static void main(String[] args) {
        final int MAXIMO_ALUNOS = 5;
        final int MATRICULA = 0;
        final int MEDIA_PROVAS = 1;
        final int MEDIA_TRABALHOS = 2;
        final int NOTA_FINAL = 3;

        Scanner scanner = new Scanner(System.in);

        int[][] alunos = new int[5][4];

        int index = 0;
        int matricula = 0;
        int mediaProvas = 0;
        int mediaTrabalhos = 0;

        while (index < MAXIMO_ALUNOS) {
            System.out.println("\nALUNO 0" + (index + 1));
            System.out.println("------------------------------");
            System.out.print("Digite a matriculado aluno: ");
            matricula = Integer.parseInt(scanner.nextLine());
            alunos[index][MATRICULA] = matricula;

            System.out.print("Digite a média das provas: ");
            mediaProvas = Integer.parseInt(scanner.nextLine());
            alunos[index][MEDIA_PROVAS] = mediaProvas;

            System.out.print("Digite a média dos trabalhos: ");
            mediaTrabalhos = Integer.parseInt(scanner.nextLine());
            alunos[index][MEDIA_TRABALHOS] = mediaTrabalhos;

            alunos[index][NOTA_FINAL] = (int) (mediaProvas * 0.6f + mediaTrabalhos * 0.4f);

            index++;
        }

        int maiorNotaFinal = 0;
        int matriculaMaiorNota = 0;

        for (int i = 0; i < MAXIMO_ALUNOS; i++) {
            if (alunos[i][NOTA_FINAL] > maiorNotaFinal) {
                maiorNotaFinal = alunos[i][NOTA_FINAL];
                matriculaMaiorNota = alunos[i][NOTA_FINAL];
            }
        }

        int totalNotasFinais = 0;

        for (int i = 0; i < MAXIMO_ALUNOS; i++) {
            totalNotasFinais += alunos[i][NOTA_FINAL];
        }

        int mediaNotasFinais = totalNotasFinais / MAXIMO_ALUNOS;

        System.out.println("\n---------------------------------------------------");
        System.out.println("Matricula com maior nota final: " + matriculaMaiorNota);
        System.out.println("Média das notas finais: " + mediaNotasFinais);
    }
}
