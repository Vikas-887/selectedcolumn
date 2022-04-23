package com.vikashbhaiya.selectedcolumn.repos;

import com.vikashbhaiya.selectedcolumn.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select first_name,last_qualification from student", nativeQuery = true)
    List<Object[]> findNameAndQualification();
}
