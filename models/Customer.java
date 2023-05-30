package models;
import java.util.Objects;
import java.util.regex.Pattern;


public class Customer implements Comparable<Customer>{
    private  String firstName;
    private  String lastName;
    private  String email;

    public Customer(String firstName, String lastName, String email){
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
  
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.email;
    }
    @Override
    public int compareTo(Customer customer){
        return this.email.compareTo(customer.email);
    }
}
