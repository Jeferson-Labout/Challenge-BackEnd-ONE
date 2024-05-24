import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {
    private static List<String> historico = new ArrayList<>();

    public static void adicionarHistorico(String moedaBase, String moedaDestino, double quantia, double resultado) {
        LocalDateTime agora = LocalDateTime.now();
        String entrada = String.format("%s: %.2f %s = %.2f %s", agora, quantia, moedaBase, resultado, moedaDestino);
        historico.add(entrada);
    }

    public static void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma conversão foi realizada ainda.");
        } else {
            System.out.println("Histórico de Conversões:");
            for (String entrada : historico) {
                System.out.println(entrada);
            }
        }
    }
}
