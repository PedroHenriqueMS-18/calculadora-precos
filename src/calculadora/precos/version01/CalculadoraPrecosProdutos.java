package calculadora.precos.version01;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class CalculadoraPrecosProdutos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        double porcentagemBruta = 0, porcentagemPerda = 0;
        int opcao = 0;

        while (true) {
            try {
                System.out.println("\n--- MENU DE CATEGORIAS ---");
                System.out.println("1) Commodities  2) Limpeza  3) Higiene  4) Hortifruti");
                System.out.println("5) Açougue      6) Bebidas  7) Padaria  8) Bomboniere");
                System.out.print("Digite o número da categoria (1-8): ");
                opcao = sc.nextInt();

                if (opcao >= 1 && opcao <= 8) break;
                System.err.println("Erro: Categoria inexistente!");
            } catch (InputMismatchException e) {
                System.err.println("Erro: Digite apenas o NÚMERO da categoria.");
                sc.next();
            }
        }

        switch (opcao) {
            case 1 -> { porcentagemBruta = lerValorValidado(sc, 10, 15, "porcentagem bruta"); porcentagemPerda = lerValorValidado(sc, 2, 5, "margem de perda"); }
            case 2, 3 -> { porcentagemBruta = lerValorValidado(sc, 20, 30, "porcentagem bruta"); porcentagemPerda = lerValorValidado(sc, 1, 3, "margem de perda"); }
            case 4 -> { porcentagemBruta = lerValorValidado(sc, 35, 50, "porcentagem bruta"); porcentagemPerda = lerValorValidado(sc, 7, 12, "margem de perda"); }
            case 5 -> { porcentagemBruta = lerValorValidado(sc, 35, 50, "porcentagem bruta"); porcentagemPerda = lerValorValidado(sc, 2, 4, "margem de perda"); }
            case 6 -> { porcentagemBruta = lerValorValidado(sc, 30, 45, "porcentagem bruta"); porcentagemPerda = lerValorValidado(sc, 1, 5, "margem de perda"); }
            case 7 -> { porcentagemBruta = lerValorValidado(sc, 30, 45, "porcentagem bruta"); porcentagemPerda = lerValorValidado(sc, 5, 8, "margem de perda"); }
            case 8 -> { porcentagemBruta = lerValorValidado(sc, 20, 50, "porcentagem bruta"); porcentagemPerda = lerValorValidado(sc, 0.5, 1, "margem de perda"); }
        }

        System.out.print("\n-> Insira o valor de CUSTO do produto: R$ ");
        double valorCusto = sc.nextDouble();

        double valorFinal = calcularValorFinal(valorCusto, porcentagemPerda, porcentagemBruta);

        System.out.println("\n========================================");
        System.out.println("          RESUMO DO CÁLCULO             ");
        System.out.println("========================================");
        System.out.printf("Custo Original:    %s\n", formatarMoeda(valorCusto));
        System.out.printf("Margem de Perda:   %.2f%%\n", porcentagemPerda);
        System.out.printf("Margem Bruta:      %.2f%%\n", porcentagemBruta);
        System.out.println("----------------------------------------");
        System.out.printf("PREÇO DE VENDA:    %s\n", formatarMoeda(valorFinal));
        System.out.println("========================================\n");

        sc.close();
    }

    public static double lerValorValidado(Scanner sc, double min, double max, String nomeCampo) {
        double valor;
        while (true) {
            try {
                System.out.printf("-> Insira a %s (entre %.1f e %.1f): ", nomeCampo, min, max);
                valor = sc.nextDouble();
                if (valor >= min && valor <= max) return valor;
                System.err.printf("Valor inválido! Limite: %.1f a %.1f.\n", min, max);
            } catch (InputMismatchException e) {
                System.err.println("Erro: Use números. Ex: 10.5");
                sc.next();
            }
        }
    }

    public static double calcularValorFinal(double valorBase, double percPerda, double percBruto) {
        BigDecimal base = BigDecimal.valueOf(valorBase);
        BigDecimal perda = base.multiply(BigDecimal.valueOf(percPerda / 100));
        BigDecimal bruto = base.multiply(BigDecimal.valueOf(percBruto / 100));

        return base.add(perda).add(bruto)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static String formatarMoeda(double valor) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
    }
}