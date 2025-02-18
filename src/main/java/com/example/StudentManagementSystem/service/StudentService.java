package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.entity.Student;
import com.example.StudentManagementSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(e->students.add(e));
        return students;
    }

    public Optional<Student> getStudentById(Long id){
         return studentRepository.findById(id);
    }

    public Student addNewStudent(Student student){
        return studentRepository.save(student);
    }

    public boolean deleteStudentById(Long id){
      Optional<Student> student =  getStudentById(id);
      if(student.isPresent()){
          studentRepository.deleteById(id);
          return true;
      }
      return false;
    }

    public Optional<Student> updateStudent(Student student){
        Optional<Student> std =  getStudentById(student.getId());

        if(std.isPresent()){
            return Optional.ofNullable(studentRepository.save(student));
        }
        return Optional.empty();
    }
}
