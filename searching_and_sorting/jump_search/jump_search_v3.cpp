/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <bits/stdc++.h>
using namespace std;

int jump_search(int arr[], int x, int n)
{
    int step = sqrt(n);
    int prev = 0;
    while (arr[min(step, n)-1] < x)
    {
        prev = step;
        step += sqrt(n);
        if (prev >= n)
            return -1;
    }
    while (arr[prev] < x)
    {
        prev++;
        if (prev == min(step,n))
            return -1;
    }
    if (arr[prev]==x)
        return prev;
    return -1;
}
void test (int arr[], int n, int x)
{
    cout << "jump_search of len (" << n << ") for val (" << x << ") returned [" << jump_search(arr, x, n) << "]\n";
}
int main()
{
    int sampleArr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int sampleArrSize = sizeof(sampleArr)/sizeof(sampleArr[0]);

    test(sampleArr, sampleArrSize, -100);   // before range
    test(sampleArr, sampleArrSize, 0);      // end of range
    test(sampleArr, sampleArrSize, 5);      // in range
    test(sampleArr, sampleArrSize, 9);   // other end of range
    test(sampleArr, sampleArrSize, 999);   // other end of range
    return 0;
}
