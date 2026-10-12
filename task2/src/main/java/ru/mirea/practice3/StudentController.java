
package ru.mirea.practical3;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        student.setId(null);
        return repository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @GetMapping(
        value = "/coat-of-arms",
        produces = MediaType.IMAGE_PNG_VALUE
    )
    public ResponseEntity<byte[]> getCoatOfArms()
            throws IOException {

        ClassPathResource resource =
                new ClassPathResource(
                    "static/MIREA_Gerb_Colour.png"
                );

        byte[] image = resource
                .getInputStream()
                .readAllBytes();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }
}
