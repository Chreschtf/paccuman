import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Hashtable;


public class GetData {
  private String[] initLab;
  private int monsters;
  private int candies;
  private int m;
  private int n;
  private int[][] positions;

  public GetData(String fileName){
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      saveInfos(bufferedReader);
      bufferedReader.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println( "Unable to open file '" + fileName + "'");
    }
    catch(IOException ex) {
      System.out.println( "Error reading file '" + fileName + "'");
    }
  }

  private void saveInfos(BufferedReader reader){
    try {
      String line = reader.readLine();

      String[] parts = line.split(" fois "); // les dimensions:
      m = Integer.parseInt(parts[0].substring(12));
      n = Integer.parseInt(parts[1]);

      int nbrLines = (m*2) + 1;
      initLab = new String[nbrLines];

      int k = 0;
      while((line = reader.readLine()) != null && k < nbrLines) { // le labyrinthe
        System.out.println(line);
        initLab[k] = line;
        k++;
      }
      //enregrister monstres, bonbons et pakkuman:
      line = reader.readLine();
      monsters = Integer.parseInt(line.substring(11));
      line = reader.readLine();
      candies = Integer.parseInt(line.substring(10));
      line = reader.readLine();

      positions = new int[1+monsters+candies][2]; // enregristre les positions
      int j = 0, i = 0;
      while((line = reader.readLine()) != null)
      {
        i=0;
        for(String retval: line.split("\\("))
        {
          if (i>0)
          {
            String[] parts2 = retval.split(",");
            positions[j][0] = Integer.parseInt(parts2[0]);
            parts2[1]=parts2[1].trim();
            positions[j][1] = Integer.parseInt(parts2[1].substring(0,parts2[1].length()-1));
            j++;
          }
          i++;
        }
      }
    }
    catch(IOException ex) {
      System.out.println( "Error reading file ");
    }
  }

  public String[] getLab(){
    return initLab;
  }

  public int getMonsters (){
    return monsters;
  }

  public int getCandies (){
    return candies;
  }

  public int getM (){
    return m;
  }

  public int getN (){
    return n;
  }

  public int[][] getPositions (){
    return positions;
  }
}
