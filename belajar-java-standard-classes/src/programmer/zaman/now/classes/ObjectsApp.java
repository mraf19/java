package programmer.zaman.now.classes;

import java.util.Objects;

public class ObjectsApp {
    public static class Data {
        private String name;

        Data(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Data data = (Data) o;

            return Objects.equals(name, data.name);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        execute(new Data("Gojo"));
    }

    public static void execute(Data data){
        System.out.println(Objects.toString(data));
        System.out.println(Objects.hashCode(data));;
//        System.out.println(data.toString());
//        System.out.println(data.hashCode());
    }
}
