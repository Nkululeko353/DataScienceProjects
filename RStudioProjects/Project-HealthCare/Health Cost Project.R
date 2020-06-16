hops  <- read.csv('HospitalCosts.csv') 
hops
head(hops)
str(hops)
# Attribute	      Description
# Age 	          Age of the patient discharged
# Female 	        A binary variable that indicates if the patient is female
# Los	            Length of stay in days
# Race 	          Race of the patient (specified numerically)
# Totchg	        Hospital discharge costs
# Aprdrg	        All Patient Refined Diagnosis Related Groups

nrow(hops)
summary(hops)

colSums(is.na(hops))

hops <- na.omit(hops)

colSums(is.na(hops))

str(hops)

# 'data.frame':	500 obs. of  6 variables:
# $ AGE   : int  17 17 17 17 17 17 17 16 16 17 ...
# $ FEMALE: int  1 0 1 1 1 0 1 1 1 1 ...
# $ LOS   : int  2 2 7 1 1 0 4 2 1 2 ...
# $ RACE  : int  1 1 1 1 1 1 1 1 1 1 ...
# $ TOTCHG: int  2660 1689 20060 736 1194 3305 2205 1167 532 1363 ...
# $ APRDRG: int  560 753 930 758 754 347 754 754 753 758 ...

# Race, Female and APRDRG are be categorical variable
# Race, Female and APRDRG are be categorical variable

hops$RACE <- as.factor(hops$RACE)
hops$FEMALE <- as.factor(hops$FEMALE)

unique(hops$APRDRG)

hops$APRDRG_Factor <- as.factor(hops$APRDRG)

str(hops)
# Creating age bins

hops$age_bins <- ifelse((hops$AGE  < 1), "infant",
                        ifelse(hops$AGE < 3, 'toddler',
                               ifelse(hops$AGE < 11, 'child',
                                      'adolescent')))

hops$age_bins <- as.factor(hops$age_bins)

str(hops)

# 'data.frame':	500 obs. of  8 variables:
# $ AGE          : int  17 17 17 17 17 17 17 16 16 17 ...
# $ FEMALE       : Factor w/ 2 levels "0","1": 2 1 2 2 2 1 2 2 2 2 ...
# $ LOS          : int  2 2 7 1 1 0 4 2 1 2 ...
# $ RACE         : Factor w/ 6 levels "1","2","3","4",..: 1 1 1 1 1 1 1 1 1 1 ...
# $ TOTCHG       : int  2660 1689 20060 736 1194 3305 2205 1167 532 1363 ...
# $ APRDRG       : int  560 753 930 758 754 347 754 754 753 758 ...
# $ APRDRG_Factor: Factor w/ 63 levels "21","23","49",..: 32 51 62 55 52 28 52 52 51 55 ...
# $ age_bins     : Factor w/ 4 levels "adolescent","child",..: 1 1 1 1 1 1 1 1 1 1 ...

# 1. To record the patient statistics, the agency wants to find the age 
# category of people who frequents the hospital and has the maximum expenditure.  

# a. To find the category that has the highest frequency of hospital visit, 

# We can use graphical analysis. A histogram would display the number of 
# occurrences of each age category.  The as.factor() is called to make sure 
# that the categories are not treated as numbers. Outlier treatments: None  
hist(hops$AGE)
# The  Histogram  Displaying the age category of people who frequents the hospital. we can see that infants AGE = 0) have the maximum , going above 300.

 



# frequency of hospital visit

summary(as.factor(hops$AGE))
# Age:        0     1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17 
# Frequency:  307  10   1   3   2   2   2   3   2   2   4   8  15  18  25  29  29  38
# Result: From the graph that is displayed, we can see that infants AGE = 0) have the maximum 
# frequency of hospital visit, going above 300. The summary of AGE attribute gives 
# the numerical output (after converting the age from numeric to factor) - and we 
# can see that there are 307 entries for those in the range of 0-1 year.

# b. To find the age category with the maximum expenditure, 

# we need to add the expenditure for each age, and find the maximum value 
# from the sum. We will use the aggregate function to add the values of 
# total expenditure according to the values of age.

# With AGE
aggregate(TOTCHG ~ AGE, FUN = sum, data = hops)
max(aggregate(TOTCHG ~ AGE, FUN = sum, data = hops))

df <- aggregate(TOTCHG ~ AGE, FUN = sum, data = hops)
df
df[(df$TOTCHG == max(df$TOTCHG)),]

summarise(group_by(walmart_data, Weekly_Sales ~ Store), m = mean(disp), sd = sd(disp))


#     AGE  TOTCHG
# 1    0   678118

# age_bins
df <- aggregate(TOTCHG ~ age_bins, FUN = sum, data = hops)
df

max(aggregate(TOTCHG ~ age_bins, FUN = sum, data = hops)$TOTCHG)
df[(df$TOTCHG == max(df$TOTCHG)),]

#    age_bins   TOTCHG
# 3    infant   678118

# Result: From the result we can see that the infant category (AGE = 0) has maximum hospital costs 
# as well (in accordance with the number or frequency of visit). Following the infants, 
# 15 and 17 year old individuals have high hospitalization costs.

# 2. In order of severity of the diagnosis and treatments and to find out the expensive 
# treatments, the agency wants to find the diagnosis related group that has maximum 
# hospitalization and expenditure.

summary(as.factor(hops$APRDRG))

aggregate(TOTCHG ~ APRDRG, FUN = sum, data = hops)
max(aggregate(TOTCHG ~ APRDRG, FUN = sum, data = hops))

df <- aggregate(TOTCHG ~  APRDRG, FUN = sum, data = hops)
df
df[(df$TOTCHG == max(df$TOTCHG)),]

#APRDRG TOTCHG
#44    640 437978

# Result: The APRDRG 640 has the maximum expenditure of 437978.

# 3. To make sure that there is no malpractice, the agency needs to analyze if the 
# race of the patient is related to the hospitalization costs.

# If there is any effect of RACE on TOTCHG

# To analyze, first convert the Race variable to factors and perform a summary of the variable. This 
# will help you to find how many patients belonging to the different groups were admitted. 
# Then, to verify if the races made an impact on the costs, perform an ANOVA with the 
# following variables:  

# Defining Hypothesis

# Ho: The races has no an impact on the costs
# H1: The races has an impact on the costs

# ANOVA dependent variable: TOTCHG 
# Categorical/grouping variable: RACE Missing values: 1 NA value, use na.omit to remove the NA value   
# 
# Code:  

str(hops$RACE)
str(hops$TOTCHG)

model <- aov(TOTCHG ~ RACE, data = hops)  # numerical ~ categorical varibale

# dependent variable ~ independent variable

summary(model)

alpha = 0.05

pvalue = 0.943

pvalue < alpha # if this is true = whenever p_value is less than alpha; we reject the null hypothesis

# Result:  The p-value is very high specifying that there is no relation between 
# the race of patient and the hospital cost.
summary(hops$RACE) 

# Race        1   2   3   4   5   6 
# Frequecy  484   6   1   3   3   2 

# From the summary we can also see that:
# the data has 484 patients of Race 1 out of the 500 entries. This will affect the 
# results of ANOVA as well, since the number of observations is very much skewed. 
# In conclusion, there is not enough data to verify if the race of patient is related 
# to the hospitalization cost.

# 4. To properly utilize the costs, the agency has to analyze the severity of the 
# hospital costs by age and gender for proper allocation of resources.  

# Defining Hypothesis

# Ho: The age had no an impact on the costs
# H1: The age had an impact on the costs


#ANOVA dependent variable: TOTCHG
#Categorical/grouping variable: age

str(hops$AGE)
str(hops$TOTCHG)

model1 <- aov(TOTCHG ~ AGE, data = hops)

summary(model1)

summary(model1)

alpha = 0.05

pvalue = 0.00333

pvalue < alpha
#From Here we can see that Age category has an effect on Cost,p-value is less than 0.05 we reject the null hypothesis.

str(hops$FEMALE)
str(hops$TOTCHG)

model2 <- aov(TOTCHG ~ FEMALE, data = hops)

summary(model2)

alpha = 0.05

pvalue = 0.185

pvalue < alpha

#From Here the Gender has no effect on Cost,p-value is higher than 0.05,so we reject the null hypothesis.

# 5. Since, the length of stay is the crucial factor for inpatients, the agency 
# wants to find if the length of stay can be predicted from age, gender, and race.


str(hops$AGE)
str(hops$LOS)

model3 <- aov(LOS ~AGE, data = hops)

summary(model3)

alpha = 0.05

pvalue = 0.123

pvalue < alpha
#The LOS cannot be predicted from age,p-value=0.123 is higher than 0.05,we do not reject the null hypothesis

str(hops$FEMALE)
str(hops$LOS)

model4 <- aov(LOS ~FEMALE, data = hops)

summary(model4)

alpha = 0.05

pvalue = 0.419

pvalue < alpha
#Gender has no impact on LOS,p-value=0.419 is higher than 0.05,we  do not reject null hypothesis

str(hops$RACE)
str(hops$LOS)

model5 <- aov(LOS ~RACE, data = hops)

summary(model5)

alpha = 0.05

pvalue =  0.982

#RACE has no impact on LOS,p-value  0.982
 is greater than 0.05.

# 6. To perform a complete analysis, the agency wants to find the variable that 
# mainly affects the hospital costs. To find the variables that mainly affect the 
# total costs, construct a linear model with all the variables as the causing variables.  
# Dependent variable: TOTCHG Independent variables: All other variables  

model6 <- aov(TOTCHG ~., data = hops)
summary(model6)
# From here we can infer that all the varibles has an impact on TOTCHG

#Df    Sum Sq   Mean Sq  F value   Pr(>F)    
#AGE             1 1.297e+08 1.297e+08  221.433  < 2e-16 ***
#  FEMALE          1 6.522e+07 6.522e+07  111.306  < 2e-16 ***
# LOS             1 3.086e+09 3.086e+09 5266.956  < 2e-16 ***
# RACE            5 1.324e+07 2.649e+06    4.521 0.000501 ***
# APRDRG          1 8.870e+08 8.870e+08 1513.819  < 2e-16 ***
#APRDRG_Factor  61 3.097e+09 5.077e+07   86.640  < 2e-16 ***
#age_bins        3 1.484e+07 4.947e+06    8.442 1.85e-05 ***
#Residuals     425 2.490e+08 5.860e+05                      
#---
# Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1
# AGE p-value=2e-16
#  FEMALE p-value=2e-16
# LOS p-value=2e-16
# RACE p-value=0.000501
# APRDRG  =2e-16
# APRDRG_Factor= 2e-16
# age_bins=1.85e-05

# All the p-values of these variables are less than 0.05,so we reject the null hypothesis from all these variables.
