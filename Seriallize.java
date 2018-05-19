package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Seriallize {
    public static void serialize (Object o, String path) throws IllegalAccessException, IOException {
        Class<?> cls = o.getClass();
        StringBuilder sb = new StringBuilder();
        Field[] field = cls.getDeclaredFields();
        for (Field f : field){
            if(f.isAnnotationPresent(Save.class)){
                if (Modifier.isPrivate(f.getModifiers())){
                    f.setAccessible(true);
                }
                sb.append(f.getName() + "-" + f.get(o) + ";");
            }
        }
        FileWriter fw = new FileWriter(path);
        fw.write(sb.toString());
        fw.close();
    }

    public static <T> T deserialize(String path, Class<T> cls) throws IllegalAccessException, InvalidParameterException, InstantiationException, IOException, NoSuchFieldException {
        FileReader fr = new FileReader(path);
        Scanner sc = new Scanner(fr);
        T res = (T) cls.newInstance();
        if (sc.hasNext()){
            String str = sc.nextLine();
            String[] pairs = str.split(";");
            for (String p : pairs){
                String[] nv = p.split("-");
                if (nv.length != 2){
                    throw new InvalidParameterException();
                }
                String name = nv[0];
                String value = nv[1];
                Field f = cls.getDeclaredField(name);

                if (Modifier.isPrivate(f.getModifiers())){
                    f.setAccessible(true);
                }

                if(f.isAnnotationPresent(Save.class)){
                    if (f.getType() == int.class){
                        f.setInt(res, Integer.parseInt(value));
                    } else if (f.getType() == String.class){
                        f.set(res, value);
                    } else if (f.getType() == char.class){
                        f.setChar(res, value.charAt(0));
                    }
                }

            }

        }
        sc.close();
        fr.close();
        return res;
    }
}
