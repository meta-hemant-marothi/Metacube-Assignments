primeFlag = True
n = int(input("Enter the number to check if it is prime or not: "))

for i in range(2, n):
    if n % i == 0:
        primeFlag = False

if primeFlag:
    print("Prime Number")
else:
    print("Not a Prime Number")
