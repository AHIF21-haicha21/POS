package at.htlkaindorf.eventmanagement.utils;

import at.htlkaindorf.eventmanagement.annotations.UpdateIgnore;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ObjectMerger {
    /**
     * Merges the updateable values from object {@code o2} into object {@code o1}.
     * <p>
     * Fields annotated with {@link at.htlkaindorf.eventmanagement.annotations.UpdateIgnore} will be skipped.
     * For fields that are JPA entities (annotated with {@link jakarta.persistence.Entity}), the merge is done recursively
     * to preserve nested references without replacing them entirely.
     *
     * @param o1  the target object to be updated
     * @param o2  the source object providing new values
     * @param <T> the type of the objects being merged
     * @return the updated object {@code o1}
     */
    public static <T> T merge(@NotNull T o1, @NotNull T o2) {

        Field currentField = null;
        try {
            Field[] fields = o1.getClass().getDeclaredFields();
            for (Field f : fields) {
                currentField = f;
                currentField.setAccessible(true);
                if (currentField.isAnnotationPresent(UpdateIgnore.class))
                    continue;

                Object newValue;
                if (currentField.getType().isAnnotationPresent(Entity.class))
                    newValue = merge(currentField.get(o1), currentField.get(o2));
                else
                    newValue = currentField.get(o2);

                currentField.set(o1, newValue);
            }
        } catch (IllegalAccessException e) {
            log.error(String.format("Access denied on field '%s'", currentField.getName()));
            throw new RuntimeException(e);
        }

        return o1;
    }
}
