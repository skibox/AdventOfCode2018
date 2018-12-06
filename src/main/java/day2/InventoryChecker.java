package day2;

import utils.FileReader;

import java.io.IOException;
import java.util.*;


public class InventoryChecker {
    public static List<String> validBoxes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> boxes = getBoxes();
        getValidBoxes(boxes);

        System.out.println();
        for (String line : validBoxes) {
            System.out.println(line);
        }

        System.out.println(validBoxes.size() + ": number of valid boxes");

    }

    public static void getValidBoxes(List<String> boxes){
        for(int i = 0; i < boxes.size(); i++){
            addboxIfValid(i, boxes);
        }
    }

    public static void addboxIfValid(int startingIndex, List<String> boxes){
        boolean output = false;
        char[] line1 = boxes.get(startingIndex).toCharArray();
        String line1String = boxes.get(startingIndex);

        for(int i = startingIndex + 1; i < boxes.size(); i++){
            char[] line2 = boxes.get(i).toCharArray();
            String line2String = boxes.get(i);

            System.out.println("=========================================");
            System.out.println(line1String + " Line " + startingIndex);
            System.out.println(line2String + " Line " + i);

            int lineCounter = 0;

            for(int j = 0; j < line1.length - 1; j++){
                if (line1[j] != line2[j]) {
                    lineCounter++;
                    if (lineCounter > 1) {
                        break;
                    }
                }
            }

            if(lineCounter < 2) {
                System.out.println("Added line:  ");
                validBoxes.add(line1String);
                validBoxes.add(line2String);
            }
        }
    }

    public static void calculateChecksum() throws IOException {
        List<String> boxes = getBoxes();
        Map<Character, Integer> duplicates;

        Integer tempVal;
        boolean lineContains2 = false;
        boolean lineContains3 = false;
        int twoMulitiplier = 0;
        int threeMultiplier = 0;

        for (String line : boxes) {
            duplicates = new HashMap<>();
            for (Character character : line.toCharArray()) {
                if (duplicates.containsKey(character)) {
                    tempVal = duplicates.get(character);
                    duplicates.put(character, ++tempVal);
                } else duplicates.put(character, 1);
            }

            for (Character character : duplicates.keySet()) {
                if (duplicates.get(character) == 2) lineContains2 = true;
                if (duplicates.get(character) == 3) lineContains3 = true;
            }

            System.out.println(line);

            if (lineContains2) {
                twoMulitiplier++;
                System.out.println("Line contains at least one pair of letters");
            }
            if (lineContains3) {
                threeMultiplier++;
                System.out.println("Line contains at least one triplet of letters");
            }

            lineContains2 = false;
            lineContains3 = false;
        }

        System.out.println(twoMulitiplier + " * " + threeMultiplier + " = " + twoMulitiplier * threeMultiplier);
        System.out.println();
        System.out.println();
    }

    public static List<String> getBoxes() throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.setFile("/boxes.txt");

        return fileReader.getLinesFromFile();
    }
}
