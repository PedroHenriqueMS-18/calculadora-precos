package calculadora.precos.version01;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;

public class CalculadoraPrecosProdutos {
    public static void main(String[] args) {
        // Configuramos o Scanner para aceitar PONTO como separador decimal (ex: 10.5)
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        double porcentagemBruta = 0, porcentagemPerda = 0;
        int opcao = 0;

        // 1. Loop para garantir que a CATEGORIA seja válida
        while (true) {
            try {
                System.out.println("\n--- MENU DE CATEGORIAS ---");
                System.out.println("1) Commodities  2) Limpeza  3) Higiene  4) Hortifruti");
                System.out.println("5) Açougue      6) Bebidas  7) Padaria  8) Bomboniere");
                System.out.print("Digite o número da categoria (1-8): ");
                opcao = sc.nextInt();

                if (opcao >= 1 && opcao <= 8) {
                    break; // Sai do loop do menu
                }
                System.err.println("Erro: Categoria inexistente! Escolha entre 1 e 8.");
            } catch (InputMismatchException e) {
                System.err.println("Erro: Digite apenas o NÚMERO da categoria.");
                sc.next(); // Limpa o erro do teclado
            }
        }

        // 2. Switch Case usando nosso método "mágico" de validação
        switch (opcao) {
            case 1:
                porcentagemBruta = lerValorValidado(sc, 10, 15, "porcentagem bruta");
                porcentagemPerda = lerValorValidado(sc, 2, 5, "margem de perda");
                break;
            case 2:
            case 3:
                porcentagemBruta = lerValorValidado(sc, 20, 30, "porcentagem bruta");
                porcentagemPerda = lerValorValidado(sc, 1, 3, "margem de perda");
                break;
            case 4:
                porcentagemBruta = lerValorValidado(sc, 35, 50, "porcentagem bruta");
                porcentagemPerda = lerValorValidado(sc, 7, 12, "margem de perda");
                break;
            case 5:
                porcentagemBruta = lerValorValidado(sc, 35, 50, "porcentagem bruta");
                porcentagemPerda = lerValorValidado(sc, 2, 4, "margem de perda");
                break;
            case 6:
                porcentagemBruta = lerValorValidado(sc, 30, 45, "porcentagem bruta");
                porcentagemPerda = lerValorValidado(sc, 1, 5, "margem de perda");
                break;
            case 7:
                porcentagemBruta = lerValorValidado(sc, 30, 45, "porcentagem bruta");
                porcentagemPerda = lerValorValidado(sc, 5, 8, "margem de perda");
                break;
            case 8:
                porcentagemBruta = lerValorValidado(sc, 20, 50, "porcentagem bruta");
                porcentagemPerda = lerValorValidado(sc, 0.5, 1, "margem de perda");
                break;
        }

        // 3. Resultado Final
        System.out.println("\n================================");
        System.out.println("DADOS COLETADOS COM SUCESSO!");
        System.out.printf("Categoria: %d\n", opcao);
        System.out.printf("Porcentagem Bruta: %.2f%%\n", porcentagemBruta);
        System.out.printf("Margem de Perda: %.2f%%\n", porcentagemPerda);
        System.out.println("================================");
        System.out.println("Pedro lindo");

        sc.close();
    }

    /**
     * MÉTODO PROFISSIONAL DE VALIDAÇÃO
     * Este método lida com intervalos numéricos e erros de digitação (letras).
     */
    public static double lerValorValidado(Scanner sc, double min, double max, String nomeCampo) {
        double valor;
        while (true) {
            try {
                System.out.printf("-> Insira a %s (entre %.1f e %.1f): ", nomeCampo, min, max);
                valor = sc.nextDouble();

                if (valor >= min && valor <= max) {
                    return valor; // Única saída possível do loop e do método
                }
                System.err.printf("Valor inválido! O campo '%s' exige entre %.1f e %.1f.\n", nomeCampo, min, max);

            } catch (InputMismatchException e) {
                System.err.println("Erro de entrada: Você digitou um texto. Use números (Ex: 10.5).");
                sc.next(); // Limpa o buffer para a próxima tentativa
            }
        }
    }
}