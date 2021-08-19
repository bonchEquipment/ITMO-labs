import re

hamlet = open ("./Hamlet.txt", encoding='Latin-1')
text = hamlet.read()

regex = r"[\w'][\w\s'-\(\)]*,[\w\s,'-\(\)]*!"
newText = re.findall(regex, text)

for i in newText:    
    print(i)
