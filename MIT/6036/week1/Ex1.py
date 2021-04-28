import numpy as np


def getNpArray(rowCount, colCount, asNp=True, val=1):
    arr = []
    for i in range(0, rowCount):
        arr2 = []
        for j in range(0, colCount):
            arr2.append(val)
        arr.append(arr2)
    if (asNp):
        return np.array(arr)
    return arr


def printAsVector(A):
    tgt = np.array(A)
    tmpStr = ""
    for i in range(2 * tgt.shape[1]):
        tmpStr += "\t"
    str1 = "_" + tmpStr + "_"
    for val in range(tgt.shape[0]):
        res = tgt[val];
        str2 = ""
        for tmp in range(tgt.shape[1]):
            str2 = str2 + "\t" + str(res[tmp]) + "\t"
        str2 = "|" + str2 + "|"
        str1 = str1 + "\n" + str2
    str1 += "\n-" + tmpStr + "-"
    print(str1)


def getColVector(dim):
    return getNpArray(dim, 1)


def getRowVector(dim):
    print('Row Vector')
    return getColVector(dim).transpose()


def getNmMatrix(row, column):
    print('Matrix')
    return getNpArray(row, column)


def display(A):
    print('Shape -> ', A.shape)
    print(A)
    printAsVector(A)


def vectorCrossProduct(A, B):
    print("Vector A")
    printAsVector(A)
    print("Vector B")
    printAsVector(B)
    print("\nVector Cross Product")
    display(np.dot(A, B))


def vectorDotProduct(A, B):
    print("Vector A")
    printAsVector(A)
    print("Vector B")
    printAsVector(B)
    print("\nVector Dot Product")
    display(np.dot(np.transpose(A), B))


def extractSubMatrixByCol():
    print("\nExtract sub matrix by col");
    A = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [10, 11, 12, 13]])
    printAsVector(A)
    print('A[:, 1] , Shape  -->', A[:, 1].shape, "\t Values -> ", A[:, 1])
    print('A[:, 0:1],Shape -->', A[:, 0:1].shape, "\t Values -> ")
    printAsVector(A[:, 0:1])
    print('A[:, 1:2],Shape -->', A[:, 1:2].shape, "\t Values -> ")
    printAsVector(A[:, 1:2])
    print('A[:, 0:3],Shape -->', A[:, 0:3].shape, "\t Values -> ")
    printAsVector(A[:, 0:3])


def extractSubMatrixByRow():
    print("\nExtract sub matrix by row");
    A = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [10, 11, 12, 13]])
    printAsVector(A)
    print('A[1, :],Shape -->', A[1, :].shape, "\t Values -> ", A[1, :])
    print('A[1:2,:], Shape  -->', A[1:2, :].shape, "\t Values -> ")
    printAsVector(A[1:2, :])
    print('A[0:3,:], Shape  -->', A[0:3, :].shape, "\t Values -> ")
    printAsVector(A[0:3, :])


def extractSubMatrixByRowAndCol():
    print("\nExtract sub matrix by row and col");
    A = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [10, 11, 12, 13]])
    printAsVector(A)
    print('A[0:3, 1:4],Shape -->', A[0:3, 1:4].shape, "\t Values -> ")
    printAsVector(A[0:3, 1:4])


def determinantOfVector(A):
    # V(Transpose) { Cross Product ) x W = V { Dot Product } W
    # Sum of Squares for a Vector V = V { Dot Product } V
    sumOfSq = np.dot(np.array(A).T, np.array(A))
    val1 = np.sqrt(sumOfSq)
    B = A ** 2
    val2 = np.sqrt(np.sum(B))
    return [val1, val2]


# print('Column Vector')
# display(getColVector(5))
# print("\n***********************************************************\n")
# display(getRowVector(5))
# print("\n***********************************************************\n")
# display(getNmMatrix(3, 4))
# print("\n***********************************************************\n")
# vectorCrossProduct(getNpArray(2, 2, val=1), getNpArray(2, 2,val=1))
# print("\n***********************************************************\n")
# vectorDotProduct(getNpArray(2, 2,val=1),getNpArray(2, 2,val=1))
# print("\n***********************************************************\n")
# extractByRow()
# print("\n***********************************************************\n")
# extractByRowAndCol()
# print("\n***********************************************************\n")
# extractByCol()
#
#
# A = np.array([[1],[2] ,[3],[4],[5]])
# # A = A**2
# print(determinantOfVector(A))

array_3d = np.array([[[1, 1, 1, 1],
                      [2, 2, 2, 2],
                      [3, 3, 3, 3]],

                     [[4, 4, 4, 4],
                      [5, 5, 5, 5],
                      [6, 6, 6, 6]],

                     [[7, 7, 7, 7],
                      [8, 8, 8, 8],
                      [9, 9, 9, 9]]]
                    )

print('Array Shape-> ', array_3d.shape)
print( array_3d)
print('Shape [0,:] ' , array_3d[0,:].shape)
print(array_3d[0,:])
print('Shape [1,:]' , array_3d[1,:].shape)
print(array_3d[1,:])
print('Min ' , np.min(array_3d[0, :]))# - 0.5
print('Max ', np.max(array_3d[0, :])) #+ 0.5
print('Min ' ,np.min(array_3d[1, :]))# - 0.5
print('Max ', np.max(array_3d[1, :]) ) # + 0.5
# print(array_3d.min(axis=2))