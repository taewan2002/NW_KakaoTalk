package public_data;

import java.util.ArrayList;

public class test {
    // getCoinData 사용법
    public static void main(String[] args){
        ArrayList<String> market = new ArrayList<>();
        market.add("BTC");
        market.add("ETH");
        market.add("XRP");

        for (int i = 0; i < 3; i++) {
            getCoinData data = new getCoinData(market.get(i));
            System.out.println("이름:" + data.getMarket());
            System.out.println("가격:" + data.getTrade_price());
            System.out.println("변동률:" + data.getChangeRate());
            System.out.println();
        }
    }
}
