# SEARCHING AND SORTING

## Searching

COMPLETE | ALGO | BEST TIMECOMPLEXITY | AVG TIMECOMPLEXITY | WORST TIMECOMPLEXITY | WORST SPACE COMPLEXITY
--- | --- | --- | --- | --- | ---
:white_check_mark: | [Linear Search](./linear_search/) | &Omicron;(1) | &Omicron;(n) | &Omicron;(n) | &Omicron;(1)
:white_check_mark: | [Binary Search](./binary_search/) | &Omicron;(1) | &Omicron;(logn) | &Omicron;(logn) | &Omicron;(logn)<sup>[1](#myfootnote1)</sup>
:white_check_mark: | [Jump Search](./jump_search/) | &Omicron;(1) | &Omicron;(&radic;<span style="text-decoration: overline">n</span>) | &Omicron;(&radic;<span style="text-decoration: overline">n</span>) | &Omicron;(1)
:white_check_mark: | [Interpolation Search](./interpolation_search/) | &Omicron;(1) | &Omicron;(loglogn)<sup>[2](#myfootnote2)</sup> | &Omicron;(loglogn)<sup>[2](#myfootnote2)</sup> | &Omicron;(1)
:white_check_mark: | [Exponential Search](./exponential_search/) | &Omicron;(1) | &Omicron;(logi)<sup>[3](#myfootnote3)</sup> | &Omicron;(logi)<sup>[3](#myfootnote3)</sup> | &Omicron;(1)
:white_check_mark: | [Ternary Search](./ternary_search/) | &Omicron;(1) | &Omicron;(logn)<sup>[4](#myfootnote4)</sup> | &Omicron;(logn) | &Omicron;(logn)<sup>[1](#myfootnote1)</sup>

<a name="myfootnote2">1</a>: When using recursion; otherwise &Omicron;(1)<br>
<a name="myfootnote2">2</a>: When data is uniformly distributed<br>
<a name="myfootnote3">3</a>: Where *i* is the index of the search value<br>
<a name="myfootnote4">4</a>: Worse than binary_search<br>

## Sorting
COMPLETE | ALGO | BEST TIMECOMPLEXITY | AVG TIMECOMPLEXITY | WORST TIMECOMPLEXITY | WORST SPACE COMPLEXITY
--- | --- | --- | --- | --- | ---
:white_check_mark: | [Selection Sort](./selection_sort/) | &Omega;(n<sup>2</sup>) | &Theta;(n<sup>2</sup>) | &Omicron;(n<sup>2</sup>) | &Omicron;(1)
:white_check_mark: | [Bubble Sort](./bubble_sort/) | &Omega;(n) | &Theta;(n<sup>2</sup>) | &Omicron;(n<sup>2</sup>) | &Omicron;(1)
:white_check_mark: | [Insertion Sort](./insertion_sort/) | &Omega;(n) | &Theta;(n<sup>2</sup>) | &Omicron;(n<sup>2</sup>) | &Omicron;(1)
:white_check_mark: | [Merge Sort](./merge_sort/) | &Omega;(logn) | &Theta;(nlogn) | &Omicron;(nlogn) | &Omicron;(n)
:white_check_mark: | [Heap Sort](./heap_sort/) | &Omega;(nlogn) | &Theta;(nlogn) | &Omicron;(nlogn) | &Omicron;(1)
:umbrella: | Quick Sort | | | |
:umbrella: | Radix Sort | | | |
:umbrella: | Counting Sort | | | |
:umbrella: | Bucket Sort | | | |
:umbrella: | Shell Sort | | | |
:umbrella: | Comb Sort | | | |
:umbrella: | Pigeon-hole Sort | | | |
:umbrella: | Cycle Sort | | | |

## Misc
- [ ] TODO
