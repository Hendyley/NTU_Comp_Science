from sense_hat import SenseHat
import time
import numpy as np
import random

s = SenseHat()
s.low_light = True

green = (0, 255, 0)
yellow = (255, 255, 0)
blue = (0, 0, 255)
red = (255, 0, 0)
white = (255,255,255)
nothing = (0,0,0)
pink = (255,105, 180)

y = (255, 255, 0)
b = (0, 0, 0)

def choosen(x1,x2):
  
  y = x1
  
  if x2 == "up":
    ups =	\
    [b, b, b, b, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, y, y, y, b, b, b,
    b, y, b, y, b, y, b, b,
    b, b, b, y, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, b, y, b, b, b, b, 
    b, b, b, b, b, b, b, b]
    return ups
  elif x2 == "left":
    lefts =	\
    [b, b, b, b, b, b, b, b,
    b, b, b, b, y, b, b, b,
    b, b, b, b, b, y, b, b,
    b, y, y, y, y, y, y, b,
    b, b, b, b, b, y, b, b,
    b, b, b, b, y, b, b, b,
    b, b, b, b, b, b, b, b, 
    b, b, b, b, b, b, b, b]
    return lefts
  elif x2 == "down":  
    downs =	\
    [b, b, b, b, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, y, b, y, b, y, b, b,
    b, b, y, y, y, b, b, b,
    b, b, b, y, b, b, b, b, 
    b, b, b, b, b, b, b, b]
    return downs
  elif x2 == "right":  
    rights =	\
    [b, b, b, b, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, y, b, b, b, b, b,
    b, y, y, y, y, y, y, b,
    b, b, y, b, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, b, b, b, b, b, b, 
    b, b, b, b, b, b, b, b]
    return rights
  else:
    uks =	\
    [b, b, b, b, b, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, y, b, y, b, b, b,
    b, b, b, b, y, b, b, b,
    b, b, b, y, b, b, b, b,
    b, b, b, b, b, b, b, b,
    b, b, b, y, b, b, b, b, 
    b, b, b, b, b, b, b, b]
    return uks

colors = [green,yellow,blue,red,white,pink]
direction = ["left","up","right","down"]
i = 0
#while True:
# arrow_color = random.choice(colors)
#  arrow_dir = direction[i]
#  if i == 3:
#    i=0
#  else:
#    i+=1
#  s.set_pixels(choosen(arrow_color,arrow_dir))
#  time.sleep(1)
  
acceleration = s.get_accelerometer_raw()
x_ = round(acceleration["x"],0) 
y_ = round(acceleration["y"],0)
z_ = round(acceleration["z"],0)


print(x_)
print(y_)
print(z_)

# x_ == -0.0 y_ == 0.0 z_ == 1.0 up
# x_ == 0.0 y_ == 0.0 z_ == 1.0 right
# x_ == -0.0 y_ == -0.0 z_ == 1.0 left
# x_ == 0.0 y_ == 0.0 z_ == 1.0 down