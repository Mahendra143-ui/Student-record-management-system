public class SearchAlgorithms {
    public static Student linearSearch(Student[] students, int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    // Binary search requires the array to be sorted by student ID.
    public static Student binarySearch(Student[] students, int id) {
        int left = 0, right = students.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midId = students[mid].getId();

            if (midId == id) return students[mid];
            if (midId < id) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}
