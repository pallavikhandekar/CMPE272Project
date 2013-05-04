library(shiny)

shinyUI(pageWithSidebar(
  
  # Application title
  headerPanel("Organic Food Sales Prediction"),
  
  sidebarPanel( 
   selectInput("Farmland", "Farmland",list("-10%","-5%","0","+5%","+10%"),selected="0"),
   selectInput("Rain", "Rain",list("-10%","-5%","0","+5%","+10%"),selected="0"),
   selectInput("Income", "Income",list("-10%","-5%","0","+5%","+10%"),selected="0"),
   selectInput("Temperature", "Temperature",list("-10%","-5%","0","+5%","+10%"),selected="0"),
   br()
  ),
  
    
  mainPanel(
    htmlOutput("View")
    )
))