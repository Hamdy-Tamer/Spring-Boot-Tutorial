package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email is taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID){
        boolean exists = studentRepository.existsById(studentID);

        if(!exists){
            throw new IllegalStateException("Student with id " + studentID + " doesn't exist");
        }
        studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new IllegalStateException(
                "Student with id " + studentID + " doesn't exist"));

                if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)){
                    student.setName(name);
                }

                if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)){
                    Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

                    if(studentOptional.isPresent()){
                        throw new IllegalStateException("Email is taken");
                    }
                    student.setEmail(email);
                }
    }
}
