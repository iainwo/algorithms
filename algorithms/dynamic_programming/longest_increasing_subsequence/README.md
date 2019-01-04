# Longest Increasing Subsequence (LIS)

The longest increasing subsequence is another dynamic programming question.

It has 1. Optimal Substructure.
> The LIS is the LIS of it's own substring plus the terminal end

```cplusplus
set<int> st;
set<int>::iterator it;
st.clear();
for(i=0; i<n; i++) {
  st.insert(array[i]);
  it=st.find(array[i]);
  it++;
  if(it!=st.end()) st.erase(it);
}
cout<<st.size()<<endl;
```
> Beautiful. Doesn't account for dupes.<br>
> From [stackoverflow.com][2]

## Patience Sorting
Super super cool.
```cplusplus
set<int> st;
set<int>::iterator it;
st.clear();
for(int i=0; i<n; i++) {
    it = st.lower_bound(a[i]);
    if (it != st.end()) st.erase(it);
    st.insert(a[i]);
}
cout<<st.size()<<endl;
```
> Better handles dupes<br>
> From [stackoverflow.com][2]

With back-tracking,
```cplusplus
typedef pair<int, int> IndexValue;

struct IndexValueCompare{
    inline bool operator() (const IndexValue &one, const IndexValue &another){
        return one.second < another.second;
    }
};

vector<int> LIS(const vector<int> &sequence){
    vector<int> parent(sequence.size());
    set<IndexValue, IndexValueCompare> s;
    for(int i = 0; i < sequence.size(); ++i){
        IndexValue iv(i, sequence[i]);
        if(i == 0){
            s.insert(iv);
            continue;
        }
        auto index = s.lower_bound(iv);
        if(index != s.end()){
            if(sequence[i] < sequence[index->first]){
                if(index != s.begin()) {
                    parent[i] = (--index)->first;
                    index++;
                }
                s.erase(index);
            }
        } else{
            parent[i] = s.rbegin()->first;
        }
        s.insert(iv);
    }
    vector<int> result(s.size());
    int index = s.rbegin()->first;
    for(auto iter = s.rbegin(); iter != s.rend(); index = parent[index], ++iter){
        result[distance(iter, s.rend()) - 1] = sequence[index];
    }
    return result;
}
```
> From [stackoverflow.com][2]

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(nlogn) | __O(nlogn)__ | _O(nlogn)_
> has to iterate through each element of the sequence<br>
> has to search through up to n "piles" of subsequences for a subsequence with a smaller/eq value - take logn time

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n)__ | __O(n)__ | __O(n)__
> to store the largest values of the current subsequence<br>
> needs a second array to keep array of backtracking pointers.

[2]: https://stackoverflow.com/a/12657267