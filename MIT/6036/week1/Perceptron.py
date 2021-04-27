import numpy as np
from Tools import getColumnVector as cv
from Tools import distanceFromPlane as dist
from solution.solution_data import super_simple_separable_through_origin


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


def testRun(sampleData, th, th0):
    print('****************************Test Run Start************************************')

    for data in sampleData:
        testPoint = np.array(cv(data[0]))
        predictedVal = data[1]
        distance = dist(testPoint, th, th0)
        act = np.sign(distance)
        print('Result', act == predictedVal, '\tPoint -> ', data[0], '\tDist', distance, '\t Predicted -> ', predictedVal,
              ' Actual -> ', act)
    print('***************************Test Run End *************************************')


def algo(sampleData, th, th0, T=3, ):
    count = 0
    for i in range(T):
        for data in sampleData:
            testPoint = np.array(cv(data[0]))
            predictedVal = data[1]
            if ( (predictedVal * calcEqVal(testPoint, th, th0) )<= 0):
                print('Sample Run with Th -> ', th, '\t Th0 -> ', th0)
                testRun(sampleData, th, th0)
                th = updateTheta(th, testPoint, predictedVal)
                th0 = updateTheta0(th0, predictedVal)
                count = count + 1
                print('Update -> ', count,  '\tTh -> ', th, '\t Th0 -> ', th0)

    print('-----------------Final Result----------------------')
    print('Final ----> Th -> ', th, '\t Th0 -> ', th0)
    testRun(sampleData, th, th0)

X,Y = super_simple_separable_through_origin()
print(X)
print(Y)


def test1():
    #  * Question #1
    sampleData = [[[1, -1], 1], [[0, 1], -1], [[-1.5, -1], 1]]
    sampleData = [[[0, 1] ,-1], [[-1.5, -1], 1], [[1, -1], 1]]
    # *Question #2 *
    sampleData = [[[1, -1], 1], [[0, 1], -1], [[-10, -1], 1]]
    sampleData = [ [[0, 1], -1], [[-10, -1], 1],[[1, -1], 1]]
    th = np.zeros((2, 1))  # Generic form of th1 = np.array(cv([0 , 0]))
    th0 = 0
    algo(sampleData, th, th0)
