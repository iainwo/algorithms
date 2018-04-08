/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/
#include <stdio.h>

int main()
{
    int sampleArr[5] = {1, 2, 3, 5, 314, 9000};
    int sampleArrSize = sizeof(sampleArr)/sizeof(sampleArr[0]);

    test(sampleArr, sampleArrSize, 3);
    test(sampleArr, sampleArrSize, 9001);
    return 0;
}
void test (int arr[], int n, int x)
{
    printf("Linear search of len (%d) for val (%d) returned [%d]\n", n, x, search(arr, n, x));
}
int search(int arr[], int n, int x)
{
  int i;
  for (i=0; i<n; i++)
    if (arr[i] == x)
      return i;
  return -1;
}
