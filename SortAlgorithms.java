public class SortAlgorithms {
    public static void bubbleSortByCgpa(Student[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j].getCgpa() < a[j + 1].getCgpa()) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void insertionSortById(Student[] a) {
        for (int i = 1; i < a.length; i++) {
            Student key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].getId() > key.getId()) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    public static void mergeSortByName(Student[] a) {
        if (a.length < 2) return;
        Student[] temp = new Student[a.length];
        mergeSort(a, temp, 0, a.length - 1);
    }

    private static void mergeSort(Student[] a, Student[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(a, temp, left, mid);
        mergeSort(a, temp, mid + 1, right);
        merge(a, temp, left, mid, right);
    }

    private static void merge(Student[] a, Student[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (a[i].getName().compareToIgnoreCase(a[j].getName()) <= 0)
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }
        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];
        for (i = left; i <= right; i++) a[i] = temp[i];
    }

    private static void swap(Student[] a, int i, int j) {
        Student t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
