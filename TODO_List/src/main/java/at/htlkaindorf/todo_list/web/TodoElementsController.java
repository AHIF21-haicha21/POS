package at.htlkaindorf.todo_list.web;

import at.htlkaindorf.todo_list.database.MockDataBase;
import at.htlkaindorf.todo_list.pojos.Category;
import at.htlkaindorf.todo_list.pojos.TodoElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.stream.Location;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoElementsController {
    private final MockDataBase db;

    @GetMapping
    public ResponseEntity<List<TodoElement>> getAllTodos() {
        return ResponseEntity.ok(db.getTodos());
    }

    @GetMapping("/category")
    public ResponseEntity<List<TodoElement>> getAllTodosByCategory(@RequestParam(name = "catID") Long id) {
        return ResponseEntity.ok(db.getAllTodosByCategory(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<TodoElement>> getAllTodosByCategories(@RequestParam(name = "ids") List<Long> ids) {
        return ResponseEntity.ok(ids.stream().map(db::getAllTodosByCategory).flatMap(Collection::stream).toList());
    }

    @GetMapping("/priority")
    public ResponseEntity<List<TodoElement>> getAllTodosByPriority(@RequestParam(name = "prio") String prio) {
        return ResponseEntity.ok(db.getAllTodosByPriority(prio));
    }

    @GetMapping("/today")
    public ResponseEntity<List<TodoElement>> getAllTodosDueToDate() {

        LocalDate today = LocalDate.now();

        return ResponseEntity.ok().body(db.getAllTodosDueToDate(today));
    }

    @GetMapping("/due")
    public ResponseEntity<List<TodoElement>> getAllTodosDueToDate(@RequestParam(name = "date") String dateString) {

        LocalDate dateTime;
        try {
            dateTime = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(TodoElement.DATE_FORMAT));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        return ResponseEntity.ok().body(db.getAllTodosDueToDate(dateTime));
    }

    @GetMapping("/future")
    public ResponseEntity<List<TodoElement>> getAllTodosDueDatesWithinTheNextDays(@RequestParam(name = "days") Integer dayCount) {

        LocalDate endDate = LocalDate.now().plusDays(dayCount);
        return ResponseEntity.ok().body(db.getAllTodosDueInRange(LocalDate.now(), endDate));
    }

    @GetMapping("/todoId")
    public ResponseEntity<TodoElement> getTodoById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.of(db.getTodoById(id));
    }

    @PostMapping()
    public ResponseEntity<TodoElement> addTodo(@RequestBody TodoElement newElement) {

        TodoElement appendedElement = db.addTodo(newElement);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/../{:id}")
                .query(null)
                .buildAndExpand(appendedElement.getId())
                .normalize()
                .toUri();

        return ResponseEntity.ok().location(location).body(appendedElement);
    }

    @PatchMapping("/change")
    public ResponseEntity<TodoElement> changeTodo(@RequestParam(name = "id") Long id, @RequestBody TodoElement todoElement) {
        return ResponseEntity.of(db.patchTodo(id, todoElement));
    }

    @PutMapping("/replace")
    public ResponseEntity<TodoElement> replaceTodo(@RequestParam(name = "id") Long id, @RequestBody TodoElement todoElement) {
        return ResponseEntity.of(db.replaceTodo(id, todoElement));
    }

    @PatchMapping("/{id}/done")
    public ResponseEntity<TodoElement> setTodoToDone(@RequestParam(name = "id") Long id) {

        Optional<TodoElement> element = db.getTodoById(id);
        if(element.isEmpty())
            return ResponseEntity.notFound().build();
        else if(element.get().getIsDone())
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.of(db.setTodoToDone(id));
    }
}
