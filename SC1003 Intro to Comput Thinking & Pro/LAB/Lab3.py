#Practical Exercise 2b
#from sense_hat import SenseHat
#sense = SenseHat()
#sense.set_rotation(180)
#sense.show_message("This is fun!")



#####
qual = 0
i = 0
#####
def isint(s):
    try: 
        int(s)
        return True
    except ValueError:
        return False
    
def isfloat(s):
    try: 
        float(s)
        return True
    except ValueError:
        return False
    
def colorcheck(x):
    qual = 1
    if isint(x) == False:
        print("Invalid - use number only")
        qual = 0
    else:
        if (int(x) > 255 or int(x) < 0):
            print("Invalid - value has to be between 0 and 255")
            qual = 0
    return qual

def speedcheck(x):
    qual = 1
    if isfloat(x) == False:
        print("Invalid - use int or float")
        qual = 0
    else:
        if (float(x) > 1 or float(x) < 0.05):
            print("Invalid - value has to be between 0 and 255")
            qual = 0
    return qual

def bye(x):
    if x == 2 and qual == 0:
        print("You have exceeded the number of trials allowed. Bye!")
        exit()



while True:
    qual = 0
    i = 0
    while qual == 0 and i < 3:    
        red_str=input("Enter the value of the red color for message:")
        qual = colorcheck(red_str)
        bye(i)
        bye(i)
        i += 1
        

    qual = 0
    i = 0
    while qual == 0 and i < 3:
        green_str=input("Enter the value of the green color for message:")
        qual = colorcheck(green_str)
        bye(i)
        i += 1
        

    qual = 0
    i = 0
    while qual == 0 and i < 3:
        blue_str=input("Enter the value of the blue color for message:")
        qual = colorcheck(blue_str)
        bye(i)
        i += 1
        



    msg_r_int= int(red_str)
    msg_g_int= int(green_str)
    msg_b_int= int(blue_str)
    msg_color= (msg_r_int,msg_g_int,msg_b_int)
    print(msg_color)

    qual = 0
    i = 0
    while qual == 0 and i < 3:        
        red_str=input("Enter the value of the red color for background:")
        qual = colorcheck(red_str)
        bye(i)
        i += 1
        

    qual = 0
    i = 0
    while qual == 0 and i < 3:
        green_str=input("Enter the value of the green color for background:")
        qual = colorcheck(green_str)
        bye(i)
        i += 1
        

    qual = 0
    i = 0
    while qual == 0 and i < 3:
        blue_str=input("Enter the value of the blue color for background:")
        qual = colorcheck(blue_str)
        bye(i)
        i += 1
        


    bg_r_int= int(red_str)
    bg_g_int= int(green_str)
    bg_b_int= int(blue_str)
    bg_color= (bg_r_int,bg_g_int,bg_b_int)
    print(bg_color)

    qual = 0
    i = 0
    while qual == 0 and i < 3:
        
        if(bg_color == msg_color):
            again = input("Message color and Background color are the same, do you want to reassign (Y/N)?")
            if again.lower().find("yes") > -1:
                qual = 0
            elif again.lower().find("no") > -1:
                qual = 1
            else:
                print("Only yes or no answer!!!!!")
            bye(i)
            i+=1
        else:
            qual = 1
            
    if qual == 1:
        break
    else:
        continue
    
                
        
 
########################
qual = 0
i = 0
while qual == 0 and i <= 3:
    bye(i)
    speed_str=input("Enter the value of the display speed:")
    qual = speedcheck(speed_str)
    i += 1
        

speed=float(speed_str)
print(speed)

#sense.show_message("I got it!", text_colour=msg_color, \                   
#                                back_colour=bg_color, \
#                                scroll_speed=speed)
