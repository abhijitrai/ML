import numpy as np
import random as random


class Roulette:
    def __init__(self):
        self.numberOfPockets = 36

    def returnFromRoll(self, choice):
        """
            Returns the Pocket of a single spin
        :return:
        """
        pocket = random.randint(0,35)
        if pocket == choice:
            return 36
        else:
            return -1


class EuropeanRoulette(Roulette):
    def returnFromRoll(self, choice):
        pocket = random.randint(0,35)
        if pocket == choice:
            return 35
        else:
            return -1


class USRoulette(Roulette):
    def returnFromRoll(self, choice):
        pocket = random.randint(0,35)
        if pocket == choice:
            return 34
        else:
            return -1


def expectedReturn(numberOfTrials =1000):
    fairRoulette = Roulette()
    european = EuropeanRoulette()
    us =USRoulette()
    fairReturns = []
    europeanReturns = []
    usReturns = []
    for x in range(numberOfTrials):
        choice = random.randint(0, 35)
        returnForSpin =fairRoulette.returnFromRoll(choice)
        fairReturns.append(returnForSpin)
        returnForSpin =us.returnFromRoll(choice)
        usReturns.append(returnForSpin)
        returnForSpin =european.returnFromRoll(choice)
        europeanReturns.append(returnForSpin)
    print("Fair Return -> " , np.mean(fairReturns), "\t Deviation -> " , np.std(fairReturns))
    print("European Return -> " , np.mean(europeanReturns), "\t Deviation -> " , np.std(europeanReturns) )
    print("US Return -> " , np.mean(usReturns), "\t Deviation -> " , np.std(usReturns))


expectedReturn()