import pandas as pd
'''
This function helps one with One ShotExploratory Data Analysis Using a Single Line of Code! Works only for Tabular Datasets !!
This OneShotEDA function helps a Data Scientist/ ML-AI Engineer / Data Analyst / Developer to understand What's in the Data. 
'''
# Data collecting form link
def OneShotEDA(data_link):
    file_type = data_link[-3:]
#File Format identification
    dtype_dict = {'csv':'Comma Separated File','lsx':'Excel File','tsv':'Tab Separated File','psv':'Pipe Separated File'}
    for i in dtype_dict.keys():
        if i == file_type:
            print('The dataset type is : '+ dtype_dict[i])
# Data Importing
    if file_type =="csv":
        data = pd.read_csv(data_link)
    elif file_type == "psv":
        data = pd.read_csv(data_link,sep='|')
    elif file_type == "tsv":
        data = pd.read_csv(data_link,sep='/')
    elif file_type == "lsx":
        data = pd.read_excel(data_link)
    else :
        print('Sorry User!! This Function Supports only CSV, PSV, TSV and EXCEL files !')

# Glimpses of Dataset
# Data Analytics
    class data_analytics:
        def __init__(self,data):
            self.data = data
            print('1) Dimension of the dataset is      :',data.shape)
            print('2) Number of Columns in the dataset :',data.shape[1])
            print('3) Number of Rows in the dataset    :',data.shape[0])
            numerical_features = [f for f in data.columns if data[f].dtypes!='O']
            print('4) Count of Numerical Features      :',len(numerical_features))
            cat_features = [c for c in data.columns if data[c].dtypes=='O']
            print('5) Count of Categorical Features    :',len(cat_features))
        def missing_values(self,data):
            print('6.1) Total Missing Values in the dataset:',(data.isnull().sum().sum()))
            print('6.2) Percentage of Total Missing Values :',(data.isnull().sum().sum()/(data.shape[0]*data.shape[1]))*100)
            print('6.3) Missing Values Estimation          :')
            for i in data.columns:
                if data[i].isna().sum()>0:
                    print(' >> The Column ',i,' has '+ str(data[i].isna().sum()) + ' missing values')
   
    analytics = data_analytics(data)
#print(analytics)
    print(analytics.missing_values(data))
# Data Insights
    lst = []
    for feature in data.columns:
        lst.append(feature)
    print('7) These are the following features or Columns in the Dataset :')
    print(lst)
    def text_analysis(feature):
        if len(data[feature].value_counts()) < data.shape[0]:
            n = int(input('Enter the range to print the most repeated Data :'))
            a= data[feature].value_counts().head(n)
            b= data[feature].value_counts().tail(n)
            print(a)
            print(b)
        
    def feature_analysis():
      feature = input('Enter the feature/column name :')
      if feature in data.columns:
          if data[feature].dtypes == 'O':
              text_analysis(feature)
          else:
              print(f'{feature} has maximum value of {data[feature].max()}')
              print(f'{feature} has a minimum value of {data[feature].min()}')
              print('Most repeated values:')
              print(data[feature].value_counts().head(5))
              print('Least repeated values:')
              print(data[feature].value_counts().tail(5))           
    print(feature_analysis()) 
# Driver Code
data_link = input("Enter the file directory path :")
OneShotEDA(data_link)
