# Direct-Address Table

Used to implement a Dictionary (a dynamic set with insert/search/delete).

Unique, in that each element of the Universe is bijectively mapped to an index in an Array. This mapping is done by associating the Object's key with a unique index in the Array. The array will either store a reference to a satellite object containing that element or the array will store the object itself!

![Direct-Addressing Diagram](algorithms/resources/direct_addressing.png)