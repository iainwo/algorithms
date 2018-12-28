/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, Perl, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>

void swap(int *xp, int *yp)
{
    int temp = *yp;
    *yp = *xp;
    *xp = temp;
}
void bubble_sort(int arr[], int n)
{
    for(int i=0; i<n-1; i++)
    {
        bool swapped=false;
        for (int j=0; j<n-1-i; j++)
        {
            if (arr[j]>arr[j+1])
            {
                swap(&arr[j], &arr[j+1]);
                swapped=true;
            }
        }

        if (false == swapped)
            break;
    }
}
void print_array (int arr[], int n)
{
    int i;
    for (int i=0; i<n; i++)
    {
        printf("%d ", arr[i]);
    }
    printf("\n");
}
void test (int arr[], int n)
{
    printf("UnSorted: ");
    print_array(arr, n);
    bubble_sort(arr, n);
    printf("Sorted:   ");
    print_array(arr, n);
}
int main()
{
    int sampleArr[] = {939, 1, 22, 3, 524, 5333, 26, 9};
    int sampleArrSize = sizeof(sampleArr)/sizeof(sampleArr[0]);

    test(sampleArr, sampleArrSize);
    return 0;
}
