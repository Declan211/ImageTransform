# ImageTransform
This program now supports a Graphical User Interface in which to use the image processor. 
The GUI was implemented in the ImageGUIController and the GraphicalView classes, which implement the IMageController and IGUIView interfaces respectively. 
The GraphicalView class sets the layout of the board; the buttons are set to execute commands on the given model (brighten, blur, etc.) and are placed in the top panel. 
The panel below consists of the image being manipulated and the histogram representing the frequency of the image’s pixel RGB values. 
The Histo class takes in a model and creates the model’s histogram. 
To launch the program, run the Main class. When the GUI pops up, select the Open File button, and choose the file you wish to manipulate. Click on the buttons to transform the image, and when done, hit the Save File button and save it to the desired location. 
