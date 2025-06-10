class InvalidPasswordException(Exception):
    """ Error is raised when the length of password is less than 8 characters
        Attributes:
            salary -- input salary which caused the error
            message -- explanation of the error
    """

    def __init__(self, password):
        self.password = password
        self.message = "The length of password is less than 8 characters."
        super().__init__(self.message)


try:
    password = input("Enter your password: ")
    if len(password) < 8:
        raise InvalidPasswordException(password)
    else:
        print("Password accepted successfully.")

except InvalidPasswordException as e:
    print(e)
