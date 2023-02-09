package com.yefeng.springtest.Client.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetail implements UserDetails {


    private Long id;

    private String name;

    private String password;

    private String phone;

    private boolean gender;

    private String address;

    private List<Role> roles;



    @Override
    public String toString() {
        return "MyUserDetail:{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    /*
    添加用户权限
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
        }
        return authorities;
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (null == this.grantedAuthorities) {
//            List<GrantedAuthority> list = new ArrayList<>();
//            for (Role role : roles) {
//                list.add(new SimpleGrantedAuthority(role.getName()));
//                for (Authority authority : role.getAuthorities()) {
//                    list.add(new SimpleGrantedAuthority(authority.getName()));
//                }
//            }
//            this.grantedAuthorities = list;
//        }
//        return this.grantedAuthorities;
//    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
