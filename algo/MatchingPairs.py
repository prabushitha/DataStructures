from collections import OrderedDict
N = int(input())
words = []
# [abca,zbxz]
for i in range(N):
   words.append(str(input()).strip())
   
def removeDuplicates(word):
   return "".join(OrderedDict.fromkeys(word))
      
count = 0
for i in range(len(words)):
   for j in range(i+1,len(words)):
      word = removeDuplicates(words[i])
      if(len(word)==len(removeDuplicates(words[j]))):
         currentWord = words[j]
         newOtherWord = ""
         wIndex = 0
         replaced = []
         for k in range(len(words[j])):
            if(words[j][k] not in replaced):
               replaced.append(words[j][k])
               currentWord = currentWord.replace(words[j][k],word[wIndex])
               wIndex = wIndex+1
         #print(currentWord+":"+words[i])
         if(currentWord==words[i]):
            count=count+1
print(count)
      
