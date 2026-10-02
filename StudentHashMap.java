import java.util.HashMap;
import java.util.Map;

public class StudentHashMap {
    private final Map<Integer, Student> map = new HashMap<>();

    public void put(Student student) {
        map.put(student.getId(), student);
    }

    public Student get(int id) {
        return map.get(id);
    }

    public Student remove(int id) {
        return map.remove(id);
    }

    public int size() {
        return map.size();
    }
}
