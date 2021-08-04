
# Reference
You can find the reference docs [here](https://numpy.org/doc/stable/user/quickstart.html)


# NDArray
NumPy’s **main object** is the homogeneous **multidimensional array**.<br> 
It is a table of elements (usually numbers), all of the same type, indexed by a tuple of non-negative integers.
In NumPy **dimensions** are called _axes_.
For example,
* The **coordinates** of a point in 3D space [1, 2, 1] has **one** _axis_.
  That _axis_ has **3 elements** in it, so we say it has a **length** of 3.
* In the example pictured below, the array has 2 axes.
    * The first axis has a **length** of **2**,
    * The second axis has a **length** of **3**
      `[[1., 0., 0.],
      [0., 1., 2.]]`
* Note that `numpy.array` is **not the same as** the Standard Python Library class `array.array`.
  The standard array can handles one-dimensional arrays and offers less functionality.

# NDArray Attributes
The more important attributes of an ndarray object are:

<Br>**Example**

`>>>import numpy as np`
<br>`>>>a = np.arange(15).reshape(3, 5)`
<br>`>>>a`

`array([`
<br>`[ 0,  1,  2,  3,  4],`
<br>`[ 5,  6,  7,  8,  9],`
<br>`[10, 11, 12, 13, 14]`
<br>`])`

* ndarray.**ndim**<br> 
  The number of **axes** (dimensions) of the array.
  <br>`a.ndim`<br>` 2`
  <br><br>
* ndarray.**shape**<br>
  For a matrix with `n rows and m columns`, shape will be **(n,m)**. 
  The **length** of the shape tuple is therefore the **number of axes**.
  In the below case the length is **2**
  <br>`a.shape`<br>`(3, 5)`
  <br><br>
* ndarray.**size**<br>
  Total number of elements in the array , equals **product** of **elements in shape**
  <br> `a.size`<BR> `15`
  
<br> 

**Another Example**
<pre>
import numpy as np
# Exercises
# Think of this as a hypercube containing two cube of size 3 x 2 x 5
# Creating a HyperCube 
tgtArray = np.arange(60).reshape(2,3,2,5)
print(tgtArray)
[ 
  [
    [[ 0  1  2  3  4]    [ 5  6  7  8  9]]
    [[10 11 12 13 14]    [15 16 17 18 19]]
    [[20 21 22 23 24]    [25 26 27 28 29]]
  ]

  [ 
    [[30 31 32 33 34] [35 36 37 38 39]]
    [[40 41 42 43 44] [45 46 47 48 49]]
    [[50 51 52 53 54] [55 56 57 58 59]]
  ]
  
]

print("Shape")
print( tgtArray.shape)
Shape
(2, 3, 2, 5)

print("Dimensions")
print( tgtArray.ndim)
Dimensions 4

print("Size")
print( tgtArray.size)
Size
60
</pre>

# NDArray Creation
## Function `array`
`>>> a = np.array(1, 2, 3, 4)    # WRONG`<br>
`>>> a = np.array([1, 2, 3, 4])  # RIGHT`<br>

## Sequences
<pre>
b = np.array([(1.5, 2, 3), (4, 5, 6)])<br>
b

array([[1.5, 2. , 3. ],
[4. , 5. , 6. ]])

</pre>
##  Functions `zeros`/`ones`/`empty`
<pre>np.zeros((3, 4))

array([[0., 0., 0., 0.],
[0., 0., 0., 0.],
[0., 0., 0., 0.]])
</pre>

## Function `arange`
<pre>np.arange(10, 30, 5)
array([10, 15, 20, 25])

np.arange(0, 2, 0.3)  # it accepts float arguments
array([0. , 0.3, 0.6, 0.9, 1.2, 1.5, 1.8])
</pre>

## Function `resize`/`transpose`/ `T`
<PRE>
    array = np.arange(10).reshape(2, 5)
    print("Input Array [2,5]")
    print(array)
    print("Resize Array to [5,2]")
    array.resize(5, 2)
    print(array)
    print("Transpose the Array using T, so it turns [2,5]")
    array = array.T
    print(array)
    print("Transpose the Array using transpose() , so it turns [5,2]")
    array = array.transpose()
    print(array)
</PRE>
**Output**
<PRE>
Input Array [2,5]
[[0 1 2 3 4]
 [5 6 7 8 9]]
Resize Array to [5,2]
[[0 1]
 [2 3]
 [4 5]
 [6 7]
 [8 9]]
Transpose the Array using T, so it turns [2,5]
[[0 2 4 6 8]
 [1 3 5 7 9]]
Transpose the Array using transpose() , so it turns [5,2]
[[0 1]
 [2 3]
 [4 5]
 [6 7]
 [8 9]]

Process finished with exit code 0

</PRE>


# NDArray Operation

## Arithmetic operators
Arithmetic operators on arrays apply **elementwise**. <br>
<PRE>
    print()
    array1 = np.arange(10, 20, 2)
    print(["Test Input     ", array1])
    print()
    print(["Array + 2", array1 + 2])
    print(["Array - 2", array1 - 2])
    print(["Array * 2", array1 * 2])
    print(["Array ** 3", array1 ** 3])
    print(["sum", array1.sum()])
    print(["min", array1.min()])
    print(["max", array1.max()])
    print(["Cumulative Sum", array1.cumsum()])
    print(["Logical < 15", array1<15 ])
</PRE>
**Output**
<PRE>
['Test Input     ', array([10, 12, 14, 16, 18])]
['Array + 2', array([12, 14, 16, 18, 20])]
['Array - 2', array([ 8, 10, 12, 14, 16])]
['Array * 2', array([20, 24, 28, 32, 36])]
['Array ** 3', array([1000, 1728, 2744, 4096, 5832], dtype=int32)]
['sum', 70]
['min', 10]
['max', 18]
['Cumulative Sum', array([10, 22, 36, 52, 70], dtype=int32)]
['Logical < 15', array([ True,  True,  True, False, False])]
</PRE>

## Matrix Product
The product operator _*_ _operates **elementwise**_ in NumPy arrays.
<br> The matrix product can be performed using the **@** **operator** (in python >=3.5) or the **dot function** :
<PRE>
print("Array 1  :  2 x 3")
array1 = np.arange(6).reshape(2,3)
print(array1)
print("Array 2  :  3 x 2")
array2 = np.arange(6).reshape(3,2)
print(array2)
array3 = array1 @ array2
print("Matrix Multiplication Way 1")
print(array3)
array4 = array1.dot( array2)
print("Matrix Multiplication Way 2")
print(array4)
</PRE>
**Output**
<PRE>
Array 1  :  2 x 3
[[0 1 2]
 [3 4 5]]
Array 2  :  3 x 2
[[0 1]
 [2 3]
 [4 5]]
Matrix Multiplication Way 1 via  @
[[10 13]
 [28 40]]
Matrix Multiplication Way 2 via 'dot'
[[10 13]
 [28 40]]
</PRE>

##Slicing - One Dimension
Single Dimension is identical to Arrays.<br>
Additionally , we can specify a subarray via **X[start_idx:end_idx:step_size]** 
<pre>
def one_d_array():
    test = np.arange(10, 20)
    print("Test Input", test[0:9])
    # The end part is the step & it cant be 0
    # The first one is invalid since the step cant be 0
    # print(test[2:5:0])
    print("Step 1", test[0:9:1])
    print("Step 1, missing start index", test[:9:1])
    print("Step 1, missing end index", test[0::1])
    print("Step 2", test[0:9:2])
    print("Step 2, missing start index", test[:9:2])
    print("Step 2, missing end index", test[0::2])
    print("Step 3", test[0:9:3])
    print("Step -1", test[9:0:-1])
    print("Step -2", test[9:0:-2])
    print("Step -2", test[9:0:-3])
    print("Step -1, starting at 0  ", test[0:9:-1])
    print("Index -1 , start at end  ", test[-1])
    print("Index -2 , start at end  ", test[-2])
</pre>
**Output**
<pre>Test Input [10 11 12 13 14 15 16 17 18]
Step 1 [10 11 12 13 14 15 16 17 18]
Step 1, missing start index [10 11 12 13 14 15 16 17 18]
Step 1, missing end index [10 11 12 13 14 15 16 17 18 19]
Step 2 [10 12 14 16 18]
Step 2, missing start index [10 12 14 16 18]
Step 2, missing end index [10 12 14 16 18]
Step 3 [10 13 16]
Step -1 [19 18 17 16 15 14 13 12 11]
Step -2 [19 17 15 13 11]
Step -2 [19 16 13]
Step -1, starting at 0   []
Index -1 , start at end   19
Index -2 , start at end   18
</pre>

##Slicing - Multi Dimension
A Multi dimensional array can be sliced similar to a one d array , except 
*  **each axis** can be sliced
* When fewer indices are provided than the number of axes, the **missing indices are considered complete slices**
<pre>
   def f(x, y):
        return 10 * x + y
    array = np.fromfunction(f, (5, 4), dtype=int)
    print("Input")
    print(array)
    print()
    print(["An element at (2,3)  ", array[2, 3]])
    print(["For the first idx start at 0 , end at 5 , step size 2 , for the second idx just select element at idx 3  ", array[0:5:2, 3]])
    print(["For the first idx start at 0 , end at 5 , step size 2 , for the second idx just select idx 2 to 3 , step size 1 ", array[0:5:2, 2:4:]])

</pre>
**Output**
<pre>
Input
[[ 0  1  2  3]
 [10 11 12 13]
 [20 21 22 23]
 [30 31 32 33]
 [40 41 42 43]]
['An element at (2,3)  ', 23]
['For the first idx start at 0 , end at 5 , step size 2 , for the second idx just select element at idx 3  ', array([ 3, 23, 43])]
['For the first idx start at 0 , end at 5 , step size 2 , for the second idx just select idx 2 to 3 , step size 1 ', array([[ 2,  3],
       [22, 23],
       [42, 43]])]
</pre>

