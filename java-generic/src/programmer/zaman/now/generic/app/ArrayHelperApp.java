package programmer.zaman.now.generic.app;

import programmer.zaman.now.generic.util.ArrayHelper;

public class ArrayHelperApp {
    public static void main(String[] args) {
        String[] names = { "Muhammad", "Rafli", "Lia", "Listriani"};
        Integer[] numbers = {1,2,3,4,5,6,7,8,9};
        System.out.println(
            ArrayHelper.<String>count(names)
        );
        System.out.println(
            ArrayHelper.count(numbers)
        );
    }
}
