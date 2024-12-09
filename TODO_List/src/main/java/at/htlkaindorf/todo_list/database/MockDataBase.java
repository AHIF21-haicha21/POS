package at.htlkaindorf.todo_list.database;

import at.htlkaindorf.todo_list.annotations.GeneratedValue;
import at.htlkaindorf.todo_list.annotations.ImmutableProperty;
import at.htlkaindorf.todo_list.pojos.Category;
import at.htlkaindorf.todo_list.pojos.TodoElement;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@Getter
public class MockDataBase {
    private List<Category> categories = new ArrayList<>();
    private List<TodoElement> todos = new ArrayList<>();

    private <T> List<T> readResource(Class<T> clazz, String resourceName) {
        InputStream is = this.getClass().getResourceAsStream("/" + resourceName);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        List<T> result = new ArrayList<>();

        List<Field> generatedFields = Arrays.stream(clazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(GeneratedValue.class)).toList();

        try {
            result = mapper.readerForListOf(clazz).readValue(is);
            int i = 0;
            for (Object o : result) {
                for (Field f : generatedFields) {
                    f.setAccessible(true);
                    Long id = f.getAnnotation(GeneratedValue.class).startValue() + f.getAnnotation(GeneratedValue.class).step() * i;
                    ReflectionUtils.setField(f, o, id);
                }
                i++;
            }
        } catch (IOException e) {
            log.error("Error in reading file");
            log.error(e.getMessage());
        }

        return result;
    }

    @PostConstruct
    public void initDatabase() {
        log.info("Initializing database");
        this.categories = readResource(Category.class, "10_categories.json");
        log.info("Categories initialized");
        this.todos = readResource(TodoElement.class, "10_todos.json");
        log.info("To-Dos initialized");
        this.todos.forEach(t -> t.setCategory(categories.stream().filter(c -> c.getId().equals(t.getCatId())).findFirst().orElse(null)));
        log.info("Initialized database");
    }

    public Optional<Category> getCategoryById(Long id) {
        log.info("Getting category by id: " + id);
        log.info(Arrays.toString(categories.toArray()));
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Category addCategory(Category category) {
        long stepSize = 1;
        long startValue = 1;

        try {
            Field idField = Category.class.getDeclaredField("id");
            if (idField.isAnnotationPresent(GeneratedValue.class)) {
                stepSize = idField.getAnnotation(GeneratedValue.class).step();
                startValue = idField.getAnnotation(GeneratedValue.class).startValue();
            }
        } catch (Exception ignored) {
        }

        Long maxId = categories.stream().mapToLong(Category::getId).max().orElse(startValue - stepSize) + stepSize;
        category.setId(maxId);
        categories.add(category);
        return category;
    }

    public Optional<Category> setCategory(Long id, Category category) {
        Optional<Category> oldCategory = getCategoryById(id);
        if (oldCategory.isPresent()) {
            categories.set(categories.indexOf(oldCategory.get()), category);
            return Optional.of(category);
        } else
            return Optional.empty();
    }

    public Optional<Category> updateCategory(Long id, Category category) {
        Optional<Category> oldCategory = getCategoryById(id);
        if (oldCategory.isEmpty()) {
            return Optional.empty();
        }

        for (Field field : oldCategory.get().getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, category);
            if (value != null && !field.isAnnotationPresent(ImmutableProperty.class))
                ReflectionUtils.setField(field, oldCategory.get(), value);
        }

        return oldCategory;
    }

    public List<TodoElement> getAllTodosByCategory(Long catID) {
        return todos.stream().filter(t -> t.getCatId().equals(catID)).toList();
    }

    public List<TodoElement> getAllTodosByPriority(String prio) {
        return todos.stream().filter(t -> t.getImportance().toString().equalsIgnoreCase(prio)).toList();
    }

    public List<TodoElement> getAllTodosDueToDate(LocalDate date) {
        return todos.stream().filter(t -> t.getDueDate().isAfter(date)).toList();
    }

    public List<TodoElement> getAllTodosDueInRange(LocalDate begin, LocalDate end) {
        return todos.stream().filter(t -> t.getDueDate().isAfter(begin) && t.getDueDate().isBefore(end)).toList();
    }

    public Optional<TodoElement> getTodoById(Long id) {
        return todos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public TodoElement addTodo(TodoElement newElement) {
        long stepSize = 1;
        long startValue = 1;

        try {
            Field idField = TodoElement.class.getDeclaredField("id");
            if (idField.isAnnotationPresent(GeneratedValue.class)) {
                stepSize = idField.getAnnotation(GeneratedValue.class).step();
                startValue = idField.getAnnotation(GeneratedValue.class).startValue();
            }
        } catch (Exception ignored) {
        }

        Long newId = todos.stream().map(TodoElement::getId).max(Long::compareTo).orElse(startValue - stepSize) + stepSize;
        newElement.setId(newId);
        todos.add(newElement);
        return newElement;
    }

    public Optional<TodoElement> patchTodo(Long id, TodoElement newValues) {
        Optional<TodoElement> toBePatched = todos.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (toBePatched.isEmpty())
            return Optional.empty();

        for (Field field : TodoElement.class.getDeclaredFields()) {
            Object value = ReflectionUtils.getField(field, newValues);
            if (!field.isAnnotationPresent(ImmutableProperty.class) && value != null)
                ReflectionUtils.setField(field, toBePatched.get(),
                        ReflectionUtils.getField(field, newValues));
        }

        return toBePatched;
    }

    public Optional<TodoElement> replaceTodo(Long id, TodoElement newElement) {
        Optional<TodoElement> toBeOverwritten = todos.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (toBeOverwritten.isEmpty())
            return Optional.empty();

        newElement.setId(toBeOverwritten.get().getId());

        int index = todos.indexOf(toBeOverwritten.get());
        todos.set(index, newElement);

        return Optional.of(newElement);
    }

    public Optional<TodoElement> setTodoToDone(Long id) {
        Optional<TodoElement> toBeSet = todos.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (toBeSet.isEmpty())
            return Optional.empty();

        toBeSet.get().setIsDone(true);

        return toBeSet;
    }

}
