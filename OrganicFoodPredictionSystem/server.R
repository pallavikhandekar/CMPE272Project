library(shiny)
library(googleVis)
# Define server logic required to plot various variables against mpg
shinyServer(function(input, output) {
  #data <- read.csv("/Users/palloabhi/Downloads/mastersheet.csv")
  #sales<-data$Sales
  #years<-data$Year
  #df<-data.frame(X=sales,Y=years)
  #output$dfPlot <- renderPlot({
  #  plot(df);
  #})
  
  data <- read.csv("/Users/palloabhi/Downloads/mastersheet.csv")
  organicPrediction<-function(x=farmTimeSeries(),y=rainTimeSeries(),z=temperatureTimeSeries(),w=incomeTimeSeries()){
    Farmland<-data$Farmland
    Rain<-data$Rain
    Temperature<-data$Temperature
    Sales<- data$Sales
    Income<-data$Income
    fit <- lm(Sales ~ Farmland + Rain + Temperature + Income)
    newfarm<-x
    newrain<-y
    newtemp<-z
    newincome<-w
    newdata<-data.frame(Farmland=newfarm,Rain=newrain,Temperature=newtemp,Income=newincome)
    newsales<-predict(fit,newdata=newdata)
    return (newsales)
  }
  
  farmTimeSeries<-function(){
    
    fit <- arima(data$Farmland, order=c(1,0,0), list(order=c(2,1,0), period=1))
    fore <- predict(fit, n.ahead=1)
    return (fore$pred)
  }
  
  rainTimeSeries<-function(){
    
    fit <- arima(data$Rain, order=c(1,0,0), list(order=c(2,1,0), period=1))
    fore <- predict(fit, n.ahead=1)
    return (fore$pred)
  }
  
  temperatureTimeSeries<-function(){
   
    fit <- arima(data$Temperature, order=c(1,0,0), list(order=c(2,1,0), period=1))
    fore <- predict(fit, n.ahead=1)
    return (fore$pred)
  }
  
  incomeTimeSeries<-function(){
   
    fit <- arima(data$Income, order=c(1,0,0), list(order=c(2,1,0), period=1))
    fore <- predict(fit, n.ahead=1)
    return (fore$pred)
  }
  
  predictedSales<-organicPrediction()
  Sales<-union(data$Sales,predictedSales)
  Year<-union(data$Year,c(2013))
  
  df<-data.frame(X=Year,Y=Sales)
  
  output$View <- renderGvis({
    gvisScatterChart(df,options=list(legend='none', width=1000,height=500))
  })
  
})