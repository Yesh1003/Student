package com.yeshwanth.Student.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int class_no;

    private int roll_no;

    private int grade;

    private String username;

    private String password;

    public Student(){

    }

    public Student(int id, String name, int class_no, int roll_no, int grade, String username, String password) {
        this.id = id;
        this.name = name;
        this.class_no = class_no;
        this.roll_no = roll_no;
        this.grade = grade;
        this.username = username;
        setPassword(password);
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }


}
