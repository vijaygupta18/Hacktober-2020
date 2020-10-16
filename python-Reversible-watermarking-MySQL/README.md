# Reversible-watermarking-on-Mysql-database
DIFFERENCE EXPANSION BASED REVERSIBLE

1)Implemented reversible database watermarking on MySQL database specified in https://pdfs.semanticscholar.org/df4d/209172bc06c7d58a2368ee30221d89f3b6c6.pdf research paper


2) This project have 2 tables


Orginial table can be viewed at http://127.0.0.1:5000/list


Watermrked table can be viewed at http://127.0.0.1:5000/update


![original_table](https://cloud.githubusercontent.com/assets/15183662/24664043/3a8a8b2c-1977-11e7-8563-7409e162d3c5.png)


3)To start the project enter,first import .sql files present in Dump20170404/ in your mysql databse on localhost and enter these commands in terminal after going in the project directory


source venv/bin/activate


python route.py


4)To start watermarking script 


python controller.py


![controller_py](https://cloud.githubusercontent.com/assets/15183662/24664030/34835254-1977-11e7-8412-294de82d703a.png)



--------------------------------------------------------------------------
Api defination for some controller.py functions
1)copy_data_from_src_to_dest('source_table_name','destination_table_name')
->It will copy all the tuples of source table to destination table

2)watermark('table_name')
->It will watermark the table with table_name

3)compare_tables('table_1','table_2')
->It will compare both tables and print the percentage of tuples matched.

4)reverse_watermark('table_name')
->It will extract the watermark from the table with table_name.

5)list()
->It is used to provide view of orginal table.

6)update()
->It is used to provide view of watermarked table.

--------------------------------------------------------------------------
