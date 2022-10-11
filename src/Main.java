import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String operation = console.nextLine();
        try {
            System.out.println(calc(operation));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String calc(String input) throws IOException {
        String[] stringArr = input.split(" ");
        if (stringArr.length!=3) throw new IOException();
        int num1;
        int num2;

        boolean b = stringArr[0].charAt(0) >= '0' && stringArr[0].charAt(0) <= '9';
        boolean a = stringArr[2].charAt(0) >= '0' && stringArr[2].charAt(0) <= '9';
        if (b!=a) throw new IOException();
        if (b){
            num1 = Integer.parseInt(stringArr[0]);
            num2 = Integer.parseInt(stringArr[2]);
        } else {
            num1=parseFromRoman(stringArr[0]);
            num2=parseFromRoman(stringArr[2]);
            if (num1<1 || num1>10 || num2<1 || num2>10) throw new IOException();

        }

        int result = 0;

        switch  (stringArr[1]) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;


        }

        if (!b && result<1) throw new IOException();
    return "" + (b ? result : parseToRoman(result));


    }

    public static int parseFromRoman(String arg){
        HashMap < Character, Integer > map = new HashMap <>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        char[] chars = arg.toCharArray();
        int result = 0;
        for (int i = 0; i<chars.length; i++){
            result += map.get(chars[i]);
            if (i>0&&map.get(chars[i-1])<map.get(chars[i])) result-=2;
        }

        return result;
    }

    public static String parseToRoman (int arg){
        StringBuilder result = new StringBuilder();
        while(arg>=100){
            result.append("C");
            arg-=100;
        }
        while(arg>=90){
            result.append("XC");
            arg-=90;
        }
        while(arg>=50){
            result.append("L");
            arg-=50;
        }
        while(arg>=40){
            result.append("XL");
            arg-=40;
        }
        while(arg>=10){
            result.append("X");
            arg-=10;
        }
        while(arg>=9){
            result.append("IX");
            arg-=9;
        }
        while(arg>=5){
            result.append("V");
            arg-=5;
        }
        while(arg>=4){
            result.append("IV");
            arg-=4;
        }
        while(arg>=1){
            result.append("I");
            arg-=1;
        }
        return result.toString();
    }

}



