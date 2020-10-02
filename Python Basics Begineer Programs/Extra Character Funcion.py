def findExtraCharacter(string1,string2): 
    smaller = "" 
    larger = "" 
    
    # if the size of one string is bigger than the other
    if(len(string1) > len(string2)): 
        smaller = string2 
        larger = string1 
        
    else: 
        smaller = string1 
        larger = string2
        
    # ordering the strings by ASCII value
    # and saving to a new variable to see the difference
    smallCodeTotal = 0
    largeCodeTotal = 0
    i = 0
    
    #The function 'ord' returns the string in alphabetical order
    while(i < len(smaller)): 
        smallCodeTotal += ord(smaller[i])
        largeCodeTotal += ord(larger[i]) 
        i += 1
    
    # Adding the rest of the large string, since the while loop stops at the smaller size
    largeCodeTotal += ord(larger[i]) 
    
    # Saving the difference
    extra = largeCodeTotal - smallCodeTotal 
    
    # Turning the ASCII code to a character again
    return str(extra)
 
# Testing 
s1 = "abcd"
s2 = "cbdae"
extraChar = findExtraCharacter(s1, s2) 
print("Extra Character:", extraChar) 