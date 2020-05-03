package LogInPack;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String passWord;
    private String nullString ;

    Person(String name, String passWord){
        this.name = name;
        this.passWord=passWord;
        this.nullString = "\n";
    }

    public String getName() {
        return name;
    }

    public String getPassWord() {
        return passWord;
    }
}
