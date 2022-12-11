package public_data;

import GUI.CoinData;

public class setCoinData {
    // 코인 데이터를 받아오는 클래스
    // 오류가 발생하면 다시 시도하도록 함, 보통은 1번만 시도하면 됨
    public setCoinData(){
        try{
            new CoinData();
        }catch (Exception e){
            new setCoinData(); // 오류가 발생하면 다시 시도
        }
    }
}
