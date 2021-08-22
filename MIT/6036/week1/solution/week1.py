import numpy as np
import random as rnd

def p(*args, sep=' ', end='\n', file=None):
    print(args)

def distance_form_hyperplane(th_vector, th0, X):
    val =  th_vector.T @ X + th0
    euclidean_norm = np.sqrt(th_vector.T @ th_vector )
    return val/euclidean_norm


def sign_of_distance(th_vector, th0, X):
    return np.sign(distance_form_hyperplane(th_vector,th0,X))

def random_classifier():
    rnd.random()

def test_distance_form_hyperplane():
    # d x 1
    th_vector = np.array([3, 4]).T
    th0 = 5
    #  d x 1
    X = np.array([0, 0]).T
    distance_form_hyperplane(th_vector, th0, X)

def test_sign_of_distance():
    # d x 1
    th_vector = np.array([3, 4]).T
    th0 = 5
    #  d x 1
    X = np.array([0, 0]).T
    return np.sign(sign_of_distance(th_vector,th0,X))

p(rnd.random())
