import java.util.Scanner;

public class Exercicio01 {
    public static void main(String[] args) {
        final double DESCONTO_BASE = 5;


        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do produto: ");
        String produto = scanner.nextLine();

        System.out.print("Digite o preço: ");
        double preco = Float.parseFloat(scanner.nextLine());

        double desconto;
        double precoComDesconto;

        System.out.println("\nPromoção: " + produto);
        System.out.println("----------------------------------");

        for (int i = 1; i <= 10; i++) {
            desconto = (DESCONTO_BASE * i / 100) * preco;
            precoComDesconto = preco - desconto;

            System.out.println(i + "x R$ " + String.format("%.2f", precoComDesconto) + " = R$ "
                    + String.format("%.2f", precoComDesconto * i));
        }
    }
}
