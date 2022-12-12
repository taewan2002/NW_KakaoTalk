package public_data;

import GUI.CoinData;
import chatting_function.ListeningThread;
import chatting_function.chatting_client;

public class setCoinData {

    // 코인 데이터를 받아오는 클래스
    // 오류가 발생하면 다시 시도하도록 함, 보통은 1번만 시도하면 됨
    public setCoinData(String user_id, chatting_client client, ListeningThread t1){
        try{
            new CoinData(user_id, client, t1);
        }catch (Exception e){
            new setCoinData(user_id, client, t1); // 오류가 발생하면 다시 시도
            System.out.println("https://api.upbit.com의 데이터 불러오기 실패, 다시 시도 중...");
        }
    }
}
