import random as random
import matplotlib.pyplot as pyplot
import math as math
import numpy as np


def getHighs():
    inFile = open('temperatures.csv')
    population = []
    map = {}
    for l in inFile:
        try:
            tempC = float(l.split(',')[1])
            date = l.split(',')[2]
            population.append(tempC)
            year, month, day = [date[0:4], date[4:6], date[6:8]]
            # month = date[4:6]
            # day = date[6:8]
            key = str(month) + "-" + str(day)
            if map.get(key) == None:
                values = []
                values.append(tempC)
                map[key] = values;
            else:
                values = map.get(key)
                values.append(tempC)
                map[key] = values;
        except:
            continue

    return [population, map]


def setPlotDefaults1():
    pyplot.grid(True)
    ax = pyplot.gca()
    ax.spines['top'].set_color('none')
    ax.spines['left'].set_position('zero')
    ax.spines['right'].set_color('none')
    ax.spines['bottom'].set_position('zero')


def setPlotDefaults2():
    pyplot.grid(True)
    rcParams = pyplot.rcParams;
    # set line width
    rcParams['lines.linewidth'] = 4
    # set font size for titles
    rcParams['axes.titlesize'] = 20
    # set font size for labels on axes
    rcParams['axes.labelsize'] = 20
    # set size of numbers on x-axis
    rcParams['xtick.labelsize'] = 16
    # set size of numbers on y-axis
    rcParams['ytick.labelsize'] = 16
    # set size of ticks on x-axis
    rcParams['xtick.major.size'] = 7
    # set size of ticks on y-axis
    rcParams['ytick.major.size'] = 7
    # set size of markers
    rcParams['lines.markersize'] = 10
    # set number of examples shown in legends
    rcParams['legend.numpoints'] = 1


def setStyle(style=1):
    if (style == 1):
        setPlotDefaults1()
    else:
        setPlotDefaults2()


def plotTemperatureOfEntirePopulation():
    setStyle(2)
    result = getHighs()[0]
    pyplot.hist(result, bins=20)
    pyplot.title('Temperature For Entire Distribution Mean -> ' + str(round(sum(result) / len(result), 3)))
    pyplot.ylabel('Frequency ')
    pyplot.xlabel('Temperature ')
    pyplot.show()
    pyplot.close()


def plotTemperatureOfOneDayOfYear(targetDayOfYear=10):
    setStyle(2)
    result = getHighs()
    map = result[1]
    values = map.get(list(map.keys())[targetDayOfYear]);
    xVals = range(len(values))
    yVals = values
    pyplot.grid(True)
    pyplot.plot(xVals, yVals, 'b.');
    pyplot.title('Temperature For Day ' + str(targetDayOfYear))
    pyplot.ylabel('Temperature')
    pyplot.xlabel('Day Sample')
    pyplot.show()
    pyplot.close()


def plotMeanTemperatureForNumberOfRandomDays(numberOfDays=1000):
    setStyle(2)
    samples = random.sample(getHighs()[0], numberOfDays)
    sampleMean = sum(samples) / len(samples)
    popMean = sum(getHighs()[0]) / len(getHighs()[0])
    print("Mean Sample-> ", sampleMean)
    print("STD -> ", np.std(samples))
    print("Mean Population-> ", popMean)
    print("STD Population-> ", np.std(getHighs()[0]))
    pyplot.hist(samples, bins=50)
    pyplot.xlabel("Buckets")
    pyplot.ylabel("Frequency")
    pyplot.axvline(x=sampleMean, color='r')
    pyplot.axvline(x=popMean, color='k')
    pyplot.title("Distribution")
    pyplot.show()
    pyplot.close()

    # The CLT says three things
    # 1. The means of means is a normal distributions - use Hist
    # 2. The mean of means is close to population mean
    # 3. The variance of mean of means is close to variance of population mean


def plotMeanOfMeansForMultipleTrialsOfRandomPicksOfGivenSize(perTrialSampleSize=200, trials=100, verbose=True):
    population = getHighs()[0]
    sampleMeanList = []
    print('Estimating Mean of Population by')
    print('1. Calculating Mean of Means for various samples ')
    print('2. Each Sample  of size  ', perTrialSampleSize)
    print('3. Number of Trials  ', trials)
    for i in range(trials):
        sample = random.sample(population, perTrialSampleSize)
        meanOfSample = np.mean(sample)  # This is  similar to iteration and then calculating MEan
        sampleMeanList.append(meanOfSample)
    meanOfMeans = round(np.mean(sampleMeanList), 2)
    varianceOfMeans = round(np.var(sampleMeanList), 2)
    populationMean = round(np.mean(population), 2)
    populationVariance = round(np.var(population), 2)
    print("Mean Sample-> ", meanOfMeans)
    print("Variance Deviation of Means->", varianceOfMeans)
    print("95% Confidence Interval -> ", meanOfMeans - 1.96 * varianceOfMeans, " - ",
          meanOfMeans + 1.96 * varianceOfMeans)
    print("Mean Population-> ", populationMean)
    print("Population Size-> ", len(population))
    if verbose:
        pyplot.hist(sampleMeanList, color='m')
        pyplot.title('Mean of Means Plot')
        pyplot.xlabel('Mean ( Buckets) ')
        pyplot.ylabel('Frequency')
        pyplot.axvline(meanOfMeans, color='m')
        pyplot.axvline(populationMean, color='c')
        pyplot.figtext(.75, .70, 'Variance Population : ' + str(populationVariance), color='b')
        pyplot.figtext(.75, .75, 'Variance of Means(' + str(perTrialSampleSize) + '): ' + str(varianceOfMeans),
                       color='r')
        pyplot.show()


def plotConfidenceIntervals(sampleSize=[50, 100, 200, 300,400,500], trials=200):
    population = getHighs()[0]
    means = []
    stds = []
    for i in sampleSize:
        sampleMeans = []
        sampleStds = []
        for j in range(trials):
            sample = random.sample(population, i)
            sampleMeans.append(np.mean(sample))
            sampleStds.append(np.std(sample))

        means.append(np.mean(sampleMeans))
        stds.append(np.std(sampleStds))

    pyplot.errorbar(sampleSize, means, yerr=1.96 * np.array(stds))
    pyplot.axhline(np.mean(means),color='r')
    pyplot.title('Error Bars')
    pyplot.xlabel('Sample Size')
    pyplot.ylabel('Means')
    pyplot.show();
    pyplot.close()


def getStdErrorOfMean(populationStd=0, sampleSize=0):
    return populationStd / sampleSize ** 0.5


def plotStandardErrorOfMeans(numberOfTrials=50):
    setPlotDefaults2()
    population = getHighs()[0]
    stdDeviationOfPopulation = np.std(population)
    sampleSizes = (100, 200, 500, 1000, 2000, 4000, 6000, 8000, 10000)
    sems = []
    sampleStds = []
    semWithSdOfSample = []

    t1 = np.arange(0.0, 3.0, 0.01)

    for currentSize in sampleSizes:
        stdErrOfMean = getStdErrorOfMean(stdDeviationOfPopulation, currentSize)
        sems.append(stdErrOfMean)
        sampleMeans = []
        for i in range(numberOfTrials):
            currentSamples = random.sample(population, currentSize)
            sampleMeans.append(np.mean(currentSamples))
        sampleStd = np.std(sampleMeans) * currentSize
        sampleStds.append(sampleStd)
        semWithSdOfSample.append(getStdErrorOfMean(sampleStd,currentSize) )

    ax1 = pyplot.subplot(212)
    ax1.margins(0.05)           # Default margin is 0.05, value 0 means fit
    ax1.plot(sampleSizes, sems, label='SEM')
    ax1.plot(sampleSizes, semWithSdOfSample, label='SEM with Sample Std')
    ax1.plot(sampleSizes, sampleStds, label='Sample STDs')
    ax1.set_title('SEM Tracking SD')
    ax1.legend()


    diffInStds = np.array(sampleStds) - stdDeviationOfPopulation
    ax2 = pyplot.subplot(221)
    ax2.margins(2, 2)           # Values >0.0 zoom out
    ax2.set_title('Sample SD')
    ax2.plot(sampleSizes,diffInStds)
    ax2.legend()
    pyplot.show()
    pyplot.close()


# plotTemperatureOfEntirePopulation()
# plotTemperatureOfOneDayOfYear(targetDayOfYear=150, style= 2)
# plotMeanTemperatureForNumberOfRandomDays(numberOfDays=100)
# plotMeanOfMeansForMultipleTrialsOfRandomPicksOfGivenSize(perTrialSampleSize=200, trials=100, verbose=True)
# plotConfidenceIntervals()
plotStandardErrorOfMeans()