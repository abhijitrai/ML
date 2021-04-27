import numpy as np
from matplotlib import colors, pyplot as plt

from solution.solution_tools import cv


def plot_data(data, labels, ax=None, clear=False,
              xmin=None, xmax=None, ymin=None, ymax=None):
    '''
    Make scatter plot of data.
    data = (numpy array)
    ax = (matplotlib plot)
    clear = (bool) clear current plot first
    xmin, xmax, ymin, ymax = (float) plot extents
    returns matplotlib plot on ax
    '''
    if ax is None:
        if xmin == None: xmin = np.min(data[0, :]) - 0.5
        if xmax == None: xmax = np.max(data[0, :]) + 0.5
        if ymin == None: ymin = np.min(data[1, :]) - 0.5
        if ymax == None: ymax = np.max(data[1, :]) + 0.5
        ax = tidy_plot(xmin, xmax, ymin, ymax)

        x_range = xmax - xmin;
        y_range = ymax - ymin
        if .1 < x_range / y_range < 10:
            ax.set_aspect('equal')
        xlim, ylim = ax.get_xlim(), ax.get_ylim()
    elif clear:
        xlim, ylim = ax.get_xlim(), ax.get_ylim()
        ax.clear()
    else:
        xlim, ylim = ax.get_xlim(), ax.get_ylim()
    colors = np.choose(labels > 0, cv(['r', 'g']))[0]
    ax.scatter(data[0, :], data[1, :], c=colors,
               marker='o', s=50, edgecolors='none')
    # Seems to occasionally mess up the limits
    ax.set_xlim(xlim);
    ax.set_ylim(ylim)
    ax.grid(True, which='both')
    # ax.axhline(y=0, color='k')
    # ax.axvline(x=0, color='k')
    return ax


def plot_nonlin_sep(predictor, ax=None, xmin=None, xmax=None,
                    ymin=None, ymax=None, res=30):
    '''
    Must either specify limits or existing ax
    Shows matplotlib plot on ax
    '''
    if ax is None:
        ax = tidy_plot(xmin, xmax, ymin, ymax)
    else:
        if xmin == None:
            xmin, xmax = ax.get_xlim()
            ymin, ymax = ax.get_ylim()
        else:
            ax.set_xlim((xmin, xmax))
            ax.set_ylim((ymin, ymax))

    cmap = colors.ListedColormap(['black', 'white'])
    bounds = [-2, 0, 2]
    norm = colors.BoundaryNorm(bounds, cmap.N)

    ima = np.array([[predictor(x1i, x2i) \
                     for x1i in np.linspace(xmin, xmax, res)] \
                    for x2i in np.linspace(ymin, ymax, res)])
    im = ax.imshow(np.flipud(ima), interpolation='none',
                   extent=[xmin, xmax, ymin, ymax],
                   cmap=cmap, norm=norm)


def plot_separator(ax, th, th_0):
    '''
    Plot separator in 2D
    ax = (matplotlib plot) plot axis
    th = (numpy array) theta
    th_0 = (float) theta_0
    '''
    xmin, xmax = ax.get_xlim()
    ymin, ymax = ax.get_ylim()
    pts = []
    eps = 1.0e-6
    # xmin boundary crossing is when xmin th[0] + y th[1] + th_0 = 0
    # that is, y = (-th_0 - xmin th[0]) / th[1]
    if abs(th[1, 0]) > eps:
        pts += [np.array([x, (-th_0 - x * th[0, 0]) / th[1, 0]]) \
                for x in (xmin, xmax)]
    if abs(th[0, 0]) > eps:
        pts += [np.array([(-th_0 - y * th[1, 0]) / th[0, 0], y]) \
                for y in (ymin, ymax)]
    in_pts = []
    for p in pts:
        if (xmin - eps) <= p[0] <= (xmax + eps) and \
                (ymin - eps) <= p[1] <= (ymax + eps):
            duplicate = False
            for p1 in in_pts:
                if np.max(np.abs(p - p1)) < 1.0e-6:
                    duplicate = True
            if not duplicate:
                in_pts.append(p)
    if in_pts and len(in_pts) >= 2:
        # Plot separator
        vpts = np.vstack(in_pts)
        ax.plot(vpts[:, 0], vpts[:, 1], 'k-', lw=2)
        # Plot normal
        vmid = 0.5 * (in_pts[0] + in_pts[1])
        scale = np.sum(th * th) ** 0.5
        diff = in_pts[0] - in_pts[1]
        dist = max(xmax - xmin, ymax - ymin)
        vnrm = vmid + (dist / 10) * (th.T[0] / scale)
        vpts = np.vstack([vmid, vnrm])
        ax.plot(vpts[:, 0], vpts[:, 1], 'k-', lw=2)
        # Try to keep limits from moving around
        ax.set_xlim((xmin, xmax))
        ax.set_ylim((ymin, ymax))
    else:
        print('Separator not in plot range')


def tidy_plot(xmin, xmax, ymin, ymax, center = False, title = None,
                 xlabel = None, ylabel = None):
    '''
    Set up axes for plotting
    xmin, xmax, ymin, ymax = (float) plot extents
    Return matplotlib axes
    '''
    plt.ion()
    plt.figure(facecolor="white")
    ax = plt.subplot()
    if center:
        ax.spines['left'].set_position('zero')
        ax.spines['right'].set_color('none')
        ax.spines['bottom'].set_position('zero')
        ax.spines['top'].set_color('none')
        ax.spines['left'].set_smart_bounds(True)
        ax.spines['bottom'].set_smart_bounds(True)
        ax.xaxis.set_ticks_position('bottom')
        ax.yaxis.set_ticks_position('left')
    else:
        ax.spines["top"].set_visible(False)
        ax.spines["right"].set_visible(False)
        ax.get_xaxis().tick_bottom()
        ax.get_yaxis().tick_left()
    eps = .05
    plt.xlim(xmin-eps, xmax+eps)
    plt.ylim(ymin-eps, ymax+eps)
    if title: ax.set_title(title)
    if xlabel: ax.set_xlabel(xlabel)
    if ylabel: ax.set_ylabel(ylabel)
    return ax