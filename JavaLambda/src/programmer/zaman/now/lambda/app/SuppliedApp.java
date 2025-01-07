package programmer.zaman.now.lambda.app;

import java.util.function.Supplier;

public class SuppliedApp {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Muhammad";

        System.out.println(supplier.get());
    }
}
