# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import numpy as np 
import pandas as pd
import matplotlib.pyplot as plt
import scipy.stats as s
import tensorflow as tf
from tensorflow import keras

#Prediction function
def predicted_category(x):
    
    likelihood_1 = s.norm.pdf(x,mu[0,1],sigma[0,1])
    
    numerator = likelihood_1 * rf[1]
    
    likelihood_0 = s.norm.pdf(x,mu[0,0],sigma[0,0])
    
    posterior_1 = numerator/(numerator + likelihood_0 * rf[0])
    
    return np.uint(posterior_1 > 0.5)


#DATA Processing and Slicing
data = pd.read_csv("data.csv")
data.drop(labels=[data.columns[0],data.columns[32]],axis=1,inplace=True)
data = data.iloc[:,[0,21,22]]
data['diagnosis'].replace(to_replace=['B','M'],value=[0,1],inplace=True)
training_data = data.iloc[0:int(0.7*len(data))]
remaining_data = data.iloc[int(0.7*len(data)):]
cv_data = remaining_data.iloc[0:int(0.33*len(remaining_data))]
testing_data = remaining_data.iloc[int(0.33*len(remaining_data)):]

#Prior probability
rf = np.array(training_data['diagnosis'].value_counts())
rf = rf/training_data.shape[0]

#Feature extraction
feature_0 = training_data[training_data['diagnosis'] == 0]['radius_worst']
feature_0 = np.array(feature_0)
feature_1 = training_data[training_data['diagnosis'] == 1]['radius_worst']
feature_1 = np.array(feature_1)

#Histograms
plt.hist(feature_0)
plt.hist(feature_1)

#Naive bayes

mu = np.zeros((1,2))

sigma = np.zeros((1,2))

mu[0,0] = np.mean(feature_0)

mu[0,1] = np.mean(feature_1)

sigma[0,0] = np.std(feature_0)

sigma[0,1] = np.std(feature_1)

predicted_answers_cv = predicted_category(cv_data.iloc[:,1])

actual_answers_cv = np.array(cv_data.iloc[:,0])

correct_count_cv = np.count_nonzero(predicted_answers_cv == actual_answers_cv) 
print("correct counts : ",correct_count_cv)

percent_accuracy_cv = (correct_count_cv/cv_data.shape[0])*100
print("cv data accuracy : ",percent_accuracy_cv)


predicted_answers_test = predicted_category(testing_data.iloc[:,1])

actual_answers_test = np.array(testing_data.iloc[:,0])
correct_count_test = np.count_nonzero(predicted_answers_test == actual_answers_test)
print("predicted counts : ",correct_count_test)

percent_accuracy_test = (correct_count_test/testing_data.shape[0])*100
print("predicted data accuracy : ",percent_accuracy_test)