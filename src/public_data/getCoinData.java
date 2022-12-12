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

        }
    }

    public String getMarket() {
        market = market.substring(4, market.length());
        return market;
    }

    public String getTrade_price() {
        // 소숫점이 있으면, 정수부분부터 ,로 자르기
        if (trade_price.contains(".")) {
            int index = trade_price.indexOf(".");
            trade_price = trade_price.substring(0, index);
        }
        // 3자리마다 , 넣기
        int len = trade_price.length();
        int cnt = 0;
        String temp = "";
        for (int i = len - 1; i >= 0; i--) {
            temp += trade_price.charAt(i);
            cnt++;
            if (cnt == 3 && i != 0) {
                temp += ",";
                cnt = 0;
            }
        }
        trade_price = "";
        for (int i = temp.length() - 1; i >= 0; i--) {
            trade_price += temp.charAt(i);
        }
        return trade_price;
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
