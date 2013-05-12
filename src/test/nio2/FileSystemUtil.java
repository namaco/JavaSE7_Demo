package test.nio2;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemUtil {
    static void createZip() throws IOException {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        URI uri = URI.create("jar:file:/D:/testDir/zip/Test2.zip");//新しく作成するzipファイル

        //おまけ２
        FileSystem fs3 = FileSystems.newFileSystem(uri, env);
        Path pathInZipFile = fs3.getPath("/Test.txt"); //zip内に作成するファイル名
        Path internalTxtFile = Paths.get("D:/testDir/zip/Test.txt");//コピー元ファイル
        Files.copy(internalTxtFile, pathInZipFile, StandardCopyOption.REPLACE_EXISTING);
        fs3.close();
    }

    static void windowsFile() {
        FileSystem fs1 = FileSystems.getDefault(); //デフォルトのFileSystemを作成
        System.out.println("fs1 is " + fs1.toString());
    }

    static void zipFile() throws IOException {
        Map<String, String> env = new HashMap<String, String>();
        env.put("create", "true");
        URI uri = URI.create("jar:file:/D:/testDir/zip/Test.zip");//zipファイルの指定

        FileSystem fs2 = FileSystems.newFileSystem(uri, env);//zipのFileSystemを作成
        System.out.println("fs2 is " + fs2.toString());//FileSystemの出力

        //おまけ
        Path pathInZipfile = fs2.getPath("/Test.txt");//zip内のコピー元ファイル
        Path externalTxtFile = Paths.get("D:/testDir/zip/Test.txt");//コピー先ファイル名
        Files.copy(pathInZipfile, externalTxtFile, StandardCopyOption.REPLACE_EXISTING); //コピー
    }

    static void fileWatch() throws IOException, InterruptedException {
        FileSystem fs4 = FileSystems.getDefault();
        WatchService ws = fs4.newWatchService(); //WatchServiceのインスタンス作成
        Path path = fs4.getPath("D:/testDir/watch/");
        path.register(ws, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);//WatchServiceの登録
        for (;;){ //フォルダ監視処理
            WatchKey key = ws.take();
            List<WatchEvent<?>> events = key.pollEvents();
            for (WatchEvent obj : events) {
                //発生したイベントをコンソール出力
                System.out.println(obj.kind().name() + "\tFileName: " + obj.context().toString());
            }
            key.reset();
        }

    }
}