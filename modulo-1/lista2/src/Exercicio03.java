import java.util.Scanner;

public class Exercicio03 {

    public static float achaJogadorMaisAlto(float[] alturaArray) {
        float maiorAltura = 0;

        for (float altura: alturaArray) {
            if (altura > maiorAltura) {
                maiorAltura = altura;
            }
        }

        return maiorAltura;
    }

    public static int achaJogadorMaisVelho(int[] idadesArray) {
        int maiorIdade = 0;

        for (int idade: idadesArray) {
            if (idade > maiorIdade) {
                maiorIdade = idade;
            }
        }

        return maiorIdade;
    }

    public static float achaJogadorMaisPesado(float[] pesosArray) {
        float maiorPeso = 0;

        for (float peso: pesosArray) {
            if (peso > maiorPeso) {
                maiorPeso = peso;
            }
        }

        return maiorPeso;
    }

    public static int calculaMediaIdades(int[] idadesArray, int index) {
        int totalIdades = 0;

        for (int idade: idadesArray) {
            totalIdades += idade;
        }

        return totalIdades / index;
    }

    public static void main(String[] args) {
        String[] nomesJogadores = new String[25];
        float[] alturasJogadores = new float[25];
        int[] idadesJogadores = new int[25];
        float[] pesosJogadores = new float[25];

        Scanner scanner = new Scanner(System.in);

        String nome;
        float altura;
        int idade;
        float peso;

        int index = 0;

        while (true) {
            System.out.print("Digite o nome do Jogador: ");
            nome = scanner.nextLine();

            if (nome.equals("SAIR")) {
                break;
            }

            nomesJogadores[index] = nome;

            System.out.print("Digite a altura do jogador: ");
            altura = Float.parseFloat(scanner.nextLine());
            alturasJogadores[index] = altura;

            System.out.print("Digite a idade do jogador: ");
            idade = Integer.parseInt(scanner.nextLine());
            idadesJogadores[index] = idade;

            System.out.print("Digite o peso do jogador: ");
            peso = Float.parseFloat(scanner.nextLine());
            pesosJogadores[index] = peso;

            index++;
        }

        int totalDeJogadores = index;
        float jogadorMaisAlto = achaJogadorMaisAlto(alturasJogadores);
        int jogadorMaisVelho = achaJogadorMaisVelho(idadesJogadores);
        float jogadorMaisPesado = achaJogadorMaisPesado(pesosJogadores);
        int mediaDeIdade = calculaMediaIdades(idadesJogadores, index);

        System.out.println("Quantidade de jogadores cadastrados: " + totalDeJogadores);
        System.out.println("Alturado do maior jogador: " + jogadorMaisAlto);
        System.out.println("Jogador mais velho: " + jogadorMaisVelho);
        System.out.println("Jogador mais pesado: " + jogadorMaisPesado);
        System.out.println("Média das alturas jogadores: " + mediaDeIdade);
    }
}
