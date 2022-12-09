package public_data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class getCoinData {
    private String market;
    private String trade_date;
    private String trade_price;
    private String changeRate;

    public getCoinData(String market) {
        String url = "https://api.upbit.com/v1/ticker?markets=KRW-";
        url += market;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
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
                this.changeRate = temp.get("signed_change_rate").toString();
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
    public String getChangeRate() {
        changeRate = Float.toString(Float.parseFloat(changeRate) * 100);
        changeRate = changeRate.substring(0, 5) + "%";
        return changeRate;
    }
}
