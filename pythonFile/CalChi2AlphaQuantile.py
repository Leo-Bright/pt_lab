from scipy.stats import chi2
import sys

print round(chi2.isf(sys.args[0],sys.args[1]),4)
