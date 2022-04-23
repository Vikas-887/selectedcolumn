package com.vikashbhaiya.selectedcolumn.rest;

import com.vikashbhaiya.selectedcolumn.model.ApiResponse;
import com.vikashbhaiya.selectedcolumn.model.StudentDTO;
import com.vikashbhaiya.selectedcolumn.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable final Long studentId) {
        return ResponseEntity.ok(studentService.get(studentId));
    }

    @GetMapping("/columns")
    public ResponseEntity<ApiResponse> getSelectedColumns(){
        // return ResponseEntity.ok(studentService.selectedColums());
        List<Object[]> objectList = studentService.selectedColums();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(true);
        apiResponse.setMessage("success");

        TreeMap<Integer, Object> map = new TreeMap<>();

        int index = 0;
        for (Object o : objectList) {
            map.put(index, o);
            index++;
        }
        apiResponse.setData(map);

        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<Long> createStudent(@RequestBody @Valid final StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.create(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable final Long studentId,
            @RequestBody @Valid final StudentDTO studentDTO) {
        studentService.update(studentId, studentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable final Long studentId) {
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }

}
