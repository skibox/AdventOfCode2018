package day2;

import utils.FileReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryChecker {
  public static void main(String[] args) throws IOException {
    calculateChecksum();
  }

  public static void calculateChecksum() throws IOException {
    FileReader fileReader = new FileReader();
    fileReader.setFile("/boxes.txt");

    List<String> boxes = fileReader.getLinesFromFile();
    Map<Character, Integer> duplicates;

    int lineCharacterCounter;
    Integer tempVal;
    boolean lineContains2 = false;
    boolean lineContains3 = false;
    int twoMulitiplier = 0;
    int threeMultiplier = 0;

    for(String line : boxes){
//      Arrays.sort(line.toCharArray());
      duplicates = new HashMap<>();
      for(Character character : line.toCharArray()){
        if(duplicates.containsKey(character)){
          tempVal = duplicates.get(character);
          duplicates.put(character, ++tempVal);
        }
        else duplicates.put(character,1);
      }

      for(Character character : duplicates.keySet()){
        if(duplicates.get(character) == 2) lineContains2 = true;
        if(duplicates.get(character) == 3) lineContains3 = true;
      }

      System.out.println(line);

      if(lineContains2) {
        twoMulitiplier++;
        System.out.println("Line contains at least one pair of letters");
      }
      if(lineContains3){
        threeMultiplier++;
        System.out.println("Line contains at least one triplet of letters");
      }

      lineContains2 = false;
      lineContains3 = false;
    }

    System.out.println(twoMulitiplier + " * " + threeMultiplier + " = " +
        twoMulitiplier*threeMultiplier);

  }
}
