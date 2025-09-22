import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str1_1 = "Payed";
        String str1_2 = "Play";
        String str1_3 = "PAYED";

        System.out.println("[ Test 1_1 ] Result = "+isEndIsED(str1_1));
        System.out.println("[ Test 1_2 ] Result = "+isEndIsED(str1_2));
        System.out.println("[ Test 1_3 ] Result = "+isEndIsED(str1_3));

        System.out.println("-----------------------------------------------------");

        String str2_1 = "Five is 5 and ten is 10";
        String str2_2 = "7 + 5 = twelve";
        String str2_3 = "12345";

        System.out.println("[ Test 2_1 ] Result = "+sumInString(str2_1));
        System.out.println("[ Test 2_2 ] Result = "+sumInString(str2_2));
        System.out.println("[ Test 2_3 ] Result = "+sumInString(str2_3));

        System.out.println("-----------------------------------------------------");

        String t3_1 = "aaBCS";
        String t3_2 = "aabbbccccd";
        String t3_3 = "XYZ";
        String t3_4 = "";

        System.out.println("[ Test 3_1 ] Result = " + longestBlock(t3_1)); // 2
        System.out.println("[ Test 3_2 ] Result = " + longestBlock(t3_2)); // 4
        System.out.println("[ Test 3_3 ] Result = " + longestBlock(t3_3)); // 1
        System.out.println("[ Test 3_4 ] Result = " + longestBlock(t3_4)); // 0

        System.out.println("-----------------------------------------------------");

        String t4_1 = "  Це   приклад   рядка з  словами  ";
        String t4_2 = "One two three";
        String t4_3 = "   AloneWord   ";

        System.out.println("[ Test 4_1 ] Words:");
        printWords(t4_1);

        System.out.println("[ Test 4_2 ] Words:");
        printWords(t4_2);

        System.out.println("[ Test 4_3 ] Words:");
        printWords(t4_3);

        System.out.println("-----------------------------------------------------");


        String a1 = "ABC";
        String b1 = "12345";
        String a2 = "Hello";
        String b2 = "WORLD";
        String a3 = "";
        String b3 = "OnlySecond";

        System.out.println("[ Test 5_1 ] Result = " + mergeAlternating(a1, b1)); // A1B2C345
        System.out.println("[ Test 5_2 ] Result = " + mergeAlternating(a2, b2)); // HWeOlRlLoD
        System.out.println("[ Test 5_3 ] Result = " + mergeAlternating(a3, b3)); // OnlySecond
    }

    public static boolean isEndIsED(String str)
    {
        str = str.toLowerCase();
        return str.charAt(str.length()-1) == 'd'&&str.charAt(str.length()-2)=='e';
    }
    public static int sumInString(String str){
        int sum =0;
        for(int i =0;i<str.length();i++){
            if(Character.isDigit(str.charAt(i))) {
                sum+=57 - str.charAt(i);
            }
        }
        return  sum;
    }
    public static int longestBlock(String s) {
        if (s == null || s.isEmpty()) return 0;

        int maxLen = 1;
        int currentLen = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentLen++;
                maxLen = Math.max(maxLen, currentLen);
            } else {
                currentLen = 1;
            }
        }

        return maxLen;
    }

    public static void printWords(String s) {
        if (s == null || s.isEmpty()) return;

        String[] words = s.trim().split("\\s+");
        for (String word : words) {
            System.out.println(word);
        }
    }

    public static String mergeAlternating(String a, String b) {
        if (a == null) a = "";
        if (b == null) b = "";

        StringBuilder result = new StringBuilder();
        int maxLen = Math.max(a.length(), b.length());

        for (int i = 0; i < maxLen; i++) {
            if (i < a.length()) result.append(a.charAt(i));
            if (i < b.length()) result.append(b.charAt(i));
        }

        return result.toString();
    }

}

