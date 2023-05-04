#Practical Exercise 5a
from sense_hat import SenseHat
from time import sleep
from random import randint

sense = SenseHat()
r=(255,0,0)
g=(0,255,0)
b=(0,0,255)
y=(255,255,0)


while True:
 
    x1=randint(0,7)
    y1=randint(0,7)
    x2=randint(0,7)
    y2=randint(0,7)
    x3=randint(0,7)
    y3=randint(0,7)
    x4=randint(0,7)
    y4=randint(0,7)
    
    sense.set_pixel(x1,y1,r)
    sense.set_pixel(x2,y2,g)
    sense.set_pixel(x3,y3,b)
    sense.set_pixel(x4,y4,y)
    
    print(x1,y1,x2,y2,x3,y3,x4,y4)
    
    sleep(1)
    sense.clear()
