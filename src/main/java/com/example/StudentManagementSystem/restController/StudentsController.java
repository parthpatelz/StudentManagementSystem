package com.example.StudentManagementSystem.restController;

import com.example.StudentManagementSystem.entity.Student;
import com.example.StudentManagementSystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
public class StudentsController {

    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>>getAllStudents(){

        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
       return new ResponseEntity<>(studentService.addNewStudent(student),HttpStatus.CREATED);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
     boolean flag  =   studentService.deleteStudentById(id);
     if(flag){

         return new ResponseEntity<>("Student ID: " + id +" deleted Successfully!!",HttpStatus.OK);
     }
        return new ResponseEntity<>("Student ID: " + id +" not found!!",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/students")
    public String updateStudent(@RequestBody Student student){
     Optional<Student> optionalStudent =  studentService.updateStudent(student);
     if(optionalStudent.isPresent()){
         return "Student record updated successfully!!";
     }
        return "Given Student not found";
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id){
      Optional<Student> student=  studentService.getStudentById(id);
      if(student.isPresent()){
          return student.get();
      }
      return new Student();
    }

}
