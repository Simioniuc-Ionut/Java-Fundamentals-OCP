package org.example.sq.part1.SmartHomeExternalService;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "LOGIN")
public class AccountEntity {

    @Id
    private int id;
    private String name;
    private String password;

    public AccountEntity() {
    }

    public AccountEntity(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
