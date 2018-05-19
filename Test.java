package com.company;

public class Test {
    @Save public int one;
    @Save private String two;
    @Save public char three;
    private int CONSTAT = 999;

    public Test(int one, String two, char three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }
    public Test() {}

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public char getThree() {
        return three;
    }

    public void setThree(char three) {
        this.three = three;
    }

    public int getCONSTAT() {
        return CONSTAT;
    }

    @Override
    public String toString() {
        return ("One: " + this.getOne() + "; Two: " + this.getTwo() + "; Three: " + this.getThree() + "; Constanta: " + this.getCONSTAT());
    }
}
