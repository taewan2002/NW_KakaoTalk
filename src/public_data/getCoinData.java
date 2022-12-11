package public_data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class getCoinData {
    private String market;
    private String time;
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
                this.trade_price = temp.get("trade_price").toString();
                this.changeRate = temp.get("signed_change_rate").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMarket() {
        market = market.substring(4, market.length());
        return market;
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
        float temp = Float.parseFloat(changeRate) * 100;
        changeRate = String.format("%.2f", temp) + "%";
        return changeRate;
    }

    public String getTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
        this.time = now.format(formatter);
        return time;
    }
}
