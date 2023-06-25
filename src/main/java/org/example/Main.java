package org.example;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

class Source {
    private final String str;
    private int begin;

    public Source(String str) {
        this.str =str;
    }

    public final int peek(){
        if (begin < str.length()){
            return str.charAt(begin);
        }
        return -1;
    }

    public final void next(){
        begin++;
    }
}

class Parser extends Source{
    public Parser(String str){
        super(str);
    }
    public final int number() {
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = peek()) >= 0 && Character.isDigit(ch)) {
            sb.append((char) ch);
            next();
        }
        return Integer.parseInt(sb.toString());
    }

    public final int exp(){
        int num = term();
        for(;;) {
            switch ((peek())) {
                case '+':
                    next();
                    num += term();
                    continue;
                case '-':
                    next();
                    num -= term();
                    continue;
            }
            break;
        }
        return num;
    }

    public final int term(){
        int num = factor();
        for(;;){
            switch (peek()){
                case '*':
                    next();
                    num *= factor();
                    continue;
                case '/':
                    next();
                    num /= factor();
                    continue;
            }
            break;
        }
        return num;
    }

    public final int factor(){
        if(peek() == '('){
            next();
            int fac = exp();
            if(peek() == ')'){
                next();
            }
            return fac;
        }
        return number();
    }
}

public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

            String str = null;
        try {
            str = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Result is " + new Parser(str).exp());
    }
}

