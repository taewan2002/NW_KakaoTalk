package public_data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class getBTC {
    private String market;
    private String trade_date;
    private String trade_price;

    public getBTC() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.upbit.com/v1/ticker?markets=KRW-BTC"))
                    .header("accept", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response.body());
            JSONArray json = (JSONArray) obj;
            for (int i = 0; i < json.size(); i++) {
                JSONObject temp = (JSONObject) json.get(i);
                this.market = temp.get("market").toString();
                this.trade_date = temp.get("trade_date").toString();
                this.trade_price = temp.get("trade_price").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getMarket() {
        return market;
    }
    public String getTrade_date() {
        return trade_date;
    }
    public String getTrade_price() {
        String temp = "";
        for (int i = 0; i < trade_price.length(); i++) {
            if (i % 3 == 0 && i != 0) {
                temp = "," + temp;
            }
            temp = trade_price.charAt(trade_price.length() - i - 1) + temp;
        }
        return temp;
    }
}
