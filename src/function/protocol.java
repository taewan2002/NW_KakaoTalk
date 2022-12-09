package function;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class protocol implements Serializable {
    private int typeofrequest;
    private ArrayList<String> list;
    private String sender;
    private String roomnumber;
    private String messege;
    private String time;
    private int num;
    private boolean file_exist;
    private String file_name;
    public protocol(){}

    // 요청 프로토콜
    // 방 생성인 경우 (방 생성 요청) type = 1
    // 방 초대인 경우 (방 초대 요청) type = 2
    // 방에서 나가기인 경우 (방 나가기 요청) type = 3
    // 메시지 보내기인 경우 (메시지 보내기 요청) type = 4
    // 로그아웃인 경우 (로그아웃 요청) type = 5

    // 방 생성인 경우 1 or 유저가 속한 방 목록 불러오기
    public protocol(int typeofrequest, String sender, ArrayList<String> list){
        this.sender = sender;
        this.typeofrequest = typeofrequest;
        this.list = list;
    }

    // 방 초대 2
    public protocol(int typeofrequest, String sender, String roomnumber, ArrayList<String>list){
        this.typeofrequest = typeofrequest;
        this.sender = sender;
        this.roomnumber = roomnumber;
        this.list = list;
    }

    // 방에서 나가기 3
    public protocol(int a, String b, String c){
        this.typeofrequest = a;
        this.sender = b;
        this.roomnumber = c;
    }

    // 메시지 보내기 4
    public protocol(int typeofrequest, String roomnumber, String sender, String messege, String time, boolean file_exist, String file_name){
        this.typeofrequest = typeofrequest;
        this.roomnumber = roomnumber;
        this.sender = sender;
        this.messege = messege;
        this.time = time;
        this.file_exist = file_exist;
        this.file_name = file_name;
    }

    // 로그아웃 5
    public protocol(int typeofrequest, String sender) {
        this.typeofrequest = typeofrequest;
        this.sender = sender;
    }

    //make getter function
    public int getTypeofrequest() {
        return typeofrequest;
    }
    public ArrayList<String> getList() {
        return list;
    }
    public String getSender() {
        return sender;
    }
    public String getRoomnumber() {
        return roomnumber;
    }
    public String getMessege() {
        return messege;
    }
    public String getTime() {
        return time;
    }
    public boolean isFile_exist() {
        return file_exist;
    }
    public String getFile_name() {
        return file_name;
    }

    //make setter function
    public void setTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.time = now.format(formatter);
    }
}

