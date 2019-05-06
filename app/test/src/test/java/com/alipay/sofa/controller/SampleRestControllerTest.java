package com.alipay.sofa.controller;

import com.alipay.sofa.common.dal.dao.StudentDAO;
import com.alipay.sofa.common.dal.tables.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @Author kerry.pzd
 */
public class SampleRestControllerTest {

    @InjectMocks
    private SampleRestController studentController = new SampleRestController();

    @Mock
    private StudentDAO studentRepository;

    private Student student;
    private List<Student> studentList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        student = new Student();
        student.setId(1);
        student.setName("testName");
        student.setAge(3);
        studentList = new ArrayList<>();

        studentList.add(student);
    }

    @Test
    public void getStudent(){
        when(studentRepository.getOne(1)).thenReturn(student);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Student user = studentController.getStudent();
        assertEquals(student,user);
    }

    @Test
    public void queryAll() {
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> studentList = studentController.queryAll();
        assertEquals(1,studentList.size());
        assertEquals(student.getId(),studentList.get(0).getId());
        assertEquals(student.getName(),studentList.get(0).getName());
        assertEquals(student.getAge(),studentList.get(0).getAge());
    }

    @Test
    public void add() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student student1 = studentController.add();
        assertNotEquals(student,student1);
        Student user = new Student();
        user.setName("junjun"+(0));
        user.setAge(6);

        assertEquals(user.getName(),student1.getName());
        assertEquals(user.getAge(),student1.getAge());
        Student student2 = studentController.add();
        Student user1 = new Student();
        user1.setName("junjun"+(1));
        user1.setAge(6);
        assertEquals(user1.getName(),student2.getName());
        assertEquals(user1.getAge(),student2.getAge());
    }

    @Test
    public void update() {
        when(studentRepository.getOne(1)).thenReturn(student);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        student.setName("duoduo");
        when(studentRepository.save(student)).thenReturn(student);
        Student user = studentController.update();
        assertEquals(student,user);
    }
}