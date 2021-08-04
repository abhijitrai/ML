from code_for_hw02_downloadable import *
import numpy as np


def arrayDetails():
    tgtArray = np.arange(60).reshape(2, 3, 2, 5)
    print(tgtArray)
    print("Shape")
    print(tgtArray.shape)
    print("Dimensions")
    print(tgtArray.ndim)
    print("Size")
    print(tgtArray.size)


def matrix_mul():
    print("Array 1  :  2 x 3")
    array1 = np.arange(6).reshape(2, 3)
    print(array1)
    print("Array 2  :  3 x 2")
    array2 = np.arange(6).reshape(3, 2)
    print(array2)
    array3 = array1 @ array2
    print("Matrix Multiplication Way 1 '@' ")
    print(array3)
    array4 = array1.dot(array2)
    print("Matrix Multiplication Way 2 'dot' ")
    print(array4)


def arithmetic_operations():
    # Arithmetic operations
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


def multi_dim_array():
    def f(x, y):
        return 10 * x + y
    array = np.fromfunction(f, (5, 4), dtype=int)
    print("Input")
    print(array)
    print()
    print(["An element at (2,3)  ", array[2, 3]])
    print(["For the first idx start at 0 , end at 5 , step size 2 , for the second idx just select element at idx 3  ", array[0:5:2, 3]])
    print(["For the first idx start at 0 , end at 5 , step size 2 , for the second idx just select idx 2 to 3 , step size 1 ", array[0:5:2, 2:4:]])


def reshaping():
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


# reshaping()
# multi_dim_array()
# arithmetic_operations()
# arrayDetails()
# matrix_mul()
# one_d_array()




