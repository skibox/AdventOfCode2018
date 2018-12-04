package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
  private List<String> lines;
  private File file;
  private Path resourceDirectory;

  public FileReader() {
    setup();
  }

  public FileReader(String filename) {
    setup();
    setFile(filename);
  }

  public List<String> getLines() {
    return lines;
  }

  private void setup(){
    this.lines = new ArrayList<>();
    resourceDirectory = Paths.get("src/main/resources/");
  }

  public void setFile(String filename){
    this.file = new File(resourceDirectory + filename);
    System.out.println("File dir: " + file.getPath());
  }

  public List<String> getLinesFromFile() throws IOException {
    return FileUtils.readLines(file, "UTF-8");
  }
}
