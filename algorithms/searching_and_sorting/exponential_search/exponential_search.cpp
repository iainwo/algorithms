/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, Perl, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <bits/stdc++.h>
using namespace std;

int binary_search(int arr[], int /*x*/, int /*leftIx*/, int /*rightIdx*/);
int exponential_search(int arr[], int x, int n)
{
    if(arr[0]==x)
        return 0;

    int i=1;
    while(i<n && arr[i] < x)
        i = 2*i;

    return binary_search(arr, x, i/2, min(i, n-1));
}
int binary_search(int arr[], int x, int l, int r)
{
    if (l<=r)
    {
        int mid = l + (r-l)/2;

        if(arr[mid]==x)
            return mid;
        if(arr[mid]<x)
            return binary_search(arr,x,mid+1, r);
        return binary_search(arr,x,l,mid-1);
    }

    return -1;
}
void test (int arr[], int n, int x)
{
    cout << "exponential_search of len (" << n << ") for val (" << x << ") returned [" << exponential_search(arr, x, n) << "]\n";
}
int main()
{
    int sampleArr[] = {0, 1, 2, 3, 4, 5, 6, 9};
    int sampleArrSize = sizeof(sampleArr)/sizeof(sampleArr[0]);

    test(sampleArr, sampleArrSize, -100);   // before range
    test(sampleArr, sampleArrSize, 0);      // end of range
    test(sampleArr, sampleArrSize, 5);      // in range
    test(sampleArr, sampleArrSize, 9);   // other end of range
    test(sampleArr, sampleArrSize, 999);   // other end of range
    return 0;
}
