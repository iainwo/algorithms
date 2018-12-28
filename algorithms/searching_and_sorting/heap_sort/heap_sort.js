function heapify(arr, i, max) {
  var sIdx, lIdx, rIdx;

  while (i < max) {
    sIdx = i;
    lIdx = 2 * sIdx + 1;
    rIdx = lIdx + 1;
    if (lIdx < max && arr[lIdx] > arr[sIdx]) {
      sIdx = lIdx;
    }
    if (rIdx < max && arr[rIdx] > arr[sIdx]) {
      sIdx = rIdx;
    }
    if (i == sIdx) {
      return;
    }
    var tmp = arr[sIdx];
    arr[sIdx] = arr[i];
    arr[i] = tmp;
    i = sIdx;
  }
}

function buildHeap(arr, n) {
  for (var i = Math.floor(n / 2 - 1); i >= 0; i--) {
    heapify(arr, i, n);
  }
}

function heapSort(arr, n) {
  var i = n - 1;
  buildHeap(arr, n);
  while (i > 0) {
    var tmp = arr[i];
    arr[i] = arr[0];
    arr[0] = tmp;
    heapify(arr, 0, i - 1);
    i--;
  }
	return arr;
}

console.table(heapSort([0, 4, -99, 4, 3, 2333, 0], 7));
