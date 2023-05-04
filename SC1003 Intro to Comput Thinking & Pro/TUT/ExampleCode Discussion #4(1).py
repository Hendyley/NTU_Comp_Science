####################################################################################
# the data structure to serve as a database to use a dictionary
# the key is a tuple (group_name, ID). NOTICE: the key has to be immutable and unique
# the value is the grade

#grades = {
#    ('FS1', 1) : 45,
#    ('FS1', 2) : 75,

#    ('FS2', 1) : 75,
#    ('FS2', 2) : 40, 
#}

def read_data():
    groupName = input("Please input the group name: ")
    sID = int(input("Please input the student id: "))
    score = int(input("Please input the score: "))
    return {('group'):groupName,
            ('ID'):sID,
            ('score'):score
            }

def insertRecord(dataBase, group, id, score):
    dataKey = (group, id)
    dataBase[dataKey] = score

def query(dataBase, group, id):
    try:
        key = (group, id)
        return dataBase[key]

    except KeyError:
        return None

def listGrades(dataBase, group):
    listOfScores = []
    for key, value in dataBase.items():
        if key[0] == group:
            listOfScores.append(value)
            
    return listOfScores

def maxGrade(dataBase, group):
    scores = listGrades(dataBase, group)

    return max(scores)

grades = { }    # the database is empty initially

data = read_data()
while data['score'] != -1: 
    insertRecord(grades, data['group'], data['ID'], data['score'])
    data = read_data()

print(grades)

print('FS5', 35, '=', query(grades,'FS5',35))
print('FS5',listGrades(grades,'FS5'))    
    
    
