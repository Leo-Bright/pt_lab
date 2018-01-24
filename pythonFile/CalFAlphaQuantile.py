from scipy.stats import f
import sys

#求F分布alpha分位点,保留4位小数点
print round(f.isf(sys.args[0],sys.args[1],sys.args[2]),4)
