import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import java.net.http.HttpResponse;

public class CurrencyConverter {
    private static final String API_KEY = "1e006470e1325f83927fee4f";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static Map<String, Double> obterTaxasDeCambio(String moedaBase) throws Exception {
        String urlStr = BASE_URL + moedaBase;
        HttpResponse<String> response = HttpClientConnection.getResponse(urlStr);


        if (response.statusCode() != 200) {
            throw new Exception("Erro ao buscar taxas de câmbio: " + response.statusCode());
        }


        String responseBody = response.body();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(responseBody);
        JsonObject jsonobj = root.getAsJsonObject();

        if (!jsonobj.get("result").getAsString().equals("success")) {
            throw new Exception("Erro ao buscar taxas de câmbio");
        }

        JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");
        Map<String, Double> taxasDeCambio = new HashMap<>();
        for (String currencyCode : new String[]{"ARS", "BOB", "BRL", "CLP", "COP", "USD", "EUR", "GBP", "JPY", "AUD"}) {
            if (conversionRates.has(currencyCode)) {
                taxasDeCambio.put(currencyCode, conversionRates.get(currencyCode).getAsDouble());
            }
        }

        return taxasDeCambio;
    }

    public static double converterMoeda(Map<String, Double> taxas, String moedaDestino, double quantia) {
        if (!taxas.containsKey(moedaDestino)) {
            throw new IllegalArgumentException("Moeda de destino não encontrada nas taxas de câmbio");
        }
        return quantia * taxas.get(moedaDestino);
    }
}
