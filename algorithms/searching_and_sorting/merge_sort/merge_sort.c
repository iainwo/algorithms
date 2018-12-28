/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, Perl, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>

void merge(int arr[], int l, int m, int r)
{
    int l_idx=0, r_idx=0, s_idx=l;
    int n1, n2;

    n1 = m - l + 1;
    n2 = r-m;

    int L[n1], R[n2];

    while(l_idx<n1)
    {
        L[l_idx]=arr[l+l_idx];
        l_idx++;
    }
    while(r_idx<n2)
    {
        R[r_idx]=arr[m+1+r_idx];
        r_idx++;
    }

    l_idx=0;
    r_idx=0;
    while(l_idx<n1 && r_idx<n2)
    {
        if(L[l_idx]<R[r_idx])
        {
            arr[s_idx]=L[l_idx];
            l_idx++;
        }
        else
        {
            arr[s_idx]=R[r_idx];
            r_idx++;
        }
        s_idx++;
    }
    while (l_idx<n1)
    {
        arr[s_idx]=L[l_idx];
        l_idx++;
        s_idx++;
    }
    while (r_idx<n2)
    {
        arr[s_idx]=R[r_idx];
        r_idx++;
        s_idx++;
    }
}
void merge_sort(int arr[], int l, int r)
{
  if (l<r)
  {
      int m;
      m=l + (r-l)/2;

      merge_sort(arr,l, m);
      merge_sort(arr,m+1, r);

      merge(arr,l, m, r);
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
    merge_sort(arr, 0, n-1);
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
