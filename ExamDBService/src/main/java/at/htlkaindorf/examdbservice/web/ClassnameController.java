package at.htlkaindorf.examdbservice.web;

import at.htlkaindorf.examdbservice.pojos.Classname;
import at.htlkaindorf.examdbservice.services.ClassnameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classes")
@Slf4j
@RequiredArgsConstructor
public class ClassnameController {
    private final ClassnameService classnameService;

    @GetMapping("all")
    public ResponseEntity<List<Classname>> getAll() {
        return ResponseEntity.ok(classnameService.getAll());
    }
}
