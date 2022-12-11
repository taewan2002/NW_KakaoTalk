package function;

import chatting_function.chatting_client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class cache_download { // 캐시 다운로드
    Socket socket = null;
    String filetype;
    chatting_client A=null;
    String roomnumber;
    int type;
    String time;
    public String filename;
    String path;
    public cache_download(Socket socket, String fileName, String roomnumber, String time, int type,chatting_client A) {
        this.socket=socket;
        this.filename=fileName;
        this.roomnumber=roomnumber;
        this.time=time;
        this.type=type;
        this.A=A;
        sending tmp =new sending();
        tmp.run();
    }

    public class sending implements Runnable{
        public void run() {
            path="chatting_data/"+roomnumber+"/"+".txt"; // 캐시 파일 경로
            try {
                readTxt read = new readTxt();
                String host = read.getHost();
                ArrayList<String> temp = read.getPort();
                int port = Integer.parseInt(temp.get(1));
                Socket S = new Socket(host, port); //서버에 접속
                protocol time = new protocol();
                time.setTime();
                String name_send1 = time.getTime();
                OutputStream os = S.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.println(Integer.toString(type)); //타입
                pw.flush();
                pw.println(roomnumber); //1방넘버
                pw.flush();
                System.out.println(time);
                if (type == 1) {
                    pw.println(roomnumber+".txt"); //2파일이름
                    pw.flush();
                } else {
                    pw.println(roomnumber); //2파일이름
                    pw.flush();
                }
                pw.println(".txt"); //3파일타입
                pw.flush();

                if (type == 2) {
                    //파일 받기
                    InputStream is = S.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    DataInputStream dataInput =new DataInputStream(is);
                    File tmp = new File("chatting_data"+"/"+roomnumber+".txt");
                    pw.println("1");
                    pw.flush();
                    String file_size = br.readLine();
                    System.out.println(file_size); // 66589840, 66589840
                    byte[] buf = new byte[104857600];      //100MB 단위로 파일을 쓰기 위한 byte타입 배열

                    FileOutputStream fileOutput = new FileOutputStream(tmp,false);
                    BufferedInputStream bufferdInput = new BufferedInputStream(dataInput);
                    int totalSize = Integer.parseInt(file_size);
                    int i = 0;  //buf 배열 인덱스용 변수
                    //전송받은 파일 사이즈가 100MB 보다 크다면 100MB 단위로 배열에 저장 후 파일에 write 하고
                    //파일에 write한 100MB만큼을  파일 사이즈에서 제외하는 while문!!!
                    while (totalSize > 104857600) {
                        while (i < 104857600) {
                            buf[i] = (byte) bufferdInput.read();
                            i++;    //배열인덱스 이동
                        }//while(i < 104857600)문
                        totalSize -= 104857600;  //파일사이즈 - 100MB
                        i = 0;                   //배열 인덱스 초기화
                        fileOutput.write(buf);   //파일에 write
                    }//while(totalSize > 104857600)문

                    //100MB보다 같거나 작은 남은 사이즈 혹은 원래의 사이즈가 100MB 보다 작을 시 if문 내용이 실행 되어
                    //파일을 write 함
                    if (totalSize <= 104857600) {
                        i = 0;                     //배열 인덱스 초기화
                        buf = new byte[totalSize]; //100MB보다 같거나 작으므로 totalSize로 배열크기 다시 생성
                        while (i < totalSize) {
                            buf[i] = (byte) bufferdInput.read();
                            i++;      //배열인덱스 이동
                        }//while문
                        fileOutput.write(buf);  //파일에 write
                    }//if문
                    fileOutput.flush();
                    System.out.println("file receive complete");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
