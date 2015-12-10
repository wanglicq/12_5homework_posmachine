package inter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class readTxt {
    public List<String> readData(String path){
        List<String> result = new ArrayList<String>();
        File filename = new File(path);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            while(line != null){
                result.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

