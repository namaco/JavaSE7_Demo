package test.nio2;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ebina
 * Date: 13/05/11
 * Time: 18:53
 */
public class FileSystemTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // WindowsFileSystemの確認
//        FileSystemUtil.windowsFile();
        //zipFileSystemの確認
        FileSystemUtil.zipFile();
        //zipファイルの作成
//        FileSystemUtil.createZip();
        //ファイルの監視
//        FileSystemUtil.fileWatch();
    }

}
