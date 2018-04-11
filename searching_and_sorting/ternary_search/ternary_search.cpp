/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, Perl, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <bits/stdc++.h>
using namespace std;

int ternary_search(int arr[], int x, int l, int r)
{
    if (r>=l)
    {
        int mid1 = l + (r-l)/3;
        int mid2 = mid1 + (r-l)/3;

        if(arr[mid1]==x)
            return mid1;
        if(arr[mid2]==x)
            return mid2;
        if(arr[mid1]>x)
            return ternary_search(arr,x,l,mid1-1);
        if(arr[mid2]<x)
            return ternary_search(arr,x,mid2+1,r);
        return ternary_search(arr,x,mid1+1, mid2-1);
    }
    return -1;
}
void test (int arr[], int n, int x)
{
    cout << "ternary_search of len (" << n << ") for val (" << x << ") returned [" << ternary_search(arr, x, 0, n-1) << "]\n";
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
