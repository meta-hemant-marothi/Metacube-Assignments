import { Employee } from "./Employee";
import { Vehicle } from "./Vehicle";
import { Pass } from "./Pass";

let empData: Employee | null = null;
let vehData: Vehicle | null = null;

const empForm = document.getElementById("form-employee") as HTMLFormElement;
const empSteps = Array.from(empForm.querySelectorAll(".step-field"));
let empIdx = 0;

function showEmpStep(index: number) {
    empSteps.forEach((step) => step.classList.remove("active"));
    empSteps[index].classList.add("active");
}

function registerEmployee(): void {
    try {
        const fullName = (document.getElementById("emp-name") as HTMLInputElement).value;
        const gender = (document.querySelector('input[name="gender"]:checked') as HTMLInputElement)?.value || "";
        const email = (document.getElementById("emp-email") as HTMLInputElement).value;
        const password = (document.getElementById("emp-pass") as HTMLInputElement).value;
        const confirmPassword = (document.getElementById("emp-pass2") as HTMLInputElement).value;
        const contactNumber = (document.getElementById("emp-contact") as HTMLInputElement).value;

        if (password !== confirmPassword) throw new Error("Passwords do not match.");
        empData = new Employee(fullName, gender, email, password, contactNumber);
        alert(`Employee Registered! ID: ${empData.employeeId}`);
        (document.getElementById("veh-empid") as HTMLInputElement).value = empData.employeeId;
        document.getElementById("btn-vehicle")?.classList.remove("disabled");
        empForm.remove();
    } catch (error: unknown) {
        alert((error instanceof Error) ? error.message : "An unexpected error occurred.");
    }
}

empForm.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
        e.preventDefault();
        empIdx++;
        if (empIdx < empSteps.length) showEmpStep(empIdx);
        registerEmployee();
    }
});

const vehForm = document.getElementById("form-vehicle") as HTMLFormElement;
const vehSteps = Array.from(vehForm.querySelectorAll(".step-field"));
let vehIdx = 0;

function showVehStep(index: number): void {
    vehSteps.forEach((step) => step.classList.remove("active"));
    vehSteps[index].classList.add("active");
}

function registerVehicle(): void {
    try {
        if (!empData) throw new Error("Employee must be registered first.");
        vehData = new Vehicle(
            (document.getElementById("veh-name") as HTMLInputElement).value,
            (document.getElementById("veh-type") as HTMLSelectElement).value,
            (document.getElementById("veh-number") as HTMLInputElement).value,
            empData.employeeId,
            (document.getElementById("veh-ident") as HTMLTextAreaElement).value
        );
        alert(`Vehicle Registered for Employee ID: ${vehData.employeeId}`);
        document.getElementById("btn-pricing")?.classList.remove("disabled");
        vehForm.remove();
    } catch (error: unknown) {
        alert((error instanceof Error) ? error.message : "An unexpected error occurred.");
    }
}

vehForm.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
        e.preventDefault();
        vehIdx++;
        if (vehIdx < vehSteps.length) showVehStep(vehIdx);
        else registerVehicle();
    }
});