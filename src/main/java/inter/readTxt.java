package inter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class readTxt {
    public List<String> readData(String path){
        try {
            return Files.readAllLines(Paths.get(path), Charset.defaultCharset());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}

