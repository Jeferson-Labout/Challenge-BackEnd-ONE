import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();

            if (opcao == 10) {
                System.out.println("Programa encerrado.");
                break;
            }

            if (opcao == 9) {
                ConversionHistory.exibirHistorico();
                continue;
            }

            if (opcao < 1 || opcao > 10) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            String moedaBase = null, moedaDestino = null;

            switch (opcao) {
                case 1:
                    moedaBase = "USD";
                    moedaDestino = "ARS";
                    break;
                case 2:
                    moedaBase = "ARS";
                    moedaDestino = "USD";
                    break;
                case 3:
                    moedaBase = "USD";
                    moedaDestino = "BRL";
                    break;
                case 4:
                    moedaBase = "BRL";
                    moedaDestino = "USD";
                    break;
                case 5:
                    moedaBase = "USD";
                    moedaDestino = "COP";
                    break;
                case 6:
                    moedaBase = "COP";
                    moedaDestino = "USD";
                    break;
                case 7:
                    moedaBase = "USD";
                    moedaDestino = "EUR";
                    break;
                case 8:
                    moedaBase = "USD";
                    moedaDestino = "GBP";
                    break;
                default:
                    throw new IllegalStateException("Opção inválida: " + opcao);
            }

            System.out.print("Digite a quantia a ser convertida: ");
            double quantia = scanner.nextDouble();

            try {
                Map<String, Double> taxas = CurrencyConverter.obterTaxasDeCambio(moedaBase);
                double quantiaConvertida = CurrencyConverter.converterMoeda(taxas, moedaDestino, quantia);

                System.out.printf("Valor %.2f [%s] corresponde ao valor final de => %.2f [%s]%n",
                        quantia, moedaBase, quantiaConvertida, moedaDestino);

                ConversionHistory.adicionarHistorico(moedaBase, moedaDestino, quantia, quantiaConvertida);

            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("***********************************************");
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]");
        System.out.println("***********************************************");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileiro");
        System.out.println("4) Real brasileiro => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Dólar => Euro");
        System.out.println("8) Dólar => Libra esterlina");
        System.out.println("9) Exibir histórico de conversões");
        System.out.println("10) Sair");
        System.out.println("***********************************************");
        System.out.print("Escolha uma opção válida: ");
    }
}
