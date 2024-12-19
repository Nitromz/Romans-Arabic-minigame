/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vivaq3;

/**
 *
 * @author nitrom
 */
import java.util.Scanner;
import java.util.HashSet;

public class Romans {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here 
        Scanner keyboard = new Scanner(System.in);
        int N = 0;
        int correct = 0;
        System.out.println("Please enter the names of the users");
        String name_input = keyboard.nextLine();
        System.out.println("Enter the value of N statements (1-10)");
        do {
            N = keyboard.nextInt();
            if (N < 0 || N > 10) {
                System.out.println("Please enter a valid N value (1 - 10)");
            }
        } while (N < 0 || N > 10);

        keyboard.nextLine();

        String user_input[] = new String[N];
        String answer[] = new String[N];
        for (int a = 0; a < N; a++) {

            String operator_input;

            user_input[a] = keyboard.nextLine();

            String separatedUser_input[] = user_input[a].split(" ");

            if (separatedUser_input.length != 5) {
                answer[a] = ("Invalid statement.");
            } else if (separatedUser_input[1].equals("+") || separatedUser_input[1].equals("-") || separatedUser_input[1].equals("*") || separatedUser_input[1].equals("/") || separatedUser_input[1].equals("%") || separatedUser_input[1].equals("^") && separatedUser_input[3].equals("=")) {
                String operand1 = separatedUser_input[0];
                String operator = separatedUser_input[1];
                String operand2 = separatedUser_input[2];
                String user_answer = separatedUser_input[4];

                int operand1Arabic = convertArabics(operand1);
                int operand2Arabic = convertArabics(operand2);
                int user_answerArabic = convertArabics(user_answer);
                int computerAnswerArabic = 0;
                String computerAnswerRoman;

                if (operand1Arabic == -1 || operand2Arabic == -1) {
                    answer[a] = "Invalid Statement. (Invalid operand)";

                } 
                else if(user_answerArabic == -1){
                    answer[a] = "Invalid Statement. (Invalid answer)";}
                else {
                    switch (operator) {
                        case "+":
                            computerAnswerArabic = operand1Arabic + operand2Arabic;
                            break;

                        case "-":
                            computerAnswerArabic = operand1Arabic - operand2Arabic;
                            break;

                        case "*":
                            computerAnswerArabic = operand1Arabic * operand2Arabic;
                            break;

                        case "/":
                            computerAnswerArabic = operand1Arabic / operand2Arabic;
                            break;

                        case "%":
                            computerAnswerArabic = operand1Arabic % operand2Arabic;
                            break;

                        case "^":
                            computerAnswerArabic = (int) Math.pow(operand1Arabic, operand2Arabic);
                            break;

                    }

                    if (user_answerArabic == computerAnswerArabic) {
                        answer[a] = (operand1Arabic + " " + operator + " " + operand2Arabic + " = " + user_answerArabic + " is correct.");
                        correct++;
                    } else {
                        if (computerAnswerArabic <= 0){
                            answer[a] = "Invalid Statement.";}
                        else{
                        answer[a] = (operand1Arabic + " " + operator + " " + operand2Arabic + " = " + user_answerArabic + " is wrong, as the answer should be " + convertRomans(computerAnswerArabic) + ", which is " + computerAnswerArabic + " in Arabic numeral form.");
                    }
                    }
                }
            } else {
                answer[a] = "Invalid Statement. (Invalid Format)";
               

            }

        }

        
        System.out.print("Statement for the Roman Numeral test are sent in by ");
        generateInitials(name_input);
        System.out.println("");
        for (String output : answer) {
            System.out.println(output);
        }
        
        System.out.println("\nNumber of Correct Statements = " + correct);
        System.out.println("Percentage of Correct Statements = " + (((double)correct / N) * 100) + "%");

    }

    public static void generateInitials(String names_input) {

        String[] separateNames = names_input.split(", ");
        String[] initials = new String[separateNames.length];

        for (int a = 0; a < separateNames.length; a++) {

            separateNames[a] = separateNames[a].replaceAll("[.'_,@]+", "");   // the regex [.'_,@] means to remove any of the characters in the bracket with '+' meaning if it occurs more than once. The second string replaces it with whitespace.
            

            
            separateNames[a] = separateNames[a].replaceAll("^\\s+|\\s+$", ""); //remove trailing and ending whitespaces respectively
            separateNames[a] = separateNames[a].replaceAll("\\s{2,}", " "); // whitespaces longer than 2 
            
            String regexWords = "(?i)\\b(bin|binti|a/l|a/p|al|ap)\\b";// "\\b (word \\b" is to make sure the entire word boundary is used, (?i) means to ignore all case sensitivies, and the words between those boundaries are removed.
            initials[a] = separateNames[a].replaceAll(regexWords, "");
            initials[a] = initials[a].replaceAll("\\W|[a-z]", ""); // remove any non-word character that is in the range of [a-z]

        }

        for (int a = 0; a < separateNames.length; a++) {
            if (a != separateNames.length - 1) {
                System.out.print(initials[a] + ", ");
            } else {
                System.out.print(initials[a]);
            }
        }
        System.out.print(" (");
        for (int a = 0; a < separateNames.length; a++) {
            if (a != separateNames.length - 1) {
                System.out.print(separateNames[a] + ", ");
            } else {
                System.out.print(separateNames[a]);
            }
        }
        System.out.print(")\n");
    }

    public static int convertArabics(String romanNumerals) {
        romanNumerals = romanNumerals.toUpperCase();
        HashSet<String> unique = new HashSet<>();
        int sum = 0;

        for (int a = 0; a < romanNumerals.length(); a++) {

            if (romanNumerals.charAt(a) == 'I') { // If current letter is I
                if (unique.contains("IV") || unique.contains("IX")) {
                    return -1;
                } else if (a == romanNumerals.length() - 1) {
                    sum += 1;
                } else if (romanNumerals.charAt(a + 1) == 'V') {
                    sum += 4;
                    a++;
                    unique.add("IV");
                    unique.add("V"); // V can't be repeated

                } else if (romanNumerals.charAt(a + 1) == 'X') {
                    sum += 9;
                    a++;
                    unique.add("IX");

                } else if (romanNumerals.charAt(a + 1) == 'I') {
                    if (a <= romanNumerals.length() - 4) {
                        if (romanNumerals.charAt(a + 1) == 'I' && romanNumerals.charAt(a + 2) == 'I' && romanNumerals.charAt(a + 3) == 'I') {
                            return -1;
                        } //end the program and mark as invalid (I is repeated 4 times).
                    }
                    sum += 1;
                } else {
                    return - 1;
                }

            } else if (romanNumerals.charAt(a) == 'V') { //If current letter is V
                if (unique.contains("IV") || unique.contains("V")|| unique.contains("IX")) { //V can't be included after IV , as 9 is represented by IX and also can't be included after IX (as something like 14 is represented by XIV, not IXV)
                    return -1;
                } else if (a == romanNumerals.length() - 1) {
                    sum += 5;

                } else if (romanNumerals.charAt(a + 1) == 'I') { // the only valid letter after V
                    sum += 5;
                } else {
                    return -1;
                }
                unique.add("V"); //V cannot be repeated

            } else if (romanNumerals.charAt(a) == 'X') { //If current letter is X
                if (unique.contains("IV") ||unique.contains("IX") || unique.contains("XC") || unique.contains("XL")) { //X can't be included after IX, as 19 is represented by XIX. IV can't preceed a bigger number.
                    return -1;
                } else if (a == romanNumerals.length() - 1) {
                    sum += 10;

                } else if (romanNumerals.charAt(a + 1) == 'L') {
                    sum += 40;
                    unique.add("XL");
                    unique.add("L"); //L cannot be repeated.
                    a++;
                } else if (romanNumerals.charAt(a + 1) == 'C') {
                    sum += 90;
                    unique.add("XC");
                    a++;
                } else if (romanNumerals.charAt(a + 1) == 'I' || romanNumerals.charAt(a + 1) == 'V') { // only possible numerals are I and V after X
                    sum += 10;
                } else if (romanNumerals.charAt(a + 1) == 'X') {
                    if (a <= romanNumerals.length() - 4) {
                        if (romanNumerals.charAt(a + 1) == 'X' && romanNumerals.charAt(a + 2) == 'X' && romanNumerals.charAt(a + 3) == 'X') {
                            return -1;
                        } //end the program and mark as invalid (I is repeated 4 times).
                    }
                    sum += 10;
                } else {
                    return -1;
                }

            } else if (romanNumerals.charAt(a) == 'C') { //If current letter is C
                if (unique.contains("IV") ||unique.contains("IX") || unique.contains("XC") || unique.contains("XL") || unique.contains("CD") || unique.contains("CM")) { //IV - XC, can't preceed a bigger number. C can't be used if it is already used as a subtractive pair.
                    return -1;
                } else if (a == romanNumerals.length() - 1) {
                    sum += 100;

                } else if (romanNumerals.charAt(a + 1) == 'D') {
                    sum += 400;
                    unique.add("CD");
                    a++;
                } else if (romanNumerals.charAt(a + 1) == 'M') {
                    sum += 900;
                    unique.add("CM");
                    a++;
                } else if (romanNumerals.charAt(a + 1) == 'I' || romanNumerals.charAt(a + 1) == 'V' || romanNumerals.charAt(a + 1) == 'X' || romanNumerals.charAt(a + 1) == 'L') { // only possible numerals are I and V after X
                    sum += 100;
                } else if (romanNumerals.charAt(a + 1) == 'C') {
                    if (a <= romanNumerals.length() - 4) {
                        if (romanNumerals.charAt(a + 1) == 'C' && romanNumerals.charAt(a + 2) == 'C' && romanNumerals.charAt(a + 3) == 'C') {
                            return -1;
                        } //end the program and mark as invalid (I is repeated 4 times).
                    }
                    sum += 100;
                } else {
                    return -1;
                }

            } else if (romanNumerals.charAt(a) == 'L') { //If current letter is L
                if (unique.contains("IV") ||unique.contains("IX") || unique.contains("XC") ||unique.contains("L") || unique.contains("XL")) {
                    return -1;
                } else if (a == romanNumerals.length() - 1) {
                    sum += 50;
                } else if (romanNumerals.charAt(a + 1) == 'X' || romanNumerals.charAt(a + 1) == 'V' || romanNumerals.charAt(a + 1) == 'I') {
                    sum += 50;
                } else {
                    return -1;
                }

                unique.add("L");
            } else if (romanNumerals.charAt(a) == 'D') { //If current letter is D
                if (unique.contains("D") || unique.contains("CD") || unique.contains("IV") ||unique.contains("IX") || unique.contains("XC") || unique.contains("XL") || unique.contains("CM")) {
                    return -1;
                } else if (a == romanNumerals.length() - 1) {
                    sum += 500;
                } else if (romanNumerals.charAt(a + 1) == 'I' || romanNumerals.charAt(a + 1) == 'V' || romanNumerals.charAt(a + 1) == 'X' || romanNumerals.charAt(a + 1) == 'L' || romanNumerals.charAt(a + 1) == 'C') { // only possible numerals are I and V after X
                    sum += 500;
                } else {
                    return -1;
                }

                unique.add("D");

            } else if (romanNumerals.charAt(a) == 'M') { //If current letter is M
                if (unique.contains("CM") || unique.contains("D") || unique.contains("CD") || unique.contains("IV") ||unique.contains("IX") || unique.contains("XC") || unique.contains("XL")) {
                    return -1;
                } else if (a == romanNumerals.length() - 1) {
                    sum += 1000;
                } else if (romanNumerals.charAt(a + 1) == 'M') {
                    if (a <= romanNumerals.length() - 4) {
                        if (romanNumerals.charAt(a + 1) == 'M' && romanNumerals.charAt(a + 2) == 'M' && romanNumerals.charAt(a + 3) == 'M') {
                            return -1;
                        } //end the program and mark as invalid (I is repeated 4 times).
                    }
                    sum += 1000;
                } else {
                    sum += 1000;
                }

            } else {
                return -1;
            }

        }

        return sum;
    }

    public static String convertRomans(int arabics) {
        StringBuilder romansBuilder = new StringBuilder();
        String romans = "";

        if (arabics > 3999) {
            romans = "Exceeded max range!";
        } else {
            String arabicsstr = String.valueOf(arabics);
            int arabicLength = arabicsstr.length();

            int currentPosition = 0;
            while (arabics != 0) {
                int a = 0;
                int currentDigit = arabics % 10;
                if (currentPosition == 0) {
                    switch (currentDigit) {
                        case 1:
                            romansBuilder.insert(a, "I");
                            break;
                        case 2:
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "I");
                            break;
                        case 3:
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "I");
                            break;
                        case 4:
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "V");
                            break;
                        case 5:
                            romansBuilder.insert(a, "V");
                            break;
                        case 6:
                            romansBuilder.insert(a, "V");
                            a++;
                            romansBuilder.insert(a, "I");
                            break;
                        case 7:
                            romansBuilder.insert(a, "V");
                            a++;
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "I");
                            break;
                        case 8:
                            romansBuilder.insert(a, "V");
                            a++;
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "I");
                            break;
                        case 9:
                            romansBuilder.insert(a, "I");
                            a++;
                            romansBuilder.insert(a, "X");

                            break;
                    }
                } else if (currentPosition == 1) {
                    switch (currentDigit) {
                        case 1:
                            romansBuilder.insert(a, "X");
                            break;
                        case 2:
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "X");
                            break;
                        case 3:
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "X");
                            break;
                        case 4:
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "L");
                            break;
                        case 5:
                            romansBuilder.insert(a, "L");
                            break;
                        case 6:
                            romansBuilder.insert(a, "L");
                            a++;
                            romansBuilder.insert(a, "X");
                            break;
                        case 7:
                            romansBuilder.insert(a, "L");
                            a++;
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "X");
                            break;
                        case 8:
                            romansBuilder.insert(a, "L");
                            a++;
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "X");
                            break;
                        case 9:
                            romansBuilder.insert(a, "X");
                            a++;
                            romansBuilder.insert(a, "C");
                            break;
                    }
                } else if (currentPosition == 2) {
                    switch (currentDigit) {
                        case 1:
                            romansBuilder.insert(a, "C");
                            break;
                        case 2:
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "C");
                            break;
                        case 3:
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "C");
                            break;
                        case 4:
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "D");
                            break;
                        case 5:
                            romansBuilder.insert(a, "D");
                            break;
                        case 6:
                            romansBuilder.insert(a, "D");
                            a++;
                            romansBuilder.insert(a, "C");
                            break;
                        case 7:
                            romansBuilder.insert(a, "D");
                            a++;
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "C");
                            break;
                        case 8:
                            romansBuilder.insert(a, "D");
                            a++;
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "C");
                            break;
                        case 9:
                            romansBuilder.insert(a, "C");
                            a++;
                            romansBuilder.insert(a, "M");
                            break;
                    }
                } else if (currentPosition == 3) {
                    switch (currentDigit) {
                        case 1:
                            romansBuilder.insert(a, "M");
                            break;
                        case 2:
                            romansBuilder.insert(a, "M");
                            a++;
                            romansBuilder.insert(a, "M");
                            break;
                        case 3:
                            romansBuilder.insert(a, "M");
                            a++;
                            romansBuilder.insert(a, "M");
                            a++;
                            romansBuilder.insert(a, "M");
                            break;

                    }

                }
                a++;
                currentPosition++;
                arabics /= 10;

            }

            romans = romansBuilder.toString();

        }
        return romans;
    }
}
