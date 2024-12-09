package at.htlkaindorf.todo_list.web;

import at.htlkaindorf.todo_list.database.MockDataBase;
import at.htlkaindorf.todo_list.pojos.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final MockDataBase db;

    @GetMapping()
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(db.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") Long id) {
        return ResponseEntity.of(db.getCategoryById(id));
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category newCat = db.addCategory(category);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{:id}")
                .buildAndExpand(newCat.getId())
                .toUri();
        return ResponseEntity.created(location).body(newCat);
    }

    @PutMapping("/replace")
    public ResponseEntity<Category> replaceCategory(@RequestParam(name = "categoryId") Long categoryId, @RequestBody Category category) {
        Optional<Category> newCat = db.setCategory(categoryId, category);
        if (newCat.isEmpty())
            return ResponseEntity.notFound().build();
        else {

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/../{:id}")
                    .query(null)
                    .buildAndExpand(newCat.get().getId())
                    .normalize()
                    .toUri();

            return ResponseEntity.created(location).body(newCat.get());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        return ResponseEntity.of(db.updateCategory(id, category));
    }
}
