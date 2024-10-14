import { UserRole } from "../enums/user-role";

export interface User {
    id?: number;
    firstName?: string;
    lastName?: string;
    packageType?: string;
    pib?: string;
    companyName?: string;
    login?: string;
    token?: string;
    role?: UserRole;
    address?: string;
    city?: string;
    country?: string;
    phoneNumber?: string;
    isLegal?: boolean;
}
