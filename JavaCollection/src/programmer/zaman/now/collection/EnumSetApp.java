package programmer.zaman.now.collection;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetApp {
    public static enum Gender {
        MALE, FEMALE, NOT_MENTION
    }

    public static void main(String[] args) {
        //EnumSet<Gender> genders = EnumSet.allOf(Gender.class); // all enum
        Set<Gender> genders = EnumSet.of(Gender.MALE, Gender.FEMALE); // part of enum

        for ( var gender: genders){
            System.out.println(gender);
        }


    }
}
