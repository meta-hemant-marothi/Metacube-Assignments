// Employee Registration
var empForm = document.getElementById('form-employee');
var empSteps = Array.prototype.slice.call(empForm.querySelectorAll('.step-field'));
var empData = {}; 
var empIdx = 0;

function showEmpStep(i) { 
    for (var j = 0; j < empSteps.length; j++) {
        empSteps[j].classList.remove('active');
    }
    empSteps[i].classList.add('active');
}

function validEmp(field) {
    var val = field.value.trim();
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
    if (pass.length < 8) return 'weak';
    if (/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*\W)/.test(pass)) return 'strong';
    return 'normal';
}

empForm.addEventListener('keydown', function(e) {
    if (e.key === 'Enter') {
        e.preventDefault(); 
        var fld = empSteps[empIdx].querySelector('input,select,textarea');
        if (!validEmp(fld)) { 
            fld.style.borderColor = 'red';
            return;
        }
        fld.style.borderColor = '';
        empData[fld.id] = fld.value.trim();
        if (fld.id === 'emp-name') document.getElementById('greet-name').innerText = empData[fld.id];

        if (fld.id === 'emp-pass') { 
            var strengthValue = strength(fld.value);
            fld.style.borderColor = strengthValue === 'strong' ? 'green' : 
                                    strengthValue === 'normal' ? 'orange' : 'red';
        }
        
        empIdx++;
        if (empIdx < empSteps.length) {
            showEmpStep(empIdx);
        } else {
            finishEmployee();
        }
    }
});

function finishEmployee() {
    var id = 'EMP' + Date.now();
    alert('Registered. ID: ' + id);
    empData.id = id;

    document.getElementById('veh-empid').value = empData.id; 

    document.getElementById('btn-vehicle').classList.remove('disabled');
    empForm.parentNode.removeChild(empForm);
}

// Vehicle Registration
var vehForm = document.getElementById('form-vehicle'); 
var vehSteps = Array.prototype.slice.call(vehForm.querySelectorAll('.step-field'));
var vehIdx = 0;

function showVehStep(i) { 
    for (var j = 0; j < vehSteps.length; j++) {
        vehSteps[j].classList.remove('active');
    }
    vehSteps[i].classList.add('active');
}

vehForm.addEventListener('keydown', function(e) {
    if (e.key === 'Enter') {
        e.preventDefault();
        var fld = vehSteps[vehIdx].querySelector('input,select');
        if (!fld.value.trim()) {
            fld.style.borderColor = 'red';
            return;
        }
        fld.style.borderColor = '';

        if (fld.id === 'veh-empid') {
            fld.value = empData.id; 
        }

        vehIdx++;
        if (vehIdx < vehSteps.length) {
            showVehStep(vehIdx);
        } else {
            finishVehicle();
        }
    }
});

document.getElementById('btn-vehicle').addEventListener('click', function(e) {
    e.preventDefault();
    if (!e.target.classList.contains('disabled')) {
        document.getElementById('veh-empid').value = empData.id;
        showVehStep(0);
    }
});

function finishVehicle() { 
    alert('Vehicle Registered'); 
    document.getElementById('btn-pricing').classList.remove('disabled'); 
    vehForm.parentNode.removeChild(vehForm);
}

// Pricing
var pricing = { 
    Cycle: { daily: 5, monthly: 100, yearly: 500 }, 
    Motorcycle: { daily: 10, monthly: 200, yearly: 1000 }, 
    FourWheeler: { daily: 20, monthly: 500, yearly: 3500 } 
};

var rates = { INR: 1, USD: 1 / 83.5, YEN: 1.5 };
var priceDiv = document.getElementById('pricing-section');

function renderPricing() {
    priceDiv.innerHTML = '';
    var cur = document.getElementById('currency').value;

    for (var type in pricing) {
        var p = pricing[type];
        var vals = { 
            daily: Math.round(p.daily * rates[cur]), 
            monthly: Math.round(p.monthly * rates[cur]), 
            yearly: Math.round(p.yearly * rates[cur]) 
        };
        priceDiv.innerHTML += '<div class="pricing-card"><h5>' + type + '</h5>' +
                              '<div class="pricing-circle">' + vals.daily + ' ' + cur + '/day</div>' +
                              '<p>' + vals.monthly + ' ' + cur + '/month</p>' +
                              '<p>' + vals.yearly + ' ' + cur + '/year</p>' +
                              '<button class="select-plan" data-type="' + type + '" data-price="' + vals.daily + '">Select</button>' +
                              '</div>';
    }
    attachPlanListeners();
}

document.getElementById('currency').addEventListener('change', renderPricing);

function attachPlanListeners() {
    var btns = document.querySelectorAll('.select-plan');
    for (var i = 0; i < btns.length; i++) {
        btns[i].onclick = function(e) {
            var selected = e.target.dataset;
            document.getElementById('final-pass').innerText = 'Plan: ' + selected.type + ', Price: ' + selected.price + ' ' + document.getElementById('currency').value;
        };
    }
}

document.getElementById('btn-pricing').addEventListener('click', function(e) {
    e.preventDefault();
    renderPricing();
    document.getElementById('btn-pricing').classList.add('disabled');
});

// Initialize
showEmpStep(0);