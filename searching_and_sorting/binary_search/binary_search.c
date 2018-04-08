/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>

int main()
{
    int sampleArr[5] = {0, 2, 3, 5, 9000};
    int sampleArrSize = sizeof(sampleArr)/sizeof(sampleArr[0]);

    test(sampleArr, sampleArrSize, -100);   // before range
    test(sampleArr, sampleArrSize, 0);      // end of range
    test(sampleArr, sampleArrSize, 3);      // in range
    test(sampleArr, sampleArrSize, 9000);   // other end of range
    test(sampleArr, sampleArrSize, 9999);   // other end of range
    return 0;
}
void test (int arr[], int n, int x)
{
    printf("binary_search of len (%d) for val (%d) returned [%d]\n", n, x, binary_search(arr, 0, n-1, x));
}
int binary_search(int arr[], int l, int r, int x)
{
    while (l <= r)
    {
        int m = l + (r-l)/2;

        if (arr[m]==x) return m;
        if (arr[m]>x)
            r=m-1;
        else
            l=m+1;
    }

    return -1;
}
