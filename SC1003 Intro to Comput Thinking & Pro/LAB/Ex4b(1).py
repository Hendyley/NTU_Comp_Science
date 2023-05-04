#Practical Exercise 5b
from sense_hat import SenseHat
from time import sleep
from random import choice

sense = SenseHat()
r=(255,0,0)
g=(0,255,0)
b=(0,0,255)
w=(200,200,200)
y=(255,255,0)

c=choice([y,r,g,w])  #choose a color

image_pixels =	[b, b, b, b, b, b, b, b,
                 b, b, b, c, b, b, b, b,
                 b, b, c, c, c, b, b, b,
                 b, c, b, c, b, c, b, b,
                 b, b, b, c, b, b, b, b,
                 b, b, b, c, b, b, b, b,
                 b, b, b, c, b, b, b, b,
                 b, b, b, b, b, b, b, b]
                 
angle = 0  #initial angle

sense.set_rotation(angle)
sense.set_pixels(image_pixels)

while True:
    sleep(1)
    #---------- change color--------------
    new_color = choice([y,r,g,w])
    while new_color == c:
        new_color = choice([y,r,g,w])
    
    for i, item in enumerate(image_pixels):
        if item == c:
            image_pixels[i] = new_color
    c = new_color       

    #---------- change angle ----------            
    new_angle = choice([0, 90, 180, 270])
    while new_angle == angle:
        new_angle = choice([0, 90, 180, 270])
    angle=new_angle    
    
    #---------- display ---------------
    sense.set_rotation(new_angle)
    sense.set_pixels(image_pixels)
