import re
text1 = """int[][] arr = new int[10][10];"""

result = re.search(r"\[.*?\]",text1)

print(result)


