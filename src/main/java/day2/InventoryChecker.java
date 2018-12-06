package day1;

import utils.FileReader;

import java.io.IOException;
import java.util.*;


public class InventoryChecker {
    public static void main(String[] args) throws IOException {
        List<String> boxes = getBoxes();
        List<String> validBoxes = wqerwer(boxes);

        for (String line : validBoxes) {
            System.out.println(line);
        }

        System.out.println(validBoxes.size() + ": number of valid boxes");
    }

//    public static void getValidBoxes() throws IOException{
//        List<String> validBoxes = new ArrayList<>();
//        List<String> boxes = getBoxes();
//        Collections.sort(boxes);
//
//        bpl(boxes, validBoxes);
//
//        System.out.println("Number of valid boxes: " + validBoxes.size());
//        for(String line : validBoxes){
//            System.out.println(line);
//        }
//    }
//
//    public static void bpl(List<String> boxes, List<String> validBoxes){
//        List<String> tempBoxes = boxes;
//        while(tempBoxes.size() > 1){
//            getLinesFromI0toImax();
//
//
//
//
//
//            bpl(boxes, validBoxes);
//        }
//    }
    public static int getLastSameCharIndex(char[] line1, char[] line2){
        int lastSameIndex = 0;
        for(int i = 0; i < line1.length - 1; i++){
            if(line1[lastSameIndex + 1] != line2[lastSameIndex + 1]) break;
            else lastSameIndex++;
        }

        return lastSameIndex;
    }

    public static List<String> wqerwer(List<String> boxes){
        List<String> validBoxes = new ArrayList<>();

        for(int i = 0; i < boxes.size(); i++){
            if(isBoxValid(i, boxes)) validBoxes.add(boxes.get(i));
        }

        return validBoxes;
    }

    public static boolean isBoxValid(int startingIndex, List<String> boxes){
        boolean output = false;
        char[] line1 = boxes.get(startingIndex).toCharArray();

        for(int i = startingIndex + 1; i < boxes.size(); i++){
            char[] line2 = boxes.get(i).toCharArray();
            int lineCounter = 0;

            for(int j = 0; j < line1.length - 1; j++){
                if (line1[j] != line2[j]) {
                    lineCounter++;
                    if (lineCounter > 1) {
                        break;
                    }
                }
                if(lineCounter == line1.length - 1){
                    output = true;
                }
            }
        }
        return output;
    }

    public static List<String> asd(List<String> boxes) {
        Collections.sort(boxes);
        int lineCounter = 0;
        int k = 1;
        List<String> validBoxes = new ArrayList<>();
        List<String> validBoxesList = new ArrayList<>();

        for (int i = 0; i < boxes.size() - 2; i++) {
            System.out.println("checking line " + i);
            char[] line1 = boxes.get(i).toCharArray();
            char[] line2 = boxes.get(i + 1).toCharArray();

            int lastSameIndex = getLastSameCharIndex(line1, line2);
            String line1begin = boxes.get(i).substring(0,lastSameIndex + 1);
            String line2begin = boxes.get(i + 1).substring(0,lastSameIndex + 1);

            while (line1begin.equals(line2begin)) {
                for (int j = lastSameIndex + 1; j < line1.length - 1; j++) {
                    if (line1[j] != line2[j]) {
                        lineCounter++;
                        if (lineCounter > 1) {
                            break;
                        }
                    }
                }
                if (lineCounter <= 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(line1).append(" almost identical to ").append("\n").append(line2).append("\n");
                    validBoxes.add(stringBuilder.toString());
                    validBoxesList.add(new String(line1));
                }
                lineCounter = 0;
                if((i + 1 + k) <= boxes.size() - 1) line2begin = boxes.get(i + 1 + k).substring(0, lastSameIndex + 1);
                if(line2begin.length() == 1) break;
                k++;
            }
            k = 1;
        }

        System.out.println("Number of valid boxes: " + validBoxesList.size());

        return validBoxesList;
    }

    public static void calculateChecksum() throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.setFile("/boxes.txt");

        List<String> boxes = fileReader.getLinesFromFile();
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
