from json2xml import json2xml
from json2xml.utils import readfromstring
import time

file_path = r"C:\Users\ergre\Desktop\инф3\результирующий файл - json2xml.txt" #

file1 = open(r"C:\Users\ergre\Desktop\инф3\исходный файл - json2xml.txt", encoding="utf-8")
file2 = open(file_path,"w",encoding="utf-8")

s = file1.read()#файл целиком в s
data = readfromstring(s) 
file2.write(json2xml.Json2xml(data).to_xml())   #записывает в файл результат скобки
file2.close()

#############начало замера
t = time.time() # t - начальное время
for x in range(10000):
    file2 = open(file_path,"w",encoding="utf-8")
    file2.write(json2xml.Json2xml(data).to_xml())
    file2.close()
    ########конец замера
print("Время на библиотечный перевод   " + str(time.time() - t)) # конечное время - начальное


file1.close()
file2.close()
