package day3;

import utils.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FabricCutter {

  private static int[][] fabric;

  public static void main(String[] args) throws IOException {
    setupFabric();

    FileReader fileReader = new FileReader("/fabricClaims.txt");
    List<String> claims = fileReader.getLinesFromFile();
    List<String[]> trimmedClaims = prepareClaims(claims);

    insertClaims(trimmedClaims);

    testClaims(trimmedClaims);

  }

  public static void testClaims(List<String[]> preparedClaims) {

    for (int i = 0; i < preparedClaims.size(); i++) {
      String[] claim = preparedClaims.get(i);
      int column = Integer.parseInt(claim[0].split("x")[0]);
      int row = Integer.parseInt(claim[0].split("x")[1]);

      int rectangleWidth = Integer.parseInt(claim[1].split("x")[0]);
      int rectangleHeight = Integer.parseInt(claim[1].split("x")[1]);

      boolean hit = true;

      for (int k = column; k < column + rectangleWidth; k++) {
        for (int l = row; l < row + rectangleHeight; l++) {
          if (fabric[k][l] > 1) {
            hit = false;
          }
        }
      }

      if (hit) System.out.println("Claim id: " + i + 1);
    }
  }

  public static void printFabric() {
    for (int i = 0; i < fabric.length; i++) {
      for (int j = 0; j < fabric[i].length; j++) {
        System.out.print(fabric[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static int countOverlappingFabric() {
    int output = 0;

    for (int i = 0; i < fabric.length; i++) {
      for (int j = 0; j < fabric[i].length; j++) {
        if (fabric[i][j] >= 2) output++;
      }
    }

    return output;
  }

  public static void insertClaims(List<String[]> preparedClaims) {
    for (String[] claim : preparedClaims) {
      insertSingleClaim(claim);
    }
  }

  public static void insertSingleClaim(String[] claim) {
    int column = Integer.parseInt(claim[0].split("x")[0]);
    int row = Integer.parseInt(claim[0].split("x")[1]);

    int rectangleWidth = Integer.parseInt(claim[1].split("x")[0]);
    int rectangleHeight = Integer.parseInt(claim[1].split("x")[1]);
    
    for (int i = column; i < column + rectangleWidth; i++) {
      for (int j = row; j < row + rectangleHeight; j++) {
        fabric[i][j]++;
      }
    }
  }


  public static List<String[]> prepareClaims(List<String> claims) {
    List<String[]> trimmedClaims = new ArrayList<>();
    String tempClaim;
    String[] claimToAdd;
    int atIndex;

    for (String claim : claims) {
      atIndex = claim.indexOf("@");
      tempClaim = claim.substring(atIndex + 2).replace(":", "");
      tempClaim = tempClaim.replace(",", "x");
      claimToAdd = tempClaim.split(" ");
      trimmedClaims.add(claimToAdd);
    }

    return trimmedClaims;
  }

  public static void setupFabric() {
    fabric = new int[1000][1000];

    for (int i = 0; i < fabric.length; i++) {
      for (int j = 0; j < fabric[i].length; j++) {
        fabric[i][j] = 0;
      }
    }
  }
}
