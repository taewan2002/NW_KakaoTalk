package chatting;

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
    private String id;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String friend;
    private boolean tf;
    public protocol(){
        // server - client protocol
        // 방 생성 type = 1
        // 방 초대 type = 2
        // 방에서 나가기 type = 3
        // 메시지 보내기 type = 4
        // 로그아웃인 경우 type = 5
        // 방 목록 요청 type = 6
        // 방 참여자 목록 요청 type = 7
        // 아이디찾기 type = 8
        // 비밀번호 변경하기 위한 확인 type = 9
        // 비밀번호 변경 type = 10
        // 로그인 여부 확인 type = 50
        // 내정보 요청 type = 51
        // 친구 추가 type = 16
        // 친구 삭제 type = 53 -> 이거 아직 안했다
        // 친구 목록 요청 type = 54
        // 탈퇴하기 type = 55
    }


    // 방 생성 1, 로그인 여부 확인 50, 친구추가 16
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

    // 방에서 나가기 3, 방 참여자 목록 불러오기 7, 아이디 찾기 8, 비밀번호 변경, 친구 삭제
    public protocol(int a, String b, String c){
        this.typeofrequest = a;
        this.sender = b;
        this.roomnumber = c;
        this.phone = c;
        this.name = b;
        this.email = c;
        this.password = c;
        this.friend = c;
    }

    // 체팅 보내기 4
    public protocol(int typeofrequest, String roomnumber, String sender, String messege, boolean file_exist, String file_name){
        this.typeofrequest = typeofrequest;
        this.roomnumber = roomnumber;
        this.sender = sender;
        this.messege = messege;
        setTime();
        this.file_exist = file_exist;
        this.file_name = file_name;
    }

    // 로그아웃 5, 자신이 속한 방 목록 불러오기 6, 내정보 요청 51, 탈퇴하기 55, 친구 목록 불러오기 54
    public protocol(int typeofrequest, String tmp) {
        this.typeofrequest = typeofrequest;
        this.sender = tmp;
    }

    // 비밀번호 변경하기 위한 확인 9
    public protocol(int typeofrequest, String email, String name, String phone) {
        this.typeofrequest = typeofrequest;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
    public protocol(int typeofrequest, boolean tf){
        this.typeofrequest = typeofrequest;
        this.tf = tf;
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
    public String getEmail() {
        return email;
    }
    public String getPhoneNum() {
        return phone;
    }
    public String getName(){
        return name;
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getFriend() {
        return friend;
    }
    public boolean getTf(){return tf;}

    //make setter function
    public void setTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.time = now.format(formatter);
    }
}

