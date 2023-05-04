#############Q3
def q3():
    for num_c in range(1,21):
        if num_c % 15 == 0:
            print("FizzBuzz")
        elif num_c % 3 == 0:
            print("Fizz")
        elif num_c % 5 == 0:
            print("Buzz")
        else:
            print(num_c)
        



#############Q4
def q4():
    patt = int(input("Please enter pattern width:"))
    for i in range(1,patt+1):
        for j in range(i):
            print("*",end="")
        print("\n")
    for i in range(patt-1,0,-1):
        for j in range(i):
            print("*",end="")
        print("\n")
   



