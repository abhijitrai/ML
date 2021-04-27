import numpy as np


######################################################################
#   Utilities


def cv(value_list):
    '''
    Takes a list of numbers and returns a column vector:  n x 1
    '''
    return np.transpose(rv(value_list))


def rv(value_list):
    '''
    Takes a list of numbers and returns a row vector: 1 x n
    '''
    return np.array([value_list])


def positive(x, th, th0):
    '''
    x is dimension d by 1
    th is dimension d by 1
    th0 is dimension 1 by 1
    return 1 by 1 matrix of +1, 0, -1
    '''
    return np.sign(y(x, th, th0))


def score(data, labels, th, th0):
    '''
    data is dimension d by n
    labels is dimension 1 by n
    ths is dimension d by 1
    th0s is dimension 1 by 1
    return 1 by 1 matrix of integer indicating number of data points correct for
    each separator.
    '''
    return np.sum(positive(data, th, th0) == labels)


def y(x, th, th0):
    '''
    x is dimension d by 1
    th is dimension d by 1
    th0 is a scalar
    return a 1 by 1 matrix
    '''
    return np.dot(np.transpose(th), x) + th0