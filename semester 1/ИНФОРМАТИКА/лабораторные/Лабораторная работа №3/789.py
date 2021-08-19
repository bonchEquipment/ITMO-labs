file1 = open(r"C:\Users\ergre\Desktop\инф3\исходный файл.txt", encoding="utf-8")
file2 = open(r"C:\Users\ergre\Desktop\инф3\результирующий файл.txt","w",encoding="utf-8")
s=(file1.readlines()) #делает из файла список

del s[0]
del s[-1]
for i in s:
    if (i.find('":"') != -1):
        ind = s.index(i)
        s[ind] = s[ind].replace('"',"<",1).replace('"',">",1).replace('"',"") #заменяем "" на <> (только для ключа)
        s[ind] = s[ind].replace(":","",1).replace(",","").replace("\n","") #убираем : , \n
        s[ind] = s[ind] + "</" + s[ind][s[ind].find("<")+1:s[ind].find(">")+1] #добавляем <x> в коне
    elif (i.find('\":\n') != -1):
        ind = s.index(i)
        s[ind] = s[ind].replace('"',"<",1).replace('"',">")
        s[ind] = s[ind].replace(":","").replace("\n","")
    
for i in range(2):    
    for i,j in enumerate(s):
         if (j.find("}") != -1):
            num = i
            while (s[num].count("{") != 1):
                 num -= 1       
            s[i] = "</" + s[num-1][s[num-1].find("<")+1:s[num-1].find(">")+1]
            del s[num]

new = "\n".join(s)
print(new)
file2.write(new)
file1.close()
file2.close()
