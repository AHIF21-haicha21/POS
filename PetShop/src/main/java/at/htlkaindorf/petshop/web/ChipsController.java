package at.htlkaindorf.petshop.web;

import at.htlkaindorf.petshop.repository.ChipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/petshop/chips")
public class ChipsController {
    private final ChipRepository chipRepository;

    @GetMapping("/all")
    private ResponseEntity<List<String>> getAllChipTypes() {
        return ResponseEntity.ok(chipRepository.findAllChipTypes());
    }
}
