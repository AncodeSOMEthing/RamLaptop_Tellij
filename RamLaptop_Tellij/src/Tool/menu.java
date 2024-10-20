package Tool;

import java.util.Scanner;

public class menu {
    public static int getChoice (String [] options){
        for(int i=0;i<options.length;i++){
            System.out.println((i+1)+". "+options[i]+".");
        }
        System.out.println("Choose 1 of "+options.length+": ");
        Scanner sc = new Scanner(System.in);
        try {
            return Integer.parseInt(sc.nextLine());
        }catch (Exception e){
            return -1;
        }
    }
}
