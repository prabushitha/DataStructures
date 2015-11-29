class Node:
   def __init__(self,key,left=None,right=None):
      self.key = key
      self.leftChild = left
      self.rightChild = right
      self.parent = None
   def setChildren(self,array):
      if(self.leftChild!=None and type(self.leftChild)==int):
         self.leftChild = array[self.leftChild]
      if(self.rightChild!=None and type(self.rightChild)==int):
         self.rightChild = array[self.rightChild]
   def __str__(self):
      return str(self.key)
   def __repr__(self):
      return self.__str__()
def inOrder(startNode):
      if(startNode==None):
         return []
      else:
         return inOrder(startNode.leftChild)+[startNode]+inOrder(startNode.rightChild)
N = int(str(input()).strip())
nodes = []
for i in range(N):
   userInput = str(input()).strip().split()
   if(len(userInput)==1):
      node = Node(int(userInput[0].strip()))
      nodes.append(node)
   elif(len(userInput)==3):
      key = str(userInput[0].strip())
      left = int(userInput[1].strip())
      right = int(userInput[2].strip())
      node = Node(key,left,right)
      nodes.append(node)
for i in range(len(nodes)):
   nodes[i].setChildren(nodes)
inor = inOrder(nodes[0])

i=0
while(i<len(inor)):
   if(inor[i].key=="*" or inor[i].key=="/"):
      if(inor[i].key=="*"):
         res = int(int(inor[i-1].key)*int(inor[i+1].key))
      else:
         res = int(int(inor[i-1].key)/int(inor[i+1].key))
      inor[i-1].key = res
      inor.pop(i+1)
      inor.pop(i)
   else:
      i=i+1
i = 0
while(i<len(inor)):
   if(inor[i].key=="+" or inor[i].key=="-"):
      if(inor[i].key=="+"):
         res = int(int(inor[i-1].key)+int(inor[i+1].key))
      else:
         res = int(int(inor[i-1].key)-int(inor[i+1].key))
      inor[i-1].key = res
      inor.pop(i+1)
      inor.pop(i)
   else:
      i=i+1
print(inor[0])

