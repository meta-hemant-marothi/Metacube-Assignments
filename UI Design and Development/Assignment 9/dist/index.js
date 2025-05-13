// import { Employee } from "./Employee";
// import { Vehicle } from "./Vehicle";

class Employee {
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


// class Pass {
//     static pricing = {
//         Cycle: { daily: 5, monthly: 100, yearly: 500 },
//         Motorcycle: { daily: 10, monthly: 200, yearly: 1000 },
//         FourWheeler: { daily: 20, monthly: 500, yearly: 3500 }
//     };
//     employeeId;
//     vehicleType;
//     passType;
//     price;
//     constructor(employeeId, vehicleType, passType) {
//         this.employeeId = employeeId;
//         this.vehicleType = this.validateVehicleType(vehicleType);
//         this.passType = this.validatePassType(passType);
//         this.price = Pass.pricing[vehicleType][passType];
//     }
//     validateVehicleType(type) {
//         const validTypes = ["Cycle", "Motorcycle", "FourWheeler"];
//         if (!validTypes.includes(type)) {
//             throw new Error("Invalid Vehicle Type.");
//         }
//         return type;
//     }
//     validatePassType(type) {
//         const validPassTypes = ["daily", "monthly", "yearly"];
//         if (!validPassTypes.includes(type)) {
//             throw new Error("Invalid Pass Type.");
//         }
//         return type;
//     }
//     getPassDetails() {
//         return `Pass for Employee ID: ${this.employeeId}, Vehicle Type: ${this.vehicleType}, Duration: ${this.passType}, Price: INR ${this.price}`;
//     }
// }

class Vehicle {
    vehicleName;
    vehicleType;
    vehicleNumber;
    employeeId;
    identification;
    constructor(vehicleName, vehicleType, vehicleNumber, employeeId, identification) {
        this.vehicleName = this.validateVehicleName(vehicleName);
        this.vehicleType = this.validateVehicleType(vehicleType);
        this.vehicleNumber = this.validateVehicleNumber(vehicleNumber);
        this.employeeId = employeeId;
        this.identification = identification;
    }
    validateVehicleName(name) {
        if (name.length < 2)
            throw new Error("Invalid Vehicle Name.");
        return name;
    }
    validateVehicleType(type) {
        const validTypes = ["Cycle", "Motorcycle", "FourWheeler"];
        if (!validTypes.includes(type))
            throw new Error("Invalid Vehicle Type.");
        return type;
    }
    validateVehicleNumber(number) {
        if (!/^[A-Z0-9]+$/.test(number) || number.length < 6)
            throw new Error("Invalid Vehicle Number.");
        return number;
    }
}


let empData = null;
let vehData = null;
const empForm = document.getElementById("form-employee");
const empSteps = Array.from(empForm.querySelectorAll(".step-field"));
let empIdx = 0;
function showEmpStep(index) {
    console.log(index,"is a index");
    empSteps.forEach((step) => step.classList.remove("active"));
    empSteps[index].classList.add("active");
}
function registerEmployee() {
    try {
        const fullName = document.getElementById("emp-name").value;
        const gender = document.querySelector('input[name="gender"]:checked')?.value || "";
        const email = document.getElementById("emp-email").value;
        const password = document.getElementById("emp-pass").value;
        const confirmPassword = document.getElementById("emp-pass2").value;
        const contactNumber = document.getElementById("emp-contact").value;
        if (password !== confirmPassword)
            throw new Error("Passwords do not match.");
        empData = new Employee(fullName, gender, email, password, contactNumber);
        alert(`Employee Registered! ID: ${empData.employeeId}`);
        document.getElementById("veh-empid").value = empData.employeeId;
        document.getElementById("btn-vehicle")?.classList.remove("disabled");
        empForm.remove();
    }
    catch (error) {
        alert((error instanceof Error) ? error.message : "An unexpected error occurred.");
    }
}
empForm.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
        e.preventDefault();
        empIdx++;
        if (empIdx < empSteps.length)
            showEmpStep(empIdx);
        else registerEmployee();
    }
});



































































const vehForm = document.getElementById("form-vehicle");
const vehSteps = Array.from(vehForm.querySelectorAll(".step-field"));
let vehIdx = 0;
function showVehStep(index) {
    vehSteps.forEach((step) => step.classList.remove("active"));
    vehSteps[index].classList.add("active");
}
function registerVehicle() {
    try {
        if (!empData)
            throw new Error("Employee must be registered first.");
        vehData = new Vehicle(document.getElementById("veh-name").value, document.getElementById("veh-type").value, document.getElementById("veh-number").value, empData.employeeId, document.getElementById("veh-ident").value);
        alert(`Vehicle Registered for Employee ID: ${vehData.employeeId}`);
        document.getElementById("btn-pricing")?.classList.remove("disabled");
        vehForm.remove();
    }
    catch (error) {
        alert((error instanceof Error) ? error.message : "An unexpected error occurred.");
    }
}
vehForm.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
        e.preventDefault();
        vehIdx++;
        if (vehIdx < vehSteps.length)
            showVehStep(vehIdx);
        else
            registerVehicle();
    }
});
