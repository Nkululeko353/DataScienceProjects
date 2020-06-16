rm(list=ls())

college_data <- read.csv('College_admission.csv')
college_data
head(college_data)
str(college_data)
View(college_data)
summary(college_data)
colSums(is.na(college_data))
colSums(!is.na(college_data))
college_data$admit <- as.factor(college_data$admit)
college_data$ses <- as.factor(college_data$ses)
college_data$Gender_Male <- as.factor(college_data$Gender_Male)
college_data$Race <- as.factor(college_data$Race)
college_data$rank <- as.factor(college_data$rank)
str(college_data)
sum(is.na(college_data))

hist(college_data$admit)
hist(college_data$admit,100)
boxplot(college_data$admit)
hist(college_data$gre)
hist(college_data$gre,100)
boxplot(college_data$gre) # There are outliers here
hist(college_data$gpa)
hist(college_data$gpa,100)
boxplot(college_data$gpa) # There are outliers here



model <- glm(admit ~ .,
             family = binomial(link = 'logit'), 
             data =college_data)

summary(model)

result <- predict(model, college_data, type='response')
result[1:10]

result <- ifelse(result > 0.5, 1, 0) 
result[1:10]

tbl  <-  table (result, college_data$admit)
tbl

# result   0   1
#      0   252 92
#      1  21 35
dim(college_data)

(252 + 35)/400
0.7175*100
accuracy <- mean(result == college_data$admit)
accuracy
#Use variable reduction techniques to identify significant variables.
library(TH.data)
library(caret)
data("college_data", package = "TH.data")

library(Boruta)
boruta_output <- Boruta(admit ~ ., data=na.omit(college_data), doTrace=0) 
names(boruta_output)

# Get significant variables including tentatives
boruta_signif <- getSelectedAttributes(boruta_output, withTentative = TRUE)
print(boruta_signif)  


roughFixMod <- TentativeRoughFix(boruta_output)
boruta_signif <- getSelectedAttributes(roughFixMod)
print(boruta_signif)


# Variable Importance Scores
imps <- attStats(roughFixMod)
imps2 = imps[imps$decision != 'Rejected', c('meanImp', 'decision')]
head(imps2[order(-imps2$meanImp), ])  # descending sort


# Plot variable importance
plot(boruta_output, cex.axis=.7, las=2, xlab="", main="Variable Importance")  

# Train an rpart model and compute variable importance.
library(caret)
set.seed(100)
rPartMod <- train(admit ~ ., data=college_data, method="rpart")
rpartImp <- varImp(rPartMod)
print(rpartImp)

# Train an RRF model and compute variable importance.
set.seed(100)
rrfMod <- train(admit ~ ., data=college_data, method="RRF")
rrfImp <- varImp(rrfMod, scale=F)
rrfImp

set.seed(2)
college_idx = sample(1:nrow(college_data), 200)
college_train = college_data[college_idx,]
college_test = college_data[-college_idx,]

college_tree = rpart(admit ~ ., data = college_trn)
rpart.plot(college_tree)


college_tree_tst_pred = predict(college_tree, college_tst, type = "class")
table(predicted = college_tree_tst_pred, actual = college_tst$admit)

calc_acc = function(actual, predicted) {
  mean(actual == predicted)
}


(college_test_acc = calc_acc(predicted = college_tree_tst_pred, actual = college_tst$admit))


college_glm = glm(admit ~ ., data = college_trn, family = "binomial")
college_glm_tst_pred = ifelse(predict(college_glm, college_tst, "response") > 0.5, 
                           "Low", "High")
table(predicted = college_glm_tst_pred, actual = college_tst$admit)


(glm_tst_acc = calc_acc(predicted = college_glm_tst_pred, actual = college_tst$admit))

library(randomForest)

college_bag = randomForest(admit ~ ., data = college_trn, mtry = 10, 
                        importance = TRUE, ntrees = 500)
college_bag

college_bag_tst_pred = predict(college_bag, newdata = college_tst)
table(predicted = college_bag_tst_pred, actual = college_tst$admit)

(college_tst_acc = calc_acc(predicted = college_bag_tst_pred, actual = college_tst$admit))


college_forest = randomForest(admit ~ ., data = college_trn, mtry = 3, importance = TRUE, ntrees = 500)

college_forest

college_forest_tst_perd = predict(college_forest, newdata = college_tst)
table(predicted = college_forest_tst_perd, actual = college_tst$admit)

(forest_tst_acc = calc_acc(predicted = college_forest_tst_perd, actual = college_tst$admit))
table(predicted = college_forest_tst_perd, actual = college_tst$admit)

(forest_tst_acc = calc_acc(predicted = college_forest_tst_perd, actual = college_tst$admit))
library(caret)
set.seed(3033)
intrain <- createDataPartition(y = college_data$admit, p= 0.7, list = FALSE)
training <- college_data[intrain,]
testing <- college_data[-intrain,]


trctrl <- trainControl(method = "repeatedcv", number = 10, repeats = 3)
set.seed(3333)
knn_fit <- train(admit ~., data = training, method = "knn",
                 trControl=trctrl,
                 preProcess = c("center", "scale"),
                 tuneLength = 10)


knn_fit

test_pred <- predict(knn_fit, newdata = testing)
 test_pred
 
 confusionMatrix(test_pred, testing$admit )
 
 
 college_trn_mod = college_trn
 college_trn_mod$admit = as.numeric(ifelse(college_trn_mod$admit == "Low", "0", "1"))
 
 library(gbm)
 college_boost = gbm(admit ~ ., data = college_trn_mod, distribution = "bernoulli", 
                  n.trees = 5000, interaction.depth = 4, shrinkage = 0.01)
 college_boost
 
 
 college_boost_tst_pred = ifelse(predict(college_boost, college_tst, n.trees = 5000, "response") > 0.5, 
                              "High", "Low")
 table(predicted = college_boost_tst_pred, actual = college_tst$admit)
 
 (boost_tst_acc = calc_acc(predicted = college_boost_tst_pred, actual = college_tst$admit))
 
 
 
 oob = trainControl(method = "oob")
 cv_5 = trainControl(method = "cv", number = 5)
 
 dim(college_trn)
 
 rf_grid =  expand.grid(mtry = 1:10)
 
 set.seed(825)
 college_rf_tune = train(admit ~ ., data = college_trn,
                      method = "rf",
                      trControl = oob,
                      verbose = FALSE,
                      tuneGrid = rf_grid)
 college_rf_tune
 
 calc_acc(predict(college_rf_tune, college_tst), college_tst$admit)
 
 
                                        
 
 
 
 