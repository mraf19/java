public class Manajer {
    String name;

    void sayHello(String name){
        System.out.println("Hello " + name + ", I am Manajer " + this.name  );
    }
}

class VicePresident extends Manajer {
    @Override
    void sayHello(String name){
        System.out.println("Hello " + name + ", I am VP " + this.name  );
    }
}
