################ Num 3
def num3():
    num_c = 1

    while num_c <= 20:
        if num_c % 15 == 0:
            print("FizzBuzz")
        elif num_c % 3 == 0:
            print("Fizz")
        elif num_c % 5 == 0:
            print("Buzz")
        else:
            print(num_c)
        num_c += 1



################# Num 4
def num4():
    num_b = int(input("Enter the number of boys: "))
    num_g = int(input("Enter the number of girls: "))
    total = num_b + num_g

    print("Boys: ",str( round(int(num_b) / total * 100)) + "%" )
    print("Girls: ", str( round(int(num_g) / total * 100)) + "%" )



print("function call!!!!")
