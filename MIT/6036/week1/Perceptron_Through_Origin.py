import numpy as np
from Tools import getColumnVector as cv
from Tools import distanceFromPlane as dist
from solution.solution_data import labels1, data1, data2, labels2
from solution.solution_plot import plot_data, plot_separator

#
# def calcEqVal(X, Th, Th0):
#     thTranspose = np.transpose(np.array(Th))
#     vector = np.array(X)
#     dotProduct = np.dot(thTranspose, vector)
#     res = dotProduct + Th0
#     return np.asscalar(res)


def updateTheta(th, testPoint, predictedVal):
    tmpVector = predictedVal * testPoint
    result = th + tmpVector
    return result


def testRun(sampleData, th):
    print('****************************Test Run Start************************************')
    for data in sampleData:
        testPoint = np.array(cv(data[0]))
        predictedVal = data[1]
        distance = dist(testPoint, th, 0)
        act = np.sign(distance)
        print('Result', act == predictedVal, '\tPoint -> ', data[0], '\tDist', distance, '\t Predicted -> ',
              predictedVal,
              ' Actual -> ', act)
    print('***************************Test Run End *************************************')


def perceptron_th_check(th, x, y):
    """
    Returns the value of Eq : Y(i) . ( Th.T X(i) )
    Here
    1. ( Th.T X(i) ) determines in which half space the point lie ( +ve /-ve ) .
    This is our prediction as per the Th
    2. Y(i) is the actual label ( +1 / -1 )
    Hence if the sign as determined by Th is equal to sign if the label , we get a +ve val back.
    Else we get a -ve value back
    :param th: Theta is a Vector of n dimensions
    :param x:  Point is a vector of n dimensions
    :param y:  Label is the true label
    :return:   Computed signed value
    """
    val = y * (np.dot(np.transpose(th), x))
    return val


def algo(sample_data, th, T=20, plotIntermediate=True):
    """
    Tries to finf the Linear Seperator by the Perceptron method
    :param sample_data:
    :param th: Th init to 0 vector
    :param T: Iterations to run
    :param plotIntermediate:  Plot intermediate results
    """
    count = 0
    for i in range(T):
        changes = 0
        for data in sample_data:
            testPoint = np.array(cv(data[0]))
            predictedVal = data[1]
            if (perceptron_th_check(th, testPoint, predictedVal) <= 0):
                print('Sample Run with Th -> ', th.flatten())
                testRun(sample_data, th)
                th = updateTheta(th, testPoint, predictedVal)
                count = count + 1
                print('Mistake -> {0} \t Th -> {1}'.format(count, th.flatten()))
                if (plotIntermediate):
                    labels = (np.array(sample_data))[:, 1]
                    plot_able_data = get_plotable_structure(sample_data)
                    ax = plot_data(plot_able_data, labels)
                    plot_separator(ax, th, 0)
                changes = 1
        if changes == 0:
            break
        else:
            labels = (np.array(sample_data))[:, 1]
            plot_able_data = get_plotable_structure(sample_data)
            ax = plot_data(plot_able_data, labels)
            plot_separator(ax, th, 0)

    print('-----------------Final Result----------------------')
    print('Final ----> Th -> ', th.flatten())
    testRun(sample_data, th)



def formatInputData(data, labels):
    xVals = data[0]
    yVals = data[1]
    lbl = labels[0]
    ar = []
    for i in range(xVals.size):
        ar.append([[yVals[i], yVals[i]], lbl[i]])
    return ar


def get_plotable_structure(sample_data):
    data = (np.array(sample_data))[:, 0]
    xVals = []
    yVals = []
    for val in data:
        xVals.append(val[0])
        yVals.append(val[1])
    return np.array([xVals, yVals])


def test1(arg=1):
    data1, labels1, data2, labels2
    th = np.zeros((2, 1))  # Generic form of th1 = np.array(cv([0 , 0]))
    #  * Question #1
    if(arg == 1 ) :
        sampleData = [[[1, -1], 1], [[0, 1], -1], [[-1.5, -1], 1]]
        algo(sampleData, th)
    if(arg == 2 ) :
        sampleData = [[[0, 1], -1], [[-1.5, -1], 1], [[1, -1], 1]]
        algo(sampleData, th)
    # *Question #2 *
    if(arg == 3 ) :
        sampleData = [[[1, -1], 1], [[0, 1], -1], [[-10, -1], 1]]
        algo(sampleData, th)
    if(arg == 4 ) :
        sampleData = [[[0, 1], -1], [[-10, -1], 1], [[1, -1], 1]]
        algo(sampleData, th)
    if(arg == 5 ) :
        sampleData = formatInputData(data1, labels1)
        algo(sampleData, th,plotIntermediate=False)
    if(arg == 6 ) :
        sampleData = formatInputData(data2, labels2)
        algo(sampleData, th, plotIntermediate=False)
    print("Done")




test1(arg=3)
