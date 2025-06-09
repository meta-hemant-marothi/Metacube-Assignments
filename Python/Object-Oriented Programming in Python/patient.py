class Patient:
    def __init__(self, id, name, ssn):
        self.__id = id
        self.__name = name
        self.__ssn = ssn

    # Getter methods
    def get_id(self):
        return self.__id

    def get_name(self):
        return self.__name

    def get_ssn(self):
        return self.__ssn

    # Setter methods
    def set_id(self, id):
        self.__id = id

    def set_name(self, name):
        self.__name = name

    def set_ssn(self, ssn):
        self.__ssn = ssn



p = Patient(1, "John Doe", "123-45-6789")
print("Patient Name:", p.get_name())
p.set_name("Jane Doe")
print("Updated Patient Name:", p.get_name())

