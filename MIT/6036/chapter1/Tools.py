import numpy as np

def error(A) :
    A = np.array(A)
    actual =    A[:,1]
    pred =    A[:,0]
    diff = pred - actual
    return np.sum(np.sqrt(diff**2))/A.shape[0]

def __testError():
    A = [[1,2],[2,3],[3,4],[3,1]  ]
    print(error(A))
    A = [[1,1],[2,2],[3,3],[3,3]  ]
    print(error(A))


def determinantOfVector(A) :
    # For Vector A & B
    # (A Transpose) {Cross Product} B = A {Dot Product} B
    # Sum of Squares for a Vector A = A { Dot Product } A
    sumOfSq = np.dot( np.array(A).T , np.array(A))
    val1 = np.sqrt(sumOfSq)
    B = A ** 2
    val2 = np.sqrt(np.sum(B))
    return [val1,val2]

def __testDeterminantOfVector() :
    A = np.array([[1],[2] ,[3],[4],[5]])
    print(determinantOfVector(A))


def distanceFromPlane(x,th,th0) :
    dist = ( np.dot( np.array(th).T , np.array(x) ) + th0 ) / determinantOfVector(th)
    return dist

def __testDistanceFromPlane():
    th = np.array([[3],[4]])
    x = np.array([[0],[0]])
    th0 = 5
    print(distanceFromPlane(x,th,th0))

