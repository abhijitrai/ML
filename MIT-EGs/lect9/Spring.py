import numpy as np
import matplotlib.pylab as pylab


def getData(fileName = "springData.txt"):
    raw_data = open(fileName)
    raw_data.readline()
    data = raw_data.readlines()
    xVals = []
    yVals = []
    for row in data:
        yVal, xVal = row.strip('\n').split(' ')
        xVals.append(float(xVal))
        yVals.append(float(yVal))
    return (xVals, yVals)


def plotDisplacementData(xVals, yVals):
    pylab.xlabel('Mass')
    pylab.ylabel('Displacements')
    pylab.title('Displacement Vs Mass plot')
    pylab.plot(xVals, yVals, 'rx')


def getWeightData():
    xVals, yVals = getData();
    yVals = pylab.array(yVals)
    yVals = yVals * 9.8
    return xVals, yVals


#
# def diff(obs, pred):
#     return obs - pred
#
# # Sum of Squares gives us a concave surface
# def objectiveFunction(obs,pred) :
#     return diff(obs,pred)**2

# PolyFit Uses Least Squares
def fitLineUsingPolyfit(xVals, yVals):
    a, b = pylab.polyfit(xVals, yVals, 1)
    estYVals = a * np.array(xVals) + b
    pylab.plot(xVals, estYVals, 'r')


# PolyFit Uses Least Squares
def fitQuadUsingPolyfit(xVals, yVals):
    a, b, c = pylab.polyfit(xVals, yVals, 2)
    estYVals = a * (np.array(xVals) ** 2) + b * np.array(xVals) + c
    pylab.plot(xVals, estYVals, 'b')


# PolyFit Uses Least Squares
def fitCubeUsingPolyfit(xVals, yVals):
    a, b, c, d = pylab.polyfit(xVals, yVals, 3)
    estYVals = a * (np.array(xVals) ** 3) + b * (np.array(xVals)) ** 2 + c * np.array(xVals) + d
    pylab.plot(xVals, estYVals, 'm')


# PolyFit Uses Least Squares
def fitQuadUsingPolyfit(xVals, yVals):
    a, b, c, d, e = pylab.polyfit(xVals, yVals, 4)
    estYVals = a * (np.array(xVals) ** 4) + b * (np.array(xVals)) ** 3 + c * (np.array(xVals)) ** 2 + d * np.array(
        xVals) + e
    pylab.plot(xVals, estYVals, 'g')

def plotMultipleFitsForDate(xVals, yVals) :
    plotDisplacementData(xVals, yVals);
    fitLineUsingPolyfit(xVals, yVals)
    fitQuadUsingPolyfit(xVals, yVals)
    fitCubeUsingPolyfit(xVals, yVals)
    fitQuadUsingPolyfit(xVals, yVals)
    pylab.show();
    pylab.close()

def fitSpringData():
    xVals, yVals = getWeightData()
    plotMultipleFitsForDate(xVals,yVals)

xVals, yVals = getData('mysteryData.txt')
plotMultipleFitsForDate(xVals,yVals)

i=0
# pylab.show();
# pylab.close()

