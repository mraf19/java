package programmer.zaman.now.lambda.app;

import java.util.function.Supplier;

public class LazyApp {
    public static void main(String[] args) {
        testScore(70, LazyApp::getName);
    }

    public static void testScore(Integer score, Supplier<String> name){
        if(score > 80){
            System.out.println("Selamat " + name.get() + ", anda lulus!");
        } else {
            System.out.println("Coba lagi tahun depan!");
        }
    }

    public static String getName(){
        System.out.println("Method getName dijalankan");
        return "Saya";
    }
}
