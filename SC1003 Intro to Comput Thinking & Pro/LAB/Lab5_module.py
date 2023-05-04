

def isint(s):
    try: 
        int(s)
        return True
    except ValueError:
        return False
    

#--- function get_color() ---------------------------
def get_color(color):
    keep_looping = True
    no_of_try=1
    while keep_looping and no_of_try <=3:
      color_str=input("Enter the value of the " + color + " color for message (0 to 255):")
      if isint(color_str) == False:
        print("Invalid - use number only")
      else:
        if (int(color_str) > 255 or int(color_str) < 0):
            print("Invalid - value has to be between 0 and 255")
        else:
            keep_looping = False
            return int(color_str)
      no_of_try += 1
    else:
      return 0
#---------------------------------------------------
