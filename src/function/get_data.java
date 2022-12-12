package function;

import chatting.protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
public class get_data{
    private String host;
    private int port;
    public get_data(){
        try{
            readTxt read = new readTxt();
            host = read.getHost();
            ArrayList<String> temp = read.getPort();
            port = Integer.parseInt(temp.get(3));
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    private InputStream is;
    private OutputStream os;
    private DataInputStream di;
    private DataOutputStream ds;
    private PrintWriter pw;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private BufferedOutputStream bos;
    private String user_id;
    private int typeofrequest;
    private String roomnumber;
    private ArrayList<String> my_room_list; // 내가 속한 방 목록
    private ArrayList<String> userlist_in_room; // 방에 있는 유저 목록
    private ArrayList<String> allUserList; // 전체 유저 목록
    private String follow_yes_or_no; // 팔로우 여부
    private String heart_yes_or_no; // 하트 여부
    private int followNum; // 팔로우 수
    private int followerNum; // 팔로워 수
    private int postNum; // 게시물 수
    private String id;
    private ArrayList<String> list;
    private ArrayList<String> Tag_list;
    private String message;
    private String file_name;
    private String feed_id;
    private String post_id;
    private String poster_id;
    private int like_num;
    private String name;
    private String phoneNum;
    private String email;


    public void setType8(String name, String phoneNum){
        this.typeofrequest = 8;
        this.name = name;
        this.phoneNum = phoneNum;
    }
    public void setType49(int typeofrequest, String user_id, String feed_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
        this.feed_id = feed_id;
    }
    public void setType50(int typeofrequest, String user_id, String feed_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
        this.feed_id = feed_id;
    }
    public void setType9(int typeofrequset, String user_id, String id){
        this.typeofrequest = typeofrequset;
        this.user_id = user_id;
        this.id = id;
    }
    public void setType10(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType11(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType12(int typeofrequest, String user_id, String roomnumber){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
        this.roomnumber = roomnumber;
    }
    public void setType15(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType19(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType20(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType18(int typeofrequest, String feed_id) {
        this.typeofrequest = typeofrequest;
        this.feed_id = feed_id;
    }
    public void setType16(int typeofrequest, String user_id) {
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType21(int typeofrequest, String post_id) {
        this.typeofrequest = typeofrequest;
        this.post_id = post_id;
    }
    public void setType22(int typeofrequest, String user_id) {
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType23(int typeofrequest, String feed_id) {
        this.typeofrequest = typeofrequest;
        this.feed_id = feed_id;
    }
    public void request(protocol content){
        try{
            this.oos.writeObject(content); // 프로토콜로 담은 내용 전송
            this.oos.flush();
        }
        catch(Exception e2){
            System.out.println(e2);
        }
    }

    public ArrayList<String> getMy_room_list() {
        return my_room_list;
    }
    public ArrayList<String> get_users_in_room() {
        return userlist_in_room;
    }
    public ArrayList<String> getAllUserList() {
        return allUserList;
    }
    public int getFollowNum() {
        System.out.println(" followNum : " + followNum);
        return followNum;
    }
    public int getFollowerNum() {
        System.out.println(" followerNum : " +followerNum);
        return followerNum;
    }
    public int getPostNum() {
        return postNum;
    }
    public String getFollow_yes_or_no() {
        return follow_yes_or_no;
    }
    public String getHeart_yes_or_no() {
        return heart_yes_or_no;
    }
    public String getMessage(){
        return message;
    }
    public String getFile_name(){
        return file_name;
    }
    public ArrayList<String> getTag_list(){
        return Tag_list;
    }
    public ArrayList<String> getfeed_list(){
        return list;
    }
    public String getFeed_id(){return feed_id;}
    public String getposter_id(){return poster_id;}
    public String getEmail(){return email;}

    public int getLikeNum() {
        return like_num;
    }
    public void start(){
        try{
            Socket socket = new Socket(host,port);
            System.out.println("port : " + port + ", host : " + host + "로 데이터 요청 시도");
            System.out.println("서버 연결 성공");
            this.is = socket.getInputStream();
            this.os = socket.getOutputStream();

            this.di = new DataInputStream(is);


            this.ds = new DataOutputStream(os);
            this.pw = new PrintWriter(os);
            this.oos = new ObjectOutputStream(os);
            this.dos = new DataOutputStream(os);
            this.bos =new BufferedOutputStream(os);

            this.pw = new PrintWriter(bos);
            if(typeofrequest == 1){

            } else if (typeofrequest == 8) {
                // 아이디찾기
                protocol p = new protocol(typeofrequest, name, phoneNum);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 8){
                            email = t.getEmail();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            } else if(typeofrequest == 49){
                protocol p = new protocol(typeofrequest, user_id, feed_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 49){
                            // this.heart_yes_or_no = String.valueOf(t.getHeart());
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 50){
                protocol p = new protocol(typeofrequest, user_id, feed_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 50){
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if (typeofrequest == 9){
                protocol p = new protocol(typeofrequest, user_id,id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 9){
                            // this.follow_yes_or_no = t.getFollow();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 10){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 10){
                            // this.postNum = t.getPostNum();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 11) {
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while (true) {
                    System.out.println("방 목록 받기");
                    try {
                        protocol t = (protocol) ois.readObject();
                        if (t.getTypeofrequest() == 11) {
                            this.my_room_list = t.getList();
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 12){
                protocol p = new protocol(typeofrequest, user_id, roomnumber);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 12){
                            userlist_in_room = t.getList();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 15){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 15){
                            allUserList = t.getList();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 16) {
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while (true) {
                    try {
                        protocol t = (protocol) ois.readObject();
                        if (t.getTypeofrequest() == 16) {
                            list = t.getList();
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 17){
                // protocol p = new protocol(typeofrequest, user_id, message, Tag_list, file_name);
                // request(p);
                this.ois = new ObjectInputStream(is);
            }
            else if(typeofrequest == 18){
                protocol p = new protocol(typeofrequest, feed_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 18){
                            feed_id = t.getSender();
                            // message = t.getMessage();
                            Tag_list = t.getList();
                            file_name = t.getFile_name();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 19){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 19){
                            // followerNum = t.get_follower_num();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 20){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 20){
                            // followNum = t.getFollow_num();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 21){
                protocol p = new protocol(typeofrequest, post_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 21){
                            poster_id = t.getSender();
                            System.out.println(poster_id);
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 22){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 22){
                            list = t.getList();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 23){
                protocol p = new protocol(typeofrequest, feed_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 23){
                            // like_num = t.getLikeNum();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

            is.close();
            os.close();
            di.close();
            ois.close();
            ds.close();
            pw.close();
            oos.close();
            bos.close();
            pw.close();
            socket.close();
            System.out.println(typeofrequest);
            System.out.println("서버 연결 종료");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
