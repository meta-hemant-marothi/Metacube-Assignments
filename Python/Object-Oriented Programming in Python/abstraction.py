from abc import ABC, abstractmethod

# Abstract base class
class TouchScreenLaptop(ABC):

    @abstractmethod
    def scroll(self):
        pass

    @abstractmethod
    def click(self):
        pass


# Abstract subclass hp
class HP(TouchScreenLaptop):

    def scroll(self):
        print("HP laptop scrolling...")

    @abstractmethod
    def click(self):
        pass


# Abstract subclass dell
class Dell(TouchScreenLaptop):

    def scroll(self):
        print("Dell laptop scrolling...")

    @abstractmethod
    def click(self):
        pass


# Concrete subclass hpnotebook
class HPNotebook(HP):

    def click(self):
        print("HP Notebook clicking...")


# Concrete subclass dellnotebook
class DellNotebook(Dell):

    def click(self):
        print("Dell Notebook clicking...")



hp_note = HPNotebook()
hp_note.scroll()
hp_note.click()

dell_note = DellNotebook()
dell_note.scroll()
dell_note.click()

