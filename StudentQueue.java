public class StudentQueue {
    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node front, rear;
    private int size;

    public void enqueue(Student student) {
        Node node = new Node(student);
        if (rear == null) front = rear = node;
        else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public Student dequeue() {
        if (front == null) return null;
        Student value = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return value;
    }

    public int size() { return size; }
}
