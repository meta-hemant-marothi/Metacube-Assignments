export class Vehicle {
    public vehicleName: string;
    public vehicleType: string;
    public vehicleNumber: string;
    public employeeId: string;
    public identification: string;

    constructor(vehicleName: string, vehicleType: string, vehicleNumber: string, employeeId: string, identification: string) {
        this.vehicleName = this.validateVehicleName(vehicleName);
        this.vehicleType = this.validateVehicleType(vehicleType);
        this.vehicleNumber = this.validateVehicleNumber(vehicleNumber);
        this.employeeId = employeeId;
        this.identification = identification;
    }

    private validateVehicleName(name: string): string {
        if (name.length < 2) throw new Error("Invalid Vehicle Name.");
        return name;
    }

    private validateVehicleType(type: string): string {
        const validTypes = ["Cycle", "Motorcycle", "FourWheeler"];
        if (!validTypes.includes(type)) throw new Error("Invalid Vehicle Type.");
        return type;
    }

    private validateVehicleNumber(number: string): string {
        if (!/^[A-Z0-9]+$/.test(number) || number.length < 6) throw new Error("Invalid Vehicle Number.");
        return number;
    }
}