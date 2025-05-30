"use strict";
// Employee Registration
const empForm = document.getElementById('form-employee');
const empSteps = Array.from(empForm.querySelectorAll('.step-field'));
let empData = {};
let empIdx = 0;
function showEmpStep(i) {
    empSteps.forEach((s) => s.classList.remove('active'));
    empSteps[i].classList.add('active');
}
function validEmp(field) {
    const val = field.value.trim();
    switch (field.id) {
        case 'emp-name': return val.length >= 2 && !/\d/.test(val);
        case 'emp-email': return /^\S+@\S+\.\S+$/.test(val);
        case 'emp-pass': return /(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}/.test(val);
        case 'emp-pass2': return val === document.getElementById('emp-pass').value;
        case 'emp-contact': return /^\d{8,}$/.test(val);
        default: return true;
    }
}
function strength(pass) {
    if (pass.length < 8)
        return 'weak';
    if (/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*\W)/.test(pass))
        return 'strong';
    return 'normal';
}
empForm.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        const fld = empSteps[empIdx].querySelector('input,select,textarea');
        if (!validEmp(fld)) {
            fld.style.borderColor = 'red';
            return;
        }
        fld.style.borderColor = '';
        empData[fld.id] = fld.value.trim();
        if (fld.id === 'emp-name')
            document.getElementById('greet-name').innerText = empData[fld.id];
        if (fld.id === 'emp-pass') {
            fld.style.borderColor = strength(fld.value) === 'strong' ? 'green' :
                strength(fld.value) === 'normal' ? 'orange' : 'red';
        }
        empIdx++;
        if (empIdx < empSteps.length)
            showEmpStep(empIdx);
        else
            finishEmployee();
    }
});
function finishEmployee() {
    const id = 'EMP' + Date.now();
    alert('Registered. ID: ' + id);
    empData.id = id;
    document.getElementById('veh-empid').value = empData.id;
    document.getElementById('btn-vehicle').classList.remove('disabled');
    empForm.remove();
}
// Vehicle Registration
const vehForm = document.getElementById('form-vehicle');
const vehSteps = Array.from(vehForm.querySelectorAll('.step-field'));
let vehIdx = 0;
function showVehStep(i) {
    vehSteps.forEach((s) => s.classList.remove('active'));
    vehSteps[i].classList.add('active');
}
vehForm.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        const fld = vehSteps[vehIdx].querySelector('input,select');
        if (!fld.value.trim()) {
            fld.style.borderColor = 'red';
            return;
        }
        fld.style.borderColor = '';
        if (fld.id === 'veh-empid') {
            fld.value = empData.id;
        }
        vehIdx++;
        if (vehIdx < vehSteps.length)
            showVehStep(vehIdx);
        else
            finishVehicle();
    }
});
document.getElementById('btn-vehicle').addEventListener('click', (e) => {
    e.preventDefault();
    if (!e.target.classList.contains('disabled')) {
        document.getElementById('veh-empid').value = empData.id;
        showVehStep(0);
    }
});
function finishVehicle() {
    alert('Vehicle Registered');
    document.getElementById('btn-pricing').classList.remove('disabled');
    vehForm.remove();
}
// Pricing
const pricing = {
    Cycle: { daily: 5, monthly: 100, yearly: 500 },
    Motorcycle: { daily: 10, monthly: 200, yearly: 1000 },
    FourWheeler: { daily: 20, monthly: 500, yearly: 3500 },
};
const rates = { INR: 1, USD: 1 / 83.5, YEN: 1.5 };
const priceDiv = document.getElementById('pricing-section');
function renderPricing() {
    priceDiv.innerHTML = '';
    const cur = document.getElementById('currency').value;
    Object.keys(pricing).forEach((type) => {
        const p = pricing[type];
        const vals = {
            daily: Math.round(p.daily * rates[cur]),
            monthly: Math.round(p.monthly * rates[cur]),
            yearly: Math.round(p.yearly * rates[cur]),
        };
        priceDiv.innerHTML += `
            <div class="pricing-card">
                <h5>${type}</h5>
                <div class="pricing-circle">${vals.daily} ${cur}/day</div>
                <p>${vals.monthly} ${cur}/month</p>
                <p>${vals.yearly} ${cur}/year</p>
                <button class="select-plan" data-type="${type}" data-price="${vals.daily}">Select</button>
            </div>`;
    });
    attachPlanListeners();
}
document.getElementById('currency').addEventListener('change', renderPricing);
function attachPlanListeners() {
    Array.from(document.querySelectorAll('.select-plan')).forEach((btn) => btn.addEventListener('click', (e) => {
        const selected = e.target.dataset;
        document.getElementById('final-pass').innerText =
            `Plan: ${selected.type}, Price: ${selected.price} ${document.getElementById('currency').value}`;
    }));
}
document.getElementById('btn-pricing').addEventListener('click', (e) => {
    e.preventDefault();
    renderPricing();
    document.getElementById('btn-pricing').classList.add('disabled');
});
// Initialize
showEmpStep(0);
