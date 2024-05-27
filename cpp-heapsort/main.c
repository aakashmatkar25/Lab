#include <stdio.h>

void insert(int A[], int n) {
    int temp, i = n;
    temp = A[i];
    
    while (i > 1 && temp < A[i/2]) {
        A[i] = A[i/2];
        i = i / 2;
    }
    A[i] = temp;
}

int delete(int A[], int n) {
    int i, j, x;
    x = A[1]; // 'x' will hold the deleted value
    A[1] = A[n]; // make the last value in the array the new root value
    A[n] = x; // place the deleted value at the end of array
    i = 1;
    j = i * 2;
    
    while (j < n - 1) {
        if (A[j + 1] < A[j]) // if right child is smaller than left child
            j = j + 1;
        if (A[i] > A[j]) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i = j;
            j = 2 * j;
        } else
            break;
    }
    return x;
}

int main() {
    
    // We will not be using the 0th location
    int H[] = {0, 10, 20, 30, 25, 5, 40, 35};
    
    // Make the H[] array a min heap
    for (int i = 1; i <= 7; i++)
        insert(H, i);
    
    // Print the min heap
    printf("Min Heap H[]: ");
    for (int i = 1; i <= 7; i++)
        printf("%d ", H[i]);
    
    // Delete the values from the min heap
    for (int i = 7; i > 1; i--)
        delete(H, i);
    printf("\n");
    
    // Deleting all the values from the min heap will result
    // in a sorted array. This is Heap Sort
    printf("H[] after deleting values from min heap: ");
    for (int i = 1; i <= 7; i++)
        printf("%d ", H[i]);
    printf("\n");
    
    return 0;
}
