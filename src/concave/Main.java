package concave;

import chatting_function.ListeningThread;
import chatting_function.chatting_client;

public class Main extends Thread{
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;
    private String room_id;

    public GameMain gamemain;
    public Main(chatting_client client, String room_id, String user_id, ListeningThread t1) {
        this.client = client;
        this.room_id = room_id;
        this.user_id = user_id;
        this.t1 = t1;
    }

    public void run(){
        this.gamemain = new GameMain(client, room_id, user_id, t1);
    }

}
