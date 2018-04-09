/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <bits/stdc++.h>
using namespace std;

int interpolation_search(int arr[], int x, int n)
{
    int lo = 0, hi = (n - 1);

    while (lo <= hi && arr[lo] <= x && x <= arr[hi])
    {
        int pos = lo + ((double)(hi-lo)/(arr[hi]-arr[lo]))*(x-arr[lo]);

        if (arr[pos] == x)
            return pos;

        if (arr[pos] < x)
            lo = pos + 1;
        else
            hi = pos - 1;
    }
    return -1;
}
void test (int arr[], int n, int x)
{
    cout << "interpolation_search of len (" << n << ") for val (" << x << ") returned [" << interpolation_search(arr, x, n) << "]\n";
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
