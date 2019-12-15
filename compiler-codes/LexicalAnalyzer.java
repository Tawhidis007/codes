import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LexicalAnalyzer {

    File input;

    //declared arraylists
    ArrayList<String> matoperators = new ArrayList<>();
    ArrayList<String> logOperators = new ArrayList<>();
    ArrayList<String> keywords = new ArrayList<>();

    //result arraylists
    Set<String> resultmatops = new HashSet<>();
    Set<String> resultkeys = new HashSet<>();
    Set<String> resultlogicops = new HashSet<>();
    Set<String> numerics = new HashSet<>();
    Set<String> others = new HashSet<>();
    Set<String> identifiers = new HashSet<>();
    int count = 0;

    public LexicalAnalyzer() { //constructor
        declaration();
        read();
        plsprint();
    }

    void declaration() {
        //math operators
        matoperators.add("+");
        matoperators.add("-");
        matoperators.add("=");

        //KEYWORDS
        keywords.add("if");
        keywords.add("else");
        keywords.add("float");
        keywords.add("int");

        //logical operators 
        logOperators.add("<=");
        logOperators.add(">=");
        logOperators.add("!=");
        logOperators.add("==");
        logOperators.add("<");
        logOperators.add(">");

    }

    void read() {
        input = new File("input.txt");
        try {
            Scanner sc = new Scanner(input);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                checker(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void checker(String line) {
        String[] tokens = line.split(" ");
        count++;
        if( count==3){ //for debugging. problem is semicolon gets picked up
            System.out.println(tokens[4]);
        }
        for (int i = 0; i < tokens.length; i++) {

            for (int j = 0; j < matoperators.size(); j++) {
                if (tokens[i].equals(matoperators.get(j))) {
                    resultmatops.add(tokens[i]);
                    continue;
                }
            }
            for (int j = 0; j < keywords.size(); j++) {
                if (tokens[i].equals(keywords.get(j))) {
                    resultkeys.add(tokens[i]);
                    continue;
                }
            }
            for (int j = 0; j < logOperators.size(); j++) {
                if (tokens[i].equals(logOperators.get(j))) {
                    resultlogicops.add(tokens[i]);
                    continue;
                }
            }

            if (isNumeric(tokens[i]) == true) {
                System.out.println(tokens[i]);
                numerics.add(tokens[i]); //DOES not work for negative values
            }
            if (isOthers(tokens[i]) == true) {
                others.add(tokens[i]);
            }
            if (isAlpha(tokens[i]) == true
                    && !tokens[i].equals("if")
                    && !tokens[i].equals("else")
                    && !tokens[i].equals("float")
                    && !tokens[i].equals("int")) {
                identifiers.add(tokens[i]);
                
               
            }
        }
        
    }

    static boolean isNumeric(String str) {
        //return str.matches("-?\\d+(\\\\d+)?");///for numeral check
        return str.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
//        try {
//            double d = Double.parseDouble(str);
//        } catch (NumberFormatException nfe) {
//            return false;
//        }
//        return true;
//        
        
        
    }


    boolean isOthers(String str) {
        return str.matches("^[,;{()}]+$"); //^start line,[start char,+prev must have, 
        //]end of char,$end of line
    }

    boolean isAlpha(String str) {
        return str.matches("[A-Za-z][a-zA-Z0-9]*");
    }

    void plsprint() {
       
        //PRINTING KEYWORDS
        System.out.print("Keywords are : ");
        for (String s : resultkeys) {
            System.out.print(s + " ");
        }
        System.out.println();
        
         //PRINTING identifiers
        System.out.print("Identifiers are : ");
        for (String s : identifiers) {
            System.out.print(s + " ");
        }
        System.out.println();
        
        //PRINTING MATH OPERATORS
        System.out.print("Math Operators are : ");
        for (String s : resultmatops) {
            System.out.print(s + " ");
        }
        System.out.println();

        //PRINTING LOGICAL OPERATORS
        System.out.print("Logical Operators are : ");
        for (String s : resultlogicops) {
            System.out.print(s + " ");
        }
        System.out.println();

        //PRINTING NUMERICAL VALUES 
        System.out.print("Numerical Values are : ");
        for (String s : numerics) {
            System.out.print(s + " ");
        }
        System.out.println();

        //PRINTING Other values
        System.out.print("Others : ");
        for (String s : others) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Compiler_Lab01();
    }
}
