from sense_hat import SenseHat
import time

# This function checks the pitch value and the x coordinate  
# to determine whether to move the marble in the x-direction.
# Similarly, it checks the roll value and y coordinate to
# determine whether to move the marble in the y-direction.
def E6A():
  while True:
    pitch = sense.get_orientation()['pitch'] 
    roll = sense.get_orientation()['roll'] 
    print("pitch {0} roll {1}".format(round(pitch,0), round(roll,0)))
    time.sleep(0.05)
    
def E6B():
  board = [[b,b,b,b,b,b,b,b], 
           [b,b,b,b,b,b,b,b],
           [b,b,b,b,b,b,b,b],
           [b,b,b,b,b,b,b,b],
           [b,b,b,b,b,b,b,b],
           [b,b,b,b,b,b,b,b],
           [b,b,b,b,b,b,b,b], 
           [b,b,b,b,b,b,b,b] ]
           
  y=2			# y coordinate of marble
  x=2			# x coordinate of marble
  board[y][x]=w		# a white marble
  board_1D=sum(board,[])        # convert to 1-dimension list
  #print(board_1D)               # for code debugging
  sense.set_pixels(board_1D)    # display it
  
def E6C():
  while True:
    pitch = sense.get_orientation()['pitch'] 
    roll = sense.get_orientation()['roll'] 
    y=2			# y coordinate of marble
    x=2			# x coordinate of marble
    x,y = move_marble(pitch,roll,x,y)
    
    board = blankitww()
    board[y][x] = w
    sense.set_pixels(sum(board,[]))
    
    
    
    time.sleep(0.05)

def E6D():
  board = [[r,r,r,r,r,r,r,r], 
         [r,b,b,b,b,b,b,r],
         [r,b,b,b,b,b,b,r],
         [r,b,b,b,b,b,b,r],
         [r,b,b,b,b,b,b,r],
         [r,b,b,b,b,b,b,r],
         [r,b,b,b,b,b,b,r], 
         [r,r,r,r,r,r,r,r] ]
  



#blank board
def blankit():
  board = [[b,b,b,b,b,b,b,b], 
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b], 
         [b,b,b,b,b,b,b,b] ]
  return board
  
def blankitww():
  board = [[b,b,b,b,b,b,b,b], 
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b],
         [b,b,b,b,b,b,b,b], 
         [b,b,b,b,b,b,b,b] ]
  return board

#move marble
def move_marble(pitch,roll,x,y): 
  new_x = x    #assume no change to start with
  new_y = y    #assume no change to start with
  if (1 < pitch < 179 and x != 0): 
      new_x -= 1    # move left
  elif (359 > pitch > 179 and x != 7): 
      new_x += 1  # move right

  if (1 < roll < 179 and y != 7): 
      new_y += 1    # move up
  elif (359 > roll > 179 and y != 0): 
      new_y -= 1  # move down

  new_x,new_y = check_wall(x,y,new_x,new_y) 
  return new_x, new_y
  
def check_wall(x,y,new_x,new_y): 
  if board[new_y][new_x] != r: 
      return new_x, new_y 
  elif board[new_y][x] != r: 
      return x, new_y 
  elif board[y][new_x] != r:
      return new_x, y 
  else:
      return x,y

sense = SenseHat()

green = (0, 255, 0)
yellow = (255, 255, 0)
blue = (0, 0, 255)
r = (255, 0, 0)
w = (255,255,255)
b = (0,0,0)
pink = (255,105, 180)


board = [[b,b,b,b,b,b,b,b], 
       [b,b,b,b,b,b,b,b],
       [b,b,b,b,b,b,b,b],
       [b,b,b,b,b,b,b,b],
       [b,b,b,b,b,b,b,b],
       [b,b,b,b,b,b,b,b],
       [b,b,b,b,b,b,b,b], 
       [b,b,b,b,b,b,b,b] ]
         
#y=7			# y coordinate of marble
#x=7			# x coordinate of marble
#board[y][x]=w		# a white marble






E6C()
