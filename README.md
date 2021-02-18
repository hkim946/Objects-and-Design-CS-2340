# GAHK-farm

The GAHK Farm is a javaFx GUI application that simulates a farming game. This game is developed through GT course CS2340.
To access the original repository, please visit this link https://github.gatech.edu/gvanwambeke3/GAHK-farm. 

![game screenshot2](/images/gahk_config.JPG?raw=true)
![game screenshot3](/images/gahk_home.JPG?raw=true)


## Setting up Environment (Intellij)

### Adding External Libraries
- Project Structure -> Add library -> select path to javafx_sdk/lib
- Project Structure -> Add library -> path to json-simple.jar 

### Setting up Runtime Configuration
- Mark GAHK-Farm directory as sources root
- Mark out sub-directory as out root
- Go to File -> Settings -> Path Variables
    - Add PATH_TO_FX variable and set it to javafx_sdk/lib
- Go to Run -> Edit Configurations
    - Choose application.Welcome for Main class
    - Set VM Options to : --module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.graphics,javafx.media,javafx.fxml
    
### Running Application
- Select proper runtime configuration and click on the green arrow

## Contributors
- Alyssa Behrend
- Hannah Kim
- Garrett VanWambeke
- Kyle Wengryn
