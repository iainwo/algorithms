/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, Perl, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>

int modified_binary_search(int arr[], int x, int n, int l, int r)
{
    if (arr[0]>x)
        return 0;
    else if (arr[r]<x && r+1<n)
        return r+1;
    else if (arr[r]<x)
        return r;

    while (l<=r)
    {
        int m = l + (r-l)/2;

        if (arr[m]==x)
            return m;
        if (arr[l]>x)
            return l;
        if (l==r)
            return 1+l;

        if (arr[m]>x)
            r=m-1;
        else
            l=m+1;
    }

    return -1;
}
void insertion_sort(int arr[], int n)
{
    for(int i=1; i<n; i++)
    {
        int j,key,smallestIdx;
        key=arr[i];
        smallestIdx=modified_binary_search(arr, key, n, 0, i-1);
        for (j=i; j>smallestIdx; j--)
        {
            arr[j]=arr[j-1];
        }
        arr[smallestIdx]=key;
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
    insertion_sort(arr, n);
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
