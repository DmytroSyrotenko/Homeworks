package myReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyReader {

    public final BufferedReader bufferedReader;

    public MyReader() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }
    public String readLine(){

        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
