rm(list=ls())
walmart_data = read.csv("Walmart_Store_sales.csv", header= TRUE, sep = ',')
walmart_data

head(walmart_data)
str(walmart_data)

summary(walmart_data)


tapply(walmart_data$Weekly_Sales, walmart_data$Holiday_Flag,mean)


#1.
aggregate(Weekly_Sales ~ Store, FUN = sum, data = walmart_data)
max(aggregate(Weekly_Sales ~ Store, FUN = sum, data = walmart_data))

 df<- aggregate( Weekly_Sales~ Store, FUN = sum, data = walmart_data)
df
df[(df$Weekly_Sales == max(df$Weekly_Sales)),]
library("dplyr")

summarize(walmart_data,s=sd(Weekly_Sales))

summarize(group_by(walmart_data,Store,Holiday_Flag==0), m=mean(walmart_data$Weekly_Sales))

tapply(walmart_data$Store,(sd(walmart_data$Weekly_Sales)))
max(sd(walmart_data$Weekly_Sales))
max(sd(walmart_data$Store))
sd(walmart_data$Store)
sd(walmart_data$Weekly_Sales)
newDate<-count(months(as.Date(walmart_data$Date)))
library(ggplot2)

ggplot(walmart_data,aes(months(as.Date(Date)),walmart_data$Weekly_Sales)) + 
  geom_point() + 
  geom_line() +
  xlab("Months") + 
  ylab("Weekly Sales")
install.packages("ggplot2") 


counts <- table(walmart_data$Weekly_Sales,months(as.Date(walmart_data$Date)),
                walmart_data$Weekly_Sales)
barplot(counts, 
        main="Monthly view Of Sales",
        xlab="Months", col=c("grey","cornflowerblue" 
        ), 
        legend = rownames(counts), 
        beside=TRUE)
usnim_ts = ts(walmart_data[, 2], start = c(2010, 1), frequency = 4)
usnim_ts

library(fUnitRoots)
library(lmtest)
library(forecast)
library(fastDummies)
library(tseries)

library(tidyverse)
library(tidyquant)
library(timetk)

tsData = ts(walmart_data, start = c(2010,1), frequency = 4)
tsData

walmart_data %>%
  group_by(Store) %>%
  summarize(std_dev = sd(Weekly_Sales, na.rm = TRUE,max()))


#2.
aggregate(Weekly_Sales ~ Store, FUN = sd, data =walmart_data)
max(aggregate(Weekly_Sales ~ Store, FUN = sd, data = walmart_data))

df <- aggregate(Weekly_Sales ~ Store, FUN = sd, data = walmart_data)
df
df[(df$Weekly_Sales == max(df$Weekly_Sales)),]


str(walmart_data)
walmart_data
filter(walmart_data,Store==1)

weeklysales_date <- walmart_data %>% select(Date, Weekly_Sales,Store)
weeklysales_date

walmart_data
FB_tbl <- walmart_data %>% filter(Store == 45)
FB_tbl

filtered
year1 <- as.numeric(format(walmart_data$Date,'%Y'))
year1

walmart_data1=mutate(walmart_data, New_date =as.yearqtr(Date))
walmart_data1
walmart_data2=mutate(walmart_data1, newyears =year1)
walmart_data2
library(plyr)  
select(walmart_data, Weekly_Sales, Store, Holiday_Flag)
library(stats)
library(dplyr)
library(zoo)

walmart_data
 filter(walmart_data,Store == 1)


walmart_data%>% 
  select(Weekly_Sales,Store,Holiday_Flag) %>%
  filter(Holiday_Flag == 0)

filter(walmart_data,Holiday_Flag == 0)

str(walmart_data)
walmart_data2




walmart_data %>%
  group_by(Holiday_Flag) %>%
  summarize(mean_sales = mean(Weekly_Sales, na.rm = TRUE))

model<-lm(Weekly_Sales~., walmart_data)
summary(model)
colMeans(walmart_data, na.rm=TRUE)


walmart_data %>%
  group_by(Holiday_Flag) %>%
  summarize(mean_sales = mean(Weekly_Sales, na.rm = TRUE))


walmarttimeseries <- ts(walmart_data)
walmarttimeseries


filter(walmart_data, Holiday_Flag == 1)

fore_data <- ts(walmart_data, start=2010, end=2012,frequency=12)

fore_data

tsData = ts(walmart_data, start = c(2010,1),end=c(2010), frequency = 12)
tsData
fore_data1 <- ts(walmart_data$Weekly_Sales, start= c(2010,1), end=2012,frequency=12)
fore_data1

tsData2 = ts(walmart_data, start = c(2010,1),end=c(2012,45), frequency = 4)

tsData2


tseries <- ts(walmart_data, start = c(2010, 1),end=c(2012,11) ,frequency = 4)
tseries

year2 <- as.numeric(format(walmart_data$Date,'%Y'))
year2

walmart_data$Date<-as.Date(walmart_data$Date, "%d-%m-%Y")

str(walmart_data)
walmart_data$Holiday_Flag <- as.factor(walmart_data$Holiday_Flag)
walmart_data$Store <- as.factor(walmart_data$Store)
str(walmart_data)



#For Store 1 - Build prediction models to forecast demand
library(caret)
library(forecast)
library(lubridate)
library(h2o)
library(prophet)
library(tidyverse)
library(timetk)

h2o.init()
h2o.no_progress()


# Create a list of holidays for use in one of the models
holiday_dates <- walmart_data %>%
  filter(Holiday_Flag == 1 & Date < as.Date('2012-01-01')) %>%
  select(Date) %>%
  unique()

holidays <- tibble(
  holiday = "holiday",
  ds = holiday_dates$Date,
  lower_window = 0,
  upper_window = 1
)


# Subset the data to focus just on Store 1
store_1 <- walmart_data %>%
  filter(Store == 1) %>%
  select(Date, Holiday_Flag, Weekly_Sales)

str(store_1)

# Split the data into training and test sets
train_tbl <- store_1 %>% filter(Date < as.Date('2012-01-01'))
test_tbl <- store_1 %>% filter(Date >= as.Date('2012-01-01'))
  
  
# Convert the data into a timeseries object
ts_1_1 <-
  ts(train_tbl$Weekly_Sales,
     start = c(2010, 5),
     frequency = 52)


set.seed(111)

# Neural Net
nnet_model <- nnetar(ts_1_1, size = 3, repeats = 1000)

# Prophet
prophet_1_1 <- train_tbl %>%
  select(Date, Weekly_Sales) %>%
  rename("ds" = Date,
          "y" = Weekly_Sales)

prophet_model <-
  prophet(
    prophet_1_1,
    yearly.seasonality = TRUE,
    weekly.seasonality = TRUE,
    holidays = holidays
  )

future <-
  make_future_dataframe(prophet_model, periods = 43, freq = "week")


prophet_forecast <- predict(prophet_model, future)

# H2O
store_1_1_clean <- store_1 %>%
  select(Date, Weekly_Sales, Holiday_Flag) %>%
  tk_augment_timeseries_signature() %>%
  select_if(~ !is.Date(.)) %>%
  select_if(~ !any(is.na(.))) %>%
  mutate_if(is.ordered, ~ as.character(.) %>% as.factor)

train_clean <- store_1_1_clean %>% filter(year < 2012)
test_clean <- store_1_1_clean %>% filter(year == 2012)

train_h2o <- as.h2o(train_clean)
test_h2o <- as.h2o(test_clean)

y <- "Weekly_Sales"
x <- setdiff(names(train_h2o), y)

h2o_model <- h2o.automl(
  x = x,
  y = y,
  training_frame = train_h2o,
  leaderboard_frame = test_h2o,
  max_runtime_secs = 120,
  stopping_metric = "RMSE"
)

h2o_leader <- h2o_model@leader

# Linear Regression
lm_model <-
  lm(Weekly_Sales ~ ., data = train_clean[, names(store_1_1_clean) != "wday.lbl"])

# Make the predictions
year1 <- as.numeric(format(store_1$Date,'%Y'))

pred_data <- store_1 %>%
  filter(year1== 2012) %>%
  select(-Holiday_Flag) %>%
  add_column(nnet_pred = forecast(nnet_model, h = 43) %>% as_tibble() %>% pull(`Point Forecast`)) %>%

  add_column(
    prophet_pred = predict(prophet_model, future) %>% as_tibble() %>% filter(year(ds) == 2012) %>% pull(yhat)
  ) %>%
  
  add_column(h2o_pred = h2o.predict(h2o_leader, test_h2o) %>% as_tibble() %>% pull(predict)) %>%
  add_column(lm_pred = predict(lm_model, test_clean) %>% tibble::enframe(name = NULL) %>% pull(value))

# Plot the actual and predicted values
pred_data %>%
  gather("prediction", "value",-Date) %>%
  ggplot(aes(x = Date, y = value, color = prediction)) +
  geom_line() +
  scale_x_date(date_breaks = "4 week", date_labels = "%B %d") +
  scale_color_manual(
    values = c(
      "Weekly_Sales" = "black",
      "h2o_pred" = "#a5d8f3",
      "lm_pred" = "#fdc7d7",
      "nnet_pred" = "#ff9de6",
      "prophet_pred" = "#e8e500"
    )
  ) +
  theme_classic() +
  labs(title = "comparing model fit",
       subtitle = "test data: 2012",
       x = "Date",
       y = "weekly sales (in USD)",
       caption = "data: walmart") + 
  theme(text = element_text(family = "Futura Medium"),
        plot.title = element_text(hjust = .5),
        plot.subtitle = element_text(hjust = .5),
        axis.text.x = element_text(angle = 45, hjust = 1))
  
# Calculate performance metrics for the four models
forecast_measures <- function(df) {
  matrix <- matrix(nrow = 5, ncol = (ncol(df) - 2))
  for (i in 3:ncol(df)) {
    error <- df[2] - df[i]
    error_pct <- error / df[2]
    matrix[1, i - 2] <- colMeans(error)
    matrix[3, i - 2] <- colMeans(abs(error))
    matrix[5, i - 2] <- colMeans(error_pct)
    matrix[4, i - 2] <- colMeans(abs(error_pct))
    matrix[2, i - 2] <- colMeans(error ^ 2) ^ 0.5
    for (k in 1:ncol(matrix)) {
      data <- matrix[, k]
      data <- round(data, 2)
      matrix[, k] <- data
    }
  }
  colnames(matrix) <- colnames(df[, 3:ncol(df)])
  rownames(matrix) <- c("Mean Error", "Mean Absolute Error", "Mean Percent Error", "Mean Absolute Percent Error", "Root Mean Square Error")
  print(matrix)
}

forecast_measures(pred_data)


#Linear Regression - Utilize variables like date and restructure dates as 1 for 5 Feb 2010(starting from the earliest date in order). 
#Hypothesize if CPI, unemployment, and fuel price have any impact on sales.


model <-lm(Weekly_Sales~CPI+Unemployment+Fuel_Price, walmart_data)
summary(model)


model<-lm(Weekly_Sales~., walmart_data)
summary(model)

#Time series forecasting model -
#Hypothesize if the data is fit for time series analysis - check for white noise probability test
#Make adjustments in historical data for events like holidays, if applicable
#Build ARIMA model to forecast 6 months i.e., input utilize only till April 2012.

#Predict next 6 months i.e., June to Oct 2010. Check for MAPE.

ggAcf(walmart_data)

walmart_data.ts <- ts(walmart_data$Unemployment, start = c(2010, 1), frequency = 12)
autoplot(walmart_data.ts)

Box.test(walmart_data.ts, lag = 24, fitdf = 0, type = "Lj")

walmart = ts(walmart_data$Weekly_Sales, frequency = 12, start = c(2010,1),end=c(2011,10))
walmart
plot(walmart)

walmart_arima= arima(rider, order=c(1,0,0))
walmart_arima

forecast_value = forecast(walmart_arima, 6)
forecast_value
plot(forecast_value)
accuracy(forecast_value)

walmart_arima1 = arima(rider, order=c(0,0,1))
walmart_arima1

forecast_value1 = forecast(walmart_arima1, 6)
forecast_value1
plot(forecast_value1)
accuracy(forecast_value1)


plot(forecast_value)

length(walmart)


training = subset(walmart, start = length(walmart) -21, end = length(walmart) - 17)
training
cycle(training)


walmart
test = subset(walmart, start = length(walmart) - 13)
cycle(test)

plot(training)
cycle(test)
plot(training)

adfTest(training)


adfTest(training)

training %>%
  ggtsdisplay()

training %>%
  diff(lag=12) %>%
  ggtsdisplay()

training %>%
  diff() %>%
  ggtsdisplay()

training %>%
  diff() %>%
  diff(lag=12) %>%
  ggtsdisplay()


model_train = Arima(training, order = c(0,0,0), seasonal = c(0,0,0))
summary(model_train)

coeftest(model_train)
checkresiduals(model_train)

pred_test = Arima(test, model = model_train)
accuracy(pred_test)

model_train %>%
  forecast(h = 20)%>%
  autoplot() + autolayer(test) 


autoarima = auto.arima(training)
autoarima

f3 = forecast(autoarima, 6)
f3

accuracy(f3)