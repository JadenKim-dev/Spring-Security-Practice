package com.cos.security1.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="tb_user")
public class User {
    @Id @GeneratedValue
    private int id;

    private String username;
    private String password;
    private String email;
    private String role;

    @CreationTimestamp
    private Timestamp createDate;

}
