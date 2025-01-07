package programmer.zaman.now.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesApp {
    public static void main(String[] args) {

        try {
            Properties properties = new Properties();

            properties.load(new FileInputStream("application.properties"));

            System.out.println(properties.getProperty("DB.host"));
            System.out.println(properties.getProperty("DB.port"));
            System.out.println(properties.getProperty("DB.username"));
            System.out.println(properties.getProperty("DB.password"));
        } catch (FileNotFoundException exception){
            System.out.println("Gagal menemukan file");
        } catch (IOException exception){
            System.out.println("Gagal Load File");
        }

        try{
            Properties properties = new Properties();
            properties.put("firstName", "Muhammad");
            properties.put("lastName", "Rafli");
            properties.put("fullName", "Muhammad Rafli");

            properties.store(new FileOutputStream("name.properties"), "Konfigurasi Nama: ");
        }  catch (FileNotFoundException exception){
            System.out.println("Gagal menemukan file");
        } catch (IOException exception){
            System.out.println("Gagal Load File");
        }
    }
}
