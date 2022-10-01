import java.util.Scanner;

public class Exercicio03 {

    public static void main(String[] args) {
        String[] nomesJogadores = new String[10];
        float[] alturasJogadores = new float[10];
        int[] idadesJogadores = new int[10];
        float[] pesosJogadores = new float[10];

        Scanner scanner = new Scanner(System.in);

        String nome;

        int index = 0;

        int indexJogadorMaisAlto = 0;
        float alturaJogadorMaisAlto = 0;

        int indexJogadorMaisVelho = 0;
        int idadeJogadorMaisVelho = 0;

        int indexJogadorMaisPesado = 0;
        float pesoJogadorMaisPesado = 0;

        float somaDasAlturas = 0;

        while (true) {
            System.out.println("\nJOGADOR " + (index + 1));
            System.out.println("----------------");
            System.out.print("Digite o nome do Jogador: ");
            nome = scanner.nextLine();

            if (nome.equalsIgnoreCase("SAIR")) {
                break;
            }

            nomesJogadores[index] = nome;

            System.out.print("Digite a altura do jogador: ");
            alturasJogadores[index] = Float.parseFloat(scanner.nextLine());

            System.out.print("Digite a idade do jogador: ");
            idadesJogadores[index] = Integer.parseInt(scanner.nextLine());

            System.out.print("Digite o peso do jogador: ");
            pesosJogadores[index] = Float.parseFloat(scanner.nextLine());

            if (index == 0 || alturasJogadores[index] > alturaJogadorMaisAlto) {
                alturaJogadorMaisAlto = alturasJogadores[index];
                indexJogadorMaisAlto = index;
            }

            if (index == 0 || idadesJogadores[index] > idadeJogadorMaisVelho) {
                idadeJogadorMaisVelho = idadesJogadores[index];
                indexJogadorMaisVelho = index;
            }

            if (index == 0 || pesosJogadores[index] > pesoJogadorMaisPesado) {
                pesoJogadorMaisPesado = pesosJogadores[index];
                indexJogadorMaisPesado = index;
            }

            somaDasAlturas += alturasJogadores[index];

            index++;
        }

        int totalDeJogadores = index;
        String jogadorMaisAlto = nomesJogadores[indexJogadorMaisAlto];
        String jogadorMaisVelho = nomesJogadores[indexJogadorMaisVelho];
        String jogadorMaisPesado = nomesJogadores[indexJogadorMaisPesado];
        float mediaDeAlturas = somaDasAlturas / index;

        System.out.println("Quantidade de jogadores cadastrados: " + totalDeJogadores);
        System.out.println("Altura do maior jogador: " + jogadorMaisAlto);
        System.out.println("Jogador mais velho: " + jogadorMaisVelho);
        System.out.println("Jogador mais pesado: " + jogadorMaisPesado);
        System.out.println("Média das alturas jogadores: " + String.format("%.2f", mediaDeAlturas));
    }
}
