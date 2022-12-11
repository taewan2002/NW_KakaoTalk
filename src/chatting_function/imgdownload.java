package chatting_function;

import java.io.*;
import java.net.Socket;

public class imgdownload extends Thread{

    private String writer;

    private String file_name;

    private String filesavepath="post/";

    private Socket socket;
    public imgdownload(String writer1, String file_name1) {

        this.writer = writer1;
        this.file_name = file_name1;

        System.out.println(writer + " " + file_name);

    }
    public void run(){
        System.out.println("이미지 다운로드 쓰레드 실행");
        try{
            socket = new Socket("swiftsjh.tplinkdns.com",9797);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            String a = makedir(writer);

            pw.println(writer);
            pw.flush();

            pw.println(file_name);
            pw.flush();

            InputStream is = socket.getInputStream();
            DataInputStream dataInput=new DataInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String file_size = br.readLine();
            System.out.println("file size:"+file_size);
            int totalSize=Integer.parseInt(file_size);
            byte[] buf = new byte[104857600];      //100MB 단위로 파일을 쓰기 위한 byte타입 배열
            FileOutputStream fileOutput = new FileOutputStream(new File(filesavepath+writer+"/"+file_name), false);
            BufferedInputStream bufferdInput = new BufferedInputStream(dataInput);
            int i = 0;
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
            System.out.println("image receive complete");

            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String makedir(String user_id){

        String path = "post/"+user_id;
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
                System.out.println("폴더가 생성되었습니다.");
                return Folder.getAbsolutePath();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }else {
            System.out.println("이미 폴더가 생성되어 있습니다.");
            return null;
        }
        return null;
    }
}
