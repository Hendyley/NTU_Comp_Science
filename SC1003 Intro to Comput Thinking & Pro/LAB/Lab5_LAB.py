#from sense_hat import SenseHat
import Lab5_module as LM
#sense = SenseHat()
#sense.set_rotation(180)

r_int = LM.get_color("red")
g_int = LM.get_color("green")
b_int = LM.get_color("blue")
msg_color = (r_int, g_int, b_int)
print(msg_color)
#sense.show_message("I got it!", text_colour=msg_color)
