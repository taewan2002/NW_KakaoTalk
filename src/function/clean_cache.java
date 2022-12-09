package function;

import java.io.File;

public class clean_cache {

    public String makedir(){

        String path = "post";
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
    public clean_cache(String filepath) {

        makedir();

        File path = new File(filepath);
        File[] fileList = path.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            System.out.print(i + "번째파일 : ");
            if (fileList[i].delete()) {
                System.out.println("삭제성공");
            } else {
                System.out.println("삭제실패");
            }
        }
    }
}
