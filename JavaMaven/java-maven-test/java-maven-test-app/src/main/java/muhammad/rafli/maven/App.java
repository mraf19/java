package muhammad.rafli.maven;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Gson gson = new Gson();

        Person person = new Person("Muhammad Rafli");

        String jsonString = gson.toJson(person);

        System.out.println(jsonString);
    }
}
