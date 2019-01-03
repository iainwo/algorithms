# Knuth-Morris-Pratt (KMP) Pattern Searching

There are $`n - m + 1`$ substrings which could possible match a Pattern of length $`m`$ in a string of length $`n`$.

If you were to check each of these substrings, char by char, that would amount to $`(n - m + 1)*m`$ char-to-char comparisons.
The worst case is that you check every substring and every char in that substring $`O(mn)`$.

`Knuth-Morris-Pratt Pattern Searching` reduces the number of comparisons, as well as reducing the number of char-to-char comparisons.
It does this by observing what comparisons have already determined.

KMP keeps track of how much of the substring it has already matched. If the rest of the comparison fails, KMP will check if there is any possibility that the current matched substring has any possibility of the pattern recurring in a shorter form (prefix).

It uses the length to do so.

[1]: https://www.ics.uci.edu/~eppstein/161/960227.html