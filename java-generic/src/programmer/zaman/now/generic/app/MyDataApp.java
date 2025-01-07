package programmer.zaman.now.generic.app;

import org.w3c.dom.ls.LSOutput;
import programmer.zaman.now.generic.MyData;

public class MyDataApp {

    public static void main(String[] args) {
        MyData<String> stringMyData = new MyData<String>("Rafli");
        MyData<Integer> integerMyData = new MyData<>(10);

        System.out.println(stringMyData.getData());
        System.out.println(integerMyData.getData());
    }


}
