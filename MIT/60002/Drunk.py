import random as random
import matplotlib.pyplot as plt
import math as math
import numpy as np


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return f"X-> {self.x}\tY->{self.y}"

    def move(self, x, y):
        return Point(self.x + x, self + y + y)


class Drunk:
    def __init__(self):
        self.choices = [Point(1, 0), Point(-1, 0), Point(0, 1), Point(0, -1)]

    def step(self):
        return self.choices[random.randint(0, 3)]

    def walk(self, steps):
        start = Point(0, 0)
        xVals = []
        yVals = []
        xVals.append(0)
        yVals.append(0)
        current = start
        for x in range(steps):
            step = self.step()
            current = Point(current.x + step.x, current.y + step.y)
            xVals.append(current.x)
            yVals.append(current.y)
        return [xVals, yVals, current]

    def plotWalk(self, steps):
        res = self.walk(steps)
        xVals = res[0]
        yVals = res[1]
        xpoints = np.array(xVals)
        ypoints = np.array(yVals)
        plt.grid(True)
        plt.plot(xpoints, ypoints)
        plt.show()
        plt.savefig("drunk.png")
        plt.close()


class BiasedDrunk(Drunk):
    def __init__(self):
        self.choices = [Point(1, 0), Point(-1, 0), Point(0, 1.2), Point(0, -0.8)]


class BiasedDrunk2(Drunk):
    def __init__(self):
        self.choices = [Point(1.2, 0), Point(-0.8, 0), Point(0, 1), Point(0, -1)]


class BiasedDrunk3(Drunk):
    def __init__(self):
        self.choices = [Point(0.8, 0), Point(-1.2, 0), Point(0, 1), Point(0, -1)]


def setPlotValues():
    plt.grid(True)
    ax = plt.gca()
    ax.spines['top'].set_color('none')
    ax.spines['left'].set_position('zero')
    ax.spines['right'].set_color('none')
    ax.spines['bottom'].set_position('zero')


def simWalk(type=0, steps=1000, trials=100):
    xVals = []
    yVals = []
    for i in range(1, trials):
        if type == 0:
            res = Drunk().walk(steps)
        if type == 1:
            res = BiasedDrunk().walk(steps)
        if type == 2:
            res = BiasedDrunk2().walk(steps)
        if type == 3:
            res = BiasedDrunk3().walk(steps)

        xVals.append(res[2].x)
        yVals.append(res[2].y)

    xpoints = np.array(xVals)
    ypoints = np.array(yVals)
    return [xpoints, ypoints];


# def hyp(x,y):
#     return math.sqrt(x**2 + y**2 )

def plotWalksEndpoint(steps=1000, trials=100):
    setPlotValues()

    result = simWalk(type=0, steps=steps, trials=trials)
    plt.plot(result[0], result[1], 'g.')

    result = simWalk(type=1, steps=steps, trials=trials)
    plt.plot(result[0], result[1], 'c.')

    result = simWalk(type=2, steps=steps, trials=trials)
    plt.plot(result[0], result[1], 'm.')

    result = simWalk(type=3, steps=steps, trials=trials)
    plt.plot(result[0], result[1], 'r.')

    # print("Mean X -> ", result[0].mean() , " \tSum -> ", sum(result[0]) , "\t Size -> ", result[0].size , " \tMean Calc -> ", sum(result[0])/result[0].size)
    # print("Mean Y -> ", result[1].mean() , " \tSum -> ", sum(result[1]) , "\t Size -> ", result[1].size , " \tMean Calc -> ", sum(result[1])/result[1].size)
    # dist = []
    # for x in range(0,result[0].size):
    #     dist.append((result[0].mean() - result[0][x])**2)
    # print("Calculated STD X -> ", result[0].std() , "Calculated STD -> " , math.sqrt(sum(dist)/result[0].size))
    #
    plt.show()
    # plt.savefig("drunk.png")
    plt.close()


def plotDistances(start=100, end=1000):
    plt.grid(True)
    xVals = []
    yVals = []
    for x in range(start, end):
        xVals.append(x)
        result = Drunk().walk(x)
        val = result[2].x ** 2 + result[2].y ** 2
        yVals.append(round(math.sqrt(val)))
        # print( ' Processed ' ,x)
    plt.plot(xVals, yVals, 'c-')
    # plt.hist(yVals, bins=100)
    plt.show()
    # plt.close()


plotWalksEndpoint(steps=3000, trials=100)
# plotDistances(0,100)
