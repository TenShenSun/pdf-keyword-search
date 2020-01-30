package pdfop.demo;


import java.io.BufferedReader;
import java.io.FileReader;

public class Chater2 {
    public static void main(String[] args) {
        String str = "test.pdf";
        try {
            // FileInputStream fis = new FileInputStream(str);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            while(bufferedReader.readLine()!=null){
                System.out.println(bufferedReader.readLine());
            }
            //System.out.println(bufferedReader.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
