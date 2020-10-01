import convertapi
import os
from os import listdir
from os.path import isfile, join

'''
-------------HOW TO RUN THIS SCRIPT-------------
1. Have all the epub files in the same directory as this SCRIPT
2. Have a folder named 'converted' in the same directory
3. run this SCRIPT
4. the pdfs will be in the 'converted' folder
'''
convertapi.api_secret = 'LZaxxqwAEgY9gsj2'

folder = os.getcwd()
target_folder = folder+'\\converted'

file_list = [f for f in listdir(folder) if isfile(join(folder, f))]

file_list = [f for f in file_list if f.split('.')[-1]=='epub']
#print(file_list)

for file in file_list:
    result = convertapi.convert(
    'pdf',
    { 'File': folder+'\\'+file },
    from_format = 'epub'
    )
    result.file.save(target_folder+'\\'+file+'.pdf')
