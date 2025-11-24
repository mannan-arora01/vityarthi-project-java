package com.hms.util;

import java.io.*;
import java.util.List;

// Handles binary file I/O using Java Serialization
public class DataManager {

    public static void saveData(List<?> data, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        }
    }

    public static List<?> loadData(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<?>) ois.readObject();
        }
    }
}