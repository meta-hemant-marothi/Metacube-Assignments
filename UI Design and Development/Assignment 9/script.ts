// Custom alert wrapper for easy future updates
const showAlert = (message: string): void => alert(message);

// Steps and tab management
const steps: HTMLElement[] = Array.from(document.querySelectorAll(".step"));
const tabs: NodeListOf<HTMLLIElement> = document.querySelectorAll(".tabs li");

let currentStep: number = 0;

const showStep = (idx: number): void => {
  steps.forEach((s, i) => s.classList.toggle("hidden", i !== idx));
  updateTabs();
};

// Validation regex patterns
const patterns: Record<string, RegExp> = {
  name: /^[A-Za-z]{2,}(?: [A-Za-z]+)*$/,
  email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[A-Za-z]{2,}$/,
  password: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\w\s]).{8,}$/,
  contact: /^\d{10,}$/,
  vehicleNum: /^[A-Z]{2}\d{2}-[A-Z]{2}-\d{4}$/
};

console.log(patterns.email.test("test@example.com")); // true
console.log(patterns.email.test("invalid-email")); // false

// Validate and advance employee
const validateAndNext = (step: number): void => {
  const ids: string[] = ["fullName", "gender", "email", "password", "confirmPassword", "contact"];
  const fid: string = ids[step - 1];

  let valid: boolean = false;

  if (fid === "gender") {
    valid = !!document.querySelector('input[name="gender"]:checked');
    if (!valid) showAlert("Select your gender.");
  } else if (fid === "confirmPassword") {
    const pw: string = (document.getElementById("password") as HTMLInputElement).value;
    const cpw: string = (document.getElementById("confirmPassword") as HTMLInputElement).value;
    if (cpw !== pw) showAlert("Passwords must match.");
    else valid = patterns.password.test(cpw);
  } else {
    const inp: HTMLInputElement | null = document.getElementById(fid) as HTMLInputElement;
    const key: string = fid === "fullName" ? "name" : fid;
    if (!patterns[key]) {
      console.error(`Validation pattern for ${key} is missing.`);
      return;
    }
    valid = patterns[key].test(inp.value.trim());
    if (!valid) showAlert(`Invalid ${fid}.`);
  }

  if (valid) {
    if (step === 1) {
      (document.getElementById("greeting") as HTMLElement).textContent =
        `Hi ${(document.getElementById("fullName") as HTMLInputElement).value.trim()}! Can I know your gender?`;
    }
    currentStep = step;
    showStep(currentStep);
  }
};

const updateTabs = (): void => {
  tabs.forEach(t => t.classList.remove("active"));
  const id: string = steps[currentStep].id;
  const section: string = id.startsWith("step-v") ? "vehicle" :
    id.startsWith("step-pass") ? "pass" : "employee";

  document.querySelector(`.tabs li[data-step="${section}"]`)?.classList.add("active");
};

showStep(currentStep);

// Employee navigation
for (let i = 1; i <= 6; i++) {
  document.getElementById(`next-${i}`)?.addEventListener("click", () => validateAndNext(i));
  document.getElementById(`prev-${i}`)?.addEventListener("click", () => {
    currentStep = i - 1;
    showStep(currentStep);
  });
}

// Submit employee and generate ID
const validateAndSubmitEmployee = (): void => {
  const contactVal: string = (document.getElementById("contact") as HTMLInputElement).value.trim();
  if (!patterns.contact.test(contactVal)) {
    showAlert("Contact number must be at least 10 digits.");
    return;
  }

  const id: string = `EMP-${Math.floor(Math.random() * 90000 + 10000)}`;
  (document.getElementById("regId") as HTMLElement).textContent = id;
  (document.getElementById("employeeId") as HTMLInputElement).value = id;

  const empData = {
    fullName: (document.getElementById("fullName") as HTMLInputElement).value.trim(),
    gender: (document.querySelector('input[name="gender"]:checked') as HTMLInputElement).value,
    email: (document.getElementById("email") as HTMLInputElement).value.trim(),
    contact: contactVal,
    regId: id
  };

  localStorage.setItem("employeeData", JSON.stringify(empData));

  currentStep = steps.findIndex(s => s.id === "employee-complete");
  showStep(currentStep);

  document.getElementById("next-empComplete")?.addEventListener("click", () => {
    currentStep++;
    showStep(currentStep);
  });
};

document.getElementById("submit-employee")?.addEventListener("click", validateAndSubmitEmployee);