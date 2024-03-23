package test.example.app.model;

public class User {
    private Long id;
    private String name;
    private String login;
    private String pass;

    public User(Long id, String name, String login, String pass) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
