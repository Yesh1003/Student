package com.yeshwanth.Student.Controller;

import com.yeshwanth.Student.Model.Student;
import com.yeshwanth.Student.ServiceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/home")
    public String homepage(){
        return "Welcome to Student Page";
    }
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student user = studentServiceImpl.addStudent(student);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        studentServiceImpl.deleteStudent(id);
        return new ResponseEntity<String>("Removed Successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Student>> findStudent(@PathVariable int id){
        Optional<Student> user = studentServiceImpl.findStudent(id);

        if(user.isEmpty()){
            return null;
        }else{
            return new ResponseEntity<Optional<Student>>(user,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentServiceImpl.getStudents();
        return new ResponseEntity<List<Student>>(students,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@RequestBody Student student){
        Optional<Student> user = studentServiceImpl.findStudent(id);

        if(user.isPresent()){
            Student updated_student = user.get();

            updated_student.setName(student.getName());
            updated_student.setRoll_no(student.getRoll_no());
            updated_student.setClass_no(student.getClass_no());
            updated_student.setGrade(student.getGrade());

            studentServiceImpl.updateStudent(updated_student);
            return new ResponseEntity<String>("Student Details Updated Successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Student not found",HttpStatus.NOT_FOUND);
        }
    }
}
