package public_data;

public class test {
    // getBTC, getETH 사용법
    public static void main(String[] args){
        getBTC btc =  new getBTC();
        System.out.println("이름:" + btc.getMarket());
        System.out.println("가격:" + btc.getTrade_price());

        getETH eth = new getETH();
        System.out.println("이름:" + eth.getMarket());
        System.out.println("가격:" + eth.getTrade_price());
    }
}
