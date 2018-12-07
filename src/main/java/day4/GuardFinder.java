package day4;

import utils.FileReader;

import java.io.IOException;
import java.util.*;

public class GuardFinder {
  public static void main(String[] args) throws IOException {
    FileReader fileReader = new FileReader("/guards.txt");
    List<String> notes = fileReader.getLinesFromFile();

    Collections.sort(notes);

    for (String line : notes) {
      System.out.println(line);
    }

    Stack<String> notesStack = convertListToStack(notes);
    List<List<String>> separatedGuardsNotes = parseGuardsInput(notesStack);
    TreeMap<Integer, List<String>> guardsMap = addGuardsToMap(separatedGuardsNotes);

    int mostAsleepId = -1;
    int mostAsleepTime = -1;

    for(Map.Entry<Integer, List<String>> pair : guardsMap.entrySet()){
      System.out.println("==============");
      System.out.println("Guard id: " + pair.getKey());
      for(String line : pair.getValue()){
        System.out.println(line);
      }
      int asleepTime = calculateTotalSleepTime(pair.getValue());
      System.out.println();
      System.out.println("Total sleep time: " + asleepTime);
      if(asleepTime > mostAsleepTime){
        mostAsleepTime = asleepTime;
        mostAsleepId = pair.getKey();
      }
    }

    System.out.println("Most asleep id: " + mostAsleepId);
    System.out.println("Time asleep: " + mostAsleepTime);

    int minuteUsuallyAsleep = calculateMinuteUsuallyAsleep(mostAsleepId, guardsMap);

    System.out.println("Minute when usually asleep: " + minuteUsuallyAsleep);

  }

  public static int calculateMinuteUsuallyAsleep(int id, TreeMap<Integer, List<String>> guardsMap){
    List<String> sleepTime = guardsMap.get(id);
    int[] minutes = new int[60];
    for(int minute : minutes){
      minute = 0;
    }

    for(String line : sleepTime){
      int sleepMinute = Integer.parseInt(line.substring(0,2));
      int awakeMinute = Integer.parseInt(line.substring(3,5));

      for(int i = sleepMinute; i < awakeMinute; i++){
        minutes[i]++;
      }
    }

    int max = 0;
    int minute = -1;

    for (int i = 0; i < minutes.length - 1; i++) {
      if(minutes[i] > max){
        max = minutes[i];
        minute = i;
      }
    }

    return minute;
  }


  public static int calculateTotalSleepTime(List<String> sleepInterval){
    int sleepTime = 0;

    for(String line : sleepInterval){
      int sleepMinute = Integer.parseInt(line.substring(0,2));
      int awakeMinute = Integer.parseInt(line.substring(3,5));

      sleepTime += awakeMinute - sleepMinute;
    }

    return sleepTime;
  }

  public static TreeMap<Integer, List<String>> addGuardsToMap(List<List<String>> separatedGuardsNotes) {
    TreeMap<Integer, List<String>> guards = new TreeMap<>();

    for(List<String> singleGuardNotes : separatedGuardsNotes){
      int id = -1;
      List<String> sleepStart = new ArrayList<>();
      List<String> sleepEnd = new ArrayList<>();
      List<String> sleepInterval = new ArrayList<>();

      for(String line : singleGuardNotes){
        if(line.contains("Guard")) id = parseGuardId(line);
        if(line.contains("falls asleep")){
          sleepStart.add(line.substring(15,17));
        }
        if(line.contains("wakes up")){
          sleepEnd.add(line.substring(15,17));
        }
      }

      for(int i = 0; i < sleepStart.size(); i++){
        sleepInterval.add(sleepStart.get(i) + "-" + sleepEnd.get(i));
      }

      if(!guards.containsKey(id)){
        guards.put(id,sleepInterval);
      }
      else{
        for(String line : sleepInterval){
          guards.get(id).add(line);
        }
      }
    }

    return guards;
  }

  public static int parseGuardId(String line){
    String id = line.substring(line.indexOf("#") + 1, line.indexOf("b") - 1);

    return Integer.parseInt(id);
  }

  public static List<List<String>> parseGuardsInput(Stack<String> notes) {
    List<List<String>> singleGuardInputs = new ArrayList<>();
    List<String> guardInput;

    while (!notes.isEmpty()) {
      if (notes.peek().contains("Guard")){
        guardInput = new ArrayList<>();
        guardInput.add(notes.pop());
        while(!notes.isEmpty() && !notes.peek().contains("Guard")){
          guardInput.add(notes.pop());
        }
        singleGuardInputs.add(guardInput);
      }
    }

    return singleGuardInputs;
  }

  public static Stack<String> convertListToStack(List<String> notes) {
    Stack<String> output = new Stack<>();

    for (int i = notes.size() - 1; i >= 0; i--) {
      output.add(notes.get(i));
    }

    return output;
  }

}
