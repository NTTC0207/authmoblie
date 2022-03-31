package my.edu.utar.cheehaoyang;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Data implements Serializable {


    @Exclude
    private String key;
    private String name;
    private String email;
    private String password;
    private String pin;

    public Data(){}
    public Data(String name,String email,String password, String pin)
    {
        this.name = name;
        this.email =email;
        this.password =password;
        this.pin =pin;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =  password ;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key=key;
    }
}
