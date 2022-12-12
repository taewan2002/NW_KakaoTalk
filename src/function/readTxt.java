package function;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class readTxt {
    String host;
    ArrayList<String> port = new ArrayList<String>();
    // port(0) = 9898, 로그인포트, listeningThread 포트
    // port(1) = 25589, 캐시파일 다운로드 포트, 파일 client, server 포트
    // port(2) = 25588, chatting 포트
    // port(3) = 9998, getData 포트
    // port(4) = 9797, imgdownload 포트

    public readTxt() {
        try{
            // host.txt, port.txt 파일에서 정보 불러와서 저장하기
            File hostfile = new File("connectionInfo/host.txt");
            File portfile = new File("connectionInfo/port.txt");
            if(hostfile.exists() && portfile.exists()){
                FileReader hostfr = new FileReader(hostfile);
                FileReader portfr = new FileReader(portfile);
                BufferedReader hostbr = new BufferedReader(hostfr);
                BufferedReader portbr = new BufferedReader(portfr);
                host = hostbr.readLine();
                String portline = portbr.readLine();
                while(portline != null){
                    port.add(portline);
                    portline = portbr.readLine();
                }
                hostbr.close();
                portbr.close();
            }
            else{
                System.out.println("host.txt, port.txt 파일이 존재하지 않습니다.");
            }
        }catch(Exception e) {
            e.getStackTrace();
        }
    }
    public String getHost(){
        return host;
    }
    public ArrayList<String> getPort(){
        return port;
    }
}
