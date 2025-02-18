package com.example.StudentManagementSystem.restController;

import com.example.StudentManagementSystem.entity.Student;
import com.example.StudentManagementSystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
       return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student){
       return studentService.addNewStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable Long id){
     boolean flag  =   studentService.deleteStudentById(id);
     if(flag){
         return "Student ID: " + id +" deleted Successfully!!";
     }
        return "Student ID: " + id +" not found!!";
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
