package com.example.education;

import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

    DoFastMaths ob = DoFastMaths.getInstance();
    String[] options = new String[4];
    Random random = new Random();

    int answer_position;

    void commonPart(boolean sameLastDigit) {
        format_expression();
        ob.question.setText(ob.program_expression);
        String infix = infixToPostfix(ob.program_expression);
        ob.resultOfExpression = result(infix);
        Arrays.fill(options, "");
        answer_position = random.nextInt(4);
        for (int i = 0; i < options.length; i++) {
            if (answer_position != i) {
                int add_sub = 0;
                String opt = "";
                while (Arrays.asList(options).contains(opt)) { // if the option is already present in the option's list then omit the current option
                    while (add_sub == 0) // the number to be subtracted or added should not be zero
                        add_sub = random.nextInt(10);
                    if (sameLastDigit) // trying to make the last digit the same so that the person has to calculate the full expression
                        add_sub *= 10;
                    boolean b = random.nextBoolean(); // true for adding and false for subtracting
                    if (b)
                        opt = Integer.toString(ob.resultOfExpression + add_sub);
                    else
                        opt = Integer.toString(ob.resultOfExpression - add_sub);
                }
                options[i] = opt;
            } else
                options[i] = Integer.toString(ob.resultOfExpression);
        }
        ob.option1.setText(options[0]);
        ob.option2.setText(options[1]);
        ob.option3.setText(options[2]);
        ob.option4.setText(options[3]);
    }

    void create(int level) {
        try {
            int len = 0;
            StringBuilder expression = new StringBuilder();
            boolean isLastDigitTrySame = false;
            switch (level) {
                case 1:
                    len = 2;
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    isLastDigitTrySame = true;
                    len = 2;
                    break;
                case 10:
                    isLastDigitTrySame = true;
                    while (len <= 1)
                        len = random.nextInt(5);
            }
            int[] operands = new int[len];
            int rangeLow = 1;
            int rangeUpp = 1;
            int randomNum = 0;
            for (int i = 0; i < operands.length - 1; i++) {
                switch (level) {
                    case 1:
                        expression.append(random.nextInt(10)).append("+");
                        rangeLow = 1;
                        rangeUpp = 10;
                        break;
                    case 2:
                        while (randomNum < 10)
                            randomNum = random.nextInt(100);
                        expression.append(randomNum).append("+");
                        rangeLow = 10;
                        rangeUpp = 100;
                        break;
                    case 3:
                        while (randomNum < 100)
                            randomNum = random.nextInt(1000);
                        expression.append(randomNum).append("+");
                        rangeLow = 100;
                        rangeUpp = 1000;
                        break;
                    case 4:
                        expression.append(random.nextInt(10)).append("-");
                        rangeLow = 1;
                        rangeUpp = 10;
                        break;
                    case 5:
                        while (randomNum < 10)
                            randomNum = random.nextInt(100);
                        expression.append(randomNum).append("-");
                        rangeLow = 10;
                        rangeUpp = 100;
                        break;
                    case 6:
                        expression.append(random.nextInt(10)).append("*");
                        rangeLow = 1;
                        rangeUpp = 10;
                        break;
                    case 7:
                        while (randomNum < 10)
                            randomNum = random.nextInt(100);
                        expression.append(randomNum).append("*");
                        rangeLow = 10;
                        rangeUpp = 100;
                        break;
                    case 8:
                        while (randomNum < 1)
                            randomNum = random.nextInt(10);
                        int randomNum2 = random.nextInt(100);
                        while (randomNum2 * randomNum > 99)
                            randomNum2 = random.nextInt(100);
                        expression.append(randomNum * randomNum2).append("/").append(randomNum);
                        break;
                    case 9:
                        while (randomNum < 1)
                            randomNum = random.nextInt(10);
                        int randomNum3 = random.nextInt(1000);
                        while (randomNum3 * randomNum > 999 || randomNum3 * randomNum < 100)
                            randomNum3 = random.nextInt(1000);
                        expression.append(randomNum * randomNum3).append("/").append(randomNum);
                        break;
                    case 10:
                        expression.append(random.nextInt(100));
                        expression.append("-*+".charAt(random.nextInt(3)));
                        rangeLow = 1;
                        rangeUpp = 100;
                }
            }
            randomNum = 0;
            while (randomNum < rangeLow && level != 8 && level != 9)
                randomNum = random.nextInt(rangeUpp);
            if (level != 8 && level != 9)
                expression.append(randomNum);
            ob.program_expression = expression.toString();
            commonPart(isLastDigitTrySame);

            int timer_time_in_seconds = 0;

            switch (level) {
                case 1:
                    timer_time_in_seconds = 5;
                    break;
                case 2:
                    timer_time_in_seconds = 8;
                    break;
                case 3:
                    timer_time_in_seconds = 15;
                    break;
                case 4:
                    timer_time_in_seconds = 6;
                    break;
                case 5:
                    timer_time_in_seconds = 12;
                    break;
                case 6:
                    timer_time_in_seconds = 4;
                    break;
                case 7:
                    timer_time_in_seconds = 20;
                    break;
                case 8:
                    timer_time_in_seconds = 8;
                    break;
                case 9:
                    timer_time_in_seconds = 12;
                    break;
                case 10:
                    int numberOfProduct = 0;
                    int numberOfSubtraction = 0;
                    int numberOfAddition = 0;
                    for (int i = 0; i < ob.program_expression.length(); i++) {
                        char ch = ob.program_expression.charAt(i);
                        if (ch == '*')
                            numberOfProduct++;
                        else if (ch == '-')
                            numberOfSubtraction++;
                        else if (ch == '+')
                            numberOfAddition++;
                    }
                    timer_time_in_seconds = numberOfProduct * 18 + numberOfSubtraction * 10 + numberOfAddition * 9;
            }
            ob.obTimer.setStartTime(timer_time_in_seconds);

        } catch (Exception e) {
            ob.nextButton.setEnabled(true);
            Toast.makeText(ob, "an error occurred, click next", Toast.LENGTH_SHORT).show();
        }
    }


    void format_expression() {
        try {
            ob.program_expression += "  ";
            StringBuilder exp = new StringBuilder();
            for (int i = 0; i < ob.program_expression.length() - 2; i++) {
                exp.append(ob.program_expression.charAt(i));
                if (ob.program_expression.charAt(i) == '(' &&
                        ob.program_expression.charAt(i + 1) == '-' &&
                        Character.isDigit(ob.program_expression.charAt(i + 2)))
                    exp.append(" ").append(ob.program_expression.charAt((i++) + 1)).append(ob.program_expression.charAt((i++) + 1));
                if (!(Character.isDigit(ob.program_expression.charAt(i)) && Character.isDigit(ob.program_expression.charAt(i + 1))))
                    exp.append(" ");
            }
            ob.program_expression = exp.toString().trim();

        } catch (Exception e) {
            Toast.makeText(ob, "an error occurred in format_expression()", Toast.LENGTH_SHORT).show();
        }
    }


    int getPriority(String ch) {
        try {
            switch (ch) {
                case "+":
                case "-":
                    return 1;
                case "*":
                case "/":
                    return 2;
                case "^":
                    return 3;
            }
            return -1;

        } catch (Exception e) {
            Toast.makeText(ob, "an error occurred in getPriority()", Toast.LENGTH_SHORT).show();
            return -1;
        }
    }


    boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    String infixToPostfix(String exp) {
        try {
            StringBuilder result = new StringBuilder();
            Stack<String> stack = new Stack<>();
            StringTokenizer each_token = new StringTokenizer(exp);
            while (each_token.hasMoreTokens()) {
                String word = each_token.nextToken();
                if (isInteger(word))
                    result.append(word).append(" ");
                else if (word.equals("("))
                    stack.push(word);
                else if (word.equals(")")) {
                    while (!stack.isEmpty() && !stack.peek().equals("("))
                        result.append(stack.pop()).append(" ");
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && getPriority(word) <= getPriority(stack.peek()))
                        result.append(stack.pop()).append(" ");
                    stack.push(word);
                }

            }
            while (!stack.isEmpty()) {
                if (stack.peek().equals("("))
                    return "Invalid Expression";
                result.append(stack.pop()).append(" ");
            }
            return result.toString().trim();

        } catch (Exception e) {
            Toast.makeText(ob, "an error occurred in infixToPostfix()", Toast.LENGTH_SHORT).show();
            return "";
        }
    }


    int result(String expression) {
        try {
            Stack<Integer> values = new Stack<>();
            StringTokenizer exp = new StringTokenizer(expression);
            while (exp.hasMoreTokens()) {
                String word = exp.nextToken();
                if (isInteger(word))
                    values.add(Integer.parseInt(word));
                else {
                    int operator2 = values.get(values.size() - 1);
                    values.remove(values.size() - 1);
                    int operator1 = values.get(values.size() - 1);
                    values.remove(values.size() - 1);
                    switch (word) {
                        case "+":
                            values.add(operator1 + operator2);
                            break;
                        case "-":
                            values.add(operator1 - operator2);
                            break;
                        case "*":
                            values.add(operator1 * operator2);
                            break;
                        case "/":
                            values.add(operator1 / operator2);
                            break;
                        case "^":
                            values.add((int) Math.pow(operator1, operator2));
                    }
                }
            }
            return values.get(values.size() - 1);

        } catch (Exception e) {
            Toast.makeText(ob, "an error occurred in result()", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}
