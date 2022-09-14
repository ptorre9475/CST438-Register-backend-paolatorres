package com.cst438;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {Student.class})
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
public class StudentControllerTest {
   
   static final String URL = "http://localhost:8080";
   public static final String TEST_STUDENT_EMAIL = "teststudent@csumb.edu";
   public static final String TEST_STUDENT_NAME = "teststudent";
   
   @MockBean
   StudentRepository studentRepository;
   
   
   @Autowired
   private MockMvc mvc;
   
   @Test
   public void addStudent() throws Exception {
      
      MockHttpServletResponse response;
      
      Student student = new Student();
      student.setEmail(TEST_STUDENT_EMAIL);
      student.setName(TEST_STUDENT_NAME);
      
      given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(student);
      
      response = mvc.perform(
            MockMvcRequestBuilders
               .post("/createStudent")
               .param("email", TEST_STUDENT_EMAIL)
               .param("name", TEST_STUDENT_NAME))
            .andReturn().getResponse();
      
      assertEquals(200, response.getStatus());
   }
   
   @Test
   public void setHold() throws Exception {
      MockHttpServletResponse response;
      
      Student student = new Student();
      student.setEmail(TEST_STUDENT_EMAIL);
      student.setName(TEST_STUDENT_NAME);
      student.setStatusCode(1);
      student.setStatus("HOLD");
      
      given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(student);
      
      response = mvc.perform(
            MockMvcRequestBuilders
               .put("/setHold")
               .param("email", TEST_STUDENT_EMAIL))
            .andReturn().getResponse();
      
      assertEquals(200, response.getStatus());
   }
   
   @Test
   public void releaseHold() throws Exception {
      MockHttpServletResponse response;
      
      Student student = new Student();
      student.setEmail(TEST_STUDENT_EMAIL);
      student.setName(TEST_STUDENT_NAME);
      student.setStatusCode(0);
      student.setStatus("No hold");
      
      given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(student);
      
      response = mvc.perform(
            MockMvcRequestBuilders
               .put("/releaseHold")
               .param("email", TEST_STUDENT_EMAIL))
            .andReturn().getResponse();
      
      assertEquals(200, response.getStatus());
   }
   
    
}
