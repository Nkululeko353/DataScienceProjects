rm(list=ls())                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  rm(list=ls())

#Analysis Task
#1.Import data into R environment.
MyRData<-read.csv("Comcast_telecom_complaints_data.csv",header=T)
MyRData

rm(list=ls())
MyRData<-read.csv("Comcast_telecom_complaints_data.csv")
MyRData
str(MyRData)

head(MyRData)
str(MyRData)
summary(MyRData)

library(dplyr)
library(plyr)
dim(MyRData)
mutate(MyRData,numofoccur<-count(as.numCustomer.Complaint))
MyRData$numofoccur<-count(MyRData$Customer.Complaint)
MyRData$numofoccur<- as.numeric(MyRData$Customer.Complaint)

dim(MyRData)
MyRData

dim(MyRData)
hist(MyRData$Customer.Complaint)
#- Provide a table with the frequency of complaint types.

#Which complaint types are maximum i.e., around internet, network issues, or across any other domains.
freqtab<-summary(MyRData$Customer.Complaint)
freqtab
# Comcast has the maximum complaint Types and which are 83.
#Comcast 
#83 
#Comcast Internet 
#18 
#Comcast Data Cap 
#17 
#comcast 
#13 
#Comcast Billing 
#11 
#Comcast Data Caps 
#11 
#Data Caps 
#11 
#Unfair Billing Practices 
#9 
#Comcast data cap 
#8 
#Comcast data caps 
#8 
#Comcast internet 
#8 
#Comcast/Xfinity 
#8 
#Data Cap 
#8 
#Internet speed 
#8 
#Billing 
#6 
#COMCAST 
#6 
#Comcast billing 
#6 
#Comcast service 
#6 
#Comcast Service 
#6 
#Comcast complaint 
#5 
#Comcast Complaint 
#5 
#Comcast Internet Service 
#5 
#Internet Speed 
#5 
#availabilty 
#4 
#Billing Dispute 
#4 
#Comcast Billing and Service Issues 
#4 
#Comcast Billing Complaint 
#4 
#comcast data cap 
#4 
#Comcast Internet Complaint 
#4 
#Comcast internet service 
#4 
#Comcast Issues 
#4 
#Comcast Unfair Billing Practices 
#4 
#Data cap 
#4 
#Internet service 
#4 
#Slow Internet 
#4 
#billing 
#3 
#Billing Complaint 
#3 
#billing issues 
#3 
#Billing Issues 
#3 
#billing/service issues 
#3 
#Comcast bait and switch 
#3 
#Comcast Billing Issues 
#3 
#Comcast billing practices 
#3 
#Comcast Customer Service 
#3 
#Comcast Internet Billing 
#3 
#Comcast Throttling 
#3 
#Complaint against Comcast 
#3 
#internet 
#3 
#Internet Availability 
#3 
#Internet Throttling 
#3 
#service issues 
#3 
#Unfair billing practices 
#3 
#Unfair pricing 
#3 
#Bad Customer Service 
#2 
#bait and switch 
#2 
#Billing issues 
#2 
#Charged for data not actually used 
#2 
#Comcast -Exfinity customer service errors, lies and wasted time 
#2 
#Comcast Atlanta Data Caps 
#2 
#Comcast Billing Practices 
#2 
#Comcast billing problem 
#2 
#Comcast Business Internet 
#2 
#Comcast cable 
#2 
#Comcast Cable 
#2 
#Comcast cable unauthorized charges 
#2 
#Comcast Charges 
#2 
#COMCAST CUSTOMER CARE ( RACISM) 
#2 
#Comcast data usage cap 
#2 
#Comcast Fraudulent Charges 
#2 
#comcast internet 
#2 
#Comcast Internet Data Cap 
#2 
#Comcast internet for low income families 
#2 
#Comcast internet overage charges 
#2 
#Comcast Internet Service Complaint 
#2 
#Comcast monopoly 
#2 
#Comcast run around 
#2 
#comcast service 
#2 
#Comcast Service and Billing Issues 
#2 
#Comcast service billing 
#2 
#Comcast Slow Internet 
#2 
#Comcast Support 
#2 
#Comcast Terrible Experience with no resolution 
#2 
#Comcast Throttling Internet 
#2 
#Comcast Throttling My Internet 
#2 
#Comcast unfair billing 
#2 
#comcast unwilling to resolve data usage issue 
#2 
#Comcast Usage Caps 
#2 
#Comcast Xfinity 
#2 
#Comcast/xfinity 
#2 
#Comcast/Xfinity- Paying for High speed service 
#2 
#Comcast/Xfinity Customer Service 
#2 
#Continued slowness for almost 3 weeks 
#2 
#Data caps 
#2 
#Data overage charges 
#2 
#exaggerated bill and Lack of responsibilities in keep appointments! Disrespect for Consumers 
#2 
#Failure of service from Comcast 
#2 
#Fraud 
#2 
#Internet 
#2 
#Internet Outages 
#2 
#(Other) 
#1759 
#- Create a new categorical variable with value as Open and Closed. Open & Pending is to be categorized as Open and Closed & Solved is to be categorized as Closed.
MyRData$newcatecastatus <- ifelse((MyRData$Status=="Pending"),"Open",
                              ifelse(MyRData$Status=="Solved",'Closed',
                                 ifelse(MyRData$Status=="Open",'Open',
                                      'Closed')))
             

MyRData$newcatecastatus<- as.factor(MyRData$newcatecastatus)

str(MyRData)

> 
  # Create a R ggplot Line Plot
  library(europepmc)
# Importing the ggplot2 library
library(ggplot2)

library(ggplot2)
ggplot(newDate, aes(x,freq)) + 
  geom_point() + 
  geom_line() +
  xlab("Months") + 
  ylab("The Complaints")

counts <- table(MyRData$newcatecastatus, 
                MyRData$State) 
            barplot(counts, 
                    main="State-wise status of complaints",
                    xlab="States", col=c("grey","cornflowerblue" 
                                                  ), 
                    legend = rownames(counts), 
                    beside=TRUE)

 






newDate<-count(months(as.Date(MyRData$Date)))


library(dplyr)
library(epiDisplay)
library(summarytools)
MyRData %>% tally()

MyRData %>% group_by(Date_month_year) %>% tally()
MyRData %>% group_by(Customer.Complaint )%>% tally()
MyRData
MyRData %>% count(Status)
MyRData %>% group_by(Date) %>% count(State)

MyRData %>% group_by(State) %>% count(Status)

summarise(group_by(MyRData,Status),count(State))



table(MyRData$State)
tab1(MyRData$State, cum.percent = TRUE)
summarytools::freq(MyRData$State, order = "freq")


library(magrittr)
MyRData %>% descr(stats = "common") %>% tb()


grouped_freqs <- stby(data =MyRData$State, 
                      INDICES = MyRData$newcatecastatus, 
                      FUN = freq, cumul = FALSE, report.nas = FALSE)
grouped_freqs
grouped_freqs3 <- stby(data =MyRData$State, 
                      INDICES = MyRData$Customer.Complaint, 
                      FUN = freq, cumul = FALSE, report.nas = FALSE)
grouped_freqs3
                      
grouped_freqs %>% tb()


grouped_freqs1 <- stby(data =MyRData$Received.Via, 
                      INDICES = MyRData$Customer.Complaint, 
                      FUN = freq, cumul = FALSE, report.nas = FALSE)
grouped_freqs1

grouped_freqs1 %>% tb()

