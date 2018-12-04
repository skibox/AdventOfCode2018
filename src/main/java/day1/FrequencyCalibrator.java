package day1;

import utils.FileReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FrequencyCalibrator {
  public static void main(String[] args) throws IOException {
    getFirstDuplicatedFrequency();
  }

  public static void getFinalFrequency() throws IOException{
    FileReader fileReader = new FileReader();
    fileReader.setFile("/Frequencies.txt");

    int calibrationSum = 0;

    for(String line : fileReader.getLinesFromFile()){
      calibrationSum += Integer.parseInt(line);
      System.out.println(line + " Current sum: " + calibrationSum);
    }
  }

  public static void getFirstDuplicatedFrequency() throws IOException{
    FileReader fileReader = new FileReader();
    fileReader.setFile("/Frequencies.txt");

    Set<Integer> frequencies = new HashSet<>();
    List<String> changes = fileReader.getLinesFromFile();
    Integer currentSum = 0;
    int counter = 0;
    int changesSize = changes.size();

    boolean go = true;

    while (go) {
      while(counter <= changesSize - 1){
        currentSum += Integer.parseInt(changes.get(counter));
        go = frequencies.add(currentSum);
        System.out.println(changes.get(counter) + " Current sum: " + currentSum);
        counter++;

        if(!go) {
          System.out.println("First duplicated frequency: " + currentSum);
          break;
        }
      }
      counter = 0;
    }
  }
}
