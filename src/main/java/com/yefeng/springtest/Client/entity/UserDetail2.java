package com.yefeng.springtest.Client.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class UserDetail2 extends User {
    private Long id;

    private String name;

    private String password;

    private String phone;

    private boolean gender;

    private String address;

    private List<Role> roles;

    private String loginRid;

    public String getLoginRid() {
        return loginRid;
    }

    public void setLoginRid(String loginRid) {
        this.loginRid = loginRid;
    }

    @Override
    public String toString() {
        return "UserDetail2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                ", loginRid='" + loginRid + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Boolean hasRoleByParam(String name,Object key){

        if (roles!=null){
            for (Role role : roles) {
                if((name=="name"||name.equals("name"))&&(role.getName()==key||role.getName().equals(key))){
                    return true;
                }else if((name=="id"||name.equals("id"))&&(role.getName()==key||role.getName().equals(key))){
                    return true;
                }
            }
        }
        return false;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserDetail2(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetail2(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, String name, String password1, String phone, boolean gender, String address, List<Role> roles) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.name = name;
        this.password = password1;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.roles = roles;
    }
}
