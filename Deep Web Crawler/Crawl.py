import requests
from bs4 import BeautifulSoup

def Main( url_1 ):
    i = 1

    def Crawl(url_l, i):

        url = url_l
        source_code = requests.get(url)
        soup = BeautifulSoup(source_code.text , "lxml")

        with open("Data" + str(i) + ".txt" , 'w' , encoding='utf-8') as file:

                list_of_tags = set()
                for tag in soup.find_all():
                    list_of_tags.add(tag.name)

                for tag in list_of_tags:
                    file.write("-- " + tag.upper() + ' --\n')
                    file.write("------------------\n")
                    for link in soup.findAll(tag):
                        if link.string is not None:
                            file.write(link.string + '\n')
                    file.write("------------------\n\n")


    def WriteLinks(url_l ):

        url = url_l
        source_code = requests.get(url)
        soup = BeautifulSoup(source_code.text, "lxml")

        with open("Hyperlinks.txt", 'a') as file2:
            #file2.write("\n")
            for tag in soup.findAll('a'):
                if tag.get("href") is not None:
                    if tag.get("href").startswith("http"):
                        string = tag.get("href")
                    else:
                        string = lis[0].split('\n')[0].rstrip('/') + tag.get("href")

                    if string + "\n" not in set_of_urls:
                        set_of_urls.add(string + "\n")
                        file2.write(string + "\n")


    with open("Hyperlinks.txt", 'r' ) as file2:

            lis = file2.readlines()
            try:
                if lis[0].split('\n')[0] == url_1:
                    pass
                else:
                    lis.clear()
                    lis.append(url_1 + '\n')
                    with open("Hyperlinks.txt", 'w') as file_new:
                        file_new.write(url_1 + '\n')
            except IndexError:
                lis.append(url_1 + '\n')
                with open("Hyperlinks.txt", 'w') as file_new:
                    file_new.write(url_1 + '\n')

            set_of_urls = set(lis)

            f = open("prof.txt" , 'r' )
            list_of_words = f.readlines()
            f.close()

            #print(set_of_urls)
            for line in lis:
                print(line.split('\n')[0] + "\n")
                try:
                    #Crawl(line.split('\n')[0], i)
                    i = i + 1
                    WriteLinks(line.split('\n')[0] )
                    for line1 in list_of_words:
                        if line.split('\n')[0].find(line1.split('\n')[0]) != -1:
                            with open("Refined_links.txt", "a", encoding='utf-8') as writefile:
                                writefile.write(line.split('\n')[0] + "\t" + line1.split('\n')[0] + '\n' )
                            break
                except :
                    pass
