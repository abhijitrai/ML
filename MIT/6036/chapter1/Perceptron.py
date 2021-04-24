import numpy as np
from Tools import getColumnVector as cv


def calcEqVal(X, Th, Th0):
    thTranspose = np.transpose(np.array(Th))
    vector = np.array(X)
    dotProduct = np.dot(thTranspose, vector)
    # dotProduct = np.sum(np.array(Th)  *  vector)
    res = dotProduct + Th0
    return np.asscalar(res)


def updateTheta0(th0, predictedVal):
    return th0 + predictedVal


def updateTheta(th, testPoint, predictedVal):
    tmpVector = predictedVal * testPoint
    result = th + tmpVector
    return result


def algo(sampleData, th, th0, T=3, ):
    for i in range(T):
        for data in sampleData:
            testPoint = np.array(cv(data[0]))
            predictedVal = data[1]
            if (predictedVal * calcEqVal(testPoint, th, th0) <= 0):
                th = updateTheta(th, testPoint, predictedVal)
                th0 = updateTheta0(th0, predictedVal)

    print('Th -> ', th, '\t Th0 -> ', th0)


sampleData = [[[1, -1], 1], [[0, 1], -1], [[-1.5, -1], 1]]
th = np.array(cv([0 , 0]))
th0 = 0
algo(sampleData, th, th0)
