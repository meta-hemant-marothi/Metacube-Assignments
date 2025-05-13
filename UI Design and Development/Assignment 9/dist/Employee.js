export class Employee {
    static employeeCount = 0;
    employeeId;
    fullName;
    gender;
    email;
    password;
    contactNumber;
    constructor(fullName, gender, email, password, contactNumber) {
        Employee.employeeCount++;
        this.employeeId = "EMP" + Employee.employeeCount;
        this.fullName = this.validateName(fullName);
        this.gender = gender;
        this.email = this.validateEmail(email);
        this.password = this.validatePassword(password);
        this.contactNumber = this.validateContact(contactNumber);
    }
    validateName(name) {
        if (name.length < 2 || /\d/.test(name))
            throw new Error("Invalid Name.");
        return name;
    }
    validateEmail(email) {
        const regex = /^\S+@\S+\.\S+$/;
        if (!regex.test(email))
            throw new Error("Invalid Email Format.");
        return email;
    }
    validatePassword(password) {
        if (password.length < 8)
            throw new Error("Weak Password.");
        if (/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(password)) {
            console.log("Password Strength: Strong");
            return password;
        }
        console.log("Password Strength: Normal");
        return password;
    }
    validateContact(contact) {
        if (!/^\d{8,}$/.test(contact))
            throw new Error("Invalid Contact Number.");
        return contact;
    }
}
