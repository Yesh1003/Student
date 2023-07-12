package com.yeshwanth.Student.Service;

import com.yeshwanth.Student.Model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student addStudent(Student student);

    public String deleteStudent(int id);

    public Optional<Student> findStudent(int id);

    public List<Student> getStudents();

    public String updateStudent(Student student);
}
