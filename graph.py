import matplotlib.pyplot as plt
import numpy as np
import csv
import time
#test

data = np.loadtxt('output.csv', delimiter=',' , dtype=float)

x_coord = data[:,0]
y_coord = data[:,1]

max_x = max(x_coord)
max_y = max(y_coord)



plt.figure(1)
plt.plot(x_coord,y_coord)
plt.ylim(0,max_y)
plt.xlim(0,max_x)
plt.show()

