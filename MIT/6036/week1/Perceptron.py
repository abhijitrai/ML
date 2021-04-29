import numpy as np
from Tools import getColumnVector as cv
from Tools import distanceFromPlane as dist
from solution.solution_data import super_simple_separable_through_origin


def perceptron_check_th(X, Th, Th0, pred):
    thTranspose = np.transpose(Th)
    vector = np.array(X)
    dotProduct = np.dot(thTranspose, vector)
    res = dotProduct + Th0
    return pred * res.item()


def updateTheta0(th0, predictedVal):
    return th0 + predictedVal


def updateTheta(th, testPoint, predictedVal):
    tmpVector = predictedVal * testPoint  # Predicted val is a scalar , test point is a vector
    result = th + tmpVector
    return result


def testRun(sampleData, th, th0):
    print('****************************Test Run Start , Th ={0} , Th0 = {1} ************************************'.format(th.flatten(),th0))
    for data in sampleData:
        testPoint = np.array(cv(data[0]))
        predictedVal = data[1]
        distance = dist(testPoint, th, th0)
        act = np.sign(distance)
        print('Result', act == predictedVal, '\tPoint -> ', data[0], '\tDist', distance, '\t Label -> ',
              predictedVal,
              ' Prediction -> ', act)
    print('***************************Test Run End , Th ={0} , Th0 = {1} *************************************'.format(th.flatten(),th0))


def algo(sampleData, th, th0, T=20, verbose = False ):
    count = 0
    for i in range(T):
        for data in sampleData:
            testPoint = np.array(cv(data[0]))
            predictedVal = data[1]
            if perceptron_check_th(testPoint, th, th0, predictedVal) <= 0:
                if th.all() == 0 and  th0 == 0  :
                    #  do nothing
                    print()
                else :
                    if verbose :
                        testRun(sampleData, th, th0)
                th = updateTheta(th, testPoint, predictedVal)
                th0 = updateTheta0(th0, predictedVal)
                count = count + 1
                print('Mistake -> ', count, '\tTh -> ', th.flatten(), '\t Th0 -> ', th0)

    print('-----------------Final Result----------------------')
    print('Final ----> Th -> ', th.flatten(), '\t Th0 -> ', th0)
    testRun(sampleData, th, th0)


def test1(test=1, verbose = False):
    #  * Question #1
    if test == 1:
        sampleData = [[[1, -1], 1], [[0, 1], -1], [[-1.5, -1], 1]]
    if test == 2:
        sampleData = [[[0, 1], -1], [[-1.5, -1], 1], [[1, -1], 1]]
    # *Question #2 *
    if test == 3:
        sampleData = [[[1, -1], 1], [[0, 1], -1], [[-10, -1], 1]]
    if test == 4:
        sampleData = [[[0, 1], -1], [[-10, -1], 1], [[1, -1], 1]]
    if test == 5:
        sampleData = [[[-3, 2], 1], [[-1, 1], -1], [[-1, -1], -1], [[2,2],-1] , [[1,-1],-1]  ]
    if test == 6:
        sampleData = [[[-1, 1], 1], [[1, -1], 1], [[1, 1], -1], [[2, 2], -1]]
    th = np.zeros((2, 1))  # Generic form of th1 = np.array(cv([0 , 0]))
    th0 = 0
    algo(sampleData, th, th0,verbose=verbose)

def test2(test=1 , verbose = False):
    if(test == 1 )  :
        sampleData = [[[0,0,0],-1],[[0,0,1],-1],[[0,1,0],-1],[[0,1,1],-1],[[1,0,0],-1],[[1,0,1],-1],[[1,1,0],-1],[[1,1,1],1]]
    th = np.zeros((3, 1))  # Generic form of th1 = np.array(cv([0 , 0]))
    th0 = 0
    algo(sampleData, th, th0,verbose=verbose)


test1(6,verbose=False)
# test2(1,verbose=True)
