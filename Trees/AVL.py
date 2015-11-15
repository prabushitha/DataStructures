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

class AVLTree(BinarySearchTree):
   def __init__(self):
      self.root = None
      self.size = 0
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
            self.checkImbalance(node)
      else:
         self.root = node
   def Rotate(self,node,pattern):#patterns ls,rs,dr,dl
      parent = node.parent #14
      parentsParent = parent.parent #8
      rightChild = node.rightChild #13
      leftChild = node.leftChild #9
      if(pattern=="ll"): #left straight
         parent.leftChild = rightChild
         if(rightChild!=None):
            rightChild.parent = parent
         if(parentsParent==None):
            self.root = node
            self.root.parent = None
         else:
            if(parentsParent.rightChild!=None and parentsParent.rightChild.key==parent.key):
               parentsParent.rightChild = node
            else:
               parentsParent.leftChild = node
            node.parent = parentsParent
         node.rightChild = parent
         parent.parent = node
      elif(pattern=="rr"):
         parent.rightChild = leftChild
         if(leftChild!=None):
            leftChild.parent = parent
         if(parentsParent==None):
            self.root = node
            self.root.parent = None
         else:
            if(parentsParent.leftChild!=None and parentsParent.leftChild.key==parent.key):
               parentsParent.leftChild = node
            else:
               parentsParent.rightChild = node
            node.parent = parentsParent
         node.leftChild = parent
         parent.parent = node
      elif(pattern=="lr"):
         #work this out
         self.Rotate(parent,"ll")
      elif(pattern=="rl"):
         #work this out
         self.Rotate(parent,"rr")
   def balance(self,node):
      return self.heightOfSubTree(node.leftChild)-self.heightOfSubTree(node.rightChild)
   def checkImbalance(self,addedNode):
      node = addedNode
      imbalanceNode = None
      rotateNode = None
      pattern = ""
      while(node!=None):
         if(abs(self.balance(node))>1):
            imbalanceNode = node
            node=None
         else:
            if(node.parent!=None):
               if(node.parent.leftChild!=None and (node.parent.leftChild.key==node.key)):
                  pattern+="l"
               elif(node.parent.rightChild!=None and (node.parent.rightChild.key==node.key)):
                  pattern+="r"
            rotateNode = node
            node = node.parent
            
      if(imbalanceNode!=None):
         pattern = pattern[len(pattern)-2:]
         self.Rotate(rotateNode,pattern)
         
         
avl = AVLTree()
avl.addNode(Node(8))
avl.addNode(Node(3))
avl.addNode(Node(9))
avl.addNode(Node(5))
avl.addNode(Node(4))
print(avl.preOrder(avl.root))
