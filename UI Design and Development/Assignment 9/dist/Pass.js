export class Pass {
    static pricing = {
        Cycle: { daily: 5, monthly: 100, yearly: 500 },
        Motorcycle: { daily: 10, monthly: 200, yearly: 1000 },
        FourWheeler: { daily: 20, monthly: 500, yearly: 3500 }
    };
    employeeId;
    vehicleType;
    passType;
    price;
    constructor(employeeId, vehicleType, passType) {
        this.employeeId = employeeId;
        this.vehicleType = this.validateVehicleType(vehicleType);
        this.passType = this.validatePassType(passType);
        this.price = Pass.pricing[vehicleType][passType];
    }
    validateVehicleType(type) {
        const validTypes = ["Cycle", "Motorcycle", "FourWheeler"];
        if (!validTypes.includes(type)) {
            throw new Error("Invalid Vehicle Type.");
        }
        return type;
    }
    validatePassType(type) {
        const validPassTypes = ["daily", "monthly", "yearly"];
        if (!validPassTypes.includes(type)) {
            throw new Error("Invalid Pass Type.");
        }
        return type;
    }
    getPassDetails() {
        return `Pass for Employee ID: ${this.employeeId}, Vehicle Type: ${this.vehicleType}, Duration: ${this.passType}, Price: INR ${this.price}`;
    }
}
