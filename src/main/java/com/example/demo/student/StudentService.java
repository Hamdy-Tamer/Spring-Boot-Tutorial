package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID) {
        boolean exists = studentRepository.existsById(studentID);

        if(!exists){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student with id " + studentID + " doesn't exist"
            );
        }

        studentRepository.deleteById(studentID);
    }

    // PUT Request ==> Full update
    @Transactional
    public void updateStudent(Long studentID, Student updatedStudent) {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student with id " + studentID + " doesn't exist"
                        ));

        if(updatedStudent.getName() != null &&
                !Objects.equals(student.getName(), updatedStudent.getName())){

            student.setName(updatedStudent.getName());
        }

        if(updatedStudent.getEmail() != null && !Objects.equals(student.getEmail(), updatedStudent.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(updatedStudent.getEmail());

            if(studentOptional.isPresent()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is taken");
            }
            student.setEmail(updatedStudent.getEmail());
        }

        if(updatedStudent.getDob() != null &&
                !Objects.equals(student.getDob(), updatedStudent.getDob())){

            student.setDob(updatedStudent.getDob());
        }

    }

    // PATCH Request ==> Partial update
    @Transactional
    public void patchStudent(Long studentID, Student updatedStudent) {

        Student student = studentRepository.findById(studentID)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student with id " + studentID + " doesn't exist"
                        ));

        if(updatedStudent.getName() != null){
            student.setName(updatedStudent.getName());
        }

        if(updatedStudent.getEmail() != null && !Objects.equals(student.getEmail(), updatedStudent.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(updatedStudent.getEmail());

            if(studentOptional.isPresent()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is taken");
            }

            student.setEmail(updatedStudent.getEmail());
        }

        if(updatedStudent.getDob() != null){

            student.setDob(updatedStudent.getDob());
        }
    }
}