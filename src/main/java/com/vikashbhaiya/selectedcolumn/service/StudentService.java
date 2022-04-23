package com.vikashbhaiya.selectedcolumn.service;

import com.vikashbhaiya.selectedcolumn.domain.Student;
import com.vikashbhaiya.selectedcolumn.model.StudentDTO;
import com.vikashbhaiya.selectedcolumn.repos.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> mapToDTO(student, new StudentDTO()))
                .collect(Collectors.toList());
    }

    public StudentDTO get(final Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> mapToDTO(student, new StudentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final StudentDTO studentDTO) {
        final Student student = new Student();
        mapToEntity(studentDTO, student);
        return studentRepository.save(student).getStudentId();
    }

    public void update(final Long studentId, final StudentDTO studentDTO) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(studentDTO, student);
        studentRepository.save(student);
    }

    public void delete(final Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public long countAll() {
        return studentRepository.count();
    }

    private StudentDTO mapToDTO(final Student student, final StudentDTO studentDTO) {
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setCorrespondanceAddress(student.getCorrespondanceAddress());
        studentDTO.setDob(student.getDob());
        studentDTO.setEmailId(student.getEmailId());
        studentDTO.setFathersName(student.getFathersName());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastQualification(student.getLastQualification());
        studentDTO.setLastname(student.getLastname());
        studentDTO.setMobile(student.getMobile());
        studentDTO.setPhotoUrl(student.getPhotoUrl());
        studentDTO.setYearofPassing(student.getYearofPassing());
        return studentDTO;
    }

    private Student mapToEntity(final StudentDTO studentDTO, final Student student) {
        student.setCorrespondanceAddress(studentDTO.getCorrespondanceAddress());
        student.setDob(studentDTO.getDob());
        student.setEmailId(studentDTO.getEmailId());
        student.setFathersName(studentDTO.getFathersName());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastQualification(studentDTO.getLastQualification());
        student.setLastname(studentDTO.getLastname());
        student.setMobile(studentDTO.getMobile());
        student.setPhotoUrl(studentDTO.getPhotoUrl());
        student.setYearofPassing(studentDTO.getYearofPassing());
        return student;
    }

    public void saveAll(List<Student> studentList) {
        studentRepository.saveAll(studentList);
    }

    public List<Object[]> selectedColums() {
        return studentRepository.findNameAndQualification();
    }
}
