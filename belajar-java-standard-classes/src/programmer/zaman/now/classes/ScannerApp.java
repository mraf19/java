package programmer.zaman.now.classes;

import java.util.Scanner;

public class ScannerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nama: ");
        var name = scanner.nextLine();

        System.out.print("Umur: ");
        var age = scanner.nextInt();

        System.out.println("Hallo, " + name + ", anda berumur " + age + " tahun.");
    }
}
