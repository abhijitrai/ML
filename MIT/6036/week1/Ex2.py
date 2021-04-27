import numpy as np
import Tools as tools
from Tools import getColumnVector as cv
from Tools import distanceFromPlane as dist

th = np.array([[1], [-1], [2], [-3]])
th0 = 0
A = [np.array(cv([1, -1, 2, -3])),
     np.array(cv([1, 2, 3, 4])),
     np.array(cv([-1, -1, -1, -1])),
     np.array(cv([1, 1, 1, 1]))]

for i in A:
    print(np.dot(np.transpose(th), i))
    print(dist(i, th, 0))
