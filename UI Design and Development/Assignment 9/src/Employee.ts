export class Employee {
    private static employeeCount = 0;
    public readonly employeeId: string;
    public fullName: string;
    public gender: string;
    public email: string;
    private password: string;
    public contactNumber: string;

    constructor(fullName: string, gender: string, email: string, password: string, contactNumber: string) {
        Employee.employeeCount++;
        this.employeeId = "EMP" + Employee.employeeCount; 
        this.fullName = this.validateName(fullName);
        this.gender = gender;
        this.email = this.validateEmail(email);
        this.password = this.validatePassword(password);
        this.contactNumber = this.validateContact(contactNumber);
    }

    private validateName(name: string): string {
        if (name.length < 2 || /\d/.test(name)) throw new Error("Invalid Name.");
        return name;
    }

    private validateEmail(email: string): string {
        const regex = /^\S+@\S+\.\S+$/;
        if (!regex.test(email)) throw new Error("Invalid Email Format.");
        return email;
    }

    private validatePassword(password: string): string {
        if (password.length < 8) throw new Error("Weak Password.");
        if (/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(password)) {
            console.log("Password Strength: Strong");
            return password;
        }
        console.log("Password Strength: Normal");
        return password;
    }

    private validateContact(contact: string): string {
        if (!/^\d{8,}$/.test(contact)) throw new Error("Invalid Contact Number.");
        return contact;
    }
}