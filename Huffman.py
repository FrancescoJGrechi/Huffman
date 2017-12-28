# imports
import os 
import sys
import json
import matplotlib.pyplot as plt

#########################
#DEALING WITH FILE NAMES
#########################

# of the form X
sourceDirectory = argv[0]

# name of original text file
sourceFile = argv[1]

# name of intermediate directory, of form X
intermediateDirectory = "Codes" 

# process file names
javaSourceFile = ".\\" + sourceDirectory + "\\" + sourceFile + ".txt"
codeFile = ".\\" + intermediateDirectory + "\\" + sourceFile + "Code.json"
#outputFile = sourceFile + ".png" 


#########################
#CALLING THE JAVA PROGRAM
#########################

cmd = "java Mainer " + javaSourceFile + " " + codeFile
os.system(cmd)


#########################
#GENERATING GRAPHICS ####
#########################
data = json.load(open(codeFile))
plt.bar(data, histo)
plt.show()

 
