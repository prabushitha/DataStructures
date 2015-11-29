import functools
N = int(input())
countZero = 0
countOne = 0
lst = []
def memoize(obj):
    """Memoization decorator from PythonDecoratorLibrary. Ignores
    **kwargs"""

    cache = obj.cache = {}

    @functools.wraps(obj)
    def memoizer(*args, **kwargs):
        if(args not in cache):
            cache[args] = obj(*args, **kwargs)
        return cache[args]
    return memoizer

@memoize
def fibo(n):
   if(n==0):
      #countZero = countZero+1
      return 0
   if(n==1):
      #countOne = countOne+1
      return 1
   return fibo(n-1)+fibo(n-2)
      
fibo(N)
#print(str(countZero)+" "+str(countOne))
