rm(list=ls())
amazon_data = read.csv("Amazon - Movies and TV Ratings.csv", header= TRUE, sep = ',')
amazon_data

str(amazon_data)
# All the variables Movie1 to Movie206 are integers and user_id is a factor

library(plyr)
library(dplyr)
View(amazon_data)
nrow(amazon_data)
colnames(amazon_data)
#Analysis Task

#- Exploratory Data Analysis:
#Which movies have maximum views/ratings?
# We can apply many functions here.Because the Movies Movie1 to Movie206 are
# treated as columns,we can check which Movies/columns have have maximum non Na values.
colSums(!is.na(amazon_data))
# Here we found that the column user_id is the column that where has maximum non Na values.
# We are only interested in the Movies that has maximum non Na values,so we delete 
# user_id.
amazon_data$user_id <- NULL
amazon_data
# The variable user_id is now deleted
max(colSums(!is.na(amazon_data)))
# The maximum column sum is 2313,and that is Movie 127
colSums(!is.na(amazon_data))

###########################################################


#2.What is the average rating for each movie? Define the top 5 movies with the maximum ratings.
# Here many functions can be applied
summary(amazon_data) 
# We can use the summary function which shows the the minimum value,1rst Quartile,Median
# and mean of every column

# We can also apply the colMeans function
colMeans(amazon_data, na.rm=TRUE) # This gives us the average of every Movie
#Define the top 5 movies with the maximum ratings.
# Here we need to do a sort,in descending order which order values from highest
# to lowest
# The first Movie127 2313 ratings
# The second Movie140 578 ratings
# The third Movie16 320 ratings
# The fourth Movie103 272 ratings
# The fifth Movie29 243 ratings

amazon_data$average_ratings <-rowMeans(amazon_data, na.rm =TRUE, dims = 1)

amazon_data$average_ratings<- as.integer(amazon_data$average_ratings)
amazon_data
str(amazon_data$average_ratings)
amazon_data[is.na(amazon_data)] <- 0
amazon_data
 sort(colSums(!is.na(amazon_data)),decreasing =T)
 #3.Define the top 5 movies with the least audience.
 # Here we sort in ascending order.
 sort(colSums(!is.na(amazon_data)))
 # The first 5 Movies with least number of ratings
 # Movie1 1
 # Movie2 1
 # Movie3 1
 # Movie6 1
 # Movie7 1
 
# 4.- Recommendation Model: Some of the movies hadn't been watched and therefore, are not rated by the users. Netflix would like to take this as an opportunity and build a machine learning recommendation algorithm which provides the ratings for each of the users.
 
 
 sum(is.na(amazon_data))
 
# Divide the data into training and test data
 #Build a recommendation model on training data
 #Make predictions on the test data
 
 library(tidyverse)
 library(keras)
 library(caret)
 library(corrplot)
 library(randomForest)
 library(rpart)
 library(kableExtra)
 library(modelr)
 library(ggthemes)
 library(scales)
 glimpse(amazon_data)
 summary(amazon_data)
 
 index <- 1:69
 training <- amazon_data[index,]
 testing <- amazon_data[-index,]
 glimpse(training)
 glimpse(testing)
 
 training %>%
         mutate_at(vars(-average_ratings), scale) -> training
 testing %>%
         mutate_at(vars(-average_ratings), scale) -> testing
 

 
 fit_lm <- lm(average_ratings~., data = training)
 
 set.seed(100) # setting seed to reproduce results of random sampling
 trainingRowIndex <- sample(1:nrow(amazon_data), 0.8*nrow(amazon_data))  # row indices for training data
 trainingData <- amazon_data[trainingRowIndex, ]  # model training data
 testData  <- amazon_data[-trainingRowIndex, ]   # test data
 
 # Build the model on training data -
 lmMod <- lm(average_ratings~., data=trainingData)  # build the model
 summary(lmMod)
 predict_lm <- predict(lmMod, newdata = testData)
 print(head(predict_lm))
 Ratings_pred <- predict(lmMod, testData)
 summary (lmMod)
 AIC (lmMod)
 
 actuals_preds <- data.frame(cbind(actuals=testData, predicteds=Ratings_pred))  # make actuals_predicteds dataframe.
 library('caret')
 library('RANN')
 correlation_accuracy <- cor(actuals_preds)
 head(actuals_preds)
 
 
 library(tidyverse)
 library(tidyquant)
 library(timetk)