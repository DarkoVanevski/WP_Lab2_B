package mk.ukim.finki.wp.lab1b.service.specification;


import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class FieldFilterSpecification {

    // Text search со LIKE '%value%'
    public static <T> Specification<T> filterContainsText(Class<T> clazz, String field, String value) {
        if (value == null || value.isEmpty()) {
            return null;  // Skip ако е празно
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(field)),
                "%" + value.toLowerCase() + "%"
        );
    }

    // Exact match (за ID-ја, Long вредности)
    public static <T> Specification<T> filterEquals(Class<T> clazz, String field, Long value) {
        if (value == null) {
            return null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(fieldToPath(field, root), value);
    }

    // Exact match (за енуми, објекти)
    public static <T, V> Specification<T> filterEqualsV(Class<T> clazz, String field, V value) {
        if (value == null) {
            return null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(fieldToPath(field, root), value);
    }

    // Greater than or equal (за минимални вредности)
    public static <T> Specification<T> filterGreaterThanOrEqual(Class<T> clazz, String field, Integer value) {
        if (value == null) {
            return null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(field), value);
    }

    // Less than or equal (за максимални вредности)
    public static <T> Specification<T> filterLessThanOrEqual(Class<T> clazz, String field, Integer value) {
        if (value == null) {
            return null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(field), value);
    }

    // Поддршка за nested полиња: "chef.firstName" → root.get("chef").get("firstName")
    private static <T> Path<T> fieldToPath(String field, Root<T> root) {
        String[] parts = field.split("\\.");
        Path<T> res = root;
        for (String p : parts) {
            res = res.get(p);
        }
        return res;
    }
}