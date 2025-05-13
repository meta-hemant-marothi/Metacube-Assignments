export class Vehicle {
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
