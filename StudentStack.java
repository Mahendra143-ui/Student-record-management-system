public class StudentStack {
    private static class Node {
        Student data;
        Node next;
        Node(Student data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node top;
    private int size;

    public void push(Student student) {
        top = new Node(student, top);
        size++;
    }

    public Student pop() {
        if (top == null) return null;
        Student value = top.data;
        top = top.next;
        size--;
        return value;
    }

    public Student peek() {
        return top == null ? null : top.data;
    }

    public int size() { return size; }
}
