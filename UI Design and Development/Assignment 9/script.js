var _a, _b, _c;
// Custom alert wrapper for easy future updates
var showAlert = function (message) { return alert(message); };
// Steps and tab management
var steps = Array.from(document.querySelectorAll(".step"));
var tabs = document.querySelectorAll(".tabs li");
var currentStep = 0;
var showStep = function (idx) {
    steps.forEach(function (s, i) { return s.classList.toggle("hidden", i !== idx); });
    updateTabs();
};
// Validation regex patterns
var patterns = {
    name: /^[A-Za-z]{2,}(?: [A-Za-z]+)*$/,
    email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[A-Za-z]{2,}$/,
    password: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\w\s]).{8,}$/,
    contact: /^\d{10,}$/,
    vehicleNum: /^[A-Z]{2}\d{2}-[A-Z]{2}-\d{4}$/
};
console.log(patterns.email.test("test@example.com")); // true
console.log(patterns.email.test("invalid-email")); // false
// Validate and advance employee
var validateAndNext = function (step) {
    var ids = ["fullName", "gender", "email", "password", "confirmPassword", "contact"];
    var fid = ids[step - 1];
    var valid = false;
    if (fid === "gender") {
        valid = !!document.querySelector('input[name="gender"]:checked');
        if (!valid)
            showAlert("Select your gender.");
    }
    else if (fid === "confirmPassword") {
        var pw = document.getElementById("password").value;
        var cpw = document.getElementById("confirmPassword").value;
        if (cpw !== pw)
            showAlert("Passwords must match.");
        else
            valid = patterns.password.test(cpw);
    }
    else {
        var inp = document.getElementById(fid);
        var key = fid === "fullName" ? "name" : fid;
        if (!patterns[key]) {
            console.error("Validation pattern for ".concat(key, " is missing."));
            return;
        }
        valid = patterns[key].test(inp.value.trim());
        if (!valid)
            showAlert("Invalid ".concat(fid, "."));
    }
    if (valid) {
        if (step === 1) {
            document.getElementById("greeting").textContent =
                "Hi ".concat(document.getElementById("fullName").value.trim(), "! Can I know your gender?");
        }
        currentStep = step;
        showStep(currentStep);
    }
};
var updateTabs = function () {
    var _a;
    tabs.forEach(function (t) { return t.classList.remove("active"); });
    var id = steps[currentStep].id;
    var section = id.startsWith("step-v") ? "vehicle" :
        id.startsWith("step-pass") ? "pass" : "employee";
    (_a = document.querySelector(".tabs li[data-step=\"".concat(section, "\"]"))) === null || _a === void 0 ? void 0 : _a.classList.add("active");
};
showStep(currentStep);
var _loop_1 = function (i) {
    (_a = document.getElementById("next-".concat(i))) === null || _a === void 0 ? void 0 : _a.addEventListener("click", function () { return validateAndNext(i); });
    (_b = document.getElementById("prev-".concat(i))) === null || _b === void 0 ? void 0 : _b.addEventListener("click", function () {
        currentStep = i - 1;
        showStep(currentStep);
    });
};
// Employee navigation
for (var i = 1; i <= 6; i++) {
    _loop_1(i);
}
// Submit employee and generate ID
var validateAndSubmitEmployee = function () {
    var _a;
    var contactVal = document.getElementById("contact").value.trim();
    if (!patterns.contact.test(contactVal)) {
        showAlert("Contact number must be at least 10 digits.");
        return;
    }
    var id = "EMP-".concat(Math.floor(Math.random() * 90000 + 10000));
    document.getElementById("regId").textContent = id;
    document.getElementById("employeeId").value = id;
    var empData = {
        fullName: document.getElementById("fullName").value.trim(),
        gender: document.querySelector('input[name="gender"]:checked').value,
        email: document.getElementById("email").value.trim(),
        contact: contactVal,
        regId: id
    };
    localStorage.setItem("employeeData", JSON.stringify(empData));
    currentStep = steps.findIndex(function (s) { return s.id === "employee-complete"; });
    showStep(currentStep);
    (_a = document.getElementById("next-empComplete")) === null || _a === void 0 ? void 0 : _a.addEventListener("click", function () {
        currentStep++;
        showStep(currentStep);
    });
};
(_c = document.getElementById("submit-employee")) === null || _c === void 0 ? void 0 : _c.addEventListener("click", validateAndSubmitEmployee);
