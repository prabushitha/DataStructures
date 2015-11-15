class Node:
   def __init__(self,key):
      self.key = key
      self.leftChild = None
      self.rightChild = None
      self.parent = None
   def __str__(self):
      return str(self.key)
   def __repr__(self):
      return self.__str__()
class BinarySearchTree:
   def __init__(self):
      self.root = None
      self.size = 0
   #add node to the tree
   def addNode(self,node):
      parent = self.search(node,True)
      self.size +=1
      if(parent!=None):
         if(parent.key == node.key):
            self.size -=1
            print("already in the tree")
         else:
            node.parent = parent
            if(node.key>parent.key):
               parent.rightChild = node
            else:
               parent.leftChild = node
      else:
         self.root = node
   #search for a node in the tree.
   #when shouldReturnParent=True, return the node if found
   #  else return the parent node of where it should be
   def search(self,node,shouldReturnParent=False):
      currentNode = self.root
      parent = None
      while(currentNode!=None):
         parent = currentNode
         if(node.key==currentNode.key):
            if(shouldReturnParent):
               return parent
            return True
         elif(node.key>currentNode.key):
            currentNode=currentNode.rightChild
         elif(node.key<currentNode.key):
            currentNode=currentNode.leftChild
      if(shouldReturnParent):
         return parent
      return False
   #Returns the size of the tree at root=node
   def sizeOfSubTree(self, node):
      if(node==None):
         return 0
      else:
         return 1+self.sizeOfSubTree(node.leftChild)+self.sizeOfSubTree(node.rightChild)
   #Returns the height of the tree at root=node
   def heightOfSubTree(self,node):
      if(node==None):
         return 0
      return 1 + max(self.heightOfSubTree(node.leftChild),self.heightOfSubTree(node.rightChild))
   #Traversals
   def preOrder(self,startNode):
      if(startNode==None):
         return []
      else:
         return [startNode]+self.preOrder(startNode.leftChild)+self.preOrder(startNode.rightChild)
   def inOrder(self,startNode):
      if(startNode==None):
         return []
      else:
         return self.inOrder(startNode.leftChild)+[startNode]+self.inOrder(startNode.rightChild)
   def postOrder(self,startNode):
      if(startNode==None):
         return []
      else:
         return self.postOrder(startNode.leftChild)+self.postOrder(startNode.rightChild)+[startNode]   

#Implementation
bst = BinarySearchTree()

'''
Input format

rootData
nodeData
nodeData
...
END

Sample input

10
5
8
16
13
1
12
END

Binary search tree generated is
              10
            /    \
           /      \
           5      16
         /  \    /  \
        1    8  13  12

#functions that can be used

bst.addNode(10) <== adds a new node with value 10
bst.preOrder(bst.root) <== print nodes in pre oder from root
bst.postOrder(bst.root) <== print nodes in post oder from root
bst.inOrder(bst.root) <== print nodes in in oder from root
'''
while(True):
   inp = input()
   if(inp=="END"):
      break
   key = int(inp)
   node = Node(key)
   bst.addNode(node)
   
preOrderList = bst.preOrder(bst.root)
print(preOrderList)
