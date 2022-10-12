package com.cst438.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.StudentRepository;

import com.cst438.domain.Student;

@RestController
public class StudentController
{ 
   @Autowired
   StudentRepository studentRepository;
   
   // helper function, retrieve student    
   private Student getStudent(String email) {
      Student student = studentRepository.findByEmail(email);
      
      return student;
     
   }
   
   // add a student
   @PostMapping("/student")
   @Transactional 
   public void addStudent(String email, String name) {
      if (email == null || name == null) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Need to pass an email and name to create a new student");
      } else if (getStudent(email) != null) {
         System.out.println("Student already exists");
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student already exists.");
      } else {
         Student student = new Student();
         student.setEmail(email);
         student.setName(name);
         student.setAdminStatus();
         studentRepository.save(student);
         System.out.println("Student created.");
      }
   }
   
   // update hold information, hold = 1  
   @PutMapping("/setHold/{email}")
   @Transactional
   public void setHold(@PathVariable String email) {
      Student student = getStudent(email);
      
      if (student == null) {
         System.out.println("Student does not exist.");
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student does not exist.");
      } else {
         student.setStatusCode(1);
         student.setStatus("HOLD");
         studentRepository.save(student);
      }
   }
   
   // remove hold information, no hold = 0   
   @PutMapping("/releaseHold/{email}")
   @Transactional
   public void releaseHold(@PathVariable String email) {
      Student student = getStudent(email);
      if (student == null) {
         System.out.println("Student does not exist.");
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student does not exist.");
      } else {
         student.setStatusCode(0);
         student.setStatus("No holds");
         studentRepository.save(student);
      }
   }
}
