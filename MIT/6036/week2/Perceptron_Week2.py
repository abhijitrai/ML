import numpy as np
from solution_data import labels1, data1, data2, labels2


def update_th(th, X, Y):
    tmpVector = Y * X  # Predicted val is a scalar , test point is a vector
    result = th + tmpVector
    return result


def update_th0(Y, th0):
    return th0 + Y


def check_th_for_perceptron_through_origin(th, X, Y):
    result = Y * np.dot(np.transpose(th), X).item()
    return result <= 0


def check_th_for_perceptron(th, th0, X, Y):
    result = Y * (np.dot(np.transpose(th), X).item() + th0)
    return result <= 0


def check_Performance(data, labels, th, th0):
    labels = labels[0]
    for i in range(data.shape[1]):
        X = np.array([data[:, i]]).T
        Y = labels[i]
        pred = np.sign(np.dot(np.transpose(th), X) + th0)
        print('Result :{0} , Predicted ;{1} Actual :{2}'.format(pred == Y, pred, Y))


def train_perceptron(data, labels, params={}, hook=None):
    """
    :param data: data is a numpy array of dimension d by n
    :param labels: labels is numpy array of dimension 1 by n
    :param params: params is a dictionary specifying extra parameters to this algorithm; your algorithm should run a number of iterations equal to T
    :param hook: takes the tuple (th, th0) as an argument and displays the separator graphically
    :returns
     return a tuple of theta (a d by 1 array) and theta_0   (a 1 by 1 array)
    """
    # if T not in params, default to 100
    through_origin = params.get('through_origin', False)
    T = params.get('T', 100)
    th = params.get('th', np.zeros((data.shape[0], 1)))
    th0 = params.get('th0', 0)
    mistake = 0
    labels = labels[0]
    for i in range(T):
        count = 0;
        for j in range(data.shape[1]):
            X = np.array([data[:, j]]).T
            Y = labels[j]
            if (through_origin):
                if check_th_for_perceptron_through_origin(th, X, Y):
                    mistake += 1
                    count = 1
                    th_old = th
                    th = update_th(th, X, Y)
                    print('Mistake :{0} , th old :{1} th new :{2}'.format(mistake, th_old.flatten(), th.flatten()))
            else:
                if check_th_for_perceptron(th, th0, X, Y):
                    count = 1
                    mistake += 1
                    th_old = th
                    th0_old = th0
                    th0 = update_th0(Y, th0)
                    th = update_th(th, X, Y)
                    print('Mistake :{0} , th old :{1} th new :{2} th0 old :{3}  th0 new :{4}'.format(mistake,
                                                                                                     th_old.flatten(),
                                                                                                     th.flatten(),
                                                                                                     th0_old, th0))
        if (count == 0):
            break;
    return {'Th': th, 'Th0': th0, 'Mistakes': mistake}


def test_perceptron(test=1, through_origin=False):
    if test == 1:
        data = np.array([[200, 800, 200, 800],
                         [0.2, 0.2, 0.8, 0.8]])
        labels = np.array([[-1, -1, 1, 1]])

    if (test == 2):
        data = np.array([
            [1, 0, -1.5],
            [-1, 1, -1]
        ])
        labels = np.array([[1, -1, 1]])

    if (test == 3):
        data = data1
        labels = labels1
        through_origin=True

    if (test == 4):
        data = np.array([
            [0, -1.5, 1],
            [1, -1, -1]
        ])
        labels = np.array([[-1, 1, 1]])

    test_data = data
    test_labels = labels
    result = train_perceptron(data, labels, params={'T': 100, 'through_origin': through_origin}, hook=None)
    th = result.get('Th')
    th0 = result.get('Th0')
    check_Performance(np.array(test_data), np.array(test_labels), th, th0)


test_perceptron(test=3)
