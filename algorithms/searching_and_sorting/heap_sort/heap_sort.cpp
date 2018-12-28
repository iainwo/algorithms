/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, Perl, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <iostream>
using namespace std;

void swap(int *foo, int *bar)
{
    int tmp = *bar;
    *bar = *foo;
    *foo = tmp;
}
void heapify(int arr[], int i, int n)
{
    int p, l, r;
    p=i;
    l=p*2+1;
    r=l+1;

    if (l<n && arr[l]>arr[p])
        p=l;
    if (r<n && arr[r]>arr[p])
        p=r;
    if (p!=i)
    {
        swap(&arr[p], &arr[i]);
        heapify(arr,p,n);
    }
}
void heap_sort (int arr[], int n)
{
    for (int i=n/2-1; i>=0; i--)
        heapify(arr,i,n);

    for (int i=n-1; i>0; i--)
    {
        swap(&arr[i], &arr[0]);
        heapify(arr,0,i);
    }
}
void print_array (int arr[], int n)
{
    int i;
    for (int i=0; i<n; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "\n";
}
void test (int arr[], int n)
{
    cout << "UnSorted: ";
    print_array(arr, n);
    heap_sort(arr, n);
    cout << "Sorted:   ";
    print_array(arr, n);
}
int main()
{
    int sampleArr[] = {939, 1, 22, 3, 524, 5333, 26, 9};
    int sampleArrSize = sizeof(sampleArr)/sizeof(sampleArr[0]);

    test(sampleArr, sampleArrSize);
    return 0;
}
