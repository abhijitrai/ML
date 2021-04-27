# Distance of a point, from the plane
import numpy as np
def length(A) :
    B = A ** 2
    val2 = np.sqrt(np.sum(B))
    return val2 ;

def distance(x,th,th0) :
    dist = ( np.dot( np.array(th).T , np.array(x) ) + th0 ) / length(th)
    return dist


th = np.array([[3],[4]])
x = np.array([[0],[0]])
th0 = 5
print(distance(x,th,th0))