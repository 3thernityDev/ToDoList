package re.ethernity.todolist.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Utilisateur")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Password;
    private String Email;

    public User() {

    }

    public User(String name, String password, String email) {
        this.Name = name;
        this.Password = password;
        this.Email = email;
    }

}
