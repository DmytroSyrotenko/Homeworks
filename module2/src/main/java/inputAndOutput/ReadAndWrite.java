package inputAndOutput;

import java.util.List;

public interface ReadAndWrite {

    List<String> readInput();

    void writeOutput(Integer price);

    boolean isInputExists();

}
