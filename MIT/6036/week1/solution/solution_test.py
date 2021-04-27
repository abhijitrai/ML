import numpy as np
from matplotlib import pyplot as plt

from solution.solution_plot import plot_data, plot_separator
from solution_data import gen_big_data, super_simple_separable_through_origin, xor, data1, labels1, data2, labels2, \
    big_data, big_data_labels
from solution_tools import score

######################################################################
#   tests

def test_linear_classifier(dataFun, learner, learner_params = {},
                             draw = False, refresh = True, pause = False):
    '''
    Prints score of your classifier on given dataset
    dataFun method that returns a dataset
    learner your classifier method
    learner_params parameters for the learner
    '''
    data, labels = dataFun()
    d, n = data.shape
    if draw:
        ax = plot_data(data, labels)
        def hook(params):
            (th, th0) = params
            if refresh: plot_data(data, labels, ax, clear = True)
            plot_separator(ax, th, th0)
            #print('th', th.T, 'th0', th0)
            plt.pause(0.05)
            if pause: input('go?')
    else:
        hook = None
    th, th0 = learner(data, labels, hook = hook, params = learner_params)
    print("Final score", float(score(data, labels, th, th0)) / n)
    print("Params", np.transpose(th), th0)

expected_perceptron = [(np.array([[-9.0], [18.0]]), np.array([[2.0]])),(np.array([[0.0], [-3.0]]), np.array([[0.0]]))]
expected_averaged = [(np.array([[-9.0525], [17.5825]]), np.array([[1.9425]])),(np.array([[1.47], [-1.7275]]), np.array([[0.985]]))]
datasets = [super_simple_separable_through_origin,xor]


def incorrect(expected,result):
    print("Test Failed.")
    print("Your code output ",result)
    print("Expected ",expected)
    print("\n")


def correct():
    print("Passed! \n")


def test_perceptron(perceptron):
    '''
    Checks perceptron theta and theta0 values for 100 iterations
    '''
    for index in range(len(datasets)):
        data, labels = datasets[index]()
        th,th0 = perceptron(data, labels, {"T": 100})
        expected_th,expected_th0 = expected_perceptron[index]
        print("-----------Test Perceptron "+str(index)+"-----------")
        if((th==expected_th).all() and (th0==expected_th0).all()):
            correct()
        else:
            incorrect("th: " + str(expected_th.tolist()) + ", th0: " + str(expected_th0.tolist()), "th: " + str(th.tolist()) + ", th0: " + str(th0.tolist()))


def test_averaged_perceptron(averaged_perceptron):
    '''
    Checks average perceptron theta and theta0 values for 100 iterations
    '''
    for index in range(2):
        data, labels = datasets[index]()
        th,th0 = averaged_perceptron(data, labels, {"T": 100})
        expected_th,expected_th0 = expected_averaged[index]
        print("-----------Test Averaged Perceptron "+str(index)+"-----------")
        if((th==expected_th).all() and (th0==expected_th0).all()):
            correct()
        else:
            incorrect("th: " + str(expected_th.tolist()) + ", th0: " + str(expected_th0.tolist()), "th: " + str(th.tolist()) + ", th0: " + str(th0.tolist()))


def test_eval_classifier(eval_classifier,perceptron):
    '''
    Checks your classifier's performance on data1
    '''
    expected = [0.5333333333333333,0.6333333333333333]
    dataset_train = [(data1,labels1),(data2,labels2)]
    for index in range(len(dataset_train)):
        data_train,labels_train = dataset_train[index]
        #print(data_train,labels_train)
        result = eval_classifier(perceptron, data_train, labels_train,data2,labels2)
        print("-----------Test Eval Classifier "+str(index)+"-----------")
        if(result==expected[index]):
            correct()
        else:
            incorrect(expected[index], result)


def test_eval_learning_alg(eval_learning_alg,perceptron):
    '''
    Checks your learning algorithm's performance on big_data
    eval_learning_alg method for evaluating learning algorithm
    perceptron your perceptron learning algorithm method
    '''
    expected = 0.5599999999999999
    result = eval_learning_alg(perceptron, gen_big_data(), 10, 10, 5)
    print("-----------Test Eval Learning Algo-----------")
    if result == expected:
        correct()
    else:
        incorrect(expected, result)


def test_xval_learning_alg(xval_learning_alg,perceptron):
    '''
    Checks your learning algorithm's performance on big_data using cross validation
    xval_learning_alg method for evaluating learning algorithm using cross validation
    perceptron your perceptron learning algorithm method
    '''
    expected = 0.61
    result=xval_learning_alg(perceptron, big_data, big_data_labels, 5)
    print("-----------Test Cross-eval Learning Algo-----------")
    if result == expected:
        correct()
    else:
        incorrect(expected, result)


# Test Cases:
# test_averaged_perceptron(averaged_perceptron)

def eval_classifier(learner, data_train, labels_train, data_test, labels_test):
    pass


# Test cases:
# test_eval_classifier(eval_classifier,perceptron)


def eval_learning_alg(learner, data_gen, n_train, n_test, it):
    pass


# Test cases:
# test_eval_learning_alg(eval_learning_alg,perceptron)


def xval_learning_alg(learner, data, labels, k):
    pass

# Test cases:
# test_xval_learning_alg(xval_learning_alg,perceptron)


# For problem 10, here is an example of how to use gen_flipped_lin_separable, in this case with a flip probability of 50%
# print(eval_learning_alg(perceptron, gen_flipped_lin_separable(pflip=.5), 20, 20, 5))


# Visualization of perceptron, comment in the next three lines to see your perceptron code in action:
'''
for datafn in (super_simple_separable_through_origin,super_simple_separable):
   data, labels = datafn()
   test_linear_classifier(datafn,perceptron,draw=True)
'''


# Test Cases:
# test_perceptron(perceptron)


# Visualization of Averaged Perceptron:
'''
for datafn in (super_simple_separable, xor, xor_more, big_higher_dim_separable):
   data, labels = datafn()
   test_linear_classifier(datafn,averaged_perceptron,draw=True)
'''

