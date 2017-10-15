package za.co.bjorn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BufferedReader br = null;

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        try {
            br = new BufferedReader(new FileReader("./maps/small_map.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
