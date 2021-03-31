import numpy as np
import random as rand
import matplotlib.pyplot as plt
class Dice :
    def spin(self):
        return rand.randint(1,6)


def plotMultipleSpins( rolls =100 ) :
    dice=Dice()
    values = []
    for i in range(rolls):
        values.append(dice.spin())
    plt.hist(values)
    plt.show()
    plt.close()

def plotMeansForMultipleRolls(sampleSize =10, trials = 1000) :
    dice=Dice()
    values = []
    means = []
    for j in range(trials):
        for i in range(sampleSize):
            values.append(dice.spin())
        means.append(np.mean(values))
        print('Trial -> ', j)
    plt.hist(means,bins=20,label='Distribution of Means')

    plt.show()
    plt.close()



# plotMultipleSpins()
plotMeansForMultipleRolls(sampleSize= 300, trials=200)
