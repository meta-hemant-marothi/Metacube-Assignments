export class Pass {
    private static pricing: { [key: string]: { daily: number; monthly: number; yearly: number } } = {
    Cycle: { daily: 5, monthly: 100, yearly: 500 },
    Motorcycle: { daily: 10, monthly: 200, yearly: 1000 },
    FourWheeler: { daily: 20, monthly: 500, yearly: 3500 }
    };

    public employeeId: string;
    public vehicleType: string;
    public passType: string;
    public price: number;

    constructor(employeeId: string, vehicleType: string, passType: "daily" | "monthly" | "yearly") {
        this.employeeId = employeeId;
        this.vehicleType = this.validateVehicleType(vehicleType);
        this.passType = this.validatePassType(passType);
        this.price = Pass.pricing[vehicleType][passType];
    }

    private validateVehicleType(type: string): string {
        const validTypes = ["Cycle", "Motorcycle", "FourWheeler"];
        if (!validTypes.includes(type)) {
            throw new Error("Invalid Vehicle Type.");
        }
        return type;
    }

    private validatePassType(type: string): string {
        const validPassTypes = ["daily", "monthly", "yearly"];
        if (!validPassTypes.includes(type)) {
            throw new Error("Invalid Pass Type.");
        }
        return type;
    }

    public getPassDetails(): string {
        return `Pass for Employee ID: ${this.employeeId}, Vehicle Type: ${this.vehicleType}, Duration: ${this.passType}, Price: INR ${this.price}`;
    }
}