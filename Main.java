package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
	Test test = new Test(123, "jhvjhv", 'h');
	String path = "temp.txt";

    Seriallize.serialize(test, path);
    Test res = Seriallize.deserialize(path, Test.class);
    System.out.println("New object:");
    System.out.println(res.toString());

    }
}
