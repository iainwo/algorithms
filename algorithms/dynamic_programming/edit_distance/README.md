# Edit Distance

__Given two strings determine the minimum number of alterations which can bring those strings into equivalence.__

They types of alterations that can be made are,
1. Delete a character
2. Insert a character
3. Substitute a character

For example,
- BOOM
- ROOM
> can edit with one op. by substituting 'R' for 'B' in "ROOM".

Define the base case, that the first letter of both words are the same or different (thus distance of 0 or 1).
Then define the base case, when there is only one letter defined for the first word, then only one letter defined for second.
Use these base cases to inductively form edit distances for longer substrings, feeding del, insert and sub costs.

Break the words down into their shortest substrings and build from bottom-up.
```
$java -Xmx128M -Xms16M com/iain/practice/Word
1	2	3	4	5	6	
2	2	3	4	5	6	
3	3	3	4	5	6	
4	4	4	4	5	6	
5	5	5	5	5	5	
Word [apple] requires at min [5] edits to become Word [orange]
```

[1]: https://web.stanford.edu/class/cs124/lec/med.pdf
[2]: https://jlordiales.me/2014/03/01/dynamic-programming-edit-distance/