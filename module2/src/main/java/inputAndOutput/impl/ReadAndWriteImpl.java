package inputAndOutput.impl;

import inputAndOutput.ReadAndWrite;
import util.Util;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteImpl implements ReadAndWrite {

    private final File fileInput = new File(Util.FILE_INPUT_NAME.getPath());
    private final File fileOutput = new File(Util.FILE_OUTPUT_NAME.getPath());

    public boolean isInputExists() {
        return this.fileInput.isFile();
    }

    public List<String> readInput() {
        List<String> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileInput);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String text;
            while (bufferedReader.ready()) {
                text = StringUtils.normalizeSpace(bufferedReader.readLine());
                list.add(text);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
        return list;
    }

    public void writeOutput(Integer price) {

        try (FileWriter fileWriter = new FileWriter(fileOutput, true)) {
            fileWriter.write(price.toString());
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("e= " + e.getMessage());
        }
    }
}