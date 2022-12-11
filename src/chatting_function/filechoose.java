package chatting_function;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class filechoose {

    public static String jFileChooserUtil(){
        String folderPath = "";
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
        chooser.setCurrentDirectory(new File("/")); // 현재 사용 디렉토리를 지정
        chooser.setAcceptAllFileFilterUsed(true);   // Fileter 모든 파일 적용
        chooser.setDialogTitle("파일을 선택해주세요"); // 창의 제목
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드
        int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈
        if(returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭
            folderPath = chooser.getSelectedFile().toString();
        }else if(returnVal == JFileChooser.CANCEL_OPTION){ // 취소를 클릭
            System.out.println("cancel");
            folderPath = "";
        }
        return folderPath;
    }
}