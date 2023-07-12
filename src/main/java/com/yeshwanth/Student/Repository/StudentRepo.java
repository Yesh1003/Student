package com.yeshwanth.Student.Repository;

import com.yeshwanth.Student.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
}
