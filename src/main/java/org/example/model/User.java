package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String first_name;
    private String second_name;
    private String password;
    private int role;
}
