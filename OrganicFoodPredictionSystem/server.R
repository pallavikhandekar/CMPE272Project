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
  
  data <- read.csv("C:/Users/Saurabh/Downloads/mastersheet.csv")
  
  organicPrediction<-function(x,y,z,w){
    if(x=="0"){
      x=farmTimeSeries()
    }
    if(x=="-10%"){
      x=farmTimeSeries()
      x<-x-(x/10)
    }
    if(x=="-5%"){
      x=farmTimeSeries()
      x<-x-(x/20)
    }
    if(x=="+5%"){
      x=farmTimeSeries()
      x<-x+(x/20)
    }
    if(x=="+10%"){
      x=farmTimeSeries()
      x<-x+(x/10)
    }
    
    if(w=="0"){
      w=incomeTimeSeries()
    }
    if(w=="-10%"){
      w=incomeTimeSeries()
      w<-w-(w/10)
    }
    if(w=="-5%"){
      w=incomeTimeSeries()
      w<-w-(w/20)
    }
    if(w=="+5%"){
      w=incomeTimeSeries()
      w<-w+(w/20)
    }
    if(w=="+10%"){
      w=incomeTimeSeries()
      w<-w+(w/10)
    }
    
    if(y=="0"){
      y=rainTimeSeries()
    }
    if(y=="-10%"){
      y=rainTimeSeries()
      y<-y-(y/10)
    }
    if(y=="-5%"){
      y=rainTimeSeries()
      y<-y-(y/20)
    }
    if(y=="+5%"){
      y=rainTimeSeries()
      y<-y+(y/20)
    }
    if(y=="+10%"){
      y=rainTimeSeries()
      y<-y+(y/10)
    }
    
    if(z=="0"){
      z=temperatureTimeSeries()
    }
    if(z=="-10%"){
      z=temperatureTimeSeries()
      z<-z-(z/10)
    }
    if(z=="-5%"){
      z=temperatureTimeSeries()
      z<-z-(z/20)
    }
    if(z=="+5%"){
      z=temperatureTimeSeries()
      z<-z+(z/20)
    }
    if(z=="+10%"){
      z=temperatureTimeSeries()
      z<-z+(z/10)
    }
    
    
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
    Sales<-union(data$Sales,newsales)
    Year<-union(data$Year,c(2013))
    
    df<-data.frame(X=Year,Y=Sales)
    return (df)
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
  
  output$View <- renderGvis({
    gvisScatterChart(organicPrediction(input$Farmland,input$Rain,input$Temperature,input$Income),options=list(legend='none', width=1000,height=500))
  })
  
})