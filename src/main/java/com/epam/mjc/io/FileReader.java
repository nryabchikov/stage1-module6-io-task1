package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        java.io.FileReader stream = null;
        try {
            stream = new java.io.FileReader(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int elem = 0;
            try {
                assert stream != null;
                elem = stream.read();
                if (elem == -1)
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append((char) elem);
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] array = getStringsFromFile(sb);
        String name = array[0];
        int age = Integer.parseInt(array[1]);
        String email = array[2];
        long phone = Long.parseLong(array[3]);
        return new Profile(name, age, email, phone);
    }
    private String[] getStringsFromFile(StringBuilder sb) {
        int count = 0;
        int counter = 0;
        String[] array = new String[4];
        while (count < sb.length()) {
            char elem = sb.charAt(count);
            if (elem == ':') {
                count++;
                StringBuilder data = new StringBuilder();
                while (elem != '\n') {
                    count++;
                    elem = sb.charAt(count);
                    data.append(elem);
                }
                data.delete(data.length() - 1, data.length());
                array[counter] = data.toString();
                counter++;
            }
            count++;
        }
        return array;
    }
}
