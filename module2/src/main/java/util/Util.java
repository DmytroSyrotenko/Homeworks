package util;

public enum Util {

    FILE_INPUT_NAME("input.txt"),
    FILE_OUTPUT_NAME("output.txt");
    private final String path;

    Util(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}