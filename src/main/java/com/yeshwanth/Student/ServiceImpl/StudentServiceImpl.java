package com.yeshwanth.Student.ServiceImpl;

import com.yeshwanth.Student.Model.Student;
import com.yeshwanth.Student.Repository.StudentRepo;
import com.yeshwanth.Student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Student addStudent(Student student) {

        String encryptedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encryptedPassword);
        Student user = studentRepo.save(student);
        return user;
    }

    @Override
    public String deleteStudent(int id) {

        studentRepo.deleteById(id);
        return "The Data has been successfully deleted";
    }

    @Override
    public Optional<Student> findStudent(int id) {

        Optional <Student> user = studentRepo.findById(id);

        if(user.isPresent()){
            return user;
        } else{
            return null;
        }
    }

    @Override
    public List<Student> getStudents() {

        List<Student> studentList = studentRepo.findAll();
        return studentList;
    }

    @Override
    public String updateStudent(Student student) {

        Optional<Student> user = studentRepo.findById(student.getId());

        if(user.isPresent()){
            Student existingStudent = user.get();
            existingStudent.setName(student.getName());
            existingStudent.setClass_no(student.getClass_no());
            existingStudent.setRoll_no(student.getRoll_no());
            existingStudent.setGrade(student.getGrade());
            existingStudent.setUsername(student.getUsername());
            existingStudent.setPassword(student.getPassword());

            studentRepo.save(existingStudent);

            return "Student Details Updated Successfully";
        }else{
            return "Student not found";
        }
    }
}
