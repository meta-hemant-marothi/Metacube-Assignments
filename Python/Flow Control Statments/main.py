def calculate_grade(maths, physics, chemistry):
    # Check if student passed all subjects
    if maths < 35 or physics < 35 or chemistry < 35:
        return "Result: Failed"

    # Calculate average marks
    average = (maths + physics + chemistry) / 3

    # Determine grade
    if average <= 59:
        grade = "C"
    elif average <= 69:
        grade = "B"
    else:
        grade = "A"

    return f"Result: Passed\nAverage Marks: {average:.2f}\nGrade: {grade}"


# Example usage
maths = int(input("Enter Maths marks: "))
physics = int(input("Enter Physics marks: "))
chemistry = int(input("Enter Chemistry marks: "))

result = calculate_grade(maths, physics, chemistry)
print(result)

