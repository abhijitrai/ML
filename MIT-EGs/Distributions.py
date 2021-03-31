
import random as rand
import matplotlib.pyplot as plot
x =[]
y = []
z = []
for i in range (10000 ):
    x.append(rand.random())
    y.append(rand.gauss(0,1))
    z.append(rand.expovariate(0.5))

plot.hist(x,color='m');
plot.figure()
plot.hist(y,color='r')
plot.figure()
plot.hist(z,color='g')
plot.show()
plot.close()
