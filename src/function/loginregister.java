package function;

import java.io.*;
import java.net.Socket;

public class loginregister {
    public static int port;
    public static String host;
    public static md5 encryptor = new md5();
    public int register(String id,String password, String name, String phone){
        try{
            readTxt read = new readTxt();
            host = read.getHost();
            port = Integer.parseInt(read.getPort().get(0));
            System.out.println("host : " + host + ", port : " + port + "로 회원가입 시도");
            Socket socket = new Socket(host,port);

            OutputStream os = socket.getOutputStream();
            DataOutputStream ds = new DataOutputStream(os);
            InputStream is = socket.getInputStream();
            DataInputStream di = new DataInputStream(is);
            PrintWriter pw = new PrintWriter(os);

            String enc_password = encryptor.encMD5(password);
            ds.writeInt(100); //100 means register status code
            ds.flush();

            // send id, enc_password;
            pw.println(id);
            pw.flush();
            pw.println(enc_password);
            pw.flush();
            pw.println(name);
            pw.flush();
            pw.println(phone);
            pw.flush();

            int register_status = -1;
            register_status = di.readInt(); //get register status
            if(register_status!=2){
                System.out.println("회원가입 성공");
                System.out.println(register_status);
                return  register_status;
            } else if (register_status==2) {
                System.out.println(register_status);
                System.out.println("중복된 아이디 존재");
                return register_status;
            }
            os.close();
            socket.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public int login(String id,String password){
        try{
            readTxt read = new readTxt();
            host = read.getHost();
            port = Integer.parseInt(read.getPort().get(0));
            System.out.println("host : " + host + ", port : " + port + "로 로그인 시도");
            Socket socket= new Socket(host,port);
            OutputStream os=socket.getOutputStream();
            InputStream is=socket.getInputStream();
            DataOutputStream ds= new DataOutputStream(os);
            DataInputStream di=new DataInputStream(is);
            PrintWriter pw= new PrintWriter(os);
            String enc_password=encryptor.encMD5(password);
            ds.writeInt(200); //200 means register status code
            ds.flush();
            pw.println(id);
            pw.flush();
            pw.println(enc_password);
            pw.flush();
            int user_id = di.readInt(); //get user_id
            os.close();
            socket.close();
            return user_id; // user_id from server received
        }catch (Exception e){
            System.out.println(e);
        }
        return -1; //when error occured return -1
    }

    public int logout(int user_id){
        try{
            Socket socket= new Socket(host,port);
            OutputStream os=socket.getOutputStream();
            InputStream is=socket.getInputStream();
            DataOutputStream ds= new DataOutputStream(os);
            DataInputStream di=new DataInputStream(is);

            ds.writeInt(300); //300 means logout status
            ds.flush();
            ds.writeInt(user_id); //send user_id to server
            ds.flush();

            os.close();
            socket.close();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return -1;
        }
    }
}
