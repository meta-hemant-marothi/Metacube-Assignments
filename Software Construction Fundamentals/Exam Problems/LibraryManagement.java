import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LibraryManagement {
    
}

class Book{
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author, boolean isAvailable){
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Book))return false;
        if(o == this)return true;
        Book book = (Book)o;
        return book.getTitle().equals(this.title);
    }

    @Override
    public int hashCode(){
        return Objects.hash(title);
    } 
}

class Member{
    private String name;
    private int memberId;
    private List<Book> listOfBorrowedBooks;

    public Member(String name, int memberId){
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public List<Book> getListOfBorrowedBooks() {
        return listOfBorrowedBooks;
    }

    public boolean borrowBook(Book book){
        if(book.isIsAvailable()){
            book.setIsAvailable(false);
            listOfBorrowedBooks.add(book);
            return true;
        }else{
            return false;
        }
    }

    public boolean returnBook(Book book){
        if(listOfBorrowedBooks.contains(book)){
            book.setIsAvailable(true);
            listOfBorrowedBooks.remove(book);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Book))return false;
        if(o == this)return true;
        Member member = (Member)o;
        return member.getMemberId() == this.memberId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(memberId);
    } 
}

class Library {
    private Scanner scanner = new Scanner(System.in);
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author, true));
    }

    public boolean removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if(book != null){
            books.remove(book);
            return true;
        }
        return false;
    }

    public void addMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member id: ");
        int memberId = scanner.nextInt();
        Member member = new Member(name, memberId);
        for(Member mem : members){
            if(mem.equals(member)){
                System.out.println("Member already present");
                return;
            }
        }
        members.add(member);
    }

    public boolean removeMember(Member member) {
        return members.remove(member);
    }

    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getMemberId() == id) {
                return member;
            }
        }
        return null;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
