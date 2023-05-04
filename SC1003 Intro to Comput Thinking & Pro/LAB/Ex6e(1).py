#Exercise 7e
from sense_hat import SenseHat 
from time import sleep 
from time import time

sense = SenseHat() 
sense.clear() 


def move_marble(pitch,roll,x,y): 
  new_x = x 
  new_y = y 
  if 1 < pitch < 179 and x != 0: 
     new_x -= 1 
  elif 359 > pitch > 179 and x != 7 : 
     new_x += 1 
  if 1 < roll < 179 and y != 7: 
     new_y += 1 
  elif 359 > roll > 179 and y != 0 : 
     new_y -= 1

  new_x,new_y = check_wall(x,y,new_x,new_y) 
  return new_x,new_y 
  
  
def check_wall(x,y,new_x,new_y): 
  if board[new_y][new_x] != r: 
     return new_x, new_y 
  elif board[new_y][x] != r: 
     return x, new_y 
  elif board[y][new_x] != r: 
     return new_x, y 
  return x,y   
  
r=(200,0,0)
b=(0,0,0)
w=(255,255,255)
g=(0,200,0)


board = [[r,r,r,r,r,r,r,r], 
         [r,b,b,b,b,b,b,r],
         [r,b,r,b,g,r,b,r],
         [r,b,r,b,r,r,b,r],
         [r,b,b,b,b,b,b,r],
         [r,b,b,r,r,r,b,r],
         [r,b,b,r,b,b,b,r], 
         [r,r,r,r,r,r,r,r] ]
         
board1 = [[r,r,r,r,r,r,r,r], 
         [r,b,b,b,b,b,b,r],
         [r,b,b,r,b,b,b,r],
         [r,r,r,r,b,b,b,r],
         [r,b,b,b,b,b,b,r],
         [r,b,g,b,r,r,r,r],
         [r,b,b,b,b,b,b,r], 
         [r,r,r,r,r,r,r,r] ]



game_over = False
x=6
y=6
while not game_over:
    pitch = sense.get_orientation()['pitch'] 
    roll = sense.get_orientation()['roll'] 
    x,y = move_marble(pitch,roll,x,y) 
    if board[y][x] == g:
        game_over = True
    else: 
        board[y][x] = w
        sense.set_pixels(sum(board,[])) 
        sleep(0.05) 
        board[y][x] = b

sense.show_message('yay!')
