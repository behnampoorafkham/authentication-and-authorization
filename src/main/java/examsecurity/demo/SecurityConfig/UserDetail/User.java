package examsecurity.demo.SecurityConfig.UserDetail;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    public User() {
    }

    public int getId() {
        return id;
    }

    public User(int id, String username, String password, String authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Id
    private int id;
    private String username;
    private String password;
    private String authority;
// Omitted getters and setters
}