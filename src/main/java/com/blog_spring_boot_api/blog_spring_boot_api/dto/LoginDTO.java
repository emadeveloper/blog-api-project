package com.blog_spring_boot_api.blog_spring_boot_api.dto;

public class LoginDTO {

    private String usernameOrEmail;
    private String password;
    
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }
    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
