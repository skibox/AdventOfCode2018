package day1;

import utils.FileReader;

import java.io.IOException;

public class FrequencyCalibrator {
  public static void main(String[] args) throws IOException {
    FileReader fileReader = new FileReader();
    fileReader.setFile("/Frequencies.txt");

    int calibrationSum = 0;

    for(String line : fileReader.getLinesFromFile()){
      calibrationSum += Integer.parseInt(line);
      System.out.println(line + " Current sum: " + calibrationSum);
    }
  }
}
