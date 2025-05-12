"use strict";
var _a, _b, _c;
// Custom alert wrapper for easy future updates
const showAlert = (message) => alert(message);
// Steps and tab management
const steps = Array.from(document.querySelectorAll(".step"));
const tabs = document.querySelectorAll(".tabs li");
let currentStep = 0;
const showStep = (idx) => {
    steps.forEach((s, i) => s.classList.toggle("hidden", i !== idx));
    updateTabs();
};
// Validation regex patterns
const patterns = {
    name: /^[A-Za-z]{2,}(?: [A-Za-z]+)*$/,
    email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[A-Za-z]{2,}$/,
    password: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\w\s]).{8,}$/,
    contact: /^\d{10,}$/,
    vehicleNum: /^[A-Z]{2}\d{2}-[A-Z]{2}-\d{4}$/
};
console.log(patterns.email.test("test@example.com")); // true
console.log(patterns.email.test("invalid-email")); // false
// Validate and advance employee
const validateAndNext = (step) => {
    const ids = ["fullName", "gender", "email", "password", "confirmPassword", "contact"];
    const fid = ids[step - 1];
    let valid = false;
    if (fid === "gender") {
        valid = !!document.querySelector('input[name="gender"]:checked');
        if (!valid)
            showAlert("Select your gender.");
    }
    else if (fid === "confirmPassword") {
        const pw = document.getElementById("password").value;
        const cpw = document.getElementById("confirmPassword").value;
        if (cpw !== pw)
            showAlert("Passwords must match.");
        else
            valid = patterns.password.test(cpw);
    }
    else {
        const inp = document.getElementById(fid);
        const key = fid === "fullName" ? "name" : fid;
        if (!patterns[key]) {
            console.error(`Validation pattern for ${key} is missing.`);
            return;
        }
        valid = patterns[key].test(inp.value.trim());
        if (!valid)
            showAlert(`Invalid ${fid}.`);
    }
    if (valid) {
        if (step === 1) {
            document.getElementById("greeting").textContent =
                `Hi ${document.getElementById("fullName").value.trim()}! Can I know your gender?`;
        }
        currentStep = step;
        showStep(currentStep);
    }
};
const updateTabs = () => {
    var _a;
    tabs.forEach(t => t.classList.remove("active"));
    const id = steps[currentStep].id;
    const section = id.startsWith("step-v") ? "vehicle" :
        id.startsWith("step-pass") ? "pass" : "employee";
    (_a = document.querySelector(`.tabs li[data-step="${section}"]`)) === null || _a === void 0 ? void 0 : _a.classList.add("active");
};
showStep(currentStep);
// Employee navigation
for (let i = 1; i <= 6; i++) {
    (_a = document.getElementById(`next-${i}`)) === null || _a === void 0 ? void 0 : _a.addEventListener("click", () => validateAndNext(i));
    (_b = document.getElementById(`prev-${i}`)) === null || _b === void 0 ? void 0 : _b.addEventListener("click", () => {
        currentStep = i - 1;
        showStep(currentStep);
    });
}
// Submit employee and generate ID
const validateAndSubmitEmployee = () => {
    var _a;
    const contactVal = document.getElementById("contact").value.trim();
    if (!patterns.contact.test(contactVal)) {
        showAlert("Contact number must be at least 10 digits.");
        return;
    }
    const id = `EMP-${Math.floor(Math.random() * 90000 + 10000)}`;
    document.getElementById("regId").textContent = id;
    document.getElementById("employeeId").value = id;
    const empData = {
        fullName: document.getElementById("fullName").value.trim(),
        gender: document.querySelector('input[name="gender"]:checked').value,
        email: document.getElementById("email").value.trim(),
        contact: contactVal,
        regId: id
    };
    localStorage.setItem("employeeData", JSON.stringify(empData));
    currentStep = steps.findIndex(s => s.id === "employee-complete");
    showStep(currentStep);
    (_a = document.getElementById("next-empComplete")) === null || _a === void 0 ? void 0 : _a.addEventListener("click", () => {
        currentStep++;
        showStep(currentStep);
    });
};
(_c = document.getElementById("submit-employee")) === null || _c === void 0 ? void 0 : _c.addEventListener("click", validateAndSubmitEmployee);
