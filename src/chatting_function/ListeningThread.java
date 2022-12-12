package chatting_function;

import chatting.protocol;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ListeningThread extends Thread { // 서버에서 보낸 메세지 읽는 Thread
	Socket socket = null;
	String follow_yes_or_no;
	int post_num=-1;
	int follower_num=-1;
	int follow_num=-1;
	private static ArrayList<String> myroom_list = new ArrayList<>();
	private static ArrayList<String> userlist_in_room;
	private static ArrayList<String> all_user_list = new ArrayList<>();
	public ListeningThread(Socket socket,ArrayList<String> myroom_list) {
		this.socket = socket; // 받아온 Socket Parameter를 해당 클래스 Socket에 넣기
		this.myroom_list=myroom_list;
	}
	public ArrayList<String> getAll_user_list() {
		return all_user_list;
	}
	public int getPost_num() {
		return post_num;
	}
	public ArrayList<String> get_myroom_list() {
		return myroom_list;
	}
	public ArrayList<String> get_users_in_room() {
		return userlist_in_room;
	}

	public String get_follow_exist(){return follow_yes_or_no;}

	public int get_follow_num(){
		return follow_num;
	}

	public int get_follower_num(){
		return follower_num;
	}
	public boolean caching(protocol content){
		String room_id=content.getRoomnumber();
		String msg=content.getMessege();
		try {
			File file =new File("chatting_data/" + room_id + ".txt");
			FileWriter fw =new FileWriter(file,true);
			BufferedWriter bw= new BufferedWriter(fw);
			bw.append(content.getTime()+":"+ content.getSender()+":"+msg+":"+content.isFile_exist() + ":" + content.getFile_name()+":" +"\n");
			bw.close();
			return  true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public void run() {

		try {
			InputStream input = socket.getInputStream();
			while(true){
				ObjectInputStream ois = new ObjectInputStream(input);
				protocol t = (protocol) ois.readObject();
				if(t.getTypeofrequest() == 1){

				}
				else if(t.getTypeofrequest() == 2) {
				}
				else if(t.getTypeofrequest() == 4){
					if(caching(t) == true){
						System.out.println("캐싱 성공");
					}
					else{
						System.out.println("캐싱 실패");
					}
					System.out.println("방 번호 : " + t.getRoomnumber());
					System.out.println("현재 시간 : " + t.getTime());
					System.out.println("보낸 사람 : " + t.getSender());
					System.out.println("메시지 : " +  t.getMessege());
					System.out.println("파일 존재 여부 : " + t.isFile_exist());
					System.out.println("파일명 : " + t.getFile_name());
				}else if(t.getTypeofrequest() == 12) {
					myroom_list=t.getList();
					System.out.println("서버의 방목록 업데이트 결과가 도착했습니다.");

				}else if(t.getTypeofrequest()==14){
					userlist_in_room=t.getList();
					System.out.println("방 안의 유저목록 업데이트 결과가 도착했습니다.");
				}
				else if(t.getTypeofrequest()==16){
					all_user_list=t.getList();
					System.out.println("모든 유저 목록 도착했습니다.");
				}
				else{
					System.out.println("잘못된 요청입니다.");
				}
			}
		} catch (SocketException e){
			e.printStackTrace();
			System.out.println("로그아웃 하였습니다.");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}