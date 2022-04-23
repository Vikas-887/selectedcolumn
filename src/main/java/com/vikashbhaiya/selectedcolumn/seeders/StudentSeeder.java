package com.vikashbhaiya.selectedcolumn.seeders;

import com.github.javafaker.Faker;
import com.vikashbhaiya.selectedcolumn.domain.Student;
import com.vikashbhaiya.selectedcolumn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentSeeder implements ApplicationRunner {

    @Autowired
    private StudentService studentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(studentService.countAll()<=0l) {
            insertRandonRecords(10);
        }
    }

    private void insertRandonRecords(int totalRecords) {
        Faker faker = new Faker();
        List<Student> studentList = new ArrayList<>();
        Student student = null;
        for (int i = 0; i < totalRecords; i++) {
            student = new Student();
            student.setDob(new SimpleDateFormat("dd-MM-yyyy").format(faker.date().birthday()));
            student.setEmailId(faker.internet().emailAddress());
            student.setFathersName(faker.name().name());
            student.setLastname(faker.name().lastName());
            student.setFirstName(faker.name().firstName());
            student.setMobile(faker.phoneNumber().phoneNumber());
            student.setLastQualification(faker.educator().course());
            student.setPhotoUrl(faker.internet().url());
            student.setYearofPassing(new SimpleDateFormat("yyyy").format(faker.date().birthday()));
            student.setCorrespondanceAddress(faker.address().fullAddress());
            studentList.add(student);
        }
        studentService.saveAll(studentList);
    }
}
