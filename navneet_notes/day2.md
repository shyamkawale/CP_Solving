Recursion

Recursion can be written in function form.. Things needed for recursion:
1. Function f(n)
2. Base Condition f(0) = 0
3. Constraint on n (for range validity on n)

Mathematical recursion:  
```math
f(i) = a * f(i - 1) + b* f(i - 2) + c * f(i - 2) .. , f(0) = 0
```

Programming recursion:
```java
void recurse(int i) {
    // add check on i, if it's valid or not to stop
    if(base condition) -> stop and return
    if(non valid range) -> stop and return

    recurse(i - 1)
}
```

> Recursion is always linear , because stack is a linear data structure, it is never branched. Our logic handles the branching, not the recursion. 🔥

Converting Loops into Recursion
Every recursive solution must have two essential components:
- Base Case: A condition that stops the recursion to prevent it from running infinitely. This is equivalent to the loop's termination condition (e.g., i < n).

- Recursive Step: The part of the function where it calls itself, passing modified arguments that progress towards the base case. This is equivalent to the loop's update expression (e.g., i++).

1. Basic Forward for Loop

```java
for(int i=0; i<n; i++) {
    do_something();
}
```

Recursive Solution:
```java
void rec(int i, int n) {
    // Base Case: Stop when the loop condition (i < n) is false.
    if (i >= n) return;
    do_something();
    // Recursive Step: Call for the next iteration (i + 1).
    rec(i + 1, n);
}

// Initial call: rec(0, n);
```

2. for Loop with Custom Parameters

```java
for(int i = a; i < b; i += c) {
    do_something();
}
```

Recursive Solution:

```java
void rec(int i, int b, int c) {
    // Base Case: Stop when the loop condition (i < b) is false.
    if (i >= b) return;
    do_something();
    // Recursive Step: Call with the next stepped value (i + c).
    rec(i + c, b, c);
}
// Initial call: rec(a, b, c);
```

3. Backward for Loop

```java
for(int i = n - 1; i >= 0; i--) {
    do_something();
}
```

```java
void rec(int i) {
    // Base Case: Stop when the loop condition (i >= 0) is false.
    if (i < 0) return;
    do_something();
    // Recursive Step: Call for the previous iteration (i - 1).
    rec(i - 1);
}
// Initial call: rec(n - 1);
```

4. Loop with Accumulator

```java
int sumArray(int arr[], int n) {
    int total = 0;
    for (int i = 0; i < n; i++) {
        total += arr[i];
    }
    return total;
}
```

Recursive Solution:
```java
int recursiveSum(int arr[], int i, int n, int current_sum) {
    // Base Case: All elements have been processed; return the final sum.
    if (i == n) {
        return current_sum;
    }
    // Recursive Step: Pass the newly updated sum to the next call.
    return recursiveSum(arr, i + 1, n, current_sum + arr[i]);
}
// Initial call: recursiveSum(arr, 0, n, 0);
```

5. Loop Crazy;

```java
for(int i=0, j=0; i<n; i=j) {
    while(j < n && arr[i] == arr[j]) j++
    process on segment [i ,j) for equal values, call it process()
}

```




