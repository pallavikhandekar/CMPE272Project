library(shiny)

shinyUI(pageWithSidebar(
  
  # Application title
  headerPanel("Organic Food Sales Prediction"),
  
  sidebarPanel( 
   selectInput("farmLand", "Farmland",list("test")),
   selectInput("rain", "Rain",list("test")),
   selectInput("income", "Income",list("test")),
   selectInput("temperature", "Temperature",list("test")),
   br(),
   submitButton("Predict")
  ),
  
  mainPanel(
    htmlOutput('View')
    )
))